package com.daiqile.test.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.model.Invest;
import com.daiqile.test.view.CircleProgressTwo;
import com.daiqile.test.view.TopBar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/12.
 */

public class InvestDetailActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.circle_progress)
    CircleProgressTwo circleProgress;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
//    @BindView(R.id.tv_safe)
//    TextView tvSafe;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.btn_invest)
    Button btnInvest;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_rule)
    TextView tvRule;

    private Invest invest;
    private Activity mActivity;
    private MyApplication application;
    private Date date;
    private  DecimalFormat df = new DecimalFormat("0.00");
    /*private MyBroad receiver;*/

    @Override
    public void init() {
        /*receiver = new MyBroad();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("calculate");
        InvestDetailActivity.this.registerReceiver(receiver,intentFilter);*/
        mActivity = InvestDetailActivity.this;
        application = (MyApplication) mActivity.getApplication();
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
                calculate();
            }
        });
        invest = (Invest) getIntent().getSerializableExtra("investInfo");
        if (invest.getIsday().equals("1")) {
            tvContent.setText(invest.getApr() + "%\n年利率\n金额：" + Float.valueOf(invest.getAccount()).intValue() + "\n期限：" + invest.getTime_limit_day() + "天");
        } else {
            tvContent.setText(invest.getApr() + "%\n年利率\n金额：" + Float.valueOf(invest.getAccount()).intValue() + "\n期限：" + invest.getTime_limit() + "个月");
        }
        tvLeft.setText("剩余可投：" + df.format(Float.valueOf(invest.getAccount() - invest.getAccount_yes())) + "元");
        tvLimit.setText("投资区间：" + Float.valueOf(invest.getLowest_account()).intValue() + "元起投");
        date = new Date(invest.getAddtime() * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tvTime.setText("发布时间：" + sdf.format(date));
        int style = Integer.valueOf(invest.getStyle());
        if (style == 0) {
            tvType.setText("还款方式：等额本息");
        } else if (style == 2) {
            tvType.setText("还款方式：到期还本付息");
        } else if (style == 3) {
            tvType.setText("还款方式：到期还本按月付息");
        }
        if(invest.getStatus().equals("3")){
            if(invest.getRepayment_account()==invest.getRepayment_yesaccount()){
                btnInvest.setText("已完成");
            }else{
                btnInvest.setText("正在还款");
            }
            btnInvest.setEnabled(false);
        }
        if(invest.getStatus().equals("1")){
            if(invest.getAccount()==invest.getAccount_yes()){
                btnInvest.setText("已满标");
                btnInvest.setEnabled(false);
            }else{
                btnInvest.setText("立即投标");
                btnInvest.setEnabled(true);
            }
        }
        doAnim(invest.getScale());
    }

    private void calculate() {
        Dialog dialog = new Dialog(mActivity);
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_calculator, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(contentView);
        WindowManager manager = getWindowManager();
        Display d = manager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (d.getWidth());
        lp.height = (int) (d.getHeight() * 0.4);
        dialog.show();
        final EditText et_InputMoney = (EditText) contentView.findViewById(R.id.et_InputMoney);
        TextView tv_BidDate = (TextView) contentView.findViewById(R.id.tv_BidDate);
        TextView tv_BidDateUnit = (TextView) contentView.findViewById(R.id.tv_BidDateUnit);
        final TextView tv_result = (TextView) contentView.findViewById(R.id.tv_result);
        final Button btn_cal = (Button) contentView.findViewById(R.id.btn_cal);
        tv_BidDateUnit.setText("个月");
        tv_BidDate.setText(invest.getTime_limit());
        et_InputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    btn_cal.setEnabled(true);
                } else {
                    btn_cal.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("##.00");
                tv_result.setText(df.format(Double.valueOf(invest.getApr()) / 360 / 100 * Integer.valueOf(invest.getTime_limit_day()) * Integer.valueOf(et_InputMoney.getText().toString().trim())) + "");
            }
        });
    }

    private void doAnim(int progress) {
        ValueAnimator animator = ValueAnimator.ofInt(progress);
        animator.setTarget(circleProgress);
        animator.start();
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleProgress.setProgress((Integer) animation.getAnimatedValue());
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invest_detail;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick({R.id.tv_detail, R.id.tv_record, R.id.tv_account, R.id.btn_invest, R.id.tv_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail:
                Intent intent1 = new Intent(mActivity, BorrowDescribeActivity.class);
                intent1.putExtra("borrow_id", invest.getId());
                startActivity(intent1);
                break;
            case R.id.tv_record:
                Intent intent2 = new Intent(mActivity, TenderLogActivity.class);
                intent2.putExtra("borrow_id", invest.getId());
                startActivity(intent2);
                break;
            case R.id.tv_account:
                Intent intent3 = new Intent(mActivity, PaymentDetailActivity.class);
                intent3.putExtra("borrow_id", invest.getId());
                startActivity(intent3);
                break;
            case R.id.btn_invest:
                Intent intent4 = new Intent(mActivity, InvestActivity.class);
                intent4.putExtra("invest", invest);
                startActivity(intent4);
                break;
            case R.id.tv_rule:
                Intent intent5 = new Intent(mActivity, WebViewActivity.class);
                String id = invest.getId();
                if (!TextUtils.isEmpty(id)){
                    intent5.putExtra("id", id);
                    intent5.putExtra("mode", 1);
                    intent5.putExtra("title", "借款协议");
                    startActivity(intent5);
                }
                break;
        }
    }

}

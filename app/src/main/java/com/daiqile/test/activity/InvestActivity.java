package com.daiqile.test.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.model.AccountCenter;
import com.daiqile.test.model.Invest;
import com.daiqile.test.model.YiMaDai;
import com.daiqile.test.utils.CommonUtil;
import com.daiqile.test.utils.ToastUtil;
import com.daiqile.test.view.CircleProgressTwo;
import com.daiqile.test.view.TopBar;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/13.
 */

public class InvestActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.circle_progress)
    CircleProgressTwo circleProgress;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_money_left)
    TextView tvMoneyLeft;
    @BindView(R.id.btn_cash)
    Button btnCash;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_all)
    Button btnAll;
    @BindView(R.id.btn_invest)
    Button btnInvest;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.direction_password)
    LinearLayout directionPassword;

    private Invest invest;
    private Activity mActivity;
    private MyApplication application;
    private double use_money;
    private double total;
    private double use_learn_money;
    private float collection;
    private AccountCenter accountCenter;
    private  DecimalFormat df = new DecimalFormat("0.00");
    private String is_learn;
    private String is_dxb;

    @Override
    public void init() {
        mActivity = InvestActivity.this;
        application = (MyApplication) mActivity.getApplication();
        invest = (Invest) getIntent().getSerializableExtra("invest");
        is_learn = invest.getIs_learn();
        is_dxb = invest.getIs_dxb();
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        if (invest.getIsday().equals("1")) {
            tvContent.setText(invest.getApr() + "%\n年利率\n金额：" + Float.valueOf(invest.getAccount()).intValue() + "\n期限：" + invest.getTime_limit_day() + "天");
        } else {
            tvContent.setText(invest.getApr() + "%\n年利率\n金额：" + Float.valueOf(invest.getAccount()).intValue() + "\n期限：" + invest.getTime_limit() + "个月");
        }
        if (is_dxb.equals("1")){
            directionPassword.setVisibility(View.VISIBLE);
        }else {
            directionPassword.setVisibility(View.GONE);
        }
        doAnim(invest.getScale());
        tvMoneyLeft.setText(df.format(invest.getAccount() - invest.getAccount_yes()) + "元");
        getAccountCenter();
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (is_learn.equals("1")) {
                    if (!s.toString().isEmpty() && count > 0) {
                        tvInfo.setText("可用余额：" + use_money + "元     收益：" + df.format(Double.valueOf(invest.getApr()) / 360 / 100 * Integer.valueOf(invest.getTime_limit_day()) * Float.valueOf(s.toString())) + "元" + "\n体验金：" + df.format(use_learn_money) + "元");
                    } else {
                        tvInfo.setText("可用余额：" + use_money + "元     收益：0.00元" + "\n体验金：0.00元" );
                    }
                } else {
                    if (!s.toString().isEmpty() && count > 0) {
                        tvInfo.setText("可用余额：" + use_money + "元     收益：" + df.format(Double.valueOf(invest.getApr()) / 12 / 100 * Integer.valueOf(invest.getTime_limit()) * Float.valueOf(s.toString())) + "元");
                    } else {
                        tvInfo.setText("可用余额：" + use_money + "元     收益：0.00元");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invest;
    }

    @Override
    public Activity bindActivity() {
        return this;
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

    @OnClick({R.id.btn_cash, R.id.btn_all, R.id.btn_invest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cash:
                startActivity(new Intent(mActivity, RechargeActivity.class));
                break;
            case R.id.btn_all:
                if (use_money < (invest.getAccount() - invest.getAccount_yes())) {
                    etMoney.setText(use_money + "");
                } else {
                    etMoney.setText(invest.getAccount() - invest.getAccount_yes() + "");
                }

                break;
            case R.id.btn_invest:
                if (CommonUtil.isFastDoubleClick()) {
                    return;
                } else {
                    tender();
                }
                break;
        }
    }

    private void isRealYimadai() {
        application.apiService.isRealYimadai(application.mUser.getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<YiMaDai>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(YiMaDai yiMaDai) {
                        String status = yiMaDai.getStatus();
                        if (status.equals("1")) {
                            tender();
                        } else if (status.equals("0")) {
                            ToastUtil.showToast(mActivity, "请先开户！");
                            startActivity(new Intent(mActivity, OpenAccountActivity.class));
                        }
                    }
                });
    }

    private void tender() {
        String money = etMoney.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (money.isEmpty()) {
            ToastUtil.showToast(mActivity, "投标金额不能为空");
            return;
        } else if (is_learn.equals("1")) {
            if (use_learn_money < Double.valueOf(money)
                    || use_learn_money <= 0) {
                ToastUtil.showToast(mActivity, "您的体验金余额不足");
                return;
            }
        } else {
            if (Double.valueOf(accountCenter.getUse_money()) < Double.valueOf(money)
                    || Double.valueOf(accountCenter.getUse_money()) <= 0) {
                ToastUtil.showToast(mActivity, "您的余额不足，请充值");
                return;
            }
        }
        if (is_dxb.equals("1")){
            if (password.isEmpty()) {
                ToastUtil.showToast(mActivity, "支付密码不能为空");
                return;
            }
        }
        Intent intent = new Intent(mActivity, InvestResultActivity.class);
        intent.putExtra("id", invest.getId());
        intent.putExtra("money", money);
        intent.putExtra("paypassword", password);
        startActivity(intent);
        finish();
//        Map<String, String> map = new HashMap<>();
//        map.put("id", invest.getId());
//        map.put("money", money);
//        map.put("paypassword", password);
//        map.put("user_id", String.valueOf(application.mUser.getId()));
//        application.apiService.getTenderResult(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<TenderResult>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(TenderResult result) {
//                        if (result.getTender_status().equals("1")) {
//                            ToastUtil.showToast(mActivity, "投标成功");
//                            finish();
//                        } else {
//                            ToastUtil.showToast(mActivity, result.getMsg());
//                        }
//                    }
//                });
    }

    private void getAccountCenter() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(application.mUser.getId()));
        map.put("dcode", Constants.DCODE);
        application.apiService.getAccountCenter(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccountCenter>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccountCenter center) {
                        accountCenter = center;
                        use_learn_money = center.getUse_learn_money();
                        use_money = center.getUse_money();
                        total = center.getTotal();
                        collection = center.getCollection();
                        if (is_learn.equals("1")) {
                            tvInfo.setText("可用余额：" + df.format(use_money) + "元     收益：0.00元"
                                    + "\n体验金：" + use_learn_money + "元");
                        } else {
                            tvInfo.setText("可用余额：" + df.format(use_money) + "元     收益：0.00元");
                        }
                    }
                });
    }
}
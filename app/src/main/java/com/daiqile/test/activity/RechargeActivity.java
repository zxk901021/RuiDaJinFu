package com.daiqile.test.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.http.HttpPostUtil;
import com.daiqile.test.model.BindCard;
import com.daiqile.test.model.RechargeModel;
import com.daiqile.test.model.RechargeResult;
import com.daiqile.test.utils.CommonUtil;
import com.daiqile.test.utils.SoftInputUtil;
import com.daiqile.test.utils.ToastUtil;
import com.daiqile.test.view.TopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.daiqile.test.R.id.et_verify_code;

/**
 * Created by Administrator on 2017/1/19.
 */

public class RechargeActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(et_verify_code)
    EditText edVerifycode;
    @BindView(R.id.btn_recharge)
    Button btnRecharge;
    @BindView(R.id.recharge_choose_bank)
    TextView chooseBank;
    @BindView(R.id.send_verify_code)
    Button sendVerify;

    private Activity mActivity;
    private MyApplication application;

    OptionsPickerView bankOptions;

    private ArrayList<String> bankNameList = new ArrayList<>();
    private ArrayList<String> bankIdList = new ArrayList<>();
    private List<BindCard.BankCardBean> bankCardBeanList = new ArrayList<>();

    private String bankId = "";
    private String bankNumber = "";
    private String result = "";
    private String bizOrderNo;
    private boolean isVerify = false;

    @Override
    public void init() {
        mActivity = RechargeActivity.this;
        application = (MyApplication) getApplication();
        bankOptions = new OptionsPickerView(mActivity);
        edVerifycode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edVerifycode.getText().toString())){
                    if (edVerifycode.getText().toString().trim().length() > 0){
                        btnRecharge.setEnabled(true);
                        btnRecharge.setBackgroundResource(R.drawable.background_button);
                        btnRecharge.setTextColor(Color.parseColor("#dd000000"));
                    }else {
                        btnRecharge.setBackgroundResource(R.drawable.background_button_grey);
                        btnRecharge.setTextColor(Color.WHITE);
                        btnRecharge.setEnabled(false);
                    }
                }else {
                    btnRecharge.setBackgroundResource(R.drawable.background_button_grey);
                    btnRecharge.setTextColor(Color.WHITE);
                    btnRecharge.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getBankList();

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    try {
                        JSONObject object = new JSONObject(result);
                        String code = object.getString("status");
                        if (code.equals("1")){
                            bankCardBeanList.clear();
                            bankNameList.clear();
                            bankIdList.clear();
                            JSONArray arr = object.getJSONArray("list");
                            if (arr != null && arr.length() > 0){
                                for (int i = 0; i < arr.length(); i ++){
                                    JSONObject bank = arr.getJSONObject(i);
                                    BindCard.BankCardBean bean = new BindCard.BankCardBean();
                                    bean.setId(bank.getString("id"));
                                    bean.setBank(bank.getString("bank"));
                                    bean.setBranch(bank.getString("branch"));
                                    bean.setCard_number(bank.getString("card_number"));
                                    bankCardBeanList.add(bean);
                                    bankNameList.add(bean.getBank());
                                    bankIdList.add(bean.getId());
                                }
                            }
                            bankOptions = new OptionsPickerView(mActivity);
                            bankOptions.setPicker(bankNameList);
                            bankOptions.setTitle("选择银行");
                            bankOptions.setCyclic(false);
                            bankOptions.setSelectOptions(0);
                            bankOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3) {
                                    bankId = bankIdList.get(options1);
                                    bankNumber = bankCardBeanList.get(options1).getCard_number();
                                    if (!TextUtils.isEmpty(bankNameList.get(options1))){
                                        chooseBank.setText(bankNameList.get(options1));
                                    }else {
                                        chooseBank.setText("");
                                    }

                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };


    private void getBankList() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = HttpPostUtil.requesPost(application.mUser.getId() + "");
                    handler.sendEmptyMessage(1);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        th.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick({R.id.btn_recharge, R.id.recharge_choose_bank, R.id.send_verify_code})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_recharge:
                if (CommonUtil.isFastDoubleClick()) {
                } else {
                    rechargeSecond();
                }
                break;
            case R.id.recharge_choose_bank:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, chooseBank);
                }
                bankOptions.show();
                break;
            case R.id.send_verify_code:
                recharge();

                break;
        }

    }

    private void rechargeSecond(){
        String verifyCode = edVerifycode.getText().toString().trim();
        if (TextUtils.isEmpty(verifyCode)){
            ToastUtil.showToast(mActivity, "请输入验证码");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("dcode", Constants.DCODE);
        params.put("user_id", String.valueOf(application.mUser.getId()));
        params.put("bizOrderNo", bizOrderNo);
        params.put("verificationCode", verifyCode);
        params.put("amount", money);
        application.apiService.getRechargeResult(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RechargeResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RechargeResult rechargeResult) {
                        if (rechargeResult.getStatus().equals("1")){
                            ToastUtil.showToast(mActivity, "操作成功，请稍后查看结果");
                            finish();
                        }else {
                            ToastUtil.showToast(mActivity, "充值失败");
                        }
                    }
                });
    }

    private String money;

    private void recharge() {
        money = etMoney.getText().toString().trim();
        if (money.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入金额");
            return;
        }
        if (TextUtils.isEmpty(bankId)){
            ToastUtil.showToast(mActivity, "请选择银行卡");
            return;
        }
        new CountDownTimer(60000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                sendVerify.setEnabled(false);
                sendVerify.setText(String.format("%ds后重新发送",millisUntilFinished/1000));
                sendVerify.setBackgroundResource(R.drawable.background_button_grey);
                sendVerify.setTextColor(Color.WHITE);
            }

            @Override
            public void onFinish() {
                sendVerify.setEnabled(true);
                sendVerify.setText("发送验证码");
                sendVerify.setBackgroundResource(R.drawable.background_button);
                sendVerify.setTextColor(Color.parseColor("#dd000000"));
            }
        }.start();
        Map<String, String> params = new HashMap<>();
        params.put("dcode", Constants.DCODE);
        params.put("user_id", String.valueOf(application.mUser.getId()));
        params.put("amount", money);
        params.put("bankCardNo", bankNumber);
        application.apiService.getOrderNo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RechargeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RechargeModel rechargeModel) {
                        if (rechargeModel.getStatus().equals("1")){
                            isVerify = true;
                            bizOrderNo = rechargeModel.getBizOrderNo();
                            ToastUtil.showToast(mActivity, "验证码发送成功");
                        }else if (rechargeModel.getStatus().equals("2")){
                            ToastUtil.showToast(mActivity, "请输入正确金额");
                        }
                    }
                });
    }
}

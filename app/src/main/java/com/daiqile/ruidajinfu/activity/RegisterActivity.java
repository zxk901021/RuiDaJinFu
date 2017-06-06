package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.model.Register;
import com.daiqile.ruidajinfu.utils.PhoneFormatCheckUtils;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TimeButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/2.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    @BindView(R.id.et_validate_number)
    EditText etValidateNumber;
    @BindView(R.id.tb_get_validate_number)
    TimeButton tbGetValidateNumber;

    private Activity mActivity;
    private MyApplication application = null;

    @Override
    public void init() {
        mActivity = RegisterActivity.this;
        application = (MyApplication) getApplication();
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 11 && PhoneFormatCheckUtils.isPhoneLegal(s.toString())) {
                    tbGetValidateNumber.setVisibility(View.VISIBLE);
                } else {
                    tbGetValidateNumber.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    private void register() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordConfirm = etPasswordAgain.getText().toString().trim();
        String phone = etPhoneNumber.getText().toString().trim();
        String captcha = etValidateNumber.getText().toString().trim();

        if (username.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入用户名！");
            return;
        }

        if (password.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入密码！");
            return;
        }

        if (passwordConfirm.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入新密码！");
            return;
        }

        if (!password.equals(passwordConfirm)) {
            ToastUtil.showToast(mActivity, "两次密码输入不一致！");
            return;
        }

        if (phone.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入手机号！");
            return;
        }

        if (captcha.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入验证码！");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("phone", phone);
        map.put("phone_valicode", captcha);
        map.put("dcode", Constants.DCODE);
        showProgress();
        application.apiService.register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Register>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Register register) {
                        dismissProgress();
                        String status = register.getStatus();
                        String user_id = register.getUser_id();
                        if (status == null) {
                            if (Integer.valueOf(register.getUser_id()) > 0) {
                                ToastUtil.showToast(mActivity, "注册成功,请先修改支付密码！");
                                finish();
                            } else {
                                ToastUtil.showToast(mActivity, "注册失败！");
                            }
                        } else if (status.equals("0")){
                            ToastUtil.showToast(mActivity, "手机号已经注册！");
                        } else if (status.equals("2")) {
                            ToastUtil.showToast(mActivity, "验证码错误！");
                        } else if (status.equals("3")) {
                            ToastUtil.showToast(mActivity, "用户名已经存在！");
                        }
                    }
                });
    }

    @OnClick({R.id.btn_register, R.id.tv_login, R.id.tb_get_validate_number})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_login:
                finish();
                break;
            case R.id.tb_get_validate_number:
                getCaptcha();
                break;
        }
    }

    private void getCaptcha() {
        if (etPhoneNumber.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(mActivity, "请输入手机号！");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("nub", etPhoneNumber.getText().toString().trim());
        map.put("phonetype", "1");
        application.apiService.getCaptcha(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            JSONObject object = new JSONObject(responseBody.string());
                            String result = object.getString("status");
                            if (result.equals("1")) {
                                ToastUtil.showToast(mActivity, "验证码发送成功，请查收！");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

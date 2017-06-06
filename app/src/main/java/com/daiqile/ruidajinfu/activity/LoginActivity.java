package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.model.User;
import com.daiqile.ruidajinfu.utils.SharedUtil;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/29.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    private Activity mActivity;
    private MyApplication application = null;

    @Override
    public void init() {
        mActivity = LoginActivity.this;
        application = (MyApplication) getApplication();
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                SharedUtil.putBoolean(mActivity, Constants.USERREMEMBERCOOKIE, isChecked);
                application.mRememberPassword = isChecked;
            }
        });
        boolean isRemembered = SharedUtil.getBoolean(mActivity, Constants.USERREMEMBERCOOKIE, false);
        String username = SharedUtil.getString(mActivity, Constants.USERNAMECOOKIE);
        String password = SharedUtil.getString(mActivity, Constants.USERPASSWORDCOOKIE);
        if (isRemembered) {
            etUsername.setText(username);
            etPassword.setText(password);
            checkbox.setChecked(true);
        } else {
            etUsername.setText("");
            etUsername.setText("");
            checkbox.setChecked(false);
        }
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(mActivity, FindPwdByPhoneActivity.class));
                break;
        }
    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (username.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入用户名");
            return;
        }
        if (password.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入登录密码");
            return;
        }
        showProgress();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("dcode", Constants.DCODE);
        application.apiService.login(username, password, Constants.DCODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBody body) {
                        try {
                            String result = URLDecoder.decode(body.string(), "GB2312");
                            Gson gson = new Gson();
                            User user = gson.fromJson(result, User.class);
                            if (user.getStatus().equals("1")) {
                                ToastUtil.showToast(mActivity, "登录成功！");
                                application.updateLoginParams(user.getUser());
                                startActivity(new Intent(mActivity, MainActivity.class));
                                finish();
                            } else {
                                ToastUtil.showToast(mActivity, "用户名或者密码错误！");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }



}

package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TimeButton;
import com.daiqile.ruidajinfu.view.TopBar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/21.
 */
public class FindPwdByPhoneActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_validate_number)
    EditText etValidateNumber;
    @BindView(R.id.tb_get_validate_number)
    TimeButton tbGetValidateNumber;
    @BindView(R.id.btn_find)
    Button btnFind;

    private Activity mActivity;
    private MyApplication application;

    @Override
    public void init() {
        mActivity = FindPwdByPhoneActivity.this;
        application = (MyApplication) mActivity.getApplication();
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_pwd_by_phone;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick({R.id.tb_get_validate_number, R.id.btn_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_get_validate_number:
                getCode();
                break;
            case R.id.btn_find:
                findPwd();
                break;
        }
    }

    private void findPwd() {
        String username = etUsername.getText().toString().trim();
        String phone = etPassword.getText().toString().trim();
        String code = etValidateNumber.getText().toString().trim();
        if (username.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入用户名");
            return;
        }
        if (phone.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入手机号");
            return;
        }
        if (code.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入验证码");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("num", phone);
        map.put("phone_valicode", code);
        map.put("dcode", Constants.DCODE);
        Log.e("tag", "username=" + username + " num=" + phone + " phone_valicode=" + code);
        application.apiService.findPwd(map)
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
                            String result = responseBody.string();
                            Log.e("tag", result);
                            switch (result) {
                                case "\r\n1":
                                    ToastUtil.showToast(mActivity, "成功 用户登录密码重置为手机号");
                                    finish();
                                    break;
                                case "\r\n-1":
                                    ToastUtil.showToast(mActivity, "用户密码重置失败");
                                    break;
                                case "\r\n-2":
                                    ToastUtil.showToast(mActivity, "验证码输入错误");
                                    break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getCode() {
        String username = etUsername.getText().toString().trim();
        String phone = etPassword.getText().toString().trim();
        if (username.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入用户名");
            return;
        }
        if (phone.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入手机号");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("phone", phone);
        map.put("dcode", Constants.DCODE);
        application.apiService.getCode(map)
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
                            String result = responseBody.string();
                            switch (result) {
                                case "\r\n\"1\"":
                                    ToastUtil.showToast(mActivity, "成功发送");
                                    break;
                                case "\r\n\"-12\"":
                                    ToastUtil.showToast(mActivity, "用户名为空");
                                    break;
                                case "\r\n\"-14\"":
                                    ToastUtil.showToast(mActivity, "找不到该用户");
                                    break;
                                case "\r\n\"-17\"":
                                    ToastUtil.showToast(mActivity, "邮箱，用户名对应不正确");
                                    break;
                                case "\r\n\"-18\"":
                                    ToastUtil.showToast(mActivity, "手机，用户名对应不正确");
                                    break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

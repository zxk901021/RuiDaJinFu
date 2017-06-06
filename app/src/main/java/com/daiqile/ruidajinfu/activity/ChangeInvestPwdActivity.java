package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.model.PayPassword;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/21.
 */
public class ChangeInvestPwdActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_again_password)
    EditText etAgainPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private Activity mActivity;
    private MyApplication application;

    @Override
    public void init() {
        mActivity = ChangeInvestPwdActivity.this;
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
        return R.layout.activity_change_invest_pwd;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        changeInvestPassword();
    }

    private void changeInvestPassword() {
        String newPassword = etNewPassword.getText().toString().trim();
        String oldPassword = etOldPassword.getText().toString().trim();
        String passwordAgain = etAgainPassword.getText().toString().trim();

        if (newPassword.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入登录密码");
            return;
        }
        if (oldPassword.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入原交易密码");
            return;
        }
        if (newPassword.length() < 6) {
            ToastUtil.showToast(mActivity, "新交易密码不能少于6位");
            return;
        }
        if (oldPassword.length() < 6) {
            ToastUtil.showToast(mActivity, "旧交易密码不能少于6位");
            return;
        }
        if (passwordAgain.isEmpty()) {
            ToastUtil.showToast(mActivity, "请再次输入交易密码");
            return;
        }
        if (!passwordAgain.equals(newPassword)) {
            ToastUtil.showToast(mActivity, "两次密码输入不一致");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(application.mUser.getId()));
        map.put("oldpaypassword", oldPassword);
        map.put("newpaypassword", newPassword);
        map.put("dcode", Constants.DCODE);
        application.apiService.getPayPasswordResult(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PayPassword>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PayPassword password) {
                        if (password.getSet_status().equals("1")) {
                            ToastUtil.showToast(mActivity, "修改成功");
                            finish();
                        } else {
                            ToastUtil.showToast(mActivity, "修改失败");
                            etNewPassword.setText("");
                            etOldPassword.setText("");
                            etAgainPassword.setText("");
                        }
                    }
                });
    }
}

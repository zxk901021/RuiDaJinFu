package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.model.AccountCenter;
import com.daiqile.ruidajinfu.model.RealStatus;
import com.daiqile.ruidajinfu.model.YiMaDai;
import com.daiqile.ruidajinfu.view.TopBar;

import org.json.JSONException;
import org.json.JSONObject;

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
public class SafeCenterActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.ll_change_login_pwd)
    LinearLayout llChangeLoginPwd;
    @BindView(R.id.ll_change_pay_pwd)
    LinearLayout llChangePayPwd;
    @BindView(R.id.ll_set_pwd)
    LinearLayout llSetPwd;
    @BindView(R.id.ll_phone_certify)
    LinearLayout llPhoneCertify;
    @BindView(R.id.ll_username_certify)
    LinearLayout llUsernameCertify;
    @BindView(R.id.ll_open_account)
    LinearLayout llOpenAccount;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.tv_yimadai)
    TextView tvYimadai;

    private Activity mActivity;
    private MyApplication application;

    private BroadcastReceiver realNameReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            isReal();
        }
    };

    @Override
    public void init() {
        mActivity = SafeCenterActivity.this;
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

        isReal();
        getAccountCenter();
        isRealYimadai();
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
                            tvYimadai.setText("已开户");
                            llOpenAccount.setEnabled(false);
                        } else if (status.equals("0")) {
                            tvYimadai.setText("未开户");
                            llOpenAccount.setEnabled(true);
                        }
                    }
                });
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
                        if (center.getPhone() == null) {

                        } else {
                            tvPhoneNumber.setText(center.getPhone().substring(0, 3) + "****" + center.getPhone().substring(7, 11));
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.action.realname");
        registerReceiver(realNameReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(realNameReceiver);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_safe_center;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick({R.id.ll_change_login_pwd, R.id.ll_change_pay_pwd, R.id.ll_set_pwd, R.id.ll_phone_certify, R.id.ll_username_certify, R.id.btn_exit, R.id.ll_open_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_change_login_pwd:
                startActivity(new Intent(mActivity, ChangePwdActivity.class));
                break;
            case R.id.ll_change_pay_pwd:
                startActivity(new Intent(mActivity, ChangeInvestPwdActivity.class));
                break;
            case R.id.ll_set_pwd:
                break;
            case R.id.ll_phone_certify:
                break;
            case R.id.ll_username_certify:
                startActivity(new Intent(mActivity, RealNameActivity.class));
                break;
            case R.id.btn_exit:
                application.clearLoginParams();
                Intent intent = new Intent("android.action.user.exit");
                sendBroadcast(intent);
                startActivity(new Intent(mActivity, LoginActivity.class));
                finish();
                break;
            case R.id.ll_open_account:
                startActivity(new Intent(mActivity, OpenAccountActivity.class));
                break;
        }
    }


    private void isReal() {
        application.apiService.isReal(application.mUser.getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RealStatus>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RealStatus realStatus) {
                        if (realStatus.getReal_status().equals("1")) {
                            tvRealName.setText("已实名认证");
                        } else {
                            tvRealName.setText("未认证");
                        }
                    }
                });
    }
}

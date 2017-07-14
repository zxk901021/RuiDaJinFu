package com.daiqile.test.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.daiqile.test.utils.NetworkUtil;
import com.daiqile.test.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by orgwcl on 2016/9/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(bindActivity());
        init();
        if (!isNetworkAvailable()) {
            ToastUtil.showToast(getApplicationContext(), "当前无可用网络！");
        }
    }

    public abstract void init();
    public abstract int getLayoutId();
    public abstract Activity bindActivity();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showProgress() {
        dialog = ProgressDialog.show(bindActivity(), null, "请稍候......");
        dialog.setCancelable(true);
    }

    public void dismissProgress() {
        dialog.dismiss();
    }

    public boolean isNetworkAvailable() {
        return NetworkUtil.getNetworkState(getApplicationContext());
    }
}

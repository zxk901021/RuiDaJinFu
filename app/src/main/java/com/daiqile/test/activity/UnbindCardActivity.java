package com.daiqile.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.view.TopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/17.
 */

public class UnbindCardActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.btn_bind)
    Button btnBind;

    @Override
    public void init() {
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
        return R.layout.activity_unbind_card;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick(R.id.btn_bind)
    public void onClick() {
        startActivity(new Intent(UnbindCardActivity.this, BindCardActivity.class));
        finish();
    }
}

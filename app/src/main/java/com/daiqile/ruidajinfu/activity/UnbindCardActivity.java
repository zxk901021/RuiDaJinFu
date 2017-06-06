package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
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

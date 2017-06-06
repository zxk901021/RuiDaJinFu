package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.os.Bundle;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/28.
 */

public class AboutActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;

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
        return R.layout.activity_about;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}

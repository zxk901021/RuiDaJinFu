package com.daiqile.test.activity;

import android.app.Activity;

import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.view.TopBar;

import butterknife.BindView;

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

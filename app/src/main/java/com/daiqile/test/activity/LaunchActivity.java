package com.daiqile.test.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/21.
 */

public class LaunchActivity extends BaseActivity {
    @BindView(R.id.img_launch)
    ImageView imgLaunch;

    @Override
    public void init() {
        animate(LaunchActivity.this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    private void animate(final Context context) {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.start();
        imgLaunch.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

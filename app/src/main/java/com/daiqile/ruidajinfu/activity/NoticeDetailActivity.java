package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/20.
 */
public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private Intent intent;

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
        intent = getIntent();
        topbar.setTitle(intent.getStringExtra("title"));
        tvContent.setText(Html.fromHtml(intent.getStringExtra("content")));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}

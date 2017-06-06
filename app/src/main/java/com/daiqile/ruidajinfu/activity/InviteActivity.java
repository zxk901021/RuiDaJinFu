package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/28.
 */
public class InviteActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_link)
    TextView tvLink;

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
        return R.layout.activity_invite;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick(R.id.tv_link)
    public void onClick() {
        copy("www.zjrdjr.com/index.php?user&q=action/reg&u=bHp4Mjh1eWUxNQ==", InviteActivity.this);
    }

    private void copy(String content, Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.setText(content);
        ToastUtil.showToast(InviteActivity.this, "已复制");
    }
}

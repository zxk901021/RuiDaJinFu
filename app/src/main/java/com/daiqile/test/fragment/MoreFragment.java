package com.daiqile.test.fragment;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;

import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.activity.AboutActivity;
import com.daiqile.test.activity.NoticeActivity;
import com.daiqile.test.base.BaseFragment;
import com.daiqile.test.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MoreFragment extends BaseFragment {
    @BindView(R.id.ll_intro)
    LinearLayout llIntro;
    @BindView(R.id.ll_news)
    LinearLayout llNews;
    @BindView(R.id.ll_notices)
    LinearLayout llNotices;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.ll_wechat)
    LinearLayout llWechat;
    @BindView(R.id.ll_question)
    LinearLayout llQuestion;

    private Activity mActivity;
    private MyApplication application;

    @Override
    public void init() {
        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_more;
    }

    @Override
    public Object bindFragment() {
        return this;
    }

    @OnClick({R.id.ll_intro, R.id.ll_news, R.id.ll_notices, R.id.ll_phone, R.id.ll_wechat,R.id.ll_question})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_intro:
                startActivity(new Intent(mActivity, AboutActivity.class));
                break;
            case R.id.ll_news:
                Intent intent2 = new Intent(mActivity, NoticeActivity.class);
                intent2.putExtra("site_id", "144");
                startActivity(intent2);
                break;
            case R.id.ll_notices:
                Intent intent3 = new Intent(mActivity, NoticeActivity.class);
                intent3.putExtra("site_id", "83");
                startActivity(intent3);
                break;
            case R.id.ll_question:
                Intent intent4 = new Intent(mActivity,NoticeActivity.class);
                intent4.putExtra("site_id","253");
                startActivity(intent4);
                break;
            case R.id.ll_phone:
                startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + "400-905-8002")));
                break;
            case R.id.ll_wechat:
                copy("ruidajinfu", mActivity);
                break;
        }
    }

    private void copy(String content, Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText(content);
        ToastUtil.showToast(mActivity, "已复制");
    }
}

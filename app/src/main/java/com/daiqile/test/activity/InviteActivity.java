package com.daiqile.test.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.model.Invite;
import com.daiqile.test.utils.ToastUtil;
import com.daiqile.test.view.TopBar;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/28.
 */
public class InviteActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.invite_qrcode)
    ImageView qrcode;
    @BindView(R.id.invite_url)
    TextView inviteUrl;

    private MyApplication application;

    @Override
    public void init() {
        application = (MyApplication) getApplication();
        getInviteCode();
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

    private void getInviteCode(){
        Map<String, String> param = new HashMap<>();
        param.put("user_id", String.valueOf(application.mSharedPreferences.getInt(Constants.USERID, 0)));
        application.apiService.getInviteUrl(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Invite>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Invite invite) {
                        String url = invite.getUrl();
                        Bitmap bitmap = CodeUtils.createImage(url, 400, 400, null);
                        qrcode.setImageBitmap(bitmap);
                        tvLink.setText(url);
                    }
                });
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

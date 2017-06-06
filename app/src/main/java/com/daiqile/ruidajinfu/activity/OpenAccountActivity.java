package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.utils.WebViewManager;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/23.
 */

public class OpenAccountActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Activity mActivity;
    private MyApplication application;
    private String url = Constants.BASE_URL + "openAccount.php";

    @Override
    public void init() {
        mActivity = OpenAccountActivity.this;
        application = (MyApplication) getApplication();

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });


        WebViewManager manager = new WebViewManager(webView);
        manager.enableJavaScript();
        manager.enableJavaScriptOpenWindowsAutomatically();
        manager.enableAdaptive();
        manager.goBack();
        webView.postUrl(url, ("user_id=" + application.mUser.getId()).getBytes());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (progressBar.getVisibility() == View.INVISIBLE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_open_account;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}

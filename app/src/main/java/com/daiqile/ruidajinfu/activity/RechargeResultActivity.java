package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.utils.WebViewManager;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/16.
 */

public class RechargeResultActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private WebViewManager manager;

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
        topbar.setTitle("充值");

        String url = getIntent().getStringExtra("url");

        manager = new WebViewManager(webView);
        manager.enableJavaScript();
        manager.enableAdaptive();
        manager.enableJavaScriptOpenWindowsAutomatically();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
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
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge_result;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }
}

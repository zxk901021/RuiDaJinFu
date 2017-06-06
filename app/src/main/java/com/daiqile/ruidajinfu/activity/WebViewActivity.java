package com.daiqile.ruidajinfu.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.utils.WebViewManager;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/21.
 */

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.web_view)
    WebView webView;

    private String title;
    private String content;
    private String css;

    private int mode;
    private String id;
    private WebSettings webSettings;

    private WebViewClient mViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    };


    @SuppressLint("SetJavaScriptEnabled")
    private void webConfigSetting() {
        webView.setWebViewClient(mViewClient);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setTextZoom(100);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
    }

    @Override
    public void init() {
        mode = getIntent().getIntExtra("mode", -1);
        if (mode == 1) {
            webConfigSetting();
            id = getIntent().getStringExtra("id");
            webView.setWebViewClient(mViewClient);
            webView.loadUrl("http://www.zjrdjr.com/invest/a" + id + ".html");
            title = getIntent().getStringExtra("title");
            topbar.setTitle(title);
        } else {
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");
            topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
                @Override
                public void leftClick() {
                    finish();
                }

                @Override
                public void rightClick() {

                }
            });
            topbar.setTitle(title);
            WebViewManager manager = new WebViewManager(webView);
            manager.enableAdaptive();
            css = "<style type=\"text/css\"> " +
                    "img {" +
                    "width:100%;" +
                    "height:auto;" +
                    "}" +
                    "body {" +
                    "margin-right:15px;" +
                    "margin-left:15px;" +
                    "margin-top:15px;" +
                    "font-size:45px;" +
                    "}" +
                    "p {" +
                    "size:16px;" +
                    "}" +
                    "</style>";
            webView.loadDataWithBaseURL(null, "<html><header>" + css + "</header><body>" + content + "</body></html>", "text/html", "UTF-8", null);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}

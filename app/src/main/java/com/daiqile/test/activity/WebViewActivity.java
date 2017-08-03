package com.daiqile.test.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daiqile.test.AppConfig;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.utils.WebViewManager;
import com.daiqile.test.view.TopBar;

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
    private String url;

    private int mode;
    private String id;
    private WebSettings webSettings;
    private MyApplication application = null;

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
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        application = (MyApplication) getApplication();
        topbar.setTitle(title);
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                if (mode == 10){
                    Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                    intent.putExtra("index", 2);
                    startActivity(intent);
                }
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        if (mode == 1) {
            webConfigSetting();
            id = getIntent().getStringExtra("id");
            webView.setWebViewClient(mViewClient);
            webView.loadUrl("http://www.zjrdjr.com/invest/a" + id + ".html");
        } else if (mode == 3){
            webConfigSetting();
            id = String.valueOf(application.getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE).getInt("id", -1));
            webView.setWebViewClient(mViewClient);
            webView.loadUrl("http://test2.zjrdjr.com/port/userSetPayPwd.php?dcode=7d5372bbcd6cc79f1bd71211f092622e&user_id=" + id);
        } else if (mode == 4){
            webConfigSetting();
            id = String.valueOf(application.getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE).getInt("id", -1));
            webView.setWebViewClient(mViewClient);
            webView.loadUrl("http://test2.zjrdjr.com/port/userResetPayPwd.php?dcode=7d5372bbcd6cc79f1bd71211f092622e&user_id=" + id);
        } else if (mode == 10){
            webConfigSetting();
            webView.setWebViewClient(mViewClient);
            webView.loadUrl(url);
        } else if (mode == 100){
            webConfigSetting();
            webView.setWebViewClient(mViewClient);
            webView.loadUrl(url);
        }else {
            content = getIntent().getStringExtra("content");
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

package com.daiqile.test.activity;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.utils.WebViewManager;
import com.daiqile.test.view.TopBar;

import butterknife.BindView;

public class WithDrawResultActivity extends BaseActivity {


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
        topbar.setTitle("取现");

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
        return R.layout.activity_with_draw_result;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }
}

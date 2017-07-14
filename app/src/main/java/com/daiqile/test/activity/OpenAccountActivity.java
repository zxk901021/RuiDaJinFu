package com.daiqile.test.activity;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.utils.WebViewManager;
import com.daiqile.test.view.TopBar;

import butterknife.BindView;

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

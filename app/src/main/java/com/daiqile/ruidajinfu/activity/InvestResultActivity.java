package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
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
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/22.
 */

public class InvestResultActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Activity mActivity;
    private MyApplication application;
    private String url = Constants.BASE_URL + "userTender.php";
    private String id;
    private String money;
    private String paypassword;

    @Override
    public void init() {
        mActivity = InvestResultActivity.this;
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

        id = getIntent().getStringExtra("id");
        money = getIntent().getStringExtra("money");
        paypassword = getIntent().getStringExtra("paypassword");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.postUrl(url, ("money=" + money + "&id=" + id + "&paypassword=" + paypassword + "&user_id=" + application.mUser.getId()).getBytes());
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
        return R.layout.activity_invest_result;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }
}

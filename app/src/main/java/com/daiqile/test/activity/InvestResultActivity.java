package com.daiqile.test.activity;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.view.TopBar;

import butterknife.BindView;

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
//        webView.postUrl(url, ("money=" + money + "&id=" + id + "&paypassword=" + paypassword + "&user_id=" + application.mUser.getId()).getBytes());
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
        String urls = url + "?dcode=7d5372bbcd6cc79f1bd71211f092622e&user_id=" + application.mUser.getId()
                + "&id=" + id + "&money=" + money + "&dxbPwd=" + paypassword;
        webView.loadUrl(urls);
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

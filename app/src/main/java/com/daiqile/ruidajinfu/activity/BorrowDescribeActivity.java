package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.utils.WebViewManager;
import com.daiqile.ruidajinfu.view.TopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/12.
 */

public class BorrowDescribeActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.topbar)
    TopBar topBar;

    private Activity mActivity;
    private MyApplication application;

    @Override
    public void init() {
        mActivity = BorrowDescribeActivity.this;
        application = (MyApplication) mActivity.getApplication();
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        WebViewManager manager = new WebViewManager(webView);
        manager.enableAdaptive().enableZoom();
        getContent(getIntent().getStringExtra("borrow_id"));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_borrow_describe;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    private void getContent(final String borrow_id) {
        Log.e("tag", borrow_id);
        Map<String, String> map = new HashMap<>();
        map.put("borrow_id", borrow_id);
        application.apiService.getContent(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        String css = "<style type=\"text/css\"> " +
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
                        try {
                            JSONArray array = new JSONArray(responseBody.string());
                            JSONObject object = array.getJSONObject(0);
                            Log.e("tag", object.getString("content"));
//                            Log.e("tag！！", object.getString("content_img"));
                            webView.loadDataWithBaseURL(null, "<html><header>" + css + "</header><body>" + utilhtml(object.getString("content")) + "</body></html>", "text/html", "UTF-8", null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    private String utilhtml(String context){
        String c=context.replaceAll("/plugins", "http://www.zjrdjr.com/plugins");
        return c;
    }
}

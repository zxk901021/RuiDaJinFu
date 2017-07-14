package com.daiqile.test.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.utils.DownPicUtil;
import com.daiqile.test.utils.WebViewManager;
import com.daiqile.test.view.TopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
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

public class BorrowDescribeActivity extends BaseActivity implements View.OnLongClickListener{
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
        webView.setOnLongClickListener(this);

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

    @Override
    public boolean onLongClick(View v) {
        final WebView.HitTestResult hitTestResult = webView.getHitTestResult();
        if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE){
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setTitle("提示");
            builder.setMessage("保存图片到本地");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String url = hitTestResult.getExtra();
                    // 下载图片到本地
                    DownPicUtil.downPic(url, new DownPicUtil.DownFinishListener(){

                        @Override
                        public void getDownPath(String s) {
                            Toast.makeText(mActivity,"下载完成",Toast.LENGTH_LONG).show();
                            Message msg = Message.obtain();
                            msg.obj=s;
                            handler.sendMessage(msg);
                        }
                    });

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                // 自动dismiss
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String picFile = (String) msg.obj;
            String[] split = picFile.split("/");
            String fileName = split[split.length-1];
            try {
                MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), picFile, fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + picFile)));
            Toast.makeText(mActivity,"图片保存图库成功",Toast.LENGTH_LONG).show();
        }
    };

}

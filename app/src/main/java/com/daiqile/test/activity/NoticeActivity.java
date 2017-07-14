package com.daiqile.test.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.model.Notice;
import com.daiqile.test.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/20.
 */
public class NoticeActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Activity mActivity;
    private MyApplication application;
    private List<Notice.NoticeInfo> infos = new ArrayList<>();
    private MyAdapter adapter;
    private String site_id;

    @Override
    public void init() {
        mActivity = NoticeActivity.this;
        application = (MyApplication) getApplication();
        site_id = getIntent().getStringExtra("site_id");
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        adapter = new MyAdapter(mActivity, infos);
        mListView.setAdapter(adapter);
        notice(false);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                if (site_id.equals("83")) {
                    Intent intent = new Intent(mActivity, NoticeDetailActivity.class);
                    intent.putExtra("title", infos.get(i).getName());
                    intent.putExtra("content", infos.get(i).getContent());
                    startActivity(intent);
                } else if (site_id.equals("144")) {
                    Intent intent = new Intent(mActivity, WebViewActivity.class);
                    intent.putExtra("title", infos.get(i).getName());
                    intent.putExtra("content", infos.get(i).getContent());
                    startActivity(intent);
                }else if(site_id.equals("253")){
                    Intent intent = new Intent(mActivity,WebViewActivity.class);
                    intent.putExtra("title", infos.get(i).getName());
                    intent.putExtra("content", infos.get(i).getContent());
                    startActivity(intent);
                }
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                notice(true);
            }
        });
    }

    private void notice(final boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("dcode", Constants.DCODE);
        map.put("site_id", site_id);
        application.apiService.notice(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Notice>() {
                    @Override
                    public void onCompleted() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Notice notice) {
                        if (isRefresh) {
                            infos.clear();
                            infos.addAll(notice.getList());
                        } else {
                            infos.addAll(notice.getList());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private class MyAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<Notice.NoticeInfo> list;

        public MyAdapter(Context context, List<Notice.NoticeInfo> list) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup group) {
            ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.item_notice, null);
                holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
                holder.tv_publish_at = (TextView) view.findViewById(R.id.tv_publish_at);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tv_title.setText(list.get(i).getName());
            holder.tv_publish_at.setText(list.get(i).getPublish());
            return view;
        }

        private class ViewHolder {
            TextView tv_title;
            TextView tv_publish_at;
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}

package com.daiqile.test.activity;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.adapter.TenderLogAdapter;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.model.TenderLog;
import com.daiqile.test.view.TopBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/13.
 */

public class TenderLogActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private Activity mActivity;
    private MyApplication application;
    private String borrow_id;
    private TenderLogAdapter adapter;

    @Override
    public void init() {
        mActivity = TenderLogActivity.this;
        application = (MyApplication) mActivity.getApplication();
        borrow_id = getIntent().getStringExtra("borrow_id");
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        easyRecyclerView.addItemDecoration(new DividerDecoration(R.color.material_textBlack_dividers, 1));
        adapter = new TenderLogAdapter(mActivity);
        easyRecyclerView.setAdapter(adapter);
        getData(false);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(true);
            }
        });
    }

    private void getData(final boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("borrow_id", borrow_id);
        application.apiService.getTenderLog(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TenderLog>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TenderLog tenderLog) {
                        if (isRefresh) {
                            adapter.clear();
                            adapter.addAll(tenderLog.getList());
                        } else {
                            adapter.addAll(tenderLog.getList());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tender_log;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }
}

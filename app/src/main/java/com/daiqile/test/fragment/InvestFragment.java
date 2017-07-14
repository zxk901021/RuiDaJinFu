package com.daiqile.test.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.activity.InvestDetailActivity;
import com.daiqile.test.activity.LoginActivity;
import com.daiqile.test.adapter.ProjectsAdapter;
import com.daiqile.test.base.BaseFragment;
import com.daiqile.test.model.Invest;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/6.
 */

public class InvestFragment extends BaseFragment {
    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;
    private Activity mActivity;
    private MyApplication application;
    private ProjectsAdapter adapter;
    private int pageSize = 10;
    private int currentPage = 1;

    @Override
    public void init() {

        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new ProjectsAdapter(mActivity);
        easyRecyclerView.setAdapter(adapter);
        bid(true);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                bid(true);
            }
        });
        adapter.setMore(R.layout.load_more_layout, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                bid(false);
            }
        });
        adapter.setNoMore(R.layout.no_more_layout);
        adapter.setError(R.layout.error_layout);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (application.mUser == null) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                } else {
                    Intent intent = new Intent(mActivity, InvestDetailActivity.class);
                    intent.putExtra("investInfo", adapter.getItem(position));
                    startActivity(intent);
                }
            }
        });
    }

    private void bid(final boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("pageSize", String.valueOf(pageSize));
        map.put("page", String.valueOf(currentPage));
        application.apiService.bid(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Invest>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Invest> invests) {
                        if (isRefresh) {
                            adapter.clear();
                            adapter.addAll(invests);
                        } else {
                            adapter.addAll(invests);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_invest;
    }

    @Override
    public Object bindFragment() {
        return this;
    }
}

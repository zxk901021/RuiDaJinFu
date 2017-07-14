package com.daiqile.test.fragment;


import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.adapter.ProjectAdapter;
import com.daiqile.test.base.BaseFragment;
import com.daiqile.test.model.Project;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/20.
 */

public class ProjectFragment extends BaseFragment {
    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private Activity mActivity;
    private MyApplication application;
    private ProjectAdapter adapter;

    @Override
    public void init() {
        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new ProjectAdapter(mActivity);
        easyRecyclerView.setAdapter(adapter);
        getInvestRecord(false);
        easyRecyclerView.setErrorView(R.layout.error_layout);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInvestRecord(true);
            }
        });
    }

    private void getInvestRecord(final boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(application.mUser.getId()));
//        map.put("user_id", "64");
        map.put("dcode", Constants.DCODE);
        map.put("borrow_status", "1");
        application.apiService.getInvestingRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Project>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Project project) {
                        if (isRefresh) {
                            adapter.clear();
                            adapter.addAll(project.getList());
                        } else {
                            adapter.addAll(project.getList());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_investing;
    }

    @Override
    public Object bindFragment() {
        return this;
    }
}

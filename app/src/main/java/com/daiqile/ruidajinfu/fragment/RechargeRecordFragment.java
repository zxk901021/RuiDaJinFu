package com.daiqile.ruidajinfu.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.adapter.RechargeRecordAdapter;
import com.daiqile.ruidajinfu.base.BaseFragment;
import com.daiqile.ruidajinfu.model.RechargeRecord;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/19.
 */
public class RechargeRecordFragment extends BaseFragment {


    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private Activity mActivity;
    private MyApplication application;
    private RechargeRecordAdapter adapter;
    private int pageNum = 1;

    @Override
    public void init() {
        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        easyRecyclerView.addItemDecoration(new DividerDecoration(R.color.material_white, 1));
        adapter = new RechargeRecordAdapter(mActivity);
        easyRecyclerView.setAdapter(adapter);
        getRechargeRecord(false);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                getRechargeRecord(true);
            }
        });
        adapter.setMore(R.layout.load_more_layout, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNum++;
                getRechargeRecord(false);
            }
        });
    }

    private void getRechargeRecord(final boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(application.mUser.getId()));
        map.put("page", pageNum + "");
        application.apiService.getRechargeRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RechargeRecord>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RechargeRecord rechargeRecord) {
                        if (isRefresh) {
                            adapter.clear();
                            adapter.addAll(rechargeRecord.getList());
                        } else {
                            adapter.addAll(rechargeRecord.getList());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_recharge_record;
    }

    @Override
    public Object bindFragment() {
        return this;
    }

}

package com.daiqile.ruidajinfu.fragment;


import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.adapter.MoneyRecordAdapter;
import com.daiqile.ruidajinfu.base.BaseFragment;
import com.daiqile.ruidajinfu.model.MoneyRecord;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/14.
 */
public class MoneyRecordFragment extends BaseFragment {
    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private Activity mActivity;
    private MyApplication application;
    private MoneyRecordAdapter adapter;
    private int pageNum = 1;

    @Override
    public void init() {
        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new MoneyRecordAdapter(mActivity);
        easyRecyclerView.setAdapter(adapter);
        getMoneyRecord(false);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                getMoneyRecord(true);
            }
        });
        adapter.setMore(R.layout.load_more_layout, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNum++;
                getMoneyRecord(false);
            }
        });
    }

    private void getMoneyRecord(final boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(application.mUser.getId()));
        map.put("page", pageNum + "");
        map.put("dcode", Constants.DCODE);
        application.apiService.getMoneyRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MoneyRecord>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<MoneyRecord> moneyRecords) {
                        if (isRefresh) {
                            adapter.clear();
                            adapter.addAll(moneyRecords);
                        } else {
                            adapter.addAll(moneyRecords);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_money_record;
    }

    @Override
    public Object bindFragment() {
        return this;
    }

}

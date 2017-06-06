package com.daiqile.ruidajinfu.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.adapter.InvestRecordAdapter;
import com.daiqile.ruidajinfu.base.BaseFragment;
import com.daiqile.ruidajinfu.model.InvestRecord;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InvestRecordFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private String mParam1;
    private Activity mActivity;
    private MyApplication application;
    private InvestRecordAdapter adapter;

    public InvestRecordFragment() {
    }

    public static InvestRecordFragment newInstance(String param1) {
        InvestRecordFragment fragment = new InvestRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void init() {
        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new InvestRecordAdapter(mActivity);
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
        map.put("type", mParam1);
        map.put("borrow_status", "0");
        application.apiService.getInvestRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InvestRecord>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(InvestRecord investRecord) {
                        for (int i = 0; i < investRecord.getInvestRecordInfo().size(); i++) {
                            investRecord.getInvestRecordInfo().get(i).setStatus(mParam1);
                        }
                        if (isRefresh) {
                            adapter.clear();
                            adapter.addAll(investRecord.getInvestRecordInfo());
                        } else {
                            adapter.addAll(investRecord.getInvestRecordInfo());
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

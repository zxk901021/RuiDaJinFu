package com.daiqile.test.activity;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.adapter.PaymentDetailAdapter;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.model.Repayment;
import com.daiqile.test.view.TopBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
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

public class PaymentDetailActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private Activity mActivity;
    private MyApplication application;
    private PaymentDetailAdapter adapter;
    private String borrow_id;
    private int page = 1;
    private int epage = 10;

    @Override
    public void init() {
        mActivity = PaymentDetailActivity.this;
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
        adapter = new PaymentDetailAdapter(mActivity);
        easyRecyclerView.setAdapter(adapter);
        getData(false);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData(true);
            }
        });
        adapter.setMore(R.layout.load_more_layout, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getData(false);
            }
        });
    }

    private void getData(final boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("id", borrow_id);
        map.put("dcode", Constants.DCODE);
        map.put("page", page + "");
        map.put("epage", epage + "");
        application.apiService.getRepayment(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Repayment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Repayment repayment) {
                        if (isRefresh) {
                            adapter.clear();
                            adapter.addAll(repayment.getList());
                        } else {
                            adapter.addAll(repayment.getList());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_detail;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }
}

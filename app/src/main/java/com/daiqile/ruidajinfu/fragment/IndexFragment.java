package com.daiqile.ruidajinfu.fragment;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.activity.InvestDetailActivity;
import com.daiqile.ruidajinfu.activity.LoginActivity;
import com.daiqile.ruidajinfu.base.BaseFragment;
import com.daiqile.ruidajinfu.model.Banner;
import com.daiqile.ruidajinfu.model.Invest;
import com.daiqile.ruidajinfu.utils.InvestUtil;
import com.daiqile.ruidajinfu.view.CircleProgress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/2.
 */
public class IndexFragment extends BaseFragment {

    @BindView(R.id.convenient_banner)
    ConvenientBanner mConvenientBanner;
    @BindView(R.id.circle_progress)
    CircleProgress mCircleProgress;
    @BindView(R.id.tv_time_limit)
    TextView tvTimeLimit;
    @BindView(R.id.tv_money_limit)
    TextView tvMoneyLimit;
    @BindView(R.id.btn_invest)
    Button btnInvest;
    @BindView(R.id.index_refresh)
    SwipeRefreshLayout refreshLayout;

    private ArrayList<String> localImages = new ArrayList<>();
    private Activity mActivity;
    private MyApplication application;
    private Invest invest;

    private MyBroad receiver;

    @Override
    public void init() {
        receiver = new MyBroad();
        IntentFilter filter = new IntentFilter();
        filter.addAction("refresh");
        getActivity().registerReceiver(receiver,filter);


        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
        banner();
//        setBanner();
        loadData();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                loadData();
            }
        });


    }

    private void loadData() {
        Map<String, String> map = new HashMap<>();
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
                        if (invests.size() == 0) {
                            return;
                        }
                        invest = invests.get(0);
                        doAnimation(invest.getAccount_yes() / invest.getAccount());
                        if (invest.getIsday().equals("1")) {
                            tvTimeLimit.setText("期限：" +
                                    invest.getTime_limit_day() + "天");
                        } else {
                            tvTimeLimit.setText("期限：" +
                                    invest.getTime_limit() + "个月");
                        }
                        tvMoneyLimit.setText(String.format("金额：%d元  |  %d元起投", Float.valueOf(invest.getAccount()).intValue(), Float.valueOf(invest.getLowest_account()).intValue()));
                        btnInvest.setText(InvestUtil.getStatusStr(invest.getStatus(), invest));
                    }
                });
    }

    private void setBanner() {
//        localImages = initImageData();
        mConvenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, localImages)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        mConvenientBanner.startTurning(5000);
    }

    private void banner(){
        application.apiService.getBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Banner>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Banner banner) {
//                        initImageData(banner.getList()) ;
                        localImages.addAll(banner.getList()) ;
                        setBanner();
                    }
                });
    }



//    private ArrayList<String> initImageData(List<String> mList) {
//        ArrayList<String> list = new ArrayList<>();
//        for (int i=0;i<mList.size();i++){
//            list.add(mList.get(i));
//        }
//
//        return list;
//    }

    private class LocalImageHolderView implements Holder<String> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(mActivity).load(data).into(imageView) ;
        }


    }

    private void doAnimation(float progress) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 240 * progress);
        animator.setTarget(mCircleProgress);
        animator.setDuration(5000).start();
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCircleProgress.setmSweepValue((Float) animation.getAnimatedValue());
                mCircleProgress.setmShowText(String.valueOf((int) ((float) animation.getAnimatedValue() / 240 * 100)) + "%");
            }
        });
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_index;
    }

    @Override
    public Object bindFragment() {
        return this;
    }

    @OnClick(R.id.btn_invest)
    public void onClick() {
        if (application.mUser == null) {
            startActivity(new Intent(mActivity, LoginActivity.class));
        } else {
            Intent intent = new Intent(mActivity, InvestDetailActivity.class);
            intent.putExtra("investInfo", invest);
            startActivity(intent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(receiver);
    }

    private class MyBroad extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if("refresh".equals(intent.getAction())){
                loadData();
            }
        }
    }
}

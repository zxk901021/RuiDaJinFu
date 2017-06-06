package com.daiqile.ruidajinfu.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.activity.BankCardActivity;
import com.daiqile.ruidajinfu.activity.InvestRecordActivity;
import com.daiqile.ruidajinfu.activity.InviteActivity;
import com.daiqile.ruidajinfu.activity.LoginActivity;
import com.daiqile.ruidajinfu.activity.MoneyRecordActivity;
import com.daiqile.ruidajinfu.activity.RechargeActivity;
import com.daiqile.ruidajinfu.activity.SafeCenterActivity;
import com.daiqile.ruidajinfu.activity.UnbindCardActivity;
import com.daiqile.ruidajinfu.activity.WithDrawActivity;
import com.daiqile.ruidajinfu.base.BaseFragment;
import com.daiqile.ruidajinfu.model.AccountCenter;
import com.daiqile.ruidajinfu.model.RealStatus;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.GlideCircleTransform;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/7.
 */
public class AccountFragment extends BaseFragment {

    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_use_money)
    TextView tvUseMoney;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView tvWithdraw;
    @BindView(R.id.tv_money_record)
    TextView tvMoneyRecord;
    @BindView(R.id.tv_invest_record)
    TextView tvInvestRecord;
    @BindView(R.id.tv_red_packet)
    TextView tvRedPacket;
    @BindView(R.id.tv_bank_card)
    TextView tvBankCard;
    @BindView(R.id.tv_my_recommend)
    TextView tvMyRecommend;
    @BindView(R.id.tv_safe)
    TextView tvSafe;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tv_experience_money)
    TextView mTvExperienceMoney;
    @BindView(R.id.tv_collection_money)
    TextView mTvCollectionMoney;

    private Activity mActivity;
    private MyApplication application;
    private DecimalFormat df = new DecimalFormat("0.00");

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.action.user.exit")) {
                exit();
            } else if (intent.getAction().equals("android.action.realname")) {
                tvRealName.setVisibility(View.GONE);
            } else if (intent.getAction().equals("android.action.refresh")) {
                getAccountCenter();
            }
        }
    };

    @Override
    public void init() {
        mActivity = getActivity();
        application = (MyApplication) mActivity.getApplication();
        refresh();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                refresh();
            }
        });
    }


    private void refresh() {
        if (application.mUser == null) {
            exit();
        } else {
            getAccountCenter();
            tvUsername.setText(application.mUser.getUsername());
            Glide.with(mActivity).load("http://www.zjrdjr.com/data/avatar/" + application.mUser.getId() + "_avatar_middle.jpg")
                    .transform(new GlideCircleTransform(mActivity))
                    .into(imgAvatar);
            isReal();
        }
    }

    private void exit() {
        Glide.with(mActivity).load(R.drawable.logo)
                .transform(new GlideCircleTransform(mActivity))
                .into(imgAvatar);
        tvTotal.setText("账户总额\n0.00");
        tvUseMoney.setText("可用余额\n0.00");
        tvCollection.setText("冻结总额\n0.00");
        mTvExperienceMoney.setText("体验金\n0.00");
        mTvCollectionMoney.setText("待收\n0.00");
        tvUsername.setText("请登录");
        tvRealName.setVisibility(View.VISIBLE);
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_account;
    }

    @Override
    public Object bindFragment() {
        return this;
    }

    @OnClick({R.id.tv_recharge, R.id.tv_withdraw, R.id.tv_money_record, R.id.tv_invest_record, R.id.tv_red_packet, R.id.tv_bank_card, R.id.tv_my_recommend, R.id.tv_safe
            , R.id.tv_real_name, R.id.rl_user})
    public void onClick(View view) {
        if (application.mUser == null) {
            startActivity(new Intent(mActivity, LoginActivity.class));
        } else {
            switch (view.getId()) {
                case R.id.tv_recharge:
                    startActivity(new Intent(mActivity, RechargeActivity.class));
                    break;
                case R.id.tv_withdraw:
                    application.apiService.isReal(application.mUser.getId() + "")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<RealStatus>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(RealStatus realStatus) {
                                    if (realStatus.getReal_status().equals("1")) {
                                        Map<String, String> map = new HashMap<>();
                                        map.put("user_id", application.mUser.getId() + "");
                                        application.apiService.isBind(map)
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
                                                        try {
                                                            JSONObject object = new JSONObject(responseBody.string());
                                                            String status = object.getString("status");
                                                            if (status.equals("0")) {
                                                                ToastUtil.showToast(mActivity, "您尚未绑定银行卡");
                                                            } else {
                                                                startActivity(new Intent(mActivity, WithDrawActivity.class));
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                    } else {
                                        ToastUtil.showToast(mActivity, "您尚未实名认证，请先实名认证！");
                                    }
                                }
                            });
                    break;
                case R.id.tv_money_record:
                    startActivity(new Intent(mActivity, MoneyRecordActivity.class));
                    break;
                case R.id.tv_invest_record:
                    startActivity(new Intent(mActivity, InvestRecordActivity.class));
                    break;
                case R.id.tv_red_packet:
                    break;
                case R.id.tv_bank_card:
                    isBind();
                    break;
                case R.id.tv_my_recommend:
                    startActivity(new Intent(mActivity, InviteActivity.class));
                    break;
                case R.id.tv_safe:
                    if (application.mUser == null) {
                        startActivity(new Intent(mActivity, LoginActivity.class));
                    } else {
                        startActivity(new Intent(mActivity, SafeCenterActivity.class));
                    }
                    break;
                case R.id.tv_real_name:
                    startActivity(new Intent(mActivity, SafeCenterActivity.class));
                    break;
                case R.id.rl_user:
                    if (application.mUser == null) {
                        startActivity(new Intent(mActivity, LoginActivity.class));
                    }
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.action.user.exit");
        filter.addAction("android.action.realname");
        filter.addAction("android.action.refresh");
        mActivity.registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.unregisterReceiver(receiver);
    }

    private void isBind() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", application.mUser.getId() + "");
        application.apiService.isBind(map)
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
                        try {
                            JSONObject object = new JSONObject(responseBody.string());
                            String status = object.getString("status");
                            if (status.equals("0")) {
                                startActivity(new Intent(mActivity, UnbindCardActivity.class));
                            } else {
                                startActivity(new Intent(mActivity, BankCardActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private void getAccountCenter() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(application.mUser.getId()));
        map.put("dcode", Constants.DCODE);
        application.apiService.getAccountCenter(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccountCenter>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccountCenter center) {
                        double use_money = center.getUse_money();
                        double total = center.getTotal();
                        double collection = center.getNo_use_money();
                        double use_learn_money = center.getUse_learn_money();
                        double collection_money = center.getCollection();
                        tvTotal.setText("账户总额\n" + df.format(total));
                        tvUseMoney.setText("可用余额\n" + df.format(use_money));
                        tvCollection.setText("冻结总额\n" + df.format(collection));
                        mTvExperienceMoney.setText("体验金\n"+df.format(use_learn_money));
                        mTvCollectionMoney.setText("待收\n"+df.format(collection_money));
                    }
                });
    }

    private void isReal() {
        application.apiService.isReal(application.mUser.getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RealStatus>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RealStatus realStatus) {
                        if (realStatus.getReal_status().equals("1")) {
                            tvRealName.setVisibility(View.GONE);
                        } else {
                            tvRealName.setText("现在去实名认证");
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}

package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.model.BindCard;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/17.
 */

public class BankCardActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_remove)
    Button btnRemove;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;

    private Activity mActivity;
    private MyApplication application;
    private List<View> viewList = new ArrayList<>();
    private LayoutInflater inflater;
    private MyPagerAdapter adapter;
    private List<BindCard.BankCardBean> bindCardBeanList = new ArrayList<>();
    private String bankId = "";
    private int removePosition = 0;

    @Override
    public void init() {
        mActivity = BankCardActivity.this;
        application = (MyApplication) getApplication();

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        inflater = LayoutInflater.from(mActivity);
        getBindCard();
        adapter = new MyPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvBankName.setText(bindCardBeanList.get(position).getBranch());
                bankId = bindCardBeanList.get(position).getId();
                removePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getBindCard() {
        application.apiService.getBindCard(application.mUser.getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BindCard>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BindCard bindCard) {
                        bindCardBeanList.addAll(bindCard.getList());
                        if (bindCardBeanList.size() == 0) {
                            finish();
                        } else {
                            for (int i = 0; i < bindCardBeanList.size(); i++) {
                                View view = inflater.inflate(R.layout.item_bank_card, null);
                                TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
                                tv_content.setText(bindCardBeanList.get(i).getBank() + "\n\n\n" +
                                        bindCardBeanList.get(i).getCard_number());
                                viewList.add(view);
                            }
                            bankId = bindCardBeanList.get(0).getId();
                            tvBankName.setText(bindCardBeanList.get(0).getBranch());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    private void removeCard() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(application.mUser.getId()));
        map.put("bank_id", bankId);
        application.apiService.removeCard(map)
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
                            String result = object.getString("status");
                            if (result.equals("1")) {
                                ToastUtil.showToast(mActivity, "成功删除银行卡");
                                finish();
                            } else if (result.equals("0")) {
                                ToastUtil.showToast(mActivity, "删除银行卡失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_card;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick({R.id.btn_remove, R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_remove:
                removeCard();
                break;
            case R.id.btn_add:
                startActivity(new Intent(mActivity, BindCardActivity.class));
                finish();
                break;
        }
    }
}

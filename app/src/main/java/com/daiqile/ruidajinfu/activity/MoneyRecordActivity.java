package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.fragment.CashRecordFragment;
import com.daiqile.ruidajinfu.fragment.MoneyRecordFragment;
import com.daiqile.ruidajinfu.fragment.RechargeRecordFragment;
import com.daiqile.ruidajinfu.view.TopBar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MoneyRecordActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    public void init() {
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        titles.add("交易记录");
        titles.add("充值记录");
        titles.add("提现记录");

        fragments.add(new MoneyRecordFragment());
        fragments.add(new RechargeRecordFragment());
        fragments.add(new CashRecordFragment());

        viewPager.setAdapter(new MoneyRecordFragmentAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
    }

    private class MoneyRecordFragmentAdapter extends FragmentPagerAdapter {

        public MoneyRecordFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_money_record;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }
}

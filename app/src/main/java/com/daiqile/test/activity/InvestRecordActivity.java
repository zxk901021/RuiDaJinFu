package com.daiqile.test.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.fragment.DueInFragment;
import com.daiqile.test.fragment.InvestRecordFragment;
import com.daiqile.test.fragment.ProjectFragment;
import com.daiqile.test.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/19.
 */
public class InvestRecordActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();

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
        initViewPager();
    }

    private void initViewPager() {
        mFragments = getFragments();

        mTitles.add("投标中");
        mTitles.add("持有中");
        mTitles.add("待收明细");
        mTitles.add("已还款");
        viewPager.setAdapter(new RecordPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new ProjectFragment());
        list.add(InvestRecordFragment.newInstance("wait"));
        list.add(DueInFragment.newInstance("DueInFragment"));
        list.add(InvestRecordFragment.newInstance("yes"));
        return list;
    }

    private class RecordPagerAdapter extends FragmentPagerAdapter {

        public RecordPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invest_record;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}

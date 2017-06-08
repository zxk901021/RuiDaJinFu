package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.fragment.AccountFragment;
import com.daiqile.ruidajinfu.fragment.IndexFragment;
import com.daiqile.ruidajinfu.fragment.InvestFragment;
import com.daiqile.ruidajinfu.fragment.MoreFragment;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;
import com.daiqile.ruidajinfu.view.ViewPagerFix;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.pager)
    ViewPagerFix mPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private Activity mActivity;
    private long clickTime = 0;

    private List<Fragment> fragments = new ArrayList<>();
    private MainPagerAdapter adapter;
    private MyApplication application;

    @Override
    public void init() {
        mActivity = MainActivity.this;
        application = (MyApplication) getApplication();
        //设置ViewPage无法左右滑动
        mPager.setScrollable(false);
        mPager.setOffscreenPageLimit(3);

        fragments.add(new IndexFragment());
        fragments.add(new InvestFragment());
        fragments.add(new AccountFragment());
        fragments.add(new MoreFragment());

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

        topbar.setRightButton(false);
        p = 0;
        //设置底部导航栏
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.material_white);
        mBottomNavigationBar.setBarBackgroundColor(R.color.black);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_nav_home, getString(R.string.home_page)))
                .addItem(new BottomNavigationItem(R.drawable.icon_nav_invest, getString(R.string.invest)))
                .addItem(new BottomNavigationItem(R.drawable.icon_nav_user, getString(R.string.account)))
                .addItem(new BottomNavigationItem(R.drawable.icon_nav_more, getString(R.string.more)))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                p = 0;
                if(position == 0){
                    topbar.setRightButton(false);
                }else {
                    topbar.setRightButton(false);
                }
                mPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                  if(p == 0){
                      Intent intent = new Intent("refresh");
                      sendBroadcast(intent);
                  }
            }
        });

    }

    private int p = 0;

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
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
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - clickTime > 2000) {
            ToastUtil.showToast(mActivity, R.string.click_again_exit);
            clickTime = currentTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }
}

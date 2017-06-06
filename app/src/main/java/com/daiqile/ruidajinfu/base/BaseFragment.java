package com.daiqile.ruidajinfu.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by orgwcl on 2016/9/2.
 */
public abstract class BaseFragment extends Fragment {

    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentId(), container, false);
        ButterKnife.bind(bindFragment(), view);
        init();
        return view;
    }

    public void showDialog() {
        dialog = ProgressDialog.show(getActivity(), null, "请稍候...");
        dialog.setCancelable(true);
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }

    public abstract void init();
    public abstract int getFragmentId();
    public abstract Object bindFragment();

    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

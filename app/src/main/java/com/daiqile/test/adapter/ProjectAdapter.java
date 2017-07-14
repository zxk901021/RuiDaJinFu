package com.daiqile.test.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.test.model.Project;
import com.daiqile.test.viewholder.ProjectViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2017/2/20.
 */

public class ProjectAdapter extends RecyclerArrayAdapter<Project.ProjectBean> {

    public ProjectAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectViewHolder(parent);
    }
}

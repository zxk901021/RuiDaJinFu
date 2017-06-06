package com.daiqile.ruidajinfu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.model.Invest;
import com.daiqile.ruidajinfu.viewholder.ProjectsViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ProjectsAdapter extends RecyclerArrayAdapter<Invest> {
    public ProjectsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectsViewHolder(parent);
    }
}

package com.daiqile.ruidajinfu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.model.TenderLog;
import com.daiqile.ruidajinfu.viewholder.TenderLogViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/12/13.
 */

public class TenderLogAdapter extends RecyclerArrayAdapter<TenderLog.TenderInfo> {
    public TenderLogAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TenderLogViewHolder(parent);
    }
}

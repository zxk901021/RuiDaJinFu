package com.daiqile.test.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.test.model.InvestRecord;
import com.daiqile.test.viewholder.DueInViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 钱展杰 on 2017/3/28.
 */

public class DueInAdapter extends RecyclerArrayAdapter<InvestRecord.InvestRecordInfo> {
    public DueInAdapter(Context context) {
        super(context);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DueInViewHolder(parent);
    }
}

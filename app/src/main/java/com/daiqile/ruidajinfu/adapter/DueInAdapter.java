package com.daiqile.ruidajinfu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.model.InvestRecord;
import com.daiqile.ruidajinfu.viewholder.DueInViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

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

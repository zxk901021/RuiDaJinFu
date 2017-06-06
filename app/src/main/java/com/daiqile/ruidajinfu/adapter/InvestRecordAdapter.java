package com.daiqile.ruidajinfu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.model.InvestRecord;
import com.daiqile.ruidajinfu.viewholder.InvestRecordViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/12/19.
 */
public class InvestRecordAdapter extends RecyclerArrayAdapter<InvestRecord.InvestRecordInfo> {
    public InvestRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new InvestRecordViewHolder(parent);
    }
}

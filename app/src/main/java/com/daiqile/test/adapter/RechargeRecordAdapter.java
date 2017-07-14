package com.daiqile.test.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.test.model.RechargeRecord;
import com.daiqile.test.viewholder.RechargeRecordViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/12/19.
 */
public class RechargeRecordAdapter extends RecyclerArrayAdapter<RechargeRecord.ListBean>{
    public RechargeRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RechargeRecordViewHolder(parent);
    }
}

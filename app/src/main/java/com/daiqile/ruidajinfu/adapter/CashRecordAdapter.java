package com.daiqile.ruidajinfu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.model.CashRecord;
import com.daiqile.ruidajinfu.viewholder.CashRecordViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/12/19.
 */
public class CashRecordAdapter extends RecyclerArrayAdapter<CashRecord.ListBean>{
    public CashRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CashRecordViewHolder(parent);
    }
}

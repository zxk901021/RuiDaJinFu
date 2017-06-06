package com.daiqile.ruidajinfu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.model.MoneyRecord;
import com.daiqile.ruidajinfu.viewholder.MoneyRecordViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/12/19.
 */

public class MoneyRecordAdapter extends RecyclerArrayAdapter<MoneyRecord> {
    public MoneyRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoneyRecordViewHolder(parent);
    }
}

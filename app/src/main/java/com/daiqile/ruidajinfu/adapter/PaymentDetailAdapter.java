package com.daiqile.ruidajinfu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.ruidajinfu.model.Repayment;
import com.daiqile.ruidajinfu.viewholder.PaymentDetailViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/12/13.
 */

public class PaymentDetailAdapter extends RecyclerArrayAdapter<Repayment.Payment> {

    public PaymentDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PaymentDetailViewHolder(parent);
    }
}

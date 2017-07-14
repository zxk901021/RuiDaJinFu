package com.daiqile.test.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daiqile.test.model.Repayment;
import com.daiqile.test.viewholder.PaymentDetailViewHolder;
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

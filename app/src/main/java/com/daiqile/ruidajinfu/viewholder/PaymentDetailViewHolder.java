package com.daiqile.ruidajinfu.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.model.Repayment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/13.
 */
public class PaymentDetailViewHolder extends BaseViewHolder<Repayment.Payment> {

    private TextView tv_borrow_name;
    private TextView tv_times;
    private TextView tv_repayment_account;
    private TextView tv_repayment_time;


    public PaymentDetailViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_payment_detail);
        tv_borrow_name = $(R.id.tv_borrow_name);
        tv_times = $(R.id.tv_times);
        tv_repayment_account = $(R.id.tv_repayment_account);
        tv_repayment_time = $(R.id.tv_repayment_time);
    }

    @Override
    public void setData(Repayment.Payment data) {
        tv_borrow_name.setText(data.getBorrow_name());
        tv_times.setText(String.format("%d / %s", Integer.valueOf(data.getOrder()) + 1, data.getTime_limit()));
        tv_repayment_account.setText(data.getRepayment_account());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.valueOf(data.getRepayment_time()) * 1000);
        tv_repayment_time.setText(sdf.format(date));
    }
}

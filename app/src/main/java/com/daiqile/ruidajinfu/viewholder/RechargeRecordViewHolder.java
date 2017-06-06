package com.daiqile.ruidajinfu.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.model.RechargeRecord;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/19.
 */
public class RechargeRecordViewHolder extends BaseViewHolder<RechargeRecord.ListBean> {

    private TextView tv_payment_name;
    private TextView tv_money;
    private TextView tv_add_time;
    private TextView tv_status;

    public RechargeRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_recharge_record);
        tv_payment_name = $(R.id.tv_payment_name);
        tv_money = $(R.id.tv_money);
        tv_add_time = $(R.id.tv_add_time);
        tv_status = $(R.id.tv_status);
    }

    @Override
    public void setData(RechargeRecord.ListBean data) {
        tv_payment_name.setText(data.getPayment_name());
        tv_money.setText(data.getMoney());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(data.getAddtime() * 1000);
        tv_add_time.setText(sdf.format(date));
        if (data.getStatus().equals("1")) {
            tv_status.setText("充值成功");
        } else {
            tv_status.setText("审核中");
        }
    }
}

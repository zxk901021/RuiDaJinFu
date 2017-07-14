package com.daiqile.test.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.test.R;
import com.daiqile.test.model.CashRecord;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/19.
 */
public class CashRecordViewHolder extends BaseViewHolder<CashRecord.ListBean> {

    private TextView tv_total;
    private TextView tv_credited;
    private TextView tv_add_time;
    private TextView tv_status;

    public CashRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_cash_record);
        tv_total = $(R.id.tv_total);
        tv_credited = $(R.id.tv_credited);
        tv_add_time = $(R.id.tv_add_time);
        tv_status = $(R.id.tv_status);
    }

    @Override
    public void setData(CashRecord.ListBean data) {
        String number = data.getAccount();
        tv_total.setText("****" + number.substring(number.length() - 4, number.length()));
        tv_credited.setText(data.getCredited());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.valueOf(data.getAddtime()) * 1000);
        tv_add_time.setText(sdf.format(date));
        if (data.getStatus().equals("1")) {
            tv_status.setText("提现成功");
        } else {
            tv_status.setText("审核中");
        }
    }
}

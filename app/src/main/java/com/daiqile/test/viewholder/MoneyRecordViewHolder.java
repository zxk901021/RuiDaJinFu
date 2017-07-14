package com.daiqile.test.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.test.R;
import com.daiqile.test.model.MoneyRecord;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/19.
 */
public class MoneyRecordViewHolder extends BaseViewHolder<MoneyRecord> {

    private TextView tv_remark, tv_money, tv_add_time;

    public MoneyRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_money_record);
        tv_remark = $(R.id.tv_remark);
        tv_money = $(R.id.tv_money);
        tv_add_time = $(R.id.tv_add_time);
    }

    @Override
    public void setData(MoneyRecord data) {
        tv_remark.setText(data.getRemark());
        tv_money.setText(data.getMoney());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(data.getAddtime() * 1000);
        tv_add_time.setText(sdf.format(date));
    }
}

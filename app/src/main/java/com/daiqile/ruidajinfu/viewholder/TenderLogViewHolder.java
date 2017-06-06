package com.daiqile.ruidajinfu.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.model.TenderLog;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/13.
 */
public class TenderLogViewHolder extends BaseViewHolder<TenderLog.TenderInfo> {

    private TextView tv_username, tv_money, tv_time, tv_status;
    private Date date;

    public TenderLogViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_tender_log);
        tv_username = $(R.id.tv_username);
        tv_money = $(R.id.tv_money);
        tv_time = $(R.id.tv_time);
        tv_status = $(R.id.tv_status);
    }

    @Override
    public void setData(TenderLog.TenderInfo data) {
        tv_username.setText(data.getUsername());
        tv_money.setText(data.getTender_money());
        date = new Date(data.getAddtime() * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tv_time.setText(sdf.format(date));
        if (data.getTender_account().equals(data.getMoney())) {
            tv_status.setText("全部通过");
        } else {
            tv_status.setText("部分通过");
        }
    }
}

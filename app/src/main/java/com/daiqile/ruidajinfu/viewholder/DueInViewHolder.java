package com.daiqile.ruidajinfu.viewholder;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.model.InvestRecord;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by 钱展杰 on 2017/3/28.
 */

public class DueInViewHolder extends BaseViewHolder<InvestRecord.InvestRecordInfo> {
    private TextView tv_title, tv_apr, tv_money, tv_status, tv_limit, tv_title_limit;

    public DueInViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_due_in);
        tv_title = $(R.id.tv_title);
        tv_apr = $(R.id.tv_apr);
        tv_money = $(R.id.tv_money);
        tv_status = $(R.id.tv_status);
        tv_limit = $(R.id.tv_limit);
        tv_title_limit = $(R.id.tv_title_limit);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setData(InvestRecord.InvestRecordInfo data) {
        tv_title.setText(data.getBorrow_name());
        tv_apr.setText(data.getRepay_account());
        tv_money.setText(data.getInterest());
      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        if (data.getStatus().equals("DueInFragment")) {


            String time = data.getRepayments_time();
            if (!TextUtils.isEmpty(time)) {
                long l = Long.parseLong(time);
                long l1 = l * 1000;
                tv_limit.setText(sdf.format(l1));
            }else{
                Log.e("llllll","time null");
            }
        } else {
            tv_status.setVisibility(View.GONE);
            tv_title_limit.setVisibility(View.GONE);
            tv_limit.setVisibility(View.GONE);
        }
    }
}

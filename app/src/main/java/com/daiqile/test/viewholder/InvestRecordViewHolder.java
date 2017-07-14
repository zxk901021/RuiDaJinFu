package com.daiqile.test.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.test.R;
import com.daiqile.test.model.InvestRecord;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/12/19.
 */
public class InvestRecordViewHolder extends BaseViewHolder<InvestRecord.InvestRecordInfo> {

    private TextView tv_title, tv_apr, tv_money, tv_status, tv_limit, tv_title_limit,tv_number_title;

    public InvestRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_invest_record);
        tv_title = $(R.id.tv_title);
        tv_apr = $(R.id.tv_apr);
        tv_money = $(R.id.tv_money);
        tv_status = $(R.id.tv_status);
        tv_limit = $(R.id.tv_limit);
        tv_title_limit = $(R.id.tv_title_limit);
        tv_number_title = $(R.id.tv_number_title);
    }
   /* repayment_account*/

    @Override
    public void setData(InvestRecord.InvestRecordInfo data) {
        tv_title.setText(data.getBorrow_name());
        tv_apr.setText(data.getApr());
        tv_money.setText(data.getAnum());
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        if (data.getStatus().equals("yes")) {
            tv_status.setVisibility(View.GONE);
            tv_title_limit.setText("利息");
            tv_limit.setText(data.getInter());
            tv_number_title.setText("本金");
//            tv_title_limit.setVisibility(View.GONE);
//            tv_limit.setVisibility(View.GONE);
        } else {
            String time = data.getRepayments_time();
            if (!TextUtils.isEmpty(time)) {
                long l = Long.parseLong(time);
                long l1 = l * 1000;
//                if (data.getIsday().equals("1")) {
                tv_limit.setText(sdf.format(l1));
                tv_title_limit.setText("借款期限");
                tv_number_title.setText("金额");
//                }
//            else {
//                tv_limit.setText(
//                        data.getTime_limit() + "个月");
//            }
//                else if (data.getIsday().equals("0")){
//                    tv_limit.setText(sdf.format(l1));
//                }
            }

        }
    }
}

package com.daiqile.ruidajinfu.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.model.Project;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/2/20.
 */

public class ProjectViewHolder extends BaseViewHolder<Project.ProjectBean> {

    private TextView tv_title, tv_apr, tv_money, tv_status, tv_limit, tv_title_limit;

    public ProjectViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_invest_record);
        tv_title = $(R.id.tv_title);
        tv_apr = $(R.id.tv_apr);
        tv_money = $(R.id.tv_money);
        tv_status = $(R.id.tv_status);
        tv_limit = $(R.id.tv_limit);
        tv_title_limit = $(R.id.tv_title_limit);
    }

    @Override
    public void setData(Project.ProjectBean data) {
        tv_title.setText(data.getBorrow_name());
        tv_apr.setText(data.getApr());
        tv_money.setText(data.getMoney());
        tv_limit.setText(data.getTime_limit()+"个月");
        tv_status.setVisibility(View.GONE);
    }
}

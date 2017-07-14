package com.daiqile.test.viewholder;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daiqile.test.R;
import com.daiqile.test.model.Invest;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ProjectsViewHolder extends BaseViewHolder<Invest> {

    private TextView tv_title, tv_rate, tv_time_limit, tv_progress;
    private ProgressBar pb;
    private ImageView img_done;

    public ProjectsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_projects);
        tv_title = $(R.id.tv_title);
        tv_rate = $(R.id.tv_rate);
        tv_time_limit = $(R.id.tv_time_limit);
        tv_progress = $(R.id.tv_progress);
        pb = $(R.id.pb);
        img_done = $(R.id.img_done);
    }

    @Override
    public void setData(Invest data) {
        tv_title.setText(data.getName());
        tv_rate.setText(data.getApr());
        if (data.getIsday().equals("1")) {
            tv_time_limit.setText(
                    data.getTime_limit_day() + "天");
        } /*else {
            tv_time_limit.setText(
                    data.getTime_limit() + "个月");
        }*/
        else if(data.getIsday().equals("0")){
            tv_time_limit.setText(data.getTime_limit() + "个月");
        }
        int progress = (int)(data.getAccount_yes() / data.getAccount() * 100);
        tv_progress.setText(progress + "%");
        pb.setProgress(progress);
        ObjectAnimator animator = ObjectAnimator.ofInt(pb, "progress", 0, progress);
        animator.setDuration(3000);
        animator.start();
        if (progress == 100) {
            img_done.setVisibility(View.VISIBLE);
            tv_progress.setVisibility(View.GONE);
        } else {
            img_done.setVisibility(View.GONE);
            tv_progress.setVisibility(View.VISIBLE);
        }
    }
}

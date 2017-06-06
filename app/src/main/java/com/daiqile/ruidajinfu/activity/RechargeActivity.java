package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.utils.CommonUtil;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/19.
 */

public class RechargeActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_recharge)
    Button btnRecharge;

    private Activity mActivity;
    private MyApplication application;

    @Override
    public void init() {
        mActivity = RechargeActivity.this;
        application = (MyApplication) getApplication();

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick(R.id.btn_recharge)
    public void onClick() {
        if (CommonUtil.isFastDoubleClick()) {
        } else {
            recharge();
        }
    }

    private void recharge() {
        String money = etMoney.getText().toString().trim();
        if (money.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入金额");
            return;
        }
        String url = Constants.BASE_URL + "huichao_mobile.php?user_id=" + application.mUser.getId()
                + "&money=" + money + "&dcode=7d5372bbcd6cc79f1bd71211f092622e";
        Intent intent = new Intent(mActivity, RechargeResultActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        finish();
    }
}

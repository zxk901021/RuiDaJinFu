package com.daiqile.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.daiqile.test.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZHY_9 on 2017/6/27.
 */

public class RegisterSecondFragment extends Fragment{

    @BindView(R.id.et_verificationCode)
    EditText verificationCode;
    @BindView(R.id.et_phone_number)
    EditText phone;

    private String verifyCode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    public Map<String, String> getEditString(){
        Map<String, String> params = new HashMap<>();
        verifyCode = verificationCode.getText().toString();
        if (TextUtils.isEmpty(verifyCode)){
            params.put("isEmpty", "1");
        } else {
            params.put("isEmpty", "0");
            params.put("verifyCode", verifyCode);
        }
        return params;
    }

    public void setPhoneData(String phoneNumber){
        phone.setText(phoneNumber);
    }
}

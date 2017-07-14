package com.daiqile.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.daiqile.test.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZHY_9 on 2017/6/27.
 */

public class RegisterFirstFragment extends Fragment{

    @BindView(R.id.et_phone_number)
    EditText phoneNumber;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.recomment_code)
    EditText recommendCode;
    @BindView(R.id.password_visiable)
    ImageView visiableFlag;

    private String username;
    private String passwordStr;
    private String inviteCode;
    private boolean isVisiable = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        visiableFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisiable){
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    visiableFlag.setImageResource(R.drawable.password_invisiable);
                    isVisiable = false;
                    int index = password.getText().toString().length();
                    password.setSelection(index);
                }else {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    visiableFlag.setImageResource(R.drawable.password_visiable);
                    isVisiable = true;
                    int index = password.getText().toString().length();
                    password.setSelection(index);
                }
            }
        });

        if (!TextUtils.isEmpty(inviteCode)){
            recommendCode.setText(inviteCode);
        }

    }

    public Map<String, String> getEditString(){
        Map<String, String> params = new HashMap<>();
        username = phoneNumber.getText().toString().trim();
        passwordStr = password.getText().toString().trim();
        inviteCode = recommendCode.getText().toString().trim();
        if (TextUtils.isEmpty(username) | TextUtils.isEmpty(passwordStr)){
            params.put("isEmpty", "1");
        } else {
            params.put("isEmpty", "0");
            params.put("username", username);
            params.put("password", passwordStr);
        }
        return params;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}

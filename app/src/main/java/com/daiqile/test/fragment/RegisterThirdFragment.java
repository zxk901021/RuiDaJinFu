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

public class RegisterThirdFragment extends Fragment{

    @BindView(R.id.et_id_card)
    EditText idCard;
    @BindView(R.id.et_name)
    EditText nameEt;

    private String idNumber;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public Map<String, String> getEditString(){
        Map<String, String> params = new HashMap<>();
        name = nameEt.getText().toString();
        idNumber = idCard.getText().toString();
        if (TextUtils.isEmpty(name) | TextUtils.isEmpty(idNumber)){
            params.put("isEmpty", "1");
        } else {
            params.put("isEmpty", "0");
            params.put("name", name);
            params.put("idNumber", idNumber);
        }
        return params;
    }
}

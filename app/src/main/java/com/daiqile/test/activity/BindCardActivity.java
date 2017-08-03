package com.daiqile.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.http.HttpPostUtil;
import com.daiqile.test.model.BankCardBean;
import com.daiqile.test.model.City;
import com.daiqile.test.model.Country;
import com.daiqile.test.model.Province;
import com.daiqile.test.utils.SoftInputUtil;
import com.daiqile.test.utils.ToastUtil;
import com.daiqile.test.view.TopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.daiqile.test.R.id.ed_phone_number;

/**
 * Created by Administrator on 2017/1/18.
 */

public class BindCardActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_choose_bank)
    TextView tvChooseBank;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_card_number)
    EditText etCardNumber;
    @BindView(R.id.et_branch)
    EditText etBranch;
    @BindView(R.id.btn_add_card)
    Button btnAddCard;
    @BindView(R.id.et_province)
    TextView etProvince;
    @BindView(R.id.et_city)
    TextView etCity;
    @BindView(R.id.et_country)
    TextView etCountry;
    @BindView(ed_phone_number)
    EditText edPhone;
    @BindView(R.id.ed_user_name)
    EditText edName;
    @BindView(R.id.ed_id_number)
    EditText idNumber;
    @BindView(R.id.ed_verify_code)
    EditText verifyCode;
    @BindView(R.id.send_verify_code)
    Button sendVerify;
    @BindView(R.id.et_bank_number)
    EditText bankNumberEd;
    @BindView(R.id.tips)
    TextView tips;

    private Activity mActivity;
    private MyApplication application;

    OptionsPickerView bankOptions;
    OptionsPickerView provinceOptions;
    OptionsPickerView cityOptions;
    OptionsPickerView countryOptions;

    private ArrayList<String> bankNameList = new ArrayList<>();
    private ArrayList<String> bankIdList = new ArrayList<>();
    private ArrayList<BankCardBean> bankCardBeanList = new ArrayList<>();

    private String bankId = "";

    private ArrayList<Province.ProvinceBean> provinceList = new ArrayList<>();
    private ArrayList<String> provinceName = new ArrayList<>();
    private ArrayList<String> provinceNIDs = new ArrayList<>();

    private ArrayList<City.CityBean> cityList = new ArrayList<>();
    private ArrayList<String> cityName = new ArrayList<>();
    private ArrayList<String> cityNIDs = new ArrayList<>();

    private ArrayList<Country.CountryBean> countryList = new ArrayList<>();
    private ArrayList<String> countryName = new ArrayList<>();
    private ArrayList<String> countryNIDs = new ArrayList<>();

    private String provinceNID = "0";
    private String cityNID = "";
    private String countryNID = "";

    private String tranceNum;
    private String bankName;
    private String bankCode;
    private String cardType;
    private String account;
    private String phone;
    private String tipsStr = "除以下银行外（工商银行、农业银行、中国银行、建设银行、中信银行、光大银行、华夏银行、平安银行、招商银行、兴业银行、浦发银行、邮储银行、宁波银行、南京银行），需要填写支付行号，获取支付行号请点击查看";
    private String bankNumber;
    private String bindCardResult;

    @Override
    public void init() {
        mActivity = BindCardActivity.this;
        application = (MyApplication) getApplication();
        provinceOptions = new OptionsPickerView(mActivity);
        cityOptions = new OptionsPickerView(mActivity);
        countryOptions = new OptionsPickerView(mActivity);
        bankOptions = new OptionsPickerView(mActivity);
        SpannableString ss = new SpannableString(tipsStr);
        ss.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(BindCardActivity.this, WebViewActivity.class);
                intent.putExtra("mode", 100);
                intent.putExtra("title", "支付行号");
                intent.putExtra("url", "https://m.so.com/index.php?a=newTranscode&u=http%3A%2F%2Fwww.tui78.com%2Fbank%2F&m=d390f4d2c51cb5ea376a8a323eb668da39c48d25&q=%E6%94%AF%E4%BB%98%E8%A1%8C%E5%8F%B7%E6%9F%A5%E8%AF%A2&sid=d2432d96fd09e5f77c150f3e91b70687");
                startActivity(intent);
            }
        }, tipsStr.length() - 4, tipsStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tips.setText(ss);
        tips.setMovementMethod(LinkMovementMethod.getInstance());

        etCity.setVisibility(View.INVISIBLE);
        etCountry.setVisibility(View.INVISIBLE);
//        getProvince();
        getBankList();
        getUser();

        verifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0){
                    btnAddCard.setEnabled(true);
                    btnAddCard.setBackgroundResource(R.drawable.background_button);
                    btnAddCard.setTextColor(Color.parseColor("#dd000000"));
                }else {
                    btnAddCard.setEnabled(false);
                    btnAddCard.setBackgroundResource(R.drawable.background_button_grey);
                    btnAddCard.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

    private String result;

    private void getUser(){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = HttpPostUtil.requestUser(String.valueOf(application.mUser.getId()));
                    handler.sendEmptyMessage(1);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        th.start();
    }

    private void getBankList() {
        application.apiService.getBankList2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BankCardBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "onNext: " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<BankCardBean> bankCardBeen) {
                        bankCardBeanList.clear();
                        bankCardBeanList.addAll(bankCardBeen);
                        bankNameList.clear();
                        bankIdList.clear();
                        for (int i = 0; i < bankCardBeanList.size(); i++) {
                            bankNameList.add(bankCardBeanList.get(i).getName());
                            bankIdList.add(bankCardBeanList.get(i).getId());
                        }
                        bankOptions = new OptionsPickerView(mActivity);
                        bankOptions.setPicker(bankNameList);
                        bankOptions.setTitle("选择银行");
                        bankOptions.setCyclic(false);
                        bankOptions.setSelectOptions(0);
                        bankOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                bankId = bankIdList.get(options1);
                                tvChooseBank.setText(bankNameList.get(options1));
                            }
                        });
                    }
                });

    }

    private void bindCard() throws UnsupportedEncodingException {
        String verifyCodes = verifyCode.getText().toString().trim();

        if (verifyCodes.isEmpty()) {
            ToastUtil.showToast(mActivity, "验证码不能为空");
            return;
        }
        String phoneStr = edPhone.getText().toString().trim();
        if (phoneStr.isEmpty()){
            ToastUtil.showToast(mActivity, "手机号码不能为空");
            return;
        }
        final Map<String, String> params = new HashMap<>();
        bankNumber = bankNumberEd.getText().toString();
        if (!TextUtils.isEmpty(bankNumber)){
         params.put("unionBank", bankNumber);
        }
        params.put("dcode", Constants.DCODE);
        params.put("user_id", String.valueOf(application.mUser.getId()));
        params.put("tranceNum", tranceNum);
        params.put("bankName", bankName);
        params.put("bankCode", bankCode);
        params.put("cardType", cardType);
        params.put("verificationCode", verifyCodes);
        params.put("account", account);
        params.put("phone", phoneStr);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bindCardResult = HttpPostUtil.bindCard(params);
                    handler.sendEmptyMessage(3);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        th.start();
//        application.apiService.bindBankCard(params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BindResult>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        int i = 1;
//                        i++;
//                        String error = e.getMessage();
//                        Log.e("error", error);
//                    }
//
//                    @Override
//                    public void onNext(BindResult bindResult) {
//                        if (bindResult.getStatus().equals("1")){
//                            ToastUtil.showToast(mActivity, "银行卡绑定成功");
//                            Intent intent = new Intent(mActivity, BankCardActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }else ToastUtil.showToast(mActivity, "银行卡绑定失败");
//                    }
//                });

    }

    private void getProvince() {
        Map<String, String> map = new HashMap<>();
        map.put("pid", provinceNID);
        map.put("dcode", Constants.DCODE);
        application.apiService.getProvinceList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Province>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Province province) {
                        provinceName.clear();
                        provinceNIDs.clear();
                        provinceList.clear();
                        provinceList.addAll(province.getContent());
                        for (int i = 0; i < provinceList.size(); i++) {
                            provinceName.add(provinceList.get(i).getName());
                            provinceNIDs.add(provinceList.get(i).getId());
                        }
                        provinceOptions.setPicker(provinceName);
                        provinceOptions.setTitle("选择省");
                        provinceOptions.setCyclic(false);
                        provinceOptions.setSelectOptions(0);
                        provinceOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                etCity.setVisibility(View.VISIBLE);
                                etProvince.setText(provinceName.get(options1));
                                provinceNID = provinceNIDs.get(options1);
                                getCity(provinceNID);
                            }
                        });
                    }
                });
    }

    private void getCity(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("pid", id);
        map.put("dcode", Constants.DCODE);
        application.apiService.getCityList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<City>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(City city) {
                        cityName.clear();
                        cityNIDs.clear();
                        cityList.clear();
                        cityList.addAll(city.getContent());
                        for (int i = 0; i < cityList.size(); i++) {
                            cityName.add(cityList.get(i).getName());
                            cityNIDs.add(cityList.get(i).getId());
                        }
                        cityOptions.setPicker(cityName);
                        cityOptions.setTitle("选择市");
                        cityOptions.setCyclic(false);
                        cityOptions.setSelectOptions(0);
                        etCity.setText(cityName.get(0));
                        cityOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                etCountry.setVisibility(View.VISIBLE);
                                etCity.setText(cityName.get(options1));
                                cityNID = cityNIDs.get(options1);
                                getCounty(cityNID);
                            }
                        });
                    }
                });
    }

    private void getCounty(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("pid", id);
        map.put("dcode", Constants.DCODE);
        application.apiService.getCountryList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Country>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Country country) {
                        countryName.clear();
                        countryNIDs.clear();
                        countryList.clear();
                        countryList.addAll(country.getContent());
                        for (int i = 0; i < countryList.size(); i++) {
                            countryName.add(countryList.get(i).getName());
                            countryNIDs.add(countryList.get(i).getId());
                        }
                        etCountry.setText(countryName.get(0));
                        countryOptions.setPicker(countryName);
                        countryOptions.setTitle("选择区");
                        countryOptions.setCyclic(false);
                        countryOptions.setSelectOptions(0);
                        countryOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                etCountry.setText(countryName.get(options1));
                                countryNID = countryNIDs.get(options1);
                            }
                        });
                    }
                });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_card;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick({R.id.tv_choose_bank, R.id.btn_add_card, R.id.et_province,
            R.id.et_city, R.id.et_country, R.id.send_verify_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_bank:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, tvChooseBank);
                }
                bankOptions.show();
                break;
            case R.id.btn_add_card:
                try {
                    bindCard();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.et_province:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, etProvince);
                }
                provinceOptions.show();
                break;
            case R.id.et_city:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, etCity);
                }
                cityOptions.show();
                break;
            case R.id.et_country:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, etCountry);
                }
                countryOptions.show();
                break;
            case R.id.send_verify_code:
                sendVerifyCode();
                break;
        }
    }
    private String resultStr;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 1:
                    if (!TextUtils.isEmpty(result)){
                        try {
                            JSONObject object = new JSONObject(result);
                            if (object!= null){
                                edPhone.setText(object.getString("phone"));
                                phone = object.getString("phone");
                                edName.setText(object.getString("realname"));
                                idNumber.setText(object.getString("card_id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    try {
                        JSONObject object = new JSONObject(resultStr);
                        if (object.getString("status").equals("1")){
                            Toast.makeText(mActivity, object.getString("message"), Toast.LENGTH_SHORT).show();
                            tranceNum = object.getString("tranceNum");
                            bankName = object.getString("bankName");
                            bankCode = object.getString("bankCode");
                            cardType = object.getString("cardType");
                        }else {
                            Toast.makeText(mActivity, object.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        JSONObject object = new JSONObject(bindCardResult);
                        if (object.getString("status").equals("1")){
                            Toast.makeText(mActivity, object.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mActivity, BankCardActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(mActivity, object.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    };

    private void sendVerifyCode(){
        account = etCardNumber.getText().toString().trim();
        bankNumber = bankNumberEd.getText().toString();
        if (TextUtils.isEmpty(bankNumber)){
            bankNumber = "";
        }
        if (account.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入银行账号");
            return;
        }
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    resultStr = HttpPostUtil.requestVerify(String.valueOf(application.mUser.getId()), account, phone, bankNumber);
                    handler.sendEmptyMessage(2);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        th.start();
        new CountDownTimer(60000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                sendVerify.setEnabled(false);
                sendVerify.setText(String.format("%ds后重新发送",millisUntilFinished/1000));
                sendVerify.setBackgroundResource(R.drawable.background_button_grey);
                sendVerify.setTextColor(Color.WHITE);
            }

            @Override
            public void onFinish() {
                sendVerify.setEnabled(true);
                sendVerify.setText("发送验证码");
                sendVerify.setBackgroundResource(R.drawable.background_button);
                sendVerify.setTextColor(Color.parseColor("#dd000000"));
            }
        }.start();
    }
}

package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.model.BindCard;
import com.daiqile.ruidajinfu.model.CashStatus;
import com.daiqile.ruidajinfu.model.City;
import com.daiqile.ruidajinfu.model.Country;
import com.daiqile.ruidajinfu.model.Province;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/21.
 */

public class WithDrawActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_choose_bank)
    TextView tvChooseBank;
    @BindView(R.id.et_province)
    TextView etProvince;
    @BindView(R.id.et_city)
    TextView etCity;
    @BindView(R.id.et_country)
    TextView etCountry;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_with_draw)
    Button btnWithDraw;

    private Activity mActivity;
    private MyApplication application;

    OptionsPickerView provinceOptions;
    OptionsPickerView cityOptions;
    OptionsPickerView countryOptions;

    OptionsPickerView bankOptions;

    private ArrayList<BindCard.BankCardBean> bankList = new ArrayList<>();
    private ArrayList<String> bankName = new ArrayList<>();
    private ArrayList<String> bankNIDs = new ArrayList<>();
    private ArrayList<String> bankNumber = new ArrayList<>();

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
    private String bankNID = "";

    @Override
    public void init() {
        mActivity = WithDrawActivity.this;
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

        provinceOptions = new OptionsPickerView(mActivity);
        cityOptions = new OptionsPickerView(mActivity);
        countryOptions = new OptionsPickerView(mActivity);
        bankOptions = new OptionsPickerView(mActivity);

//        getProvince();
        getBindCard();
    }

    private void withDraw() {
        String money = etMoney.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (money.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入提现金额");
            return;
        }
        if (password.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入支付密码");
            return;
        }
        btnWithDraw.setEnabled(false);
        application.apiService.withDraw(String.valueOf(application.mUser.getId()), bankNID, password, money)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CashStatus>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CashStatus cashStatus) {
                        btnWithDraw.setEnabled(true);
                        String result = cashStatus.getCash_status();
                        if (result.equals("1")) {
                            ToastUtil.showToast(mActivity, "提现成功");
                            Intent intent = new Intent();
                            intent.setAction("android.action.refresh");
                            sendBroadcast(intent);
                            finish();
                        } else if (result.equals("-2")) {
                            ToastUtil.showToast(mActivity, "金额有误");
                        } else if (result.equals("-3")) {
                            ToastUtil.showToast(mActivity, "交易密码有误");
                        } else if (result.equals("0")) {
                            ToastUtil.showToast(mActivity, "可用余额不足");
                        } else if (result.equals("-1")) {
                            ToastUtil.showToast(mActivity, "未设置银行卡,请到电脑上绑定银行卡");
                        } else if (result.equals("-6")) {
                            ToastUtil.showToast(mActivity, "尚未开户，请先开户！");
                        }
                    }
                });
    }

    private void getBindCard() {
        application.apiService.getBindCard(application.mUser.getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BindCard>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BindCard bindCard) {
                        bankList.clear();
                        bankName.clear();
                        bankNIDs.clear();
                        bankList.addAll(bindCard.getList());
                        for (int i = 0; i < bankList.size(); i++) {
                            bankName.add(bankList.get(i).getBank());
                            bankNIDs.add(bankList.get(i).getId());
                            bankNumber.add(bankList.get(i).getCard_number());
                        }
                        bankOptions.setPicker(bankNumber);
                        bankOptions.setTitle("选择银行卡");
                        bankOptions.setCyclic(false);
                        bankOptions.setSelectOptions(0);
                        tvChooseBank.setText(bankName.get(0) + bankNumber.get(0));
                        bankNID = bankNIDs.get(0);
                        bankOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                bankNID = bankNIDs.get(options1);
                                tvChooseBank.setText(bankName.get(options1) + bankNumber.get(options1));
                            }
                        });
                    }
                });

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
                                etProvince.setText(provinceName.get(options1));
                                cityNID = provinceNIDs.get(options1);
                                getCity(cityNID);
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
                                etCity.setText(cityName.get(options1));
                                countryNID = cityNIDs.get(options1);
                                getCounty(countryNID);
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
        return R.layout.activity_with_draw;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick({R.id.tv_choose_bank, R.id.et_province, R.id.et_city, R.id.et_country, R.id.btn_with_draw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_bank:
                bankOptions.show();
                break;
            case R.id.et_province:
                provinceOptions.show();
                break;
            case R.id.et_city:
                cityOptions.show();
                break;
            case R.id.et_country:
                countryOptions.show();
                break;
            case R.id.btn_with_draw:
                withDraw();
                break;
        }
    }
}

package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.ruidajinfu.Constants;
import com.daiqile.ruidajinfu.MyApplication;
import com.daiqile.ruidajinfu.R;
import com.daiqile.ruidajinfu.base.BaseActivity;
import com.daiqile.ruidajinfu.model.City;
import com.daiqile.ruidajinfu.model.Country;
import com.daiqile.ruidajinfu.model.IsReal;
import com.daiqile.ruidajinfu.model.Province;
import com.daiqile.ruidajinfu.model.RealStatus;
import com.daiqile.ruidajinfu.utils.SoftInputUtil;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/17.
 */

public class RealNameActivity extends BaseActivity {


    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.et_realname)
    EditText etRealname;
    @BindView(R.id.et_id_card)
    EditText etIdCard;
    @BindView(R.id.et_province)
    TextView etProvince;
    @BindView(R.id.et_city)
    TextView etCity;
    @BindView(R.id.et_country)
    TextView etCountry;
    @BindView(R.id.btn_add_card)
    Button btnAddCard;

    private Activity mActivity;
    private MyApplication application;
    OptionsPickerView provinceOptions;
    OptionsPickerView cityOptions;
    OptionsPickerView countryOptions;

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

    @Override
    public void init() {
        mActivity = RealNameActivity.this;
        application = (MyApplication) getApplication();

        provinceOptions = new OptionsPickerView(mActivity);
        cityOptions = new OptionsPickerView(mActivity);
        countryOptions = new OptionsPickerView(mActivity);

        isReal();
        getProvince();
        etCity.setVisibility(View.INVISIBLE);
        etCountry.setVisibility(View.INVISIBLE);
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
        return R.layout.activity_real_name;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick({R.id.et_province, R.id.btn_add_card, R.id.et_city, R.id.et_country})
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.btn_add_card:
                auth();
                break;
        }
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
                                etCountry.setVisibility(View.VISIBLE);
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

    private void isReal() {
        application.apiService.isReal(application.mUser.getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RealStatus>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RealStatus realStatus) {
                        if (realStatus.getReal_status().equals("1")) {
                            etRealname.setText(realStatus.getRealname());
                            etIdCard.setText(realStatus.getCard_id());
                            etProvince.setText(realStatus.getProvince());
                            etCity.setText(realStatus.getCity());
                            etCountry.setText(realStatus.getArea());
                            btnAddCard.setVisibility(View.GONE);
                            etProvince.setEnabled(false);
                            etCountry.setEnabled(false);
                            etCity.setEnabled(false);
                        } else {
                            etRealname.setText("");
                            etIdCard.setText("");
                            etProvince.setText("选择开户地");
                            etCity.setText("");
                            etCountry.setText("");
                            btnAddCard.setVisibility(View.VISIBLE);
                            etProvince.setEnabled(true);
                            etCountry.setEnabled(true);
                            etCity.setEnabled(true);
                        }
                    }
                });
    }

    private void auth() {
        String name = etRealname.getText().toString().trim();
        String card = etIdCard.getText().toString().trim();
        if (name.isEmpty()) {
            ToastUtil.showToast(mActivity, "姓名不能为空");
            return;
        }
        if (card.isEmpty()) {
            ToastUtil.showToast(mActivity, "身份证不能为空");
            return;
        }
        if (countryNID.equals("")) {
            ToastUtil.showToast(mActivity, "开户地不能为空");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("realname", name);
        map.put("card_id", card);
        map.put("area", countryNID);
        map.put("user_id", application.mUser.getId() + "");

        application.apiService.authRealName(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IsReal>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IsReal isReal) {
                        String status = isReal.getReal_status();
                        if (status.equals("0")) {
                            Toast.makeText(mActivity, "实名认证成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("android.action.realname");
                            sendBroadcast(intent);
                            finish();
                        } else if (status.equals("1")) {
                            Toast.makeText(mActivity, "实名认证失败", Toast.LENGTH_SHORT).show();
                        } else if (status.equals("2")) {
                            Toast.makeText(mActivity, "用户ID不正确", Toast.LENGTH_SHORT).show();
                        } else if (status.equals("3")) {
                            Toast.makeText(mActivity, "姓名不正确", Toast.LENGTH_SHORT).show();
                        } else if (status.equals("4")) {
                            Toast.makeText(mActivity, "身份证号不正确", Toast.LENGTH_SHORT).show();
                        } else if (status.equals("5")) {
                            Toast.makeText(mActivity, "身份证号已存在", Toast.LENGTH_SHORT).show();
                        } else if (status.equals("6")) {
                            Toast.makeText(mActivity, "所属地区不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}

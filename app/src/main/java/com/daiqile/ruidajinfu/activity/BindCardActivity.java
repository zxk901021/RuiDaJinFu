package com.daiqile.ruidajinfu.activity;

import android.app.Activity;
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
import com.daiqile.ruidajinfu.model.AddResult;
import com.daiqile.ruidajinfu.model.BankCard;
import com.daiqile.ruidajinfu.model.City;
import com.daiqile.ruidajinfu.model.Country;
import com.daiqile.ruidajinfu.model.Province;
import com.daiqile.ruidajinfu.utils.SoftInputUtil;
import com.daiqile.ruidajinfu.utils.ToastUtil;
import com.daiqile.ruidajinfu.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    private Activity mActivity;
    private MyApplication application;

    OptionsPickerView bankOptions;
    OptionsPickerView provinceOptions;
    OptionsPickerView cityOptions;
    OptionsPickerView countryOptions;

    private ArrayList<String> bankNameList = new ArrayList<>();
    private ArrayList<String> bankIdList = new ArrayList<>();
    private ArrayList<BankCard.CardBean> bankCardList = new ArrayList<>();

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

    @Override
    public void init() {
        mActivity = BindCardActivity.this;
        application = (MyApplication) getApplication();
        provinceOptions = new OptionsPickerView(mActivity);
        cityOptions = new OptionsPickerView(mActivity);
        countryOptions = new OptionsPickerView(mActivity);
        bankOptions = new OptionsPickerView(mActivity);

        etCity.setVisibility(View.INVISIBLE);
        etCountry.setVisibility(View.INVISIBLE);
        getProvince();
        getBankList();

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

    private void getBankList() {
        application.apiService.getBankList("yes")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BankCard>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BankCard bankCard) {
                        bankCardList.clear();
                        bankNameList.clear();
                        bankIdList.clear();
                        bankCardList.addAll(bankCard.getList());
                        for (int i = 0; i < bankCardList.size(); i++) {
                            bankNameList.add(bankCardList.get(i).getName());
                            bankIdList.add(bankCardList.get(i).getId());
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

    private void bindCard() {
        final String account = etCardNumber.getText().toString().trim();
        final String branch = etBranch.getText().toString().trim();

        if (account.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入银行账号");
            return;
        }
        if (branch.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入银行分行");
            return;
        }
        if (bankId.equals("")) {
            ToastUtil.showToast(mActivity, "请选择银行");
            return;
        }
        application.apiService.getBindResult(String.valueOf(application.mUser.getId()), account, bankId, branch, countryNID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddResult addResult) {
                        String result = addResult.getCash_status();
                        if (result.equals("1")) {
                            ToastUtil.showToast(mActivity, "银行卡绑定成功");
                            finish();
                        } else if (result.equals("2")) {
                            ToastUtil.showToast(mActivity, "银行卡绑定失败");
                        } else if (result.equals("-1")) {
                            ToastUtil.showToast(mActivity, "请先实名认证");
                        }else if(result.equals("3")){
                            ToastUtil.showToast(mActivity,"请正确选择开户地");
                        }else if (result.equals("4")){
                            ToastUtil.showToast(mActivity,"请将信息填写完整");
                        }
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


    @OnClick({R.id.tv_choose_bank, R.id.btn_add_card, R.id.et_province, R.id.et_city, R.id.et_country})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_bank:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, tvChooseBank);
                }
                bankOptions.show();
                break;
            case R.id.btn_add_card:
                bindCard();
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
        }
    }
}

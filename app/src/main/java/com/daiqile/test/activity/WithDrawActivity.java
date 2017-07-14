package com.daiqile.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.http.HttpPostUtil;
import com.daiqile.test.model.BindCard;
import com.daiqile.test.model.City;
import com.daiqile.test.model.Country;
import com.daiqile.test.model.Province;
import com.daiqile.test.utils.ToastUtil;
import com.daiqile.test.view.TopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
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
    private String result = "";

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
//        String password = etPassword.getText().toString().trim();
        if (money.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入提现金额");
            return;
        }
//        if (password.isEmpty()) {
//            ToastUtil.showToast(mActivity, "请输入支付密码");
//            return;
//        }
//        btnWithDraw.setEnabled(false);
        String url = Constants.BASE_URL + "userCashNew.php?dcode=7d5372bbcd6cc79f1bd71211f092622e&user_id="
                + application.mUser.getId() + "&money=" + money + "&bank_id=" + bankNID;
        Intent intent = new Intent(mActivity, WithDrawResultActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);


    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    try {
                        JSONObject object = new JSONObject(result);
                        String code = object.getString("status");
                        if (code.equals("1")){
                            bankList.clear();
                            bankName.clear();
                            bankNIDs.clear();
                            JSONArray arr = object.getJSONArray("list");
                            if (arr != null && arr.length() > 0){
                                for (int i = 0; i < arr.length(); i ++){
                                    JSONObject bank = arr.getJSONObject(i);
                                    BindCard.BankCardBean bean = new BindCard.BankCardBean();
                                    bean.setId(bank.getString("id"));
                                    bean.setBank(bank.getString("bank"));
                                    bean.setBranch(bank.getString("branch"));
                                    bean.setCard_number(bank.getString("card_number"));
                                    bankList.add(bean);
                                    bankName.add(bean.getBank());
                                    bankNIDs.add(bean.getId());
                                    bankNumber.add(bean.getCard_number());
                                }
                            }
                            bankOptions.setPicker(bankName);
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    private void getBindCard() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = HttpPostUtil.requesPost(application.mUser.getId() + "");
                    handler.sendEmptyMessage(1);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        th.start();
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


    public static void requesPost() throws Throwable{
        String path = "http://test2.zjrdjr.com/port/getUserBankList.php";
        // 请求的参数转换为byte数组
        String params = "dcode=" + URLEncoder.encode("7d5372bbcd6cc79f1bd71211f092622e", "UTF-8")
                + "&user_id=" + URLEncoder.encode("336", "UTF-8");
        byte[] postData = params.getBytes();
        // 新建一个URL对象
        URL url = new URL(path);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(5 * 1000);
        // Post请求必须设置允许输出
        urlConn.setDoOutput(true);
        // Post请求不能使用缓存
        urlConn.setUseCaches(false);
        // 设置为Post请求
        urlConn.setRequestMethod("POST");
        urlConn.setInstanceFollowRedirects(true);
        // 配置请求Content-Type
        urlConn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        // 开始连接
        urlConn.connect();
        // 发送请求参数
        DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
        dos.write(postData);
        dos.flush();
        dos.close();
        // 判断请求是否成功
        if (urlConn.getResponseCode() == 200) {
            // 获取返回的数据
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            InputStream is = urlConn.getInputStream();
            byte[] buffer = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buffer, 0, 1024)) > 0){
                bao.write(buffer, 0, rc);
            }
            byte[] data = bao.toByteArray();
            System.out.println(new String(data, "UTF-8"));

        } else {

        }
    }

}

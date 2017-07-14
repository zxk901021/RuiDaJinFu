package com.daiqile.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiqile.test.AppConfig;
import com.daiqile.test.Constants;
import com.daiqile.test.MyApplication;
import com.daiqile.test.R;
import com.daiqile.test.adapter.RegisterFragmentPagerAdapter;
import com.daiqile.test.base.BaseActivity;
import com.daiqile.test.fragment.RegisterFirstFragment;
import com.daiqile.test.fragment.RegisterFourthFragment;
import com.daiqile.test.fragment.RegisterSecondFragment;
import com.daiqile.test.fragment.RegisterThirdFragment;
import com.daiqile.test.model.Register;
import com.daiqile.test.model.RegisterResponse;
import com.daiqile.test.model.User;
import com.daiqile.test.utils.PhoneFormatCheckUtils;
import com.daiqile.test.utils.ToastUtil;
import com.daiqile.test.view.NoScrollViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/2.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.recomment_code)
    EditText recommendCode;
    @BindView(R.id.register_pager)
    NoScrollViewPager pager;
    @BindView(R.id.register_progress)
    ImageView progress;

    private RegisterFirstFragment first;
    private RegisterSecondFragment second;
    private RegisterThirdFragment third;
    private RegisterFourthFragment fourth;
    private RegisterFragmentPagerAdapter adapter;
    private List<Fragment> fragments;

    private Activity mActivity;
    private MyApplication application = null;

    private Map<String, String> params;
    private String user_id;
    private int index;
    private String recomCode;

    @Override
    public void init() {
        Intent intent = getIntent();
        recomCode = intent.getStringExtra("code");
        index = intent.getIntExtra("index", -1);

        first = new RegisterFirstFragment();
        second = new RegisterSecondFragment();
        third = new RegisterThirdFragment();

        first.setInviteCode(recomCode);

        fragments = new ArrayList<>();
        fragments.add(first);
        fragments.add(second);
        fragments.add(third);
        adapter = new RegisterFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
        pager.setNoScroll(true);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1){
                    progress.setImageResource(R.drawable.register_progress_2);
                } else if (position == 2){
                    progress.setImageResource(R.drawable.register_progress_3);
                } else if (position == 0){
                    progress.setImageResource(R.drawable.register_progress_1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (index != -1) {
            pager.setCurrentItem(index);
        }

        if (!TextUtils.isEmpty(recomCode)) {
            recommendCode.setText(recomCode);
        }
        mActivity = RegisterActivity.this;
        application = (MyApplication) getApplication();
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 11 && PhoneFormatCheckUtils.isPhoneLegal(s.toString())) {
                } else {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    private void register(final String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("dcode", Constants.DCODE);
        map.put("username", username);
        map.put("password", password);
        if (!TextUtils.isEmpty(recomCode)) {
            map.put("invite_userid", recomCode);
        }
        showProgress();
        application.apiService.register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Register>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("registerFirst", e.getMessage());
                    }

                    @Override
                    public void onNext(Register register) {
                        dismissProgress();
                        String status = register.getStatus();
                        if (status.equals("1")) {
                            ToastUtil.showToast(mActivity, "注册成功,请进行下一步操作！");
                            user_id = register.getUser_id();
                            User.UserInfo info = new User.UserInfo();
                            info.setId(Integer.valueOf(user_id));
                            application.updateLoginParams(info);
                            second.setPhoneData(username);
                            pager.setCurrentItem(1);
                            getVerify();
                        } else if (status.equals("2")) {
                            ToastUtil.showToast(mActivity, "手机号格式不正确");
                        } else if (status.equals("3")) {
                            ToastUtil.showToast(mActivity, "手机号已经存在");
                        } else if (status.equals("4")) {
                            ToastUtil.showToast(mActivity, "密码格式不正确");
                        } else if (status.equals("9")) {
                            ToastUtil.showToast(mActivity, "注册失败");
                        }
                    }
                });
    }

    @OnClick({R.id.btn_register, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (pager.getCurrentItem() == 0) {
                    params = first.getEditString();
                    if (params.get("isEmpty").equals("1")) {
                        ToastUtil.showToast(this, "信息填写不完整！");
                        return;
                    } else {
                        String username = params.get("username");
                        String password = params.get("password");
                        register(username, password);
                    }
                } else if (pager.getCurrentItem() == 1) {
                    params = second.getEditString();
                    if (params.get("isEmpty").equals("1")) {
                        ToastUtil.showToast(this, "请输入验证码！");
                        return;
                    } else {
                        String verifyCode = params.get("verifyCode");
                        verify(verifyCode);
                    }

                } else if (pager.getCurrentItem() == 2) {
                    params = third.getEditString();
                    if (params.get("isEmpty").equals("1")) {
                        ToastUtil.showToast(this, "请完善个人信息！");
                    } else {
                        String name = params.get("name");
                        String idNumber = params.get("idNumber");
                        checkIn(name, idNumber);
                    }
                }
                break;
            case R.id.tv_login:
                finish();
                break;
        }
    }

    private void getVerify() {
        Map<String, String> map = new HashMap<>();
        map.put("dcode", Constants.DCODE);
        if (!TextUtils.isEmpty(user_id)) {
            map.put("user_id", user_id);
        } else {
            user_id = String.valueOf(application.getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE).getInt("id", 0));
            map.put("user_id", user_id);
        }
        map.put("verificationCodeType", "9");
        showProgress();
        application.apiService.sendVerify(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegisterResponse register) {
                        dismissProgress();
                        String status = register.getStatus();
                        if (status.equals("1")) {
                            ToastUtil.showToast(mActivity, "手机验证码发送成功");
                        } else if (status.equals("2")) {
                            ToastUtil.showToast(mActivity, "手机验证码发送失败");
                        }
                    }
                });
    }

    private void verify(String verifyCode) {
        Map<String, String> map = new HashMap<>();
        map.put("dcode", Constants.DCODE);
        map.put("user_id", String.valueOf(application.getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE).getInt("id", 0)));
        map.put("verificationCode", verifyCode);
        showProgress();
        application.apiService.verify(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegisterResponse register) {
                        dismissProgress();
                        String status = register.getStatus();
                        if (status.equals("1")) {
                            ToastUtil.showToast(mActivity, "手机号验证成功，请进行下一步操作");
                            pager.setCurrentItem(2);
                        } else if (status.equals("2")) {
                            ToastUtil.showToast(mActivity, "您已经绑定过手机");
                        } else if (status.equals("3")) {
                            ToastUtil.showToast(mActivity, "验证码错误");
                        } else if (status.equals("9")) {
                            ToastUtil.showToast(mActivity, "手机号验证失败");
                        }
                    }
                });
    }

    private void checkIn(String name, String idNumber) {
        final Map<String, String> map = new HashMap<>();
        map.put("dcode", Constants.DCODE);
        map.put("user_id", String.valueOf(application.getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE).getInt("id", 0)));
        map.put("realname", name);
        map.put("card_id", idNumber);
        showProgress();
        application.apiService.checkIn(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("idcard", e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterResponse register) {
                        dismissProgress();
                        String status = register.getStatus();
                        if (status.equals("1")) {
                            ToastUtil.showToast(mActivity, "实名认证成功，请设置交易密码");
                            Intent intent = new Intent(mActivity, WebViewActivity.class);
                            intent.putExtra("mode", 10);
                            intent.putExtra("title", "设置支付密码");
                            intent.putExtra("url", "http://test2.zjrdjr.com/port/userSetPayPwd.php?dcode=7d5372bbcd6cc79f1bd71211f092622e&user_id="
                                    + application.mUser.getId());
                            startActivity(intent);
                            finish();
                        } else if (status.equals("2")) {
                            ToastUtil.showToast(mActivity, "请将资料填写全");
                        } else if (status.equals("3")) {
                            ToastUtil.showToast(mActivity, "您已经实名认证通过了，请不要重复认证");
                        } else if (status.equals("4")) {
                            ToastUtil.showToast(mActivity, "身份证号码格式不正确");
                        } else if (status.equals("5")) {
                            ToastUtil.showToast(mActivity, "身份证号码已经存在");
                        } else if (status.equals("6")) {
                            ToastUtil.showToast(mActivity, "姓名与身份证号不符");
                        } else if (status.equals("9")) {
                            ToastUtil.showToast(mActivity, "实名认证失败");
                        }
                    }
                });
    }

    private void getCaptcha() {
        if (etPhoneNumber.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(mActivity, "请输入手机号！");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("nub", etPhoneNumber.getText().toString().trim());
        map.put("phonetype", "1");
        application.apiService.getCaptcha(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            JSONObject object = new JSONObject(responseBody.string());
                            String result = object.getString("status");
                            if (result.equals("1")) {
                                ToastUtil.showToast(mActivity, "验证码发送成功，请查收！");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


}

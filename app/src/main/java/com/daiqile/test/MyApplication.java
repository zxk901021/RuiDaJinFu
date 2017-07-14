package com.daiqile.test;

import android.app.Application;
import android.content.SharedPreferences;

import com.daiqile.test.http.ApiService;
import com.daiqile.test.http.RetrofitClient;
import com.daiqile.test.model.User;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import retrofit2.Retrofit;

/**
 * Created by orgwcl on 2016/9/2.
 */
public class MyApplication extends Application {
    public User.UserInfo mUser = null;
    public boolean mRememberPassword = false;
    public boolean isFirstStart = true;
    public SharedPreferences mSharedPreferences = null;
    public ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE);
        initLoginParams();
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
        ZXingLibrary.initDisplayOpinion(this);
    }

    /**
     * 上次登录信息
     */
    private void initLoginParams() {
        SharedPreferences preferences = getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE);
        Integer id = preferences.getInt(Constants.USERID, 0);
        String userName = preferences.getString(Constants.USERNAMECOOKIE, null);
        String userPwd = preferences.getString(Constants.USERPASSWORDCOOKIE, null);
        String phone = preferences.getString(Constants.PHONECOOKIE, null);
        Boolean userRemember = preferences.getBoolean(Constants.USERREMEMBERCOOKIE, false);
        isFirstStart = preferences.getBoolean(Constants.ISFIRSTSTART, true);
        if (userName != null) {
            mUser = new User.UserInfo();
            mUser.setId(id);
            mUser.setUsername(userName);
            mUser.setPassword(userPwd);
            mRememberPassword = userRemember;
        }
    }

    /**
     * 更新登录信息
     * @param user
     */
    public void updateLoginParams(User.UserInfo user) {
        mUser = user;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (mRememberPassword) {
            editor.putInt(Constants.USERID, user.getId());
            editor.putString(Constants.USERNAMECOOKIE, user.getUsername());
            editor.putString(Constants.USERPASSWORDCOOKIE, user.getPassword());
            editor.putBoolean(Constants.ISFIRSTSTART, false);
        } else {
            editor.putInt(Constants.USERID, user.getId());
            editor.putString(Constants.USERNAMECOOKIE, user.getUsername());
            editor.putBoolean(Constants.ISFIRSTSTART, false);
        }
        editor.commit();
        isFirstStart = false;
    }

    /**
     * 清空登录信息
     */
    public void clearLoginParams() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
        mUser = null;
    }
}

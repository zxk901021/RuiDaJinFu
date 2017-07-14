package com.daiqile.test.http;

import com.daiqile.test.model.AccountCenter;
import com.daiqile.test.model.AddResult;
import com.daiqile.test.model.BankCard;
import com.daiqile.test.model.BankCardBean;
import com.daiqile.test.model.Banner;
import com.daiqile.test.model.BindBank;
import com.daiqile.test.model.BindCard;
import com.daiqile.test.model.BindResult;
import com.daiqile.test.model.CashRecord;
import com.daiqile.test.model.CashStatus;
import com.daiqile.test.model.City;
import com.daiqile.test.model.Country;
import com.daiqile.test.model.Invest;
import com.daiqile.test.model.InvestRecord;
import com.daiqile.test.model.Invite;
import com.daiqile.test.model.IsReal;
import com.daiqile.test.model.MoneyRecord;
import com.daiqile.test.model.Notice;
import com.daiqile.test.model.PasswordResult;
import com.daiqile.test.model.PayPassword;
import com.daiqile.test.model.Project;
import com.daiqile.test.model.Province;
import com.daiqile.test.model.RealStatus;
import com.daiqile.test.model.RechargeModel;
import com.daiqile.test.model.RechargeRecord;
import com.daiqile.test.model.RechargeResult;
import com.daiqile.test.model.Register;
import com.daiqile.test.model.RegisterResponse;
import com.daiqile.test.model.Repayment;
import com.daiqile.test.model.TenderLog;
import com.daiqile.test.model.TenderResult;
import com.daiqile.test.model.UsersInfo;
import com.daiqile.test.model.YiMaDai;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;


public interface ApiService {

    //注册手机验证码
    @POST("http://www.zjrdjr.com/core/phone_ajax56.php")
    Observable<ResponseBody> getCaptcha(@QueryMap Map<String, String> map);

    //找回密码验证码
    @POST("getPwd.php")
    Observable<ResponseBody> getCode(@QueryMap Map<String, String> map);

    //登录
    @FormUrlEncoded
    @POST("login.php")
    Observable<ResponseBody> login(@Field("username") String username, @Field("password") String password, @Field("dcode") String dcode);


    //找回密码
    @GET("getPwdPhone.php")
    Observable<ResponseBody> findPwd(@QueryMap Map<String, String> map);

    //注册
    @FormUrlEncoded
    @POST("userReg.php")
    Observable<Register> register(@FieldMap Map<String, String> map);

    //发送验证码
    @FormUrlEncoded
    @POST("sendJinchengVerificationCode.php")
    Observable<RegisterResponse> sendVerify(@FieldMap Map<String, String> map);

    //验证验证码
    @FormUrlEncoded
    @POST("userPhone.php")
    Observable<RegisterResponse> verify(@FieldMap Map<String, String> map);

    //身份信息提交
    @FormUrlEncoded
    @POST("userRealname.php")
    Observable<RegisterResponse> checkIn(@FieldMap Map<String, String> map);

    //网站公告
    @FormUrlEncoded
    @POST("getArticle.php")
    Observable<Notice> notice(@FieldMap Map<String, String> map);

    //标列表
    @POST("getBorrowList.php")
    Observable<List<Invest>> bid(@QueryMap Map<String, String> map);

    //标的详情
    @POST("getBorrowContent.php")
    Observable<ResponseBody> getContent(@QueryMap Map<String, String> map);

    //还款详情
    @POST("getRepaymentDetail.php")
    Observable<Repayment> getRepayment(@QueryMap Map<String, String> map);

    //投资记录
    @POST("getTenderLogList.php")
    Observable<TenderLog> getTenderLog(@QueryMap Map<String, String> map);

    //资金记录
    @POST("getAccountLog.php")
    Observable<List<MoneyRecord>> getMoneyRecord(@QueryMap Map<String, String> map);

    //充值记录
    @POST("getAccoutRecharge.php")
    Observable<RechargeRecord> getRechargeRecord(@QueryMap Map<String, String> map);

    //提现记录
    @POST("getCashList.php")
    Observable<CashRecord> getCashRecord(@QueryMap Map<String, String> map);

    //投标记录
    @POST("getUserInvestList.php")
    Observable<InvestRecord> getInvestRecord(@QueryMap Map<String, String> map);

    //投标中项目
    @POST("getUserInvestList.php")
    Observable<Project> getInvestingRecord(@QueryMap Map<String, String> map);

    //密码修改
    @POST("setPassword.php")
    Observable<PasswordResult> getPasswordResult(@QueryMap Map<String, String> map);

    //交易密码修改
    @POST("setPayPassword.php")
    Observable<PayPassword> getPayPasswordResult(@QueryMap Map<String, String> map);

    //账户中心
    @POST("getUserLog.php")
    Observable<AccountCenter> getAccountCenter(@QueryMap Map<String, String> map);

    //获取省列表
    @POST("getAreaList.php")
    Observable<Province> getProvinceList(@QueryMap Map<String, String> map);

    //获取市列表
    @POST("getAreaList.php")
    Observable<City> getCityList(@QueryMap Map<String, String> map);

    //获取区列表
    @POST("getAreaList.php")
    Observable<Country> getCountryList(@QueryMap Map<String, String> map);

    //实名认证
    @POST("regRealName.php")
    Observable<IsReal> authRealName(@QueryMap Map<String, String> map);

    //是否已实名认证
    @FormUrlEncoded
    @POST("readrealname.php")
    Observable<RealStatus> isReal(@Field("user_id") String user_id);

    //是否已绑定银行卡
    @POST("getUserBankList.php")
    Observable<ResponseBody> isBind(@QueryMap Map<String, String> map);

    //已绑定银行卡列表
    @FormUrlEncoded
    @POST("getUserBankList.php")
    Observable<BindCard> getBindCard(@Field("user_id") String user_id);

    //获取银行卡列表
    @GET("getBankList.php")
    Observable<BankCard> getBankList();

    //获取银行卡列表
    @GET("getBankList.php")
    Observable<List<BankCardBean>> getBankList2();

    //绑定银行卡
    @FormUrlEncoded
    @POST("bankAdd.php")
    Observable<AddResult> getBindResult(@Field("user_id") String user_id, @Field("account") String account, @Field("bank") String bank, @Field("branch") String branch, @Field("area") String area);

    //绑定银行卡
    @FormUrlEncoded
    @POST("userBank.php")
    Observable<BindResult> bindUserBank(@Field("user_id") String user_id, @Field("account") String account, @Field("bank") String bank, @Field("dcode") String dcode);

    //解绑银行卡
    @FormUrlEncoded
    @POST("userBank.php")
    Observable<BindResult> unBindUserBank(@Field("dcode") String dcode, @Field("delid") String delid, @Field("user_id") String userId);

    //投标
    @POST("userTender.php")
    Observable<TenderResult> getTenderResult(@QueryMap Map<String, String> map);

    //删除银行卡
    @POST("removeUserBank.php")
    Observable<ResponseBody> removeCard(@QueryMap Map<String, String> map);

    //提现
    @FormUrlEncoded
    @POST("userCashNew.php")
    Observable<CashStatus> withDraw(@Field("user_id") String user_id, @Field("bank_id") String bank_id, @Field("paypassword") String paypassword, @Field("money") String money);

    //判断是否已开户
    @FormUrlEncoded
    @POST("readRealYimadai.php")
    Observable<YiMaDai> isRealYimadai(@Field("user_id") String user_id);

    //轮播图
    @POST("getBannerList.php")
    Observable<Banner> getBannerList();

    @POST("userInvite.php")
    Observable<Invite> getInviteUrl(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("getUser.php")
    Observable<UsersInfo> getUser(@Field("user_id") String userId, @Field("dcode") String dcode);

    @FormUrlEncoded
    @POST("userBank.php?type=code")
    Observable<BindBank> getVerifyCode(@Field("dcode") String dcode, @Field("user_id") String userId, @Field("account") String account);

    @FormUrlEncoded
    @POST("userBank.php?type=card")
    Observable<BindResult> bindBankCard(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("userRecharge.php?type=apply")
    Observable<RechargeModel> getOrderNo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("userRecharge.php?type=pay")
    Observable<RechargeResult> getRechargeResult(@FieldMap Map<String, String> params);

}


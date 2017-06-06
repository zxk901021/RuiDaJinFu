package com.daiqile.ruidajinfu.http;

import com.daiqile.ruidajinfu.model.AccountCenter;
import com.daiqile.ruidajinfu.model.AddResult;
import com.daiqile.ruidajinfu.model.BankCard;
import com.daiqile.ruidajinfu.model.Banner;
import com.daiqile.ruidajinfu.model.BindCard;
import com.daiqile.ruidajinfu.model.CashRecord;
import com.daiqile.ruidajinfu.model.CashStatus;
import com.daiqile.ruidajinfu.model.City;
import com.daiqile.ruidajinfu.model.Country;
import com.daiqile.ruidajinfu.model.Invest;
import com.daiqile.ruidajinfu.model.InvestRecord;
import com.daiqile.ruidajinfu.model.IsReal;
import com.daiqile.ruidajinfu.model.MoneyRecord;
import com.daiqile.ruidajinfu.model.Notice;
import com.daiqile.ruidajinfu.model.PasswordResult;
import com.daiqile.ruidajinfu.model.PayPassword;
import com.daiqile.ruidajinfu.model.Project;
import com.daiqile.ruidajinfu.model.Province;
import com.daiqile.ruidajinfu.model.RealStatus;
import com.daiqile.ruidajinfu.model.RechargeRecord;
import com.daiqile.ruidajinfu.model.Register;
import com.daiqile.ruidajinfu.model.Repayment;
import com.daiqile.ruidajinfu.model.TenderLog;
import com.daiqile.ruidajinfu.model.TenderResult;
import com.daiqile.ruidajinfu.model.YiMaDai;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
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
    @POST("reg.php")
    Observable<Register> register(@QueryMap Map<String, String> map);

    //网站公告
    @POST("getArticle.php")
    Observable<Notice> notice(@QueryMap Map<String, String> map);

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
    @FormUrlEncoded
    @POST("bankAdd.php")
    Observable<BankCard> getBankList(@Field("getallbank") String data);


    //绑定银行卡
    @FormUrlEncoded
    @POST("bankAdd.php")
    Observable<AddResult> getBindResult(@Field("user_id") String user_id, @Field("account") String account, @Field("bank") String bank, @Field("branch") String branch, @Field("area") String area);

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

}


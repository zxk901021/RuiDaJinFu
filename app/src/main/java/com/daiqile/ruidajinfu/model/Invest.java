package com.daiqile.ruidajinfu.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/2.
 */

public class Invest implements Serializable{

    /**
     * scale : 100
     * other : 0
     * scale_width : 140
     * repayment_noaccount : 40267.5
     * lave_time : -1580506
     * r_months1 : 2
     * vouch_scale : 0
     * vouch_other : 39000
     * vouchscale_width : 0
     * id : 9
     * site_id : 0
     * user_id : 11
     * name : [天津]生意周转
     * status : 3
     * dispaly : 1
     * order : 0
     * hits : 0
     * litpic :
     * flag : 0
     * is_vouch : 0
     * vouch_award : 0.00
     * vouch_user : 0
     * vouch_account : 0.00
     * vouch_times : 0
     * verify_user :
     * verify_time : 1478829716
     * verify_remark :
     * repayment_user : 1
     * forst_account : 0.00
     * repayment_account : 40267.50
     * monthly_repayment : 422.50
     * repayment_yesaccount : 0.00
     * repayment_yesinterest : 0.00
     * repayment_time : 1478849526
     * repayment_remark : 通过
     * success_time : 1478849526
     * end_time : 1486798326
     * payment_account : 39422.50
     * each_time : 每月11日
     * use : 245
     * time_limit : 3
     * style : 3
     * account : 39000.00
     * account_yes : 39000.00
     * tender_times : 1
     * apr : 13.00
     * lowest_account : 100
     * most_account : 0
     * valid_time : 3
     * award : 0
     * part_account : 0.00
     * funds : 0.00
     * fundsadd : 0.00
     * is_false :
     * content :
     * addtime : 1478585334
     * addip : 125.38.47.55
     * is_mb : 0
     * is_xyb : 0
     * is_fast : 1
     * is_jin : 0
     * cz_old_id : 0
     * jiaoyan : 0
     * is_cz : 0
     * is_dxb : 0
     * pwd :
     * isday : 0
     * time_limit_day : 1
     * old_loanee :
     * old_date :
     * old_time : 0
     * old_apr : 0
     * apr_add : 0.0
     * lowest_months : 0
     * add_months : 0
     * per_account : 0
     * max_nums : 0
     * is_liuzhuan : 0
     * buy_times : 0
     * r_nums : -1
     * r_months : 0
     * jiang : 0
     * istyhk : 0
     * is_dsm : 0
     * is_dan : 0
     * vouch_organ :
     * auto_rate : 0.00
     * lowest_waitaccount : 0
     * bsid : 0
     * fabu_time : 1478829600
     * is_jian : 0
     * is_new : 0
     * is_privacy :
     * borrowmanual_id : 10
     * isqiye :
     * fastid :
     * username : wanglijie
     * user_area : 31
     * kefu_username :
     * qq :
     * credit_jifen : 39020
     * credit_pic : credit_s45.gif
     * add_area :
     * scales : 1.000000
     */

    private int scale;
    private String other;
    private String scale_width;
    private String repayment_noaccount;
    private String lave_time;
    private String r_months1;
    private String vouch_scale;
    private String vouch_other;
    private String vouchscale_width;
    private String id;
    private String site_id;
    private String user_id;
    private String name;
    private String status;
    private String dispaly;
    private String order;
    private String hits;
    private String litpic;
    private String flag;
    private String is_vouch;
    private String vouch_award;
    private String vouch_user;
    private String vouch_account;
    private String vouch_times;
    private String verify_user;
    private String verify_time;
    private String verify_remark;
    private String repayment_user;
    private String forst_account;
    private String repayment_account;
    private String monthly_repayment;
    private String repayment_yesaccount;
    private String repayment_yesinterest;
    private String repayment_time;
    private String repayment_remark;
    private String success_time;
    private String end_time;
    private String payment_account;
    private String each_time;
    private String use;
    private String time_limit;
    private int style;
    private float account;
    private float account_yes;
    private double use_learn_money;
    private String tender_times;
    private String apr;
    private String lowest_account;
    private String most_account;
    private String valid_time;
    private String award;
    private String part_account;
    private String funds;
    private String fundsadd;
    private String is_false;
    private String content;
    private long addtime;
    private String addip;
    private String is_mb;
    private String is_xyb;
    private String is_fast;
    private String is_jin;
    private String cz_old_id;
    private String jiaoyan;
    private String is_cz;
    private String is_dxb;
    private String pwd;
    private String isday;
    private String time_limit_day;
    private String old_loanee;
    private String old_date;
    private String old_time;
    private String old_apr;
    private String apr_add;
    private String lowest_months;
    private String add_months;
    private String per_account;
    private String max_nums;
    private String is_liuzhuan;
    private String buy_times;
    private String r_nums;
    private String r_months;
    private String jiang;
    private String istyhk;
    private String is_dsm;
    private String is_dan;
    private String vouch_organ;
    private String auto_rate;
    private String lowest_waitaccount;
    private String bsid;
    private String fabu_time;
    private String is_jian;
    private String is_new;
    private String is_privacy;
    private String borrowmanual_id;
    private String isqiye;
    private String fastid;
    private String username;
    private String user_area;
    private String kefu_username;
    private String qq;
    private String credit_jifen;
    private String credit_pic;
    private String add_area;
    private String scales;
    private String is_learn;

    public String getIs_learn() {
        return is_learn;
    }

    public void setIs_learn(String is_learn) {
        this.is_learn = is_learn;
    }

    public double getUse_learn_money() {
        return use_learn_money;
    }

    public void setUse_learn_money(double use_learn_money) {
        this.use_learn_money = use_learn_money;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getScale_width() {
        return scale_width;
    }

    public void setScale_width(String scale_width) {
        this.scale_width = scale_width;
    }

    public String getRepayment_noaccount() {
        return repayment_noaccount;
    }

    public void setRepayment_noaccount(String repayment_noaccount) {
        this.repayment_noaccount = repayment_noaccount;
    }

    public String getLave_time() {
        return lave_time;
    }

    public void setLave_time(String lave_time) {
        this.lave_time = lave_time;
    }

    public String getR_months1() {
        return r_months1;
    }

    public void setR_months1(String r_months1) {
        this.r_months1 = r_months1;
    }

    public String getVouch_scale() {
        return vouch_scale;
    }

    public void setVouch_scale(String vouch_scale) {
        this.vouch_scale = vouch_scale;
    }

    public String getVouch_other() {
        return vouch_other;
    }

    public void setVouch_other(String vouch_other) {
        this.vouch_other = vouch_other;
    }

    public String getVouchscale_width() {
        return vouchscale_width;
    }

    public void setVouchscale_width(String vouchscale_width) {
        this.vouchscale_width = vouchscale_width;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDispaly() {
        return dispaly;
    }

    public void setDispaly(String dispaly) {
        this.dispaly = dispaly;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIs_vouch() {
        return is_vouch;
    }

    public void setIs_vouch(String is_vouch) {
        this.is_vouch = is_vouch;
    }

    public String getVouch_award() {
        return vouch_award;
    }

    public void setVouch_award(String vouch_award) {
        this.vouch_award = vouch_award;
    }

    public String getVouch_user() {
        return vouch_user;
    }

    public void setVouch_user(String vouch_user) {
        this.vouch_user = vouch_user;
    }

    public String getVouch_account() {
        return vouch_account;
    }

    public void setVouch_account(String vouch_account) {
        this.vouch_account = vouch_account;
    }

    public String getVouch_times() {
        return vouch_times;
    }

    public void setVouch_times(String vouch_times) {
        this.vouch_times = vouch_times;
    }

    public String getVerify_user() {
        return verify_user;
    }

    public void setVerify_user(String verify_user) {
        this.verify_user = verify_user;
    }

    public String getVerify_time() {
        return verify_time;
    }

    public void setVerify_time(String verify_time) {
        this.verify_time = verify_time;
    }

    public String getVerify_remark() {
        return verify_remark;
    }

    public void setVerify_remark(String verify_remark) {
        this.verify_remark = verify_remark;
    }

    public String getRepayment_user() {
        return repayment_user;
    }

    public void setRepayment_user(String repayment_user) {
        this.repayment_user = repayment_user;
    }

    public String getForst_account() {
        return forst_account;
    }

    public void setForst_account(String forst_account) {
        this.forst_account = forst_account;
    }

    public String getRepayment_account() {
        return repayment_account;
    }

    public void setRepayment_account(String repayment_account) {
        this.repayment_account = repayment_account;
    }

    public String getMonthly_repayment() {
        return monthly_repayment;
    }

    public void setMonthly_repayment(String monthly_repayment) {
        this.monthly_repayment = monthly_repayment;
    }

    public String getRepayment_yesaccount() {
        return repayment_yesaccount;
    }

    public void setRepayment_yesaccount(String repayment_yesaccount) {
        this.repayment_yesaccount = repayment_yesaccount;
    }

    public String getRepayment_yesinterest() {
        return repayment_yesinterest;
    }

    public void setRepayment_yesinterest(String repayment_yesinterest) {
        this.repayment_yesinterest = repayment_yesinterest;
    }

    public String getRepayment_time() {
        return repayment_time;
    }

    public void setRepayment_time(String repayment_time) {
        this.repayment_time = repayment_time;
    }

    public String getRepayment_remark() {
        return repayment_remark;
    }

    public void setRepayment_remark(String repayment_remark) {
        this.repayment_remark = repayment_remark;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public void setSuccess_time(String success_time) {
        this.success_time = success_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPayment_account() {
        return payment_account;
    }

    public void setPayment_account(String payment_account) {
        this.payment_account = payment_account;
    }

    public String getEach_time() {
        return each_time;
    }

    public void setEach_time(String each_time) {
        this.each_time = each_time;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(String time_limit) {
        this.time_limit = time_limit;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    public float getAccount_yes() {
        return account_yes;
    }

    public void setAccount_yes(float account_yes) {
        this.account_yes = account_yes;
    }

    public String getTender_times() {
        return tender_times;
    }

    public void setTender_times(String tender_times) {
        this.tender_times = tender_times;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getLowest_account() {
        return lowest_account;
    }

    public void setLowest_account(String lowest_account) {
        this.lowest_account = lowest_account;
    }

    public String getMost_account() {
        return most_account;
    }

    public void setMost_account(String most_account) {
        this.most_account = most_account;
    }

    public String getValid_time() {
        return valid_time;
    }

    public void setValid_time(String valid_time) {
        this.valid_time = valid_time;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getPart_account() {
        return part_account;
    }

    public void setPart_account(String part_account) {
        this.part_account = part_account;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public String getFundsadd() {
        return fundsadd;
    }

    public void setFundsadd(String fundsadd) {
        this.fundsadd = fundsadd;
    }

    public String getIs_false() {
        return is_false;
    }

    public void setIs_false(String is_false) {
        this.is_false = is_false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAddtime() {
        return addtime;
    }

    public void setAddtime(long addtime) {
        this.addtime = addtime;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip;
    }

    public String getIs_mb() {
        return is_mb;
    }

    public void setIs_mb(String is_mb) {
        this.is_mb = is_mb;
    }

    public String getIs_xyb() {
        return is_xyb;
    }

    public void setIs_xyb(String is_xyb) {
        this.is_xyb = is_xyb;
    }

    public String getIs_fast() {
        return is_fast;
    }

    public void setIs_fast(String is_fast) {
        this.is_fast = is_fast;
    }

    public String getIs_jin() {
        return is_jin;
    }

    public void setIs_jin(String is_jin) {
        this.is_jin = is_jin;
    }

    public String getCz_old_id() {
        return cz_old_id;
    }

    public void setCz_old_id(String cz_old_id) {
        this.cz_old_id = cz_old_id;
    }

    public String getJiaoyan() {
        return jiaoyan;
    }

    public void setJiaoyan(String jiaoyan) {
        this.jiaoyan = jiaoyan;
    }

    public String getIs_cz() {
        return is_cz;
    }

    public void setIs_cz(String is_cz) {
        this.is_cz = is_cz;
    }

    public String getIs_dxb() {
        return is_dxb;
    }

    public void setIs_dxb(String is_dxb) {
        this.is_dxb = is_dxb;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getIsday() {
        return isday;
    }

    public void setIsday(String isday) {
        this.isday = isday;
    }

    public String getTime_limit_day() {
        return time_limit_day;
    }

    public void setTime_limit_day(String time_limit_day) {
        this.time_limit_day = time_limit_day;
    }

    public String getOld_loanee() {
        return old_loanee;
    }

    public void setOld_loanee(String old_loanee) {
        this.old_loanee = old_loanee;
    }

    public String getOld_date() {
        return old_date;
    }

    public void setOld_date(String old_date) {
        this.old_date = old_date;
    }

    public String getOld_time() {
        return old_time;
    }

    public void setOld_time(String old_time) {
        this.old_time = old_time;
    }

    public String getOld_apr() {
        return old_apr;
    }

    public void setOld_apr(String old_apr) {
        this.old_apr = old_apr;
    }

    public String getApr_add() {
        return apr_add;
    }

    public void setApr_add(String apr_add) {
        this.apr_add = apr_add;
    }

    public String getLowest_months() {
        return lowest_months;
    }

    public void setLowest_months(String lowest_months) {
        this.lowest_months = lowest_months;
    }

    public String getAdd_months() {
        return add_months;
    }

    public void setAdd_months(String add_months) {
        this.add_months = add_months;
    }

    public String getPer_account() {
        return per_account;
    }

    public void setPer_account(String per_account) {
        this.per_account = per_account;
    }

    public String getMax_nums() {
        return max_nums;
    }

    public void setMax_nums(String max_nums) {
        this.max_nums = max_nums;
    }

    public String getIs_liuzhuan() {
        return is_liuzhuan;
    }

    public void setIs_liuzhuan(String is_liuzhuan) {
        this.is_liuzhuan = is_liuzhuan;
    }

    public String getBuy_times() {
        return buy_times;
    }

    public void setBuy_times(String buy_times) {
        this.buy_times = buy_times;
    }

    public String getR_nums() {
        return r_nums;
    }

    public void setR_nums(String r_nums) {
        this.r_nums = r_nums;
    }

    public String getR_months() {
        return r_months;
    }

    public void setR_months(String r_months) {
        this.r_months = r_months;
    }

    public String getJiang() {
        return jiang;
    }

    public void setJiang(String jiang) {
        this.jiang = jiang;
    }

    public String getIstyhk() {
        return istyhk;
    }

    public void setIstyhk(String istyhk) {
        this.istyhk = istyhk;
    }

    public String getIs_dsm() {
        return is_dsm;
    }

    public void setIs_dsm(String is_dsm) {
        this.is_dsm = is_dsm;
    }

    public String getIs_dan() {
        return is_dan;
    }

    public void setIs_dan(String is_dan) {
        this.is_dan = is_dan;
    }

    public String getVouch_organ() {
        return vouch_organ;
    }

    public void setVouch_organ(String vouch_organ) {
        this.vouch_organ = vouch_organ;
    }

    public String getAuto_rate() {
        return auto_rate;
    }

    public void setAuto_rate(String auto_rate) {
        this.auto_rate = auto_rate;
    }

    public String getLowest_waitaccount() {
        return lowest_waitaccount;
    }

    public void setLowest_waitaccount(String lowest_waitaccount) {
        this.lowest_waitaccount = lowest_waitaccount;
    }

    public String getBsid() {
        return bsid;
    }

    public void setBsid(String bsid) {
        this.bsid = bsid;
    }

    public String getFabu_time() {
        return fabu_time;
    }

    public void setFabu_time(String fabu_time) {
        this.fabu_time = fabu_time;
    }

    public String getIs_jian() {
        return is_jian;
    }

    public void setIs_jian(String is_jian) {
        this.is_jian = is_jian;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_privacy() {
        return is_privacy;
    }

    public void setIs_privacy(String is_privacy) {
        this.is_privacy = is_privacy;
    }

    public String getBorrowmanual_id() {
        return borrowmanual_id;
    }

    public void setBorrowmanual_id(String borrowmanual_id) {
        this.borrowmanual_id = borrowmanual_id;
    }

    public String getIsqiye() {
        return isqiye;
    }

    public void setIsqiye(String isqiye) {
        this.isqiye = isqiye;
    }

    public String getFastid() {
        return fastid;
    }

    public void setFastid(String fastid) {
        this.fastid = fastid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_area() {
        return user_area;
    }

    public void setUser_area(String user_area) {
        this.user_area = user_area;
    }

    public String getKefu_username() {
        return kefu_username;
    }

    public void setKefu_username(String kefu_username) {
        this.kefu_username = kefu_username;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCredit_jifen() {
        return credit_jifen;
    }

    public void setCredit_jifen(String credit_jifen) {
        this.credit_jifen = credit_jifen;
    }

    public String getCredit_pic() {
        return credit_pic;
    }

    public void setCredit_pic(String credit_pic) {
        this.credit_pic = credit_pic;
    }

    public String getAdd_area() {
        return add_area;
    }

    public void setAdd_area(String add_area) {
        this.add_area = add_area;
    }

    public String getScales() {
        return scales;
    }

    public void setScales(String scales) {
        this.scales = scales;
    }
}

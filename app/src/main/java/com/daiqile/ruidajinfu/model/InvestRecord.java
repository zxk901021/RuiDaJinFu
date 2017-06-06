package com.daiqile.ruidajinfu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class InvestRecord {

    /**
     * list : [{"repayment_yesaccount":"100.18","repayment_account":"100.18","tender_time":"1473146698","anum":"100.00","inter":"0.18","borrow_name":"100元起投，新手体验标B000200021","account":"3000.00","time_limit":"1","isday":"1","time_limit_day":"7","apr":"9.00","username":"18658818150","id":"47","style":"2","account_yes":"3000.00","is_mb":"0","is_xyb":"","is_fast":"0","is_dan":"0","is_jin":"0","is_dxb":"0","is_liuzhuan":"0"}]
     * total : 1
     * page : 1
     * epage : 10
     * total_page : 1
     */

    private String total;
    private String page;
    private String epage;
    private String total_page;

    /**
     * repayment_yesaccount : 100.18
     * repayment_account : 100.18
     * tender_time : 1473146698
     * anum : 100.00
     * inter : 0.18
     * borrow_name : 100元起投，新手体验标B000200021
     * account : 3000.00
     * time_limit : 1
     * isday : 1
     * time_limit_day : 7
     * apr : 9.00
     * username : 18658818150
     * id : 47
     * style : 2
     * account_yes : 3000.00
     * is_mb : 0
     * is_xyb :
     * is_fast : 0
     * is_dan : 0
     * is_jin : 0
     * is_dxb : 0
     * is_liuzhuan : 0
     */

    @SerializedName("list")
    private List<InvestRecordInfo> investRecordInfo;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getEpage() {
        return epage;
    }

    public void setEpage(String epage) {
        this.epage = epage;
    }

    public String getTotal_page() {
        return total_page;
    }

    public void setTotal_page(String total_page) {
        this.total_page = total_page;
    }

    public List<InvestRecordInfo> getInvestRecordInfo() {
        return investRecordInfo;
    }

    public void setInvestRecordInfo(List<InvestRecordInfo> investRecordInfo) {
        this.investRecordInfo = investRecordInfo;
    }

    public static class InvestRecordInfo {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String repayment_yesaccount;
        private String repayment_account;
        private String tender_time;
        private String anum;
        private String inter;
        private String borrow_name;
        private String account;
        private String time_limit;
        private String isday;
        private String time_limit_day;
        private String apr;
        private String username;
        private String id;
        private String style;
        private String account_yes;
        private String is_mb;
        private String is_xyb;
        private String is_fast;
        private String is_dan;
        private String is_jin;
        private String is_dxb;
        private String is_liuzhuan;
        private String repayments_time;
        private String interest;
        private String repay_account;

        public String getRepay_account() {
            return repay_account;
        }

        public void setRepay_account(String repay_account) {
            this.repay_account = repay_account;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getRepayment_yesaccount() {
            return repayment_yesaccount;
        }

        public void setRepayment_yesaccount(String repayment_yesaccount) {
            this.repayment_yesaccount = repayment_yesaccount;
        }

        public String getRepayments_time() {
            return repayments_time;
        }

        public void setRepayments_time(String repayments_time) {
            this.repayments_time = repayments_time;
        }

        public String getRepayment_account() {
            return repayment_account;
        }

        public void setRepayment_account(String repayment_account) {
            this.repayment_account = repayment_account;
        }

        public String getTender_time() {
            return tender_time;
        }

        public void setTender_time(String tender_time) {
            this.tender_time = tender_time;
        }

        public String getAnum() {
            return anum;
        }

        public void setAnum(String anum) {
            this.anum = anum;
        }

        public String getInter() {
            return inter;
        }

        public void setInter(String inter) {
            this.inter = inter;
        }

        public String getBorrow_name() {
            return borrow_name;
        }

        public void setBorrow_name(String borrow_name) {
            this.borrow_name = borrow_name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(String time_limit) {
            this.time_limit = time_limit;
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

        public String getApr() {
            return apr;
        }

        public void setApr(String apr) {
            this.apr = apr;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getAccount_yes() {
            return account_yes;
        }

        public void setAccount_yes(String account_yes) {
            this.account_yes = account_yes;
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

        public String getIs_dan() {
            return is_dan;
        }

        public void setIs_dan(String is_dan) {
            this.is_dan = is_dan;
        }

        public String getIs_jin() {
            return is_jin;
        }

        public void setIs_jin(String is_jin) {
            this.is_jin = is_jin;
        }

        public String getIs_dxb() {
            return is_dxb;
        }

        public void setIs_dxb(String is_dxb) {
            this.is_dxb = is_dxb;
        }

        public String getIs_liuzhuan() {
            return is_liuzhuan;
        }

        public void setIs_liuzhuan(String is_liuzhuan) {
            this.is_liuzhuan = is_liuzhuan;
        }
    }
}

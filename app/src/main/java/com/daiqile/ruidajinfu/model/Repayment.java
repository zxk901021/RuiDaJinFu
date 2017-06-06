package com.daiqile.ruidajinfu.model;


import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class Repayment {

    /**
     * list : [{"id":"16","site_id":"0","status":"0","webstatus":"0","order":"0","borrow_id":"11","repayment_time":"1483953317","repayment_yestime":null,"repayment_account":"90825.00","repayment_yesaccount":"0.00","late_days":"0","late_interest":"0","interest":"825.00","capital":"90000.00","forfeit":"0.00","reminder_fee":"0.00","addtime":"1481274917","addip":"125.37.178.79","tender_id":"0","month_nums":"0","is_cz":"0","borrow_name":"[天津]生意周转+2%年化奖励","is_fast":"1","time_limit":"1","username":"zhulailu","user_id":"5","phone":"","area":"25"}]
     * total : 1
     * page : 1
     * epage : 10
     * total_page : 1
     */

    private String total;
    private String page;
    private String epage;
    private String total_page;
    private List<Payment> list;

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

    public List<Payment> getList() {
        return list;
    }

    public void setList(List<Payment> list) {
        this.list = list;
    }

    public static class Payment {
        /**
         * id : 16
         * site_id : 0
         * status : 0
         * webstatus : 0
         * order : 0
         * borrow_id : 11
         * repayment_time : 1483953317
         * repayment_yestime : null
         * repayment_account : 90825.00
         * repayment_yesaccount : 0.00
         * late_days : 0
         * late_interest : 0
         * interest : 825.00
         * capital : 90000.00
         * forfeit : 0.00
         * reminder_fee : 0.00
         * addtime : 1481274917
         * addip : 125.37.178.79
         * tender_id : 0
         * month_nums : 0
         * is_cz : 0
         * borrow_name : [天津]生意周转+2%年化奖励
         * is_fast : 1
         * time_limit : 1
         * username : zhulailu
         * user_id : 5
         * phone :
         * area : 25
         */

        private String id;
        private String site_id;
        private String status;
        private String webstatus;
        private String order;
        private String borrow_id;
        private String repayment_time;
        private Object repayment_yestime;
        private String repayment_account;
        private String repayment_yesaccount;
        private String late_days;
        private String late_interest;
        private String interest;
        private String capital;
        private String forfeit;
        private String reminder_fee;
        private String addtime;
        private String addip;
        private String tender_id;
        private String month_nums;
        private String is_cz;
        private String borrow_name;
        private String is_fast;
        private String time_limit;
        private String username;
        private String user_id;
        private String phone;
        private String area;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWebstatus() {
            return webstatus;
        }

        public void setWebstatus(String webstatus) {
            this.webstatus = webstatus;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getBorrow_id() {
            return borrow_id;
        }

        public void setBorrow_id(String borrow_id) {
            this.borrow_id = borrow_id;
        }

        public String getRepayment_time() {
            return repayment_time;
        }

        public void setRepayment_time(String repayment_time) {
            this.repayment_time = repayment_time;
        }

        public Object getRepayment_yestime() {
            return repayment_yestime;
        }

        public void setRepayment_yestime(Object repayment_yestime) {
            this.repayment_yestime = repayment_yestime;
        }

        public String getRepayment_account() {
            return repayment_account;
        }

        public void setRepayment_account(String repayment_account) {
            this.repayment_account = repayment_account;
        }

        public String getRepayment_yesaccount() {
            return repayment_yesaccount;
        }

        public void setRepayment_yesaccount(String repayment_yesaccount) {
            this.repayment_yesaccount = repayment_yesaccount;
        }

        public String getLate_days() {
            return late_days;
        }

        public void setLate_days(String late_days) {
            this.late_days = late_days;
        }

        public String getLate_interest() {
            return late_interest;
        }

        public void setLate_interest(String late_interest) {
            this.late_interest = late_interest;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public String getForfeit() {
            return forfeit;
        }

        public void setForfeit(String forfeit) {
            this.forfeit = forfeit;
        }

        public String getReminder_fee() {
            return reminder_fee;
        }

        public void setReminder_fee(String reminder_fee) {
            this.reminder_fee = reminder_fee;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAddip() {
            return addip;
        }

        public void setAddip(String addip) {
            this.addip = addip;
        }

        public String getTender_id() {
            return tender_id;
        }

        public void setTender_id(String tender_id) {
            this.tender_id = tender_id;
        }

        public String getMonth_nums() {
            return month_nums;
        }

        public void setMonth_nums(String month_nums) {
            this.month_nums = month_nums;
        }

        public String getIs_cz() {
            return is_cz;
        }

        public void setIs_cz(String is_cz) {
            this.is_cz = is_cz;
        }

        public String getBorrow_name() {
            return borrow_name;
        }

        public void setBorrow_name(String borrow_name) {
            this.borrow_name = borrow_name;
        }

        public String getIs_fast() {
            return is_fast;
        }

        public void setIs_fast(String is_fast) {
            this.is_fast = is_fast;
        }

        public String getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(String time_limit) {
            this.time_limit = time_limit;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}

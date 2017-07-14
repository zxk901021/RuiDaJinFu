package com.daiqile.test.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class TenderLog {

    /**
     * list : [{"id":"5","site_id":"0","user_id":"12","status":"1","borrow_id":"8","money":"51200","account":"51200.00","repayment_account":"52864.01","interest":"1664.01","repayment_yesaccount":"0.00","wait_account":"52864.01","wait_interest":"1664.01","repayment_yesinterest":"0.00","addtime":"1478739763","jiaoyan":"0","addip":"125.38.47.55","lzpay":"0","month_nums":"0","tender_type":"0","per_account":"1","awardflag":"0","auto_liuzhuan":"0","debt":"0","source":"1","xtcoll":"0.00","tid":"5","tender_account":"51200","tender_money":"51200","borrow_userid":"10","op_username":"mengqinglong","username":"xiang","apr":"13.00","time_limit":"3","is_liuzhuan":"0","borrow_name":"[天津]生意周转","account_yes":"51200.00","style":"3","credit_jifen":"117560","credit_pic":"credit_s45.gif","equal":[{"repayment_account":"554.67","repayment_time":"1484273495","interest":"554.67","capital":"0"},{"repayment_account":"554.67","repayment_time":"1486951895","interest":"554.67","capital":"0"},{"repayment_account":"51754.67","repayment_time":"1489371095","interest":"554.67","capital":"51200.00"}],"other":"0","scale":"100","scale_width":"140","repayment_noaccount":"52864.01"}]
     * total : 1
     * page : 1
     * epage : 10
     * total_page : 1
     */

    private String total;
    private String page;
    private String epage;
    private String total_page;
    private List<TenderInfo> list;

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

    public List<TenderInfo> getList() {
        return list;
    }

    public void setList(List<TenderInfo> list) {
        this.list = list;
    }

    public static class TenderInfo {
        /**
         * id : 5
         * site_id : 0
         * user_id : 12
         * status : 1
         * borrow_id : 8
         * money : 51200
         * account : 51200.00
         * repayment_account : 52864.01
         * interest : 1664.01
         * repayment_yesaccount : 0.00
         * wait_account : 52864.01
         * wait_interest : 1664.01
         * repayment_yesinterest : 0.00
         * addtime : 1478739763
         * jiaoyan : 0
         * addip : 125.38.47.55
         * lzpay : 0
         * month_nums : 0
         * tender_type : 0
         * per_account : 1
         * awardflag : 0
         * auto_liuzhuan : 0
         * debt : 0
         * source : 1
         * xtcoll : 0.00
         * tid : 5
         * tender_account : 51200
         * tender_money : 51200
         * borrow_userid : 10
         * op_username : mengqinglong
         * username : xiang
         * apr : 13.00
         * time_limit : 3
         * is_liuzhuan : 0
         * borrow_name : [天津]生意周转
         * account_yes : 51200.00
         * style : 3
         * credit_jifen : 117560
         * credit_pic : credit_s45.gif
         * equal : [{"repayment_account":"554.67","repayment_time":"1484273495","interest":"554.67","capital":"0"},{"repayment_account":"554.67","repayment_time":"1486951895","interest":"554.67","capital":"0"},{"repayment_account":"51754.67","repayment_time":"1489371095","interest":"554.67","capital":"51200.00"}]
         * other : 0
         * scale : 100
         * scale_width : 140
         * repayment_noaccount : 52864.01
         */

        private String id;
        private String site_id;
        private String user_id;
        private String status;
        private String borrow_id;
        private String money;
        private String account;
        private String repayment_account;
        private String interest;
        private String repayment_yesaccount;
        private String wait_account;
        private String wait_interest;
        private String repayment_yesinterest;
        private long addtime;
        private String jiaoyan;
        private String addip;
        private String lzpay;
        private String month_nums;
        private String tender_type;
        private String per_account;
        private String awardflag;
        private String auto_liuzhuan;
        private String debt;
        private String source;
        private String xtcoll;
        private String tid;
        private String tender_account;
        private String tender_money;
        private String borrow_userid;
        private String op_username;
        private String username;
        private String apr;
        private String time_limit;
        private String is_liuzhuan;
        private String borrow_name;
        private String account_yes;
        private String style;
        private String credit_jifen;
        private String credit_pic;
        private String other;
        private String scale;
        private String scale_width;
        private String repayment_noaccount;
        private List<EqualInfo> equal;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBorrow_id() {
            return borrow_id;
        }

        public void setBorrow_id(String borrow_id) {
            this.borrow_id = borrow_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getRepayment_account() {
            return repayment_account;
        }

        public void setRepayment_account(String repayment_account) {
            this.repayment_account = repayment_account;
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

        public String getWait_account() {
            return wait_account;
        }

        public void setWait_account(String wait_account) {
            this.wait_account = wait_account;
        }

        public String getWait_interest() {
            return wait_interest;
        }

        public void setWait_interest(String wait_interest) {
            this.wait_interest = wait_interest;
        }

        public String getRepayment_yesinterest() {
            return repayment_yesinterest;
        }

        public void setRepayment_yesinterest(String repayment_yesinterest) {
            this.repayment_yesinterest = repayment_yesinterest;
        }

        public long getAddtime() {
            return addtime;
        }

        public void setAddtime(long addtime) {
            this.addtime = addtime;
        }

        public String getJiaoyan() {
            return jiaoyan;
        }

        public void setJiaoyan(String jiaoyan) {
            this.jiaoyan = jiaoyan;
        }

        public String getAddip() {
            return addip;
        }

        public void setAddip(String addip) {
            this.addip = addip;
        }

        public String getLzpay() {
            return lzpay;
        }

        public void setLzpay(String lzpay) {
            this.lzpay = lzpay;
        }

        public String getMonth_nums() {
            return month_nums;
        }

        public void setMonth_nums(String month_nums) {
            this.month_nums = month_nums;
        }

        public String getTender_type() {
            return tender_type;
        }

        public void setTender_type(String tender_type) {
            this.tender_type = tender_type;
        }

        public String getPer_account() {
            return per_account;
        }

        public void setPer_account(String per_account) {
            this.per_account = per_account;
        }

        public String getAwardflag() {
            return awardflag;
        }

        public void setAwardflag(String awardflag) {
            this.awardflag = awardflag;
        }

        public String getAuto_liuzhuan() {
            return auto_liuzhuan;
        }

        public void setAuto_liuzhuan(String auto_liuzhuan) {
            this.auto_liuzhuan = auto_liuzhuan;
        }

        public String getDebt() {
            return debt;
        }

        public void setDebt(String debt) {
            this.debt = debt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getXtcoll() {
            return xtcoll;
        }

        public void setXtcoll(String xtcoll) {
            this.xtcoll = xtcoll;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTender_account() {
            return tender_account;
        }

        public void setTender_account(String tender_account) {
            this.tender_account = tender_account;
        }

        public String getTender_money() {
            return tender_money;
        }

        public void setTender_money(String tender_money) {
            this.tender_money = tender_money;
        }

        public String getBorrow_userid() {
            return borrow_userid;
        }

        public void setBorrow_userid(String borrow_userid) {
            this.borrow_userid = borrow_userid;
        }

        public String getOp_username() {
            return op_username;
        }

        public void setOp_username(String op_username) {
            this.op_username = op_username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getApr() {
            return apr;
        }

        public void setApr(String apr) {
            this.apr = apr;
        }

        public String getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(String time_limit) {
            this.time_limit = time_limit;
        }

        public String getIs_liuzhuan() {
            return is_liuzhuan;
        }

        public void setIs_liuzhuan(String is_liuzhuan) {
            this.is_liuzhuan = is_liuzhuan;
        }

        public String getBorrow_name() {
            return borrow_name;
        }

        public void setBorrow_name(String borrow_name) {
            this.borrow_name = borrow_name;
        }

        public String getAccount_yes() {
            return account_yes;
        }

        public void setAccount_yes(String account_yes) {
            this.account_yes = account_yes;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
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

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
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

        public List<EqualInfo> getEqual() {
            return equal;
        }

        public void setEqual(List<EqualInfo> equal) {
            this.equal = equal;
        }

        public static class EqualInfo {
            /**
             * repayment_account : 554.67
             * repayment_time : 1484273495
             * interest : 554.67
             * capital : 0
             */

            private String repayment_account;
            private String repayment_time;
            private String interest;
            private String capital;

            public String getRepayment_account() {
                return repayment_account;
            }

            public void setRepayment_account(String repayment_account) {
                this.repayment_account = repayment_account;
            }

            public String getRepayment_time() {
                return repayment_time;
            }

            public void setRepayment_time(String repayment_time) {
                this.repayment_time = repayment_time;
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
        }
    }
}

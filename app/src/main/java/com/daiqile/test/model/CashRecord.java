package com.daiqile.test.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
public class CashRecord {

    /**
     * list : [{"id":"3","user_id":"12","status":"1","account":"231412353667","bank":"300","branch":"海曙支行","total":"1800.00","credited":"1800.00","fee":"0.00","verify_userid":"1","verify_time":"1463463619","verify_remark":"213213","addtime":"1463463572","addip":"122.246.149.249","username":"xiang","realname":"祥仔","bank_name":"中国工商银行"},{"id":"2","user_id":"12","status":"1","account":"3146850789745879","bank":"300","branch":"海曙支行","total":"180.00","credited":"180.00","fee":"0.00","verify_userid":"1","verify_time":"1463119754","verify_remark":"afasgd","addtime":"1463119735","addip":"123.137.81.106","username":"xiang","realname":"祥仔","bank_name":"中国工商银行"},{"id":"1","user_id":"12","status":"1","account":"6224532627257","bank":"300","branch":"海曙支行","total":"180.00","credited":"180.00","fee":"0.00","verify_userid":"1","verify_time":"1461896971","verify_remark":"asdf","addtime":"1461896952","addip":"122.246.147.222","username":"xiang","realname":"祥仔","bank_name":"中国工商银行"}]
     * total : 3
     * page : 1
     * epage : 10
     * total_page : 1
     * txze : 2160.00
     * dzje : 2160.00
     * sxf : 0.00
     */

    private String total;
    private String page;
    private String epage;
    private String total_page;
    private String txze;
    private String dzje;
    private String sxf;
    private List<ListBean> list;

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

    public String getTxze() {
        return txze;
    }

    public void setTxze(String txze) {
        this.txze = txze;
    }

    public String getDzje() {
        return dzje;
    }

    public void setDzje(String dzje) {
        this.dzje = dzje;
    }

    public String getSxf() {
        return sxf;
    }

    public void setSxf(String sxf) {
        this.sxf = sxf;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * user_id : 12
         * status : 1
         * account : 231412353667
         * bank : 300
         * branch : 海曙支行
         * total : 1800.00
         * credited : 1800.00
         * fee : 0.00
         * verify_userid : 1
         * verify_time : 1463463619
         * verify_remark : 213213
         * addtime : 1463463572
         * addip : 122.246.149.249
         * username : xiang
         * realname : 祥仔
         * bank_name : 中国工商银行
         */

        private String id;
        private String user_id;
        private String status;
        private String account;
        private String bank;
        private String branch;
        private String total;
        private String credited;
        private String fee;
        private String verify_userid;
        private String verify_time;
        private String verify_remark;
        private String addtime;
        private String addip;
        private String username;
        private String realname;
        private String bank_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getCredited() {
            return credited;
        }

        public void setCredited(String credited) {
            this.credited = credited;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getVerify_userid() {
            return verify_userid;
        }

        public void setVerify_userid(String verify_userid) {
            this.verify_userid = verify_userid;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }
}

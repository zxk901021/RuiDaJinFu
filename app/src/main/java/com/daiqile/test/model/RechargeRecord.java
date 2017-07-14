package com.daiqile.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
public class RechargeRecord {


    /**
     * list : [{"id":"17","trade_no":"1481006889122","user_id":"12","status":"1","money":"0.01","payment":"2","return":"","type":"1","remark":"汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":"1481007005","verify_remark":"成功充值","addtime":"1481006889","addip":"125.37.178.79","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"16","trade_no":"1480921037128","user_id":"12","status":"1","money":"0.01","payment":"2","return":"","type":"1","remark":"汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":"1480921119","verify_remark":"成功充值","addtime":"1480921037","addip":"60.24.153.243","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"15","trade_no":"1480915767126","user_id":"12","status":"1","money":"0.01","payment":"2","return":"","type":"1","remark":"汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":"1480916092","verify_remark":"成功充值","addtime":"1480915767","addip":"60.24.153.243","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"14","trade_no":"1480915707129","user_id":"12","status":"0","money":"0.01","payment":"2","return":"","type":"1","remark":"汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":null,"verify_remark":null,"addtime":"1480915707","addip":"60.24.153.243","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"13","trade_no":"1480915062125","user_id":"12","status":"0","money":"0.01","payment":"2","return":"","type":"1","remark":"汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":null,"verify_remark":null,"addtime":"1480915062","addip":"60.24.153.243","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"12","trade_no":"1480730846121","user_id":"12","status":"0","money":"0.01","payment":"2","return":"","type":"1","remark":"汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":null,"verify_remark":null,"addtime":"1480730846","addip":"1.15.233.195","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"11","trade_no":"1480695276124","user_id":"12","status":"0","money":"0.01","payment":"2","return":"","type":"1","remark":"汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":null,"verify_remark":null,"addtime":"1480695276","addip":"59.109.142.216","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"10","trade_no":"1480695173125","user_id":"12","status":"0","money":"0.01","payment":"2","return":"","type":"1","remark":"【瑞达金融】汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":null,"verify_remark":null,"addtime":"1480695173","addip":"59.109.142.216","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"9","trade_no":"1480694804127","user_id":"12","status":"0","money":"0.01","payment":"2","return":"","type":"1","remark":"【瑞达金融】汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":null,"verify_remark":null,"addtime":"1480694804","addip":"59.109.142.216","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"},{"id":"8","trade_no":"1480694739129","user_id":"12","status":"0","money":"0.01","payment":"2","return":"","type":"1","remark":"【瑞达金融】汇潮支付在线充值","lzx_payname":null,"fee":"0.00","verify_userid":"0","verify_time":null,"verify_remark":null,"addtime":"1480694739","addip":"59.109.142.216","background":"0","award":"0.00","total":"0.01","username":"xiang","payment_name":"汇潮支付","realname":"祥仔"}]
     * total : 16
     * page : 1
     * epage : 10
     * total_page : 2
     * czje : 236312.10
     * czjl : 0.00
     * fy : 3.63
     * dzje : 236308.47
     */

    private String total;
    private String page;
    private String epage;
    private String total_page;
    private String czje;
    private String czjl;
    private String fy;
    private String dzje;
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

    public String getCzje() {
        return czje;
    }

    public void setCzje(String czje) {
        this.czje = czje;
    }

    public String getCzjl() {
        return czjl;
    }

    public void setCzjl(String czjl) {
        this.czjl = czjl;
    }

    public String getFy() {
        return fy;
    }

    public void setFy(String fy) {
        this.fy = fy;
    }

    public String getDzje() {
        return dzje;
    }

    public void setDzje(String dzje) {
        this.dzje = dzje;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 17
         * trade_no : 1481006889122
         * user_id : 12
         * status : 1
         * money : 0.01
         * payment : 2
         * return :
         * type : 1
         * remark : 汇潮支付在线充值
         * lzx_payname : null
         * fee : 0.00
         * verify_userid : 0
         * verify_time : 1481007005
         * verify_remark : 成功充值
         * addtime : 1481006889
         * addip : 125.37.178.79
         * background : 0
         * award : 0.00
         * total : 0.01
         * username : xiang
         * payment_name : 汇潮支付
         * realname : 祥仔
         */

        private String id;
        private String trade_no;
        private String user_id;
        private String status;
        private String money;
        private String payment;
        @SerializedName("return")
        private String returnX;
        private String type;
        private String remark;
        private Object lzx_payname;
        private String fee;
        private String verify_userid;
        private String verify_time;
        private String verify_remark;
        private long addtime;
        private String addip;
        private String background;
        private String award;
        private String total;
        private String username;
        private String payment_name;
        private String realname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getReturnX() {
            return returnX;
        }

        public void setReturnX(String returnX) {
            this.returnX = returnX;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getLzx_payname() {
            return lzx_payname;
        }

        public void setLzx_payname(Object lzx_payname) {
            this.lzx_payname = lzx_payname;
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

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getAward() {
            return award;
        }

        public void setAward(String award) {
            this.award = award;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }
    }
}

package com.daiqile.test.model;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */

public class BindCard {


    /**
     * status : 1
     * list : [{"id":"5","bank":"中国工商银行","branch":"中国工商银行兴宁支行","card_number":"334445666666"},{"id":"6","bank":"中国银行","branch":"中国银行中行天津华苑支行","card_number":"6217560200002096024"}]
     */

    private String status;
    private List<BankCardBean> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BankCardBean> getList() {
        return list;
    }

    public void setList(List<BankCardBean> list) {
        this.list = list;
    }

    public static class BankCardBean {
        /**
         * id : 5
         * bank : 中国工商银行
         * branch : 中国工商银行兴宁支行
         * card_number : 334445666666
         */

        private String id;
        private String bank;
        private String branch;
        private String card_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

    }
}

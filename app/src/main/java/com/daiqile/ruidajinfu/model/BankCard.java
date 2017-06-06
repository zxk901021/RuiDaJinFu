package com.daiqile.ruidajinfu.model;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */

public class BankCard {

    private List<CardBean> list;

    public List<CardBean> getList() {
        return list;
    }

    public void setList(List<CardBean> list) {
        this.list = list;
    }

    public static class CardBean {
        /**
         * id : 594
         * status : 0
         * order : 10
         * type_id : 25
         * pid : 0
         * name : 三井住友银行(中国)有限公司
         * value : 三井住友银行(中国)有限公司
         * addtime : 0
         * addip : null
         */

        private String id;
        private String status;
        private String order;
        private String type_id;
        private String pid;
        private String name;
        private String value;
        private String addtime;
        private Object addip;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public Object getAddip() {
            return addip;
        }

        public void setAddip(Object addip) {
            this.addip = addip;
        }
    }
}

package com.daiqile.test.model;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class Province {

    /**
     * status : 1
     * content : [{"id":"1","name":"北京","nid":"beijing","pid":"0","domain":null,"order":"10"},{"id":"21","name":"天津","nid":"tianjin","pid":"0","domain":null,"order":"10"},{"id":"40","name":"上海","nid":"shanghai","pid":"0","domain":null,"order":"10"},{"id":"61","name":"重庆","nid":"zhongqing","pid":"0","domain":null,"order":"10"},{"id":"102","name":"河北省","nid":"hebeisheng","pid":"0","domain":null,"order":"10"},{"id":"297","name":"山西省","nid":"shanxisheng","pid":"0","domain":null,"order":"10"},{"id":"439","name":"内蒙古区","nid":"neimengguqu","pid":"0","domain":null,"order":"10"},{"id":"561","name":"辽宁省","nid":"liaoningsheng","pid":"0","domain":null,"order":"10"},{"id":"690","name":"吉林省","nid":"jilinsheng","pid":"0","domain":null,"order":"10"},{"id":"768","name":"黑龙江省","nid":"heilongjiangsheng","pid":"0","domain":null,"order":"10"},{"id":"924","name":"江苏省","nid":"jiangsusheng","pid":"0","domain":null,"order":"10"},{"id":"1057","name":"浙江省","nid":"zhejiangsheng","pid":"0","domain":null,"order":"11"},{"id":"1170","name":"安徽省","nid":"anhuisheng","pid":"0","domain":null,"order":"10"},{"id":"1310","name":"福建省","nid":"fujiansheng","pid":"0","domain":null,"order":"10"},{"id":"1414","name":"江西省","nid":"jiangxisheng","pid":"0","domain":null,"order":"10"},{"id":"1536","name":"山东省","nid":"shandongsheng","pid":"0","domain":null,"order":"10"},{"id":"1711","name":"河南省","nid":"henansheng","pid":"0","domain":null,"order":"10"},{"id":"1905","name":"湖北省","nid":"hubeisheng","pid":"0","domain":null,"order":"10"},{"id":"2034","name":"湖南省","nid":"hunansheng","pid":"0","domain":null,"order":"10"},{"id":"2184","name":"广东省","nid":"guangdongsheng","pid":"0","domain":null,"order":"10"},{"id":"2403","name":"广西区","nid":"guangxiqu","pid":"0","domain":null,"order":"10"},{"id":"2541","name":"海南省","nid":"hainansheng","pid":"0","domain":null,"order":"10"},{"id":"2570","name":"四川省","nid":"sichuansheng","pid":"0","domain":null,"order":"10"},{"id":"2791","name":"贵州省","nid":"guizhousheng","pid":"0","domain":null,"order":"10"},{"id":"2892","name":"云南省","nid":"yunnansheng","pid":"0","domain":null,"order":"10"},{"id":"3046","name":"西藏区","nid":"xicangqu","pid":"0","domain":null,"order":"10"},{"id":"3128","name":"陕西省","nid":"shanxisheng","pid":"0","domain":null,"order":"10"},{"id":"3256","name":"甘肃省","nid":"gansusheng","pid":"0","domain":null,"order":"10"},{"id":"3369","name":"青海省","nid":"qinghaisheng","pid":"0","domain":null,"order":"10"},{"id":"3422","name":"宁夏区","nid":"ningxiaqu","pid":"0","domain":null,"order":"10"},{"id":"3454","name":"新疆区","nid":"xinjiangqu","pid":"0","domain":null,"order":"10"},{"id":"3571","name":"台湾省","nid":"taiwansheng","pid":"0","domain":null,"order":"10"},{"id":"3573","name":"香港特区","nid":"xianggangtequ","pid":"0","domain":null,"order":"10"},{"id":"3575","name":"澳门特区","nid":"aomentequ","pid":"0","domain":null,"order":"10"}]
     */

    private String status;
    private List<ProvinceBean> content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProvinceBean> getContent() {
        return content;
    }

    public void setContent(List<ProvinceBean> content) {
        this.content = content;
    }

    public static class ProvinceBean {
        /**
         * id : 1
         * name : 北京
         * nid : beijing
         * pid : 0
         * domain : null
         * order : 10
         */

        private String id;
        private String name;
        private String nid;
        private String pid;
        private Object domain;
        private String order;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public Object getDomain() {
            return domain;
        }

        public void setDomain(Object domain) {
            this.domain = domain;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}

package com.example.administrator.zqzy.bean;

import java.util.List;

public class KnowDetailBodyBean {

    private List<OrderDetailBean> orderDetail;

    public List<OrderDetailBean> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailBean> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public static class OrderDetailBean {
        /**
         * F0003 : 1
         * F0004 :
         * F0005 : 2018-11-12T13:47:35
         * F0006 : 林老师
         */

        private int F0003;
        private String F0004;
        private String F0005;
        private String F0006;

        public int getF0003() {
            return F0003;
        }

        public void setF0003(int F0003) {
            this.F0003 = F0003;
        }

        public String getF0004() {
            return F0004;
        }

        public void setF0004(String F0004) {
            this.F0004 = F0004;
        }

        public String getF0005() {
            return F0005;
        }

        public void setF0005(String F0005) {
            this.F0005 = F0005;
        }

        public String getF0006() {
            return F0006;
        }

        public void setF0006(String F0006) {
            this.F0006 = F0006;
        }
    }
}

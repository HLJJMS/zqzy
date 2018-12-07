package com.example.administrator.zqzy.bean;

import java.util.List;

public class KnowDetailHeadBean {


    private List<OrderMainBean> orderMain;

    public List<OrderMainBean> getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(List<OrderMainBean> orderMain) {
        this.orderMain = orderMain;
    }

    public static class OrderMainBean {
        /**
         * F0001 : 10008
         * F0002 : LR00000039
         * F0005 : 税务里抄报、申报、完税各是什么意思？
         * F0006 :
         * F0012 : 2018-11-12T13:46:59.113
         */

        private int F0001;
        private String F0002;
        private String F0005;
        private String F0006;
        private String F0012;

        public int getF0001() {
            return F0001;
        }

        public void setF0001(int F0001) {
            this.F0001 = F0001;
        }

        public String getF0002() {
            return F0002;
        }

        public void setF0002(String F0002) {
            this.F0002 = F0002;
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

        public String getF0012() {
            return F0012;
        }

        public void setF0012(String F0012) {
            this.F0012 = F0012;
        }
    }
}

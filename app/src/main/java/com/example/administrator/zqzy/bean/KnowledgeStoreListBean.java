package com.example.administrator.zqzy.bean;

import java.util.List;

public class KnowledgeStoreListBean {

    private List<OrderMainBean> orderMain;

    public List<OrderMainBean> getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(List<OrderMainBean> orderMain) {
        this.orderMain = orderMain;
    }

    public static class OrderMainBean {
        /**
         * F0000 : 28
         * F0002 : LR00000041
         * F0005 : 用友财务软件如何建账？
         * F0012 : 2018-11-13T10:20:49.687
         * answernum : 1
         * rowid : 1
         */

        private String F0000;
        private String F0002;
        private String F0005;
        private String F0012;
        private String answernum;
        private String rowid;

        public String getF0000() {
            return F0000;
        }

        public void setF0000(String F0000) {
            this.F0000 = F0000;
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

        public String getF0012() {
            return F0012;
        }

        public void setF0012(String F0012) {
            this.F0012 = F0012;
        }

        public String getAnswernum() {
            return answernum;
        }

        public void setAnswernum(String answernum) {
            this.answernum = answernum;
        }

        public String getRowid() {
            return rowid;
        }

        public void setRowid(String rowid) {
            this.rowid = rowid;
        }
    }
}

package com.example.administrator.zqzy.bean;

import java.util.List;

public class SelectClassListBean {


    private List<OrderDetailBean> orderDetail;

    public List<OrderDetailBean> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailBean> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public static class OrderDetailBean {
        /**
         * F0001 : 10008
         * F0002 : KC000009
         * F0003 : CV00000041
         * F0004 : 《第一讲 整体结账工作流程》
         * F0005 : JS000003
         * F0017 : /Uploads/10008/TU0N02/JS000003_1.jpg
         * teachname : 安丰文
         * teachStringroduction : %3Cp%3E%E5%AE%89%E4%B8%B0%E6%96%87%EF%BC%8CMBA%20%3Cspan%20style%3D%22font-family%3A%20%E5%AE%8B%E4%BD%93%3B%22%3E%E5%B7%A5%E5%95%86%E7%AE%A1%E7%90%86%E7%A1%95%E5%A3%AB%E3%80%81%E9%AB%98%E7%BA%A7%E4%BC%9A%E8%AE%A1%E5%B8%88%E3%80%81%E4%BC%97%E4%BC%81%E6%99%BA%E4%BA%91%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E4%BA%91%E5%B9%B3%E5%8F%B0%E5%88%9B%E5%A7%8B%E4%BA%BA%E3%80%81%E7%B2%BE%E8%AF%BE%E5%9C%A8%E7%BA%BF%E6%95%99%E8%82%B2%E5%B9%B3%E5%8F%B0%E8%81%94%E5%90%88%E5%88%9B%E5%A7%8B%E4%BA%BA%E3%80%82%3C%2Fspan%3E%3C%2Fp%3E%3Col%20start%3D%221%22%20type%3D%221%22%3E%3Cli%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E5%85%B7%E5%A4%87%3C%2Fspan%3E%3Cspan%3E16%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E5%B9%B4%E5%A4%96%E8%B5%84%E4%BC%81%E4%B8%9A%E8%B7%A8%E5%9B%BD%E5%85%AC%E5%8F%B8%E5%B7%A5%E4%BD%9C%E8%83%8C%E6%99%AF%E3%80%82%3C%2Fspan%3E%3C%2Fli%3E%3Cli%3E%3Cspan%3E10%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E5%B9%B4%E8%B4%A2%E5%8A%A1%E7%AE%A1%E7%90%86%E7%BB%8F%E9%AA%8C%EF%BC%8C%3C%2Fspan%3E%3Cspan%3E12%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E5%B9%B4%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E5%92%8C%E4%BF%A1%E6%81%AF%E5%8C%96%E7%AE%A1%E7%90%86%E7%BB%8F%E9%AA%8C%E3%80%82%3C%2Fspan%3E%3C%2Fli%3E%3Cli%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E6%9B%BE%E6%8B%85%E4%BB%BB%E8%BF%87%E5%A4%A7%E5%9E%8B%E4%BC%81%E4%B8%9A%3C%2Fspan%3E%3Cspan%3ECFO%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E3%80%81%3C%2Fspan%3E%3Cspan%3ECIO%20%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E3%80%81%3C%2Fspan%3E%3Cspan%3ESAP%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E9%A1%B9%E7%9B%AE%E9%AB%98%E7%BA%A7%E7%BB%8F%E7%90%86%E3%80%81%E6%80%BB%E7%BB%8F%E7%90%86%E7%AD%89%E8%81%8C%E5%8A%A1%E3%80%82%3C%2Fspan%3E%3C%2Fli%3E%3Cli%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E6%93%85%E9%95%BF%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E6%B5%81%E7%A8%8B%E8%A7%84%E5%88%92%EF%BC%8C%E5%8D%8F%E5%8A%A9%E4%BC%97%E5%A4%9A%E8%B7%A8%E5%9B%BD%E5%85%AC%E5%8F%B8%E5%92%8C%E5%9B%BD%E4%BC%81%E8%BF%9B%E8%A1%8C%E6%B5%81%E7%A8%8B%E5%86%8D%E9%80%A0%E5%92%8C%E7%AE%A1%E7%90%86%E5%9F%B9%E8%AE%AD%E3%80%81%E7%AE%A1%E7%90%86%E8%90%BD%E5%9C%B0%E3%80%82%3C%2Fspan%3E%3C%2Fli%3E%3Cli%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E4%B8%B0%E5%AF%8C%E7%9A%84%E8%B4%A2%E5%8A%A1%E7%AE%A1%E7%90%86%E3%80%81%E4%BE%9B%E5%BA%94%E9%93%BE%E7%AE%A1%E7%90%86%E3%80%81%E4%BF%A1%E6%81%AF%E5%8C%96%E7%AE%A1%E7%90%86%E5%92%8C%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E7%BB%8F%E9%AA%8C%E3%80%82%3C%2Fspan%3E%3C%2Fli%3E%3Cli%3E%3Cspan%3E15%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E5%B9%B4%E5%AF%B9%E6%8E%A5%3C%2Fspan%3E%3Cspan%3EKPMG%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E6%AF%95%E9%A9%AC%E5%A8%81%E5%92%8C%E7%BE%8E%E5%9B%BD%3C%2Fspan%3E%3Cspan%3ESOX%3C%2Fspan%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E5%AE%A1%E8%AE%A1%E3%80%81%E7%A8%8E%E5%8A%A1%E5%AE%A1%E8%AE%A1%E4%BA%8B%E5%8A%A1%E7%AE%A1%E7%90%86%E7%BB%8F%E9%AA%8C%E3%80%82%3C%2Fspan%3E%3C%2Fli%3E%3Cli%3E%3Cspan%20style%3D%22%3Bfont-family%3A%E5%AE%8B%E4%BD%93%22%3E%E4%B8%BB%E8%AE%B2%E8%B4%A2%E5%8A%A1%E7%AE%A1%E7%90%86%E3%80%81%E4%BE%9B%E5%BA%94%E9%93%BE%E7%AE%A1%E7%90%86%E3%80%81%E4%BC%81%E4%B8%9A%E4%BF%A1%E6%81%AF%E5%8C%96%E7%AE%A1%E7%90%86%3C%2Fspan%3E%3C%2Fli%3E%3C%2Fol%3E%3Cp%3E%3Cbr%2F%3E%3C%2Fp%3E
         * teachheadship : MBA工商管理硕士、高级会计师
         * F0006 : 2018-12-12T14:00:00
         * F0007 : 2018-12-12T18:00:00
         * F0008 : 
         * F0009 : 
         * F0006_1 : 星期三
         * F0007_1 : 星期三
         */

        private String F0001;
        private String F0002;
        private String F0003;
        private String F0004;
        private String F0005;
        private String F0017;
        private String teachname;
        private String teachStringroduction;
        private String teachheadship;
        private String F0006;
        private String F0007;
        private String F0008;
        private String F0009;
        private String F0006_1;
        private String F0007_1;

        public String getF0001() {
            return F0001;
        }

        public void setF0001(String F0001) {
            this.F0001 = F0001;
        }

        public String getF0002() {
            return F0002;
        }

        public void setF0002(String F0002) {
            this.F0002 = F0002;
        }

        public String getF0003() {
            return F0003;
        }

        public void setF0003(String F0003) {
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

        public String getF0017() {
            return F0017;
        }

        public void setF0017(String F0017) {
            this.F0017 = F0017;
        }

        public String getTeachname() {
            return teachname;
        }

        public void setTeachname(String teachname) {
            this.teachname = teachname;
        }

        public String getTeachStringroduction() {
            return teachStringroduction;
        }

        public void setTeachStringroduction(String teachStringroduction) {
            this.teachStringroduction = teachStringroduction;
        }

        public String getTeachheadship() {
            return teachheadship;
        }

        public void setTeachheadship(String teachheadship) {
            this.teachheadship = teachheadship;
        }

        public String getF0006() {
            return F0006;
        }

        public void setF0006(String F0006) {
            this.F0006 = F0006;
        }

        public String getF0007() {
            return F0007;
        }

        public void setF0007(String F0007) {
            this.F0007 = F0007;
        }

        public String getF0008() {
            return F0008;
        }

        public void setF0008(String F0008) {
            this.F0008 = F0008;
        }

        public String getF0009() {
            return F0009;
        }

        public void setF0009(String F0009) {
            this.F0009 = F0009;
        }

        public String getF0006_1() {
            return F0006_1;
        }

        public void setF0006_1(String F0006_1) {
            this.F0006_1 = F0006_1;
        }

        public String getF0007_1() {
            return F0007_1;
        }

        public void setF0007_1(String F0007_1) {
            this.F0007_1 = F0007_1;
        }
    }
}

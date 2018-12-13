package com.example.administrator.zqzy.bean;

import java.util.List;

public class LoginBean {

    /**
     * code : 0
     * data :
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"F0001":10008}]
     */

    private String code;
    private String data;
    private String detial;
    private String message;
    private List<MyDynamicDataBean> myDynamicData;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MyDynamicDataBean> getMyDynamicData() {
        return myDynamicData;
    }

    public void setMyDynamicData(List<MyDynamicDataBean> myDynamicData) {
        this.myDynamicData = myDynamicData;
    }

    public static class MyDynamicDataBean {
        /**
         * F0001 : 10008
         */
        private String F0005;

        public String getF0005() {
            return F0005;
        }

        public void setF0005(String f0005) {
            F0005 = f0005;
        }

        private String F0001;

        public String getF0001() {
            return F0001;
        }

        public void setF0001(String F0001) {
            this.F0001 = F0001;
        }
    }
}

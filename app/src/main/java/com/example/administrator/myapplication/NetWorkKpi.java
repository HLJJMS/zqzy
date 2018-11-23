package com.example.administrator.myapplication;

public class NetWorkKpi {
//    热门客
    public static String getRecommendClassListData(){
        return new StringBuffer().append(AppConfig.Service).append("GetRecommendClassListData?companyid=").append(AppConfig.CompanyId).toString();
    }
//    试听课（全部）
    public static String getClassListData(String state){
        return new StringBuffer().append(AppConfig.Service).append("GetClassListData?companyid=").append(AppConfig.CompanyId).append("&state=").append(state).toString();
    }
//    试听课（筛选）
    public static String getClassListData(String state,String type){
        return new StringBuffer().append(AppConfig.Service).append("GetClassListData?companyid=").append(AppConfig.CompanyId).append("&state=").append(state).append("&typename=").append(type).toString();
    }
//    教师列表
    public static String getTeacherData(){
        return new StringBuffer().append(AppConfig.Service).append("GetTeacherData?companyid=").append(AppConfig.CompanyId).toString();
    }
}

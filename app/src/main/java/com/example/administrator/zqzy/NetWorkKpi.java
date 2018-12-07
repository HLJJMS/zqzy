package com.example.administrator.zqzy;

public class NetWorkKpi {
    //    热门客
    public final static String getRecommendClassListData() {
        return new StringBuffer().append(AppConfig.Service).append("GetRecommendClassListData?companyid=").append(AppConfig.CompanyId).toString();
    }

    //    试听课（全部）
    public final static String getClassListData(String state) {
        return new StringBuffer().append(AppConfig.Service).append("GetClassListData?companyid=").append(AppConfig.CompanyId).append("&state=").append(state).toString();
    }

    //    试听课（筛选）
    public final static String getClassListData(String state, String type) {
        return new StringBuffer().append(AppConfig.Service).append("GetClassListData?companyid=").append(AppConfig.CompanyId).append("&state=").append(state).append("&typename=").append(type).toString();
    }

    //    教师列表
    public final static String getTeacherData() {
        return new StringBuffer().append(AppConfig.Service).append("GetTeacherData?companyid=").append(AppConfig.CompanyId).toString();
    }

    //    选课详情
    public final static String getSelectClassDetailData() {
        return new StringBuffer().append(AppConfig.Service).append("GetClassData?").toString();
    }

    //    咨询问题
    public final static String SubmitConsultInfo(String typename, String username, String phone, String content) {
        return new StringBuffer().append(AppConfig.Service).append("SubmitConsultInfo?userid=").append(AppConfig.UserId).append("&typename=").append(typename).append("&username=").append(username).append("&phone=").append(phone).append("&content=").append(content).append("&companyid=").append(AppConfig.CompanyId).toString();
    }

    //    获取知识库列表数据
    public final static String GetKnowledgeStoreListData(String typename, int page) {
        return new StringBuffer().append(AppConfig.Service).append("GetKnowledgeStoreListData?companyid=").append(AppConfig.CompanyId).append("&typename=").append(typename).append("&page=").append(page).append("&ShowNum=10").toString();
    }

    //    搜索知识库列表数据
    public final static String SearchKnowledgeStoreListData(String typename, int page, String keyword) {
        return new StringBuffer().append(AppConfig.Service).append("SearchKnowledgeStoreListData?companyid=").append(AppConfig.CompanyId).append("&typename=").append(typename).append("&page=").append(page).append("&ShowNum=700").append("&keyword=").append(keyword).toString();
    }

    //    知识库详情
    public final static String GetKnowledgeStoreData(String code) {
        return new StringBuffer().append(AppConfig.Service).append("GetKnowledgeStoreData?code=").append(code).toString();
    }
}

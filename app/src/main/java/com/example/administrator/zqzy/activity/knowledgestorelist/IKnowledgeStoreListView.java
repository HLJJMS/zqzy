package com.example.administrator.zqzy.activity.knowledgestorelist;

import com.example.administrator.zqzy.bean.KnowledgeStoreListBean;

import java.util.List;

public interface IKnowledgeStoreListView {
    void showToast(String s);
    void setData(List<KnowledgeStoreListBean.OrderMainBean> bean);
    void addData(List<KnowledgeStoreListBean.OrderMainBean> bean);
    void adapterDoAdd();
    void goNextView(String code);
}

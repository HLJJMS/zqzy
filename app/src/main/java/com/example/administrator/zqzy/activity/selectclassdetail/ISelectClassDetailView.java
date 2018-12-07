package com.example.administrator.zqzy.activity.selectclassdetail;

import com.example.administrator.zqzy.bean.SelectClassListBean;
import com.example.administrator.zqzy.bean.SelectClassdetailBean;
import com.example.administrator.zqzy.bean.SelectTeacherDetailBean;

public interface ISelectClassDetailView {
    void showToast(String s);
    void setData(SelectClassdetailBean selectClassdetailBean,SelectClassListBean selectClassListBean,SelectTeacherDetailBean selectTeacherDetailBean);
}

package com.example.administrator.myapplication.activity.freeclasslist;

import com.example.administrator.myapplication.bean.GetRecommendClassListDataBean;
import com.example.administrator.myapplication.bean.TeacherBean;

import java.util.List;

public interface IFreeClassListView {
    void showToast(String s);
    void setDataAdapter(List<GetRecommendClassListDataBean.OrderMainBean> list);
    void setDropDown(List<TeacherBean.OrderMainBean> list);

}

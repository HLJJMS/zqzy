package com.example.administrator.zqzy.activity.freeclasslist;

import com.example.administrator.zqzy.bean.GetRecommendClassListDataBean;
import com.example.administrator.zqzy.bean.TeacherBean;

import java.util.List;

public interface IFreeClassListView {
    void showToast(String s);
    void setDataAdapter(List<GetRecommendClassListDataBean.OrderMainBean> list);
    void setDropDown(List<TeacherBean.OrderMainBean> list);

}

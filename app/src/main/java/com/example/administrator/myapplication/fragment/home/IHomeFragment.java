package com.example.administrator.myapplication.fragment.home;

import com.example.administrator.myapplication.bean.GetRecommendClassListDataBean;

import java.util.List;

public interface IHomeFragment {
    void showToast(String s);
    void setDateToHotClassAdapter(List<GetRecommendClassListDataBean.OrderMainBean> list);
}

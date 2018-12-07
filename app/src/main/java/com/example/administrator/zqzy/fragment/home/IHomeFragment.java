package com.example.administrator.zqzy.fragment.home;

import com.example.administrator.zqzy.bean.GetRecommendClassListDataBean;

import java.util.List;

public interface IHomeFragment {
    void showToast(String s);
    void setDateToHotClassAdapter(List<GetRecommendClassListDataBean.OrderMainBean> list);
}

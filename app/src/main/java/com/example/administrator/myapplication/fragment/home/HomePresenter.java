package com.example.administrator.myapplication.fragment.home;

import com.example.administrator.myapplication.NetWorkKpi;
import com.example.administrator.myapplication.base.BaseOkHttp;
import com.example.administrator.myapplication.bean.GetRecommendClassListDataBean;

public class HomePresenter {
    private IHomeFragment view;
    private GetRecommendClassListDataBean getRecommendClassListDataBean;
    public HomePresenter(IHomeFragment view) {
        this.view = view;
    }
    public void getRecommendClassListData(){
        BaseOkHttp<GetRecommendClassListDataBean> baseOkHttp = new BaseOkHttp(getRecommendClassListDataBean,GetRecommendClassListDataBean.class);
        baseOkHttp.getData(NetWorkKpi.getRecommendClassListData(), new BaseOkHttp.CallBack<GetRecommendClassListDataBean>() {
            @Override
            public void success(GetRecommendClassListDataBean bean) {
                view.setDateToHotClassAdapter(bean.getOrderMain());
            }

            @Override
            public void fail(String s) {
                view.showToast(s);
            }
        });
    }
}

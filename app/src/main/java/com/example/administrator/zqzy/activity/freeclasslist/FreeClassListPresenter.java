package com.example.administrator.zqzy.activity.freeclasslist;

import com.example.administrator.zqzy.NetWorkKpi;
import com.example.administrator.zqzy.base.BaseOkHttp;
import com.example.administrator.zqzy.base.MyOkHttp;
import com.example.administrator.zqzy.bean.GetRecommendClassListDataBean;
import com.example.administrator.zqzy.bean.TeacherBean;

public class FreeClassListPresenter {
    private IFreeClassListView view;
    private GetRecommendClassListDataBean getRecommendClassListDataBean;
    private TeacherBean teacherBean;
    //    初始化课成列表
    public FreeClassListPresenter(IFreeClassListView view) {
        this.view = view;
    }

    public void getDataForRecyclerView() {
        BaseOkHttp<GetRecommendClassListDataBean> baseOkHttp = new BaseOkHttp(getRecommendClassListDataBean, GetRecommendClassListDataBean.class);
        baseOkHttp.getData(NetWorkKpi.getClassListData("0"), new BaseOkHttp.CallBack<GetRecommendClassListDataBean>() {
            @Override
            public void success(GetRecommendClassListDataBean bean) {
                view.setDataAdapter(bean.getOrderMain());
            }

            @Override
            public void fail(String s) {
                view.showToast(s);
            }
        });
    }

    //获取教师列表
    public void getTeacherData() {
        BaseOkHttp<TeacherBean> baseOkHttp = new BaseOkHttp(teacherBean, TeacherBean.class);
        baseOkHttp.getData(NetWorkKpi.getTeacherData(), new BaseOkHttp.CallBack<TeacherBean>() {
            @Override
            public void success(TeacherBean bean) {
                view.setDropDown(bean.getOrderMain());
            }

            @Override
            public void fail(String s) {
                view.showToast(s);
            }
        });
    }

    //筛选课成列表
    public void getClassListData(String type) {
        BaseOkHttp<GetRecommendClassListDataBean> baseOkHttp = new BaseOkHttp(getRecommendClassListDataBean, GetRecommendClassListDataBean.class);
        baseOkHttp.getData(NetWorkKpi.getClassListData("0", type), new BaseOkHttp.CallBack<GetRecommendClassListDataBean>() {
            @Override
            public void success(GetRecommendClassListDataBean bean) {
                view.setDataAdapter(bean.getOrderMain());
            }

            @Override
            public void fail(String s) {
                view.showToast(s);
            }
        });
    }
}

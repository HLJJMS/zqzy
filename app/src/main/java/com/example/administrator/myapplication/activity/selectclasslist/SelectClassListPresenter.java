package com.example.administrator.myapplication.activity.selectclasslist;

import com.example.administrator.myapplication.NetWorkKpi;
import com.example.administrator.myapplication.base.BaseOkHttp;
import com.example.administrator.myapplication.bean.GetRecommendClassListDataBean;
import com.example.administrator.myapplication.bean.TeacherBean;

public class SelectClassListPresenter {
    private ISelectClassListView view;
    private GetRecommendClassListDataBean getRecommendClassListDataBean;
    private TeacherBean teacherBean;
    public SelectClassListPresenter(ISelectClassListView view) {
        this.view = view;
    }
    public void getDataForRecyclerView() {
        BaseOkHttp<GetRecommendClassListDataBean> baseOkHttp = new BaseOkHttp(getRecommendClassListDataBean, GetRecommendClassListDataBean.class);
        baseOkHttp.getData(NetWorkKpi.getClassListData("1"), new BaseOkHttp.CallBack<GetRecommendClassListDataBean>() {
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
        baseOkHttp.getData(NetWorkKpi.getClassListData("1", type), new BaseOkHttp.CallBack<GetRecommendClassListDataBean>() {
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

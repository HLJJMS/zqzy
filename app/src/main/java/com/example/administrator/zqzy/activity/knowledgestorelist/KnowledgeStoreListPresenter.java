package com.example.administrator.zqzy.activity.knowledgestorelist;

import com.example.administrator.zqzy.NetWorkKpi;
import com.example.administrator.zqzy.base.MyOkHttp;
import com.example.administrator.zqzy.bean.KnowledgeStoreListBean;

public class KnowledgeStoreListPresenter {
    private IKnowledgeStoreListView view;
    private MyOkHttp myOkHttp = MyOkHttp.getInstance();
    private KnowledgeStoreListBean bean;
    public KnowledgeStoreListPresenter(IKnowledgeStoreListView view) {
        this.view = view;
    }

   public void getData(final String type, int page, final boolean isNew){
        myOkHttp.getSomeThing( KnowledgeStoreListBean.class,NetWorkKpi.GetKnowledgeStoreListData(type, page), new MyOkHttp.CallBack() {
            @Override
            public void success(Object s) {
                bean = KnowledgeStoreListBean.class.cast(s);
                if(isNew==true){
                    view.setData(bean.getOrderMain());
                }else{
                    view.addData(bean.getOrderMain());
                }
            }

            @Override
            public void fail(String s) {
                view.showToast(s);
            }
        });
   }
   public void searchData(final String type, int page,String keyWord){
       myOkHttp.getSomeThing( KnowledgeStoreListBean.class,NetWorkKpi.SearchKnowledgeStoreListData(type, page,keyWord), new MyOkHttp.CallBack() {
           @Override
           public void success(Object s) {
               bean = KnowledgeStoreListBean.class.cast(s);
                   view.setData(bean.getOrderMain());
           }

           @Override
           public void fail(String s) {
               view.showToast(s);
           }
       });
   }
}

package com.example.administrator.zqzy.activity.login;

import com.example.administrator.zqzy.NetWorkKpi;
import com.example.administrator.zqzy.base.MyOkHttp;
import com.example.administrator.zqzy.bean.LoginBean;

public class LoginPresenter {
    private LoginBean bean =  new LoginBean();
    private MyOkHttp myOkHttp = MyOkHttp.getInstance();
    private ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }
    public void login(String number, final String passWord){
        myOkHttp.getSomeThing(LoginBean.class, NetWorkKpi.Login(number, passWord), new MyOkHttp.CallBack() {
            @Override
            public void success(Object s) {
                bean = LoginBean.class.cast(s);
            }

            @Override
            public void fail(String s) {
                view.showToast(s);
            }
        });
    }
}

package com.example.administrator.zqzy.activity.selectclassdetail;

import android.os.AsyncTask;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.zqzy.AppConfig;
import com.example.administrator.zqzy.NetWorkKpi;
import com.example.administrator.zqzy.bean.SelectClassListBean;
import com.example.administrator.zqzy.bean.SelectClassdetailBean;
import com.example.administrator.zqzy.bean.SelectTeacherDetailBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SelectClassDetailPresenter {
    private ISelectClassDetailView view;
    private String s = "", code;
    private int defaultCode;
    private SelectClassdetailBean selectClassdetailBean;
    private SelectClassListBean selectClassListBean;
    private SelectTeacherDetailBean selectTeacherDetailBean;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private GetData getData = new GetData();

    public SelectClassDetailPresenter(ISelectClassDetailView view) {
        this.view = view;
    }

    public void getAllData(String code) {
        this.code = code;
        getData.execute();
    }


    class GetData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            RequestBody requestBody = new FormBody.Builder().add("code", code).add("companyid", AppConfig.CompanyId).build();
            Request request = new Request.Builder().post(requestBody).url(NetWorkKpi.getSelectClassDetailData()).build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                defaultCode = response.code();
                s = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            if (defaultCode == 200) {
                JSONObject jsonObject = JSONObject.parseObject(s);
                if (jsonObject.getString("data").equals(null)) {
                    view.showToast("请求异常");
                } else {
                    JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
                    if (jsonObjectData.getString("orderMain").equals(null)) {
                        view.showToast("部分数据丢失");
                    } else {
                        Gson gson = new Gson();
                        selectClassdetailBean = gson.fromJson(jsonObjectData.toJSONString(), SelectClassdetailBean.class);
                    }

                }
                if (jsonObject.getString("detial").equals(null)) {
                    view.showToast("请求异常");
                } else {
                    JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("detial"));
                    if (jsonObjectData.getString("orderDetail").equals(null)) {
                        view.showToast("部分数据丢失");
                    } else {
                        Gson gson = new Gson();
                        selectClassListBean = gson.fromJson(jsonObjectData.toJSONString(), SelectClassListBean.class);
                    }
                }

                if (jsonObject.getString("myDynamicData").equals(null)) {
                    view.showToast("部分数据丢失");
                } else {
                    Gson gson = new Gson();
                    selectTeacherDetailBean = gson.fromJson(jsonObject.toJSONString(), SelectTeacherDetailBean.class);
                }
                view.setData(selectClassdetailBean, selectClassListBean, selectTeacherDetailBean);
            }
            super.onPostExecute(s);
        }
    }

    class AddShop extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {

            return s;
        }
    }
}

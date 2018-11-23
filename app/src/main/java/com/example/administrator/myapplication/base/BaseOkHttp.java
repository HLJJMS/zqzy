package com.example.administrator.myapplication.base;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//    okhttp基类
public class BaseOkHttp<T> {
    //http协议默认的code；
    private int defaultCode;
    //http协议默认的信息
    private String message;
    private OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();
    private Handler handler;
    private CallBack<T> callBack;
    //   发送请求的连接
    String url = "";
    //   泛型相当于Bean.Class
    private Class<T> type;
    //   实体类
    private T bean;
    GetFunction getFunction = new GetFunction();

    //从外部传入泛型及bean
    public BaseOkHttp(T key, Class<T> type) {
        this.bean = key;
        this.type = type;
    }

    //   请求方法Get
    public void getData(String url, final CallBack<T> callBack) {
        this.url = url;
        getFunction.execute();
        this.callBack = callBack;
    }


    //    GET请求
    class GetFunction extends AsyncTask<Void, Integer, String> {
        String result = "";

        @Override
        protected String doInBackground(Void... voids) {
            Log.e("连接", url);
            Request request = new Request.Builder().url(url).get().build();
            try {
                Response response = client.newCall(request).execute();
                defaultCode = response.code();
                message = response.message();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e("进程", values.toString());
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("请求结果", s);
            if (defaultCode == 200) {
                JSONObject jsonObject = JSONObject.parseObject(s);
                if (jsonObject.getString("data").equals(null)) {
                    callBack.fail("请求异常");
                } else {
                    JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
                    if (jsonObjectData.getString("orderMain").equals(null)) {
                        callBack.fail("暂无数据");
                    } else {
                        Gson gson = new Gson();
                        bean = gson.fromJson(jsonObjectData.toJSONString(), type);
                        callBack.success(bean);
                    }

                }

            } else {
//                请求失败返回错误信息
                callBack.fail("请求异常");
            }
        }
    }

    //    回调接口(暴露在外部)
    public interface CallBack<T> {
        void success(T bean);

        void fail(String s);
    }
}

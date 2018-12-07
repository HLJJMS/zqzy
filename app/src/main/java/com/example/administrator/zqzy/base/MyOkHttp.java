package com.example.administrator.zqzy.base;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//    okhttp基类
public class MyOkHttp {
    //http协议默认的code；
    private int defaultCode;
    //http协议默认的信息
    private String message;
    private OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();
    private Handler handler;
    private String url;
    private Class<?> classzz;
    private Object object;
    private CallBack callBack;
    public static MyOkHttp instance;

    private MyOkHttp() {
    }

    ;

    public synchronized static MyOkHttp getInstance() {
        if (instance == null) {
            instance = new MyOkHttp();
        }
        return instance;
    }

    public void getSomeThing(Class claszz, String url, CallBack callBack) {
        GetFunction getFunction = new GetFunction();
        this.classzz = claszz;
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
                    if (jsonObjectData.getString("orderMain").equals(null)||jsonObjectData.getString("orderMain").equals("")||jsonObjectData.getString("orderMain").equals("[]")) {
                        callBack.fail("暂无数据");
                    } else {
                        Gson gson = new Gson();
                        object = gson.fromJson(jsonObjectData.toJSONString(), classzz);
                        callBack.success(object);
                    }

                }

            } else {
//                请求失败返回错误信息
                callBack.fail("请求异常");
            }
        }
    }

    //    回调接口(暴露在外部)
    public interface CallBack {
        void success(Object s);

        void fail(String s);
    }
}

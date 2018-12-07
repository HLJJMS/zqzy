package com.example.administrator.zqzy.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.zqzy.NetWorkKpi;
import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.bean.KnowDetailBodyBean;
import com.example.administrator.zqzy.bean.KnowDetailHeadBean;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/*知识库详情界面*/
public class KnowDetailActivity extends AppCompatActivity {
    @BindView(R.id.teacher)
    TextView teacher;
    //http协议默认的code；
    private int defaultCode;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    OkHttpClient okHttpClient = new OkHttpClient();
    @BindView(R.id.question)
    WebView question;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.answer)
    WebView answer;
    @BindView(R.id.title_question)
    TextView titleQuestion;
    String s = "";
    private boolean head = false, body = false;
    private KnowDetailHeadBean headBean = new KnowDetailHeadBean();
    private KnowDetailBodyBean bodyBean = new KnowDetailBodyBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_detail);
        ButterKnife.bind(this);
        title.setText("知识库");
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        s = intent.getStringExtra("code");
        GetData getData = new GetData();
        getData.execute();

    }

    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private void setData() {
        titleQuestion.setText(headBean.getOrderMain().get(0).getF0005());
            answer.loadDataWithBaseURL(null, bodyBean.getOrderDetail().get(0).getF0004(), "text/html", "utf-8", null);
            question.loadDataWithBaseURL(null, headBean.getOrderMain().get(0).getF0006(), "text/html", "utf-8", null);
        time.setText("发表于 ： " + headBean.getOrderMain().get(0).getF0012().substring(0, 10));
        teacher.setText(bodyBean.getOrderDetail().get(0).getF0006());
    }

    class GetData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Request request = new Request.Builder().url(NetWorkKpi.GetKnowledgeStoreData(s)).get().build();
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
            super.onPostExecute(s);
            Log.e("请求结果", s);
            if (defaultCode == 200) {
                JSONObject jsonObject = JSONObject.parseObject(s);
                if (jsonObject.getString("data").equals(null)) {
                    showToast("请求异常");
                    head = false;
                    body = false;
                } else {
                    JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
                    if (jsonObjectData.getString("orderMain").equals(null)||jsonObjectData.getString("orderMain").equals("")||jsonObjectData.getString("orderMain").equals("[]")) {
                        head = false;
                    } else {
                        head = true;

                    }
                    JSONObject jsonObjectDetail = JSONObject.parseObject(jsonObject.getString("detial"));
                    String a = jsonObjectDetail.getString("orderDetail");
                    if (jsonObjectDetail.getString("orderDetail").equals(null)||jsonObjectDetail.getString("orderDetail").equals("")||jsonObjectDetail.getString("orderDetail").equals("[]")) {
                        body = false;
                    } else {
                        body = true;

                    }
                    if (body == true && head == true) {
                        Gson gson = new Gson();
                        headBean = gson.fromJson(jsonObjectData.toJSONString(), KnowDetailHeadBean.class);
                        bodyBean = gson.fromJson(jsonObjectDetail.toJSONString(), KnowDetailBodyBean.class);
                        setData();
                    } else {
                        showToast("暂无数据");
                    }
                }

            } else {
//                请求失败返回错误信息
                showToast("请求异常");
            }
        }
    }
}

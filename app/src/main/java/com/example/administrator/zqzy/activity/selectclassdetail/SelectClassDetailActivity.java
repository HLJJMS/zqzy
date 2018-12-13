package com.example.administrator.zqzy.activity.selectclassdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.zqzy.AppConfig;
import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.adpter.SelectClassDetailListAdapter;
import com.example.administrator.zqzy.adpter.SelectClassDetailTeacherAdapter;
import com.example.administrator.zqzy.bean.SelectClassListBean;
import com.example.administrator.zqzy.bean.SelectClassdetailBean;
import com.example.administrator.zqzy.bean.SelectTeacherDetailBean;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*选课详情界面*/
public class SelectClassDetailActivity extends AppCompatActivity implements ISelectClassDetailView {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.menu)
    TextView menu;
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.class_detail)
    WebView classDetail;
    @BindView(R.id.man)
    TextView man;
    @BindView(R.id.place)
    TextView place;
    @BindView(R.id.much)
    LinearLayout much;
    Context context;
    @BindView(R.id.teacher)
    RecyclerView teacher;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.share)
    TextView share;
    @BindView(R.id.ok)
    TextView ok;
    private SelectClassDetailPresenter presenter;
    private String classDetailString, code, titleTxt, descriptionTxt;
    private SelectClassDetailListAdapter listAdapter;
    private SelectClassDetailTeacherAdapter teacherAdapter;
    private IWXAPI api;
    private ByteBuffer byteBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class_detail);
        ButterKnife.bind(this);
        context = this;
        title.setText("课程详情");
        presenter = new SelectClassDetailPresenter(this);
        aboutAdapter();
        getData();
    }

    //      教师,课程数量列表
    private void aboutAdapter() {
        listAdapter = new SelectClassDetailListAdapter(context);
        recycler.setAdapter(listAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        teacherAdapter = new SelectClassDetailTeacherAdapter(context);
        teacher.setLayoutManager(new LinearLayoutManager(context));
        teacher.setAdapter(teacherAdapter);
        recycler.setVisibility(View.VISIBLE);
        much.setVisibility(View.GONE);
        detail.setTextColor(getResources().getColor(R.color.black));
        menu.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    //接受列表传过来的数据并发送请求
    private void getData() {
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        presenter.getAllData(intent.getStringExtra("code"));
    }

    @OnClick({R.id.back, R.id.menu, R.id.detail, R.id.ok, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.menu:
                recycler.setVisibility(View.VISIBLE);
                much.setVisibility(View.GONE);
                detail.setTextColor(getResources().getColor(R.color.black));
                menu.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.detail:
                recycler.setVisibility(View.GONE);
                much.setVisibility(View.VISIBLE);
                detail.setTextColor(getResources().getColor(R.color.colorAccent));
                menu.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.ok:
                goShop();
                break;
            case R.id.share:
                shareFunction();
                break;
        }
    }

    private void goShop() {

    }

    private void shareFunction() {
        api = WXAPIFactory.createWXAPI(context, AppConfig.WXAppId, true);
        api.registerApp(AppConfig.WXAppId);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://web.yunerpoa.com/ZQZYPublicNumber/scheInfo/scheInfo.html?code=" + code;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = titleTxt;
        msg.description = descriptionTxt.substring(0,16);
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        req.transaction = "share";
        //调用api接口，发送数据到微信
        boolean a = api.sendReq(req);
        Log.e("结果",String.valueOf(a));
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    //    数据装在
    @Override
    public void setData(SelectClassdetailBean selectClassdetailBean, SelectClassListBean selectClassListBean, SelectTeacherDetailBean selectTeacherDetailBean) {
        Glide.with(context).load(AppConfig.ImgUrl + selectClassdetailBean.getOrderMain().get(0).getF0020()).into(img);
        try {
            classDetailString = URLDecoder.decode(selectClassdetailBean.getOrderMain().get(0).getF0007(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        titleTxt = selectClassdetailBean.getOrderMain().get(0).getF0003();
        descriptionTxt = StripHT(classDetailString);
        classDetail.loadDataWithBaseURL(null, classDetailString, "text/html;charset=utf-8", "UTF-8", null);
        man.setText(selectClassdetailBean.getOrderMain().get(0).getF0024());
        place.setText(selectClassdetailBean.getOrderMain().get(0).getF0025());
        listAdapter.setData(selectClassListBean.getOrderDetail());
        teacherAdapter.setdata(selectTeacherDetailBean.getMyDynamicData());
    }

    private String StripHT(String strHtml) {
        String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        return txtcontent;
    }
}

package com.example.administrator.zqzy.activity.selectclassdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
    private String classDetailString;
    private SelectClassDetailListAdapter listAdapter;
    private SelectClassDetailTeacherAdapter teacherAdapter;
    private IWXAPI api;
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
//        教师,课程数量列表
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
        presenter.getAllData(intent.getStringExtra("code"));
    }

    @OnClick({R.id.back, R.id.menu, R.id.detail ,R.id.ok,R.id.share})
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
//        presenter.
    }

    private void shareFunction() {
//        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
//        miniProgramObj.webpageUrl = "http://www.qq.com"; // 兼容低版本的网页链接
//        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
//        miniProgramObj.userName = "gh_d43f693ca31f";     // 小程序原始id
//        miniProgramObj.path = "/pages/media";            //小程序页面路径
//        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
//        msg.title = "小程序消息Title";                    // 小程序消息title
//        msg.description = "小程序消息Desc";               // 小程序消息desc
////        msg.thumbData = getThumb();                      // 小程序消息封面图片，小于128k
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("webpage");
//        req.message = msg;
//        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前支持会话
//        api.sendReq(req);
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
        classDetail.loadData(Html.fromHtml(classDetailString).toString(), "text/html;charset=utf-8", "UTF-8");
        man.setText(selectClassdetailBean.getOrderMain().get(0).getF0024());
        place.setText(selectClassdetailBean.getOrderMain().get(0).getF0025());
        listAdapter.setData(selectClassListBean.getOrderDetail());
        teacherAdapter.setdata(selectTeacherDetailBean.getMyDynamicData());
    }

}

package com.example.administrator.myapplication.activity.freeclasslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.FreeClassDetailActivity;
import com.example.administrator.myapplication.adpter.FreeClassListAdapter;
import com.example.administrator.myapplication.adpter.OneLine;
import com.example.administrator.myapplication.base.DropDownPhotoView;
import com.example.administrator.myapplication.base.DropDownView;
import com.example.administrator.myapplication.base.bean.DropDownItem;
import com.example.administrator.myapplication.base.bean.DropDownPhotoItem;
import com.example.administrator.myapplication.bean.GetRecommendClassListDataBean;
import com.example.administrator.myapplication.bean.TeacherBean;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FreeClassListActivity extends AppCompatActivity implements IFreeClassListView {


    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.drop_down_type)
    DropDownView dropDownType;
    @BindView(R.id.drop_down_name)
    DropDownPhotoView dropDownName;
    private String myDecode;
    private FreeClassListAdapter adapter;
    private Context context;
    private FreeClassListPresenter presenter;
    private LinkedList<DropDownItem> typeDown = new LinkedList<DropDownItem>();
    private LinkedList<DropDownPhotoItem> nameDown = new LinkedList<DropDownPhotoItem>();
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_class_list);
        ButterKnife.bind(this);
        title.setText("免费公开课");
        img.setVisibility(View.GONE);
        intent = new Intent();
        presenter = new FreeClassListPresenter(this);
        presenter.getDataForRecyclerView();
        presenter.getTeacherData();
        context = this;
        dropDownClick();
        aboutAdapter();
        addList();
    }



    private void dropDownClick() {
        dropDownType.setOnItemClickListener(new DropDownView.OnItemClickListener() {
            @Override
            public void onItemClick(String name, String Code, int position) {
                presenter.getClassListData(name);
                dropDownName.setText("请选择教师");
            }
        });
        dropDownName.setOnItemClickListener(new DropDownPhotoView.OnItemClickListener() {
            @Override
            public void onItemClick(String name, String Code, int position) {
                presenter.getClassListData(name);
                dropDownType.setText("请选择类别");
            }
        });
    }

    private void addList() {
        typeDown.add(new DropDownItem("外资企业财务", "外资企业财务"));
        typeDown.add(new DropDownItem("内资企业财务", "内资企业财务"));
        typeDown.add(new DropDownItem("最新政策解读", "最新政策解读"));
        typeDown.add(new DropDownItem("企业税务业务", "企业税务业务"));
        typeDown.add(new DropDownItem("用友软件操作", "用友软件操作"));
        typeDown.add(new DropDownItem("用友软件实施", "用友软件实施"));
        dropDownType.setmData(typeDown);
    }

    private void aboutAdapter() {
        adapter = new FreeClassListAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new OneLine(context));
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new FreeClassListAdapter.OnItemClick() {
            @Override
            public void onItemClick(String url) throws UnsupportedEncodingException {
                intent.putExtra("url",url);
                intent.setClass(context,FreeClassDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.back, R.id.img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.img:
                showSelect();
                break;
        }
    }

    private void showSelect() {


    }

    @Override
    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDataAdapter(List<GetRecommendClassListDataBean.OrderMainBean> list) {
        adapter.setDate(list);
    }

    @Override
    public void setDropDown(List<TeacherBean.OrderMainBean> list) {
        for (TeacherBean.OrderMainBean bean : list) {
            nameDown.add(new DropDownPhotoItem(bean.getF0016(), bean.getF0002(), bean.getF0017()));
        }
        dropDownName.setmData(nameDown);
    }

}

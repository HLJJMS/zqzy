package com.example.administrator.zqzy.activity.selectclasslist;

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

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.activity.selectclassdetail.SelectClassDetailActivity;
import com.example.administrator.zqzy.base.OneLineCommon;
import com.example.administrator.zqzy.adpter.SelectClassListAdapter;
import com.example.administrator.zqzy.base.DropDownPhotoView;
import com.example.administrator.zqzy.base.DropDownView;
import com.example.administrator.zqzy.base.bean.DropDownItem;
import com.example.administrator.zqzy.base.bean.DropDownPhotoItem;
import com.example.administrator.zqzy.bean.GetRecommendClassListDataBean;
import com.example.administrator.zqzy.bean.TeacherBean;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*选课列表界面*/
public class SelectClassListActivity extends AppCompatActivity implements ISelectClassListView {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.drop_down_type)
    DropDownView dropDownType;
    @BindView(R.id.drop_down_name)
    DropDownPhotoView dropDownName;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private SelectClassListAdapter adapter;
    private Context context;
    private SelectClassListPresenter presenter;
    private LinkedList<DropDownItem> typeDown = new LinkedList<DropDownItem>();
    private LinkedList<DropDownPhotoItem> nameDown = new LinkedList<DropDownPhotoItem>();
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class_list);
        ButterKnife.bind(this);
        title.setText("选课列表");
        img.setVisibility(View.GONE);
        context = this;
        presenter = new SelectClassListPresenter(this);
        addList();
        dropDownClick();
        aboutRecyclerView();
        presenter.getTeacherData();
    }
//        关于列表的操作
    private void aboutRecyclerView() {
        adapter = new SelectClassListAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new OneLineCommon(context));
        recycler.setAdapter(adapter);
        presenter.getDataForRecyclerView();
        adapter.setOnItemClickListener(new SelectClassListAdapter.OnItemClick() {
            @Override
            public void result(String code) {
                intent.putExtra("code", code);
                intent.setClass(context, SelectClassDetailActivity.class);
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
                break;
        }
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
//    向列表里加入数据
    @Override
    public void setDataAdapter(List<GetRecommendClassListDataBean.OrderMainBean> list) {
        adapter.setDate(list);
    }
//    向教师列表里计入数据
    @Override
    public void setDropDown(List<TeacherBean.OrderMainBean> list) {
        for (TeacherBean.OrderMainBean bean : list) {
            nameDown.add(new DropDownPhotoItem(bean.getF0016(), bean.getF0002(), bean.getF0017()));
        }
        dropDownName.setmData(nameDown);
    }
//    初始化下拉列表（类别）
    private void addList() {
        typeDown.add(new DropDownItem("外资企业财务", "外资企业财务"));
        typeDown.add(new DropDownItem("内资企业财务", "内资企业财务"));
        typeDown.add(new DropDownItem("最新政策解读", "最新政策解读"));
        typeDown.add(new DropDownItem("企业税务业务", "企业税务业务"));
        typeDown.add(new DropDownItem("用友软件操作", "用友软件操作"));
        typeDown.add(new DropDownItem("用友软件实施", "用友软件实施"));
        dropDownType.setmData(typeDown);
    }
//    下来列表点击事件
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

}

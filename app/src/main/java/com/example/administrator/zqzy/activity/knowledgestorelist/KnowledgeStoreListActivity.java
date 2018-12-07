package com.example.administrator.zqzy.activity.knowledgestorelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.activity.KnowDetailActivity;
import com.example.administrator.zqzy.adpter.KnowledgeStoreListAdapter;
import com.example.administrator.zqzy.base.OneLinePage;
import com.example.administrator.zqzy.bean.KnowledgeStoreListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*知识库列表*/
public class KnowledgeStoreListActivity extends AppCompatActivity implements IKnowledgeStoreListView {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.txt)
    EditText txt;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private KnowledgeStoreListAdapter adapter;
    private KnowledgeStoreListPresenter presenter;
    private int page = 1;
    private String type = "会计实务培训";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_store_list);
        ButterKnife.bind(this);
        title.setText("知识库");
        presenter = new KnowledgeStoreListPresenter(this);
        adoutRecyclerView();
    }

    //    关于知识库列表的操作
    private void adoutRecyclerView() {
        adapter = new KnowledgeStoreListAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new OneLinePage(this));
        presenter.getData(type, page, true);
    }

    //点击事件
    @OnClick({R.id.back, R.id.one, R.id.two, R.id.three, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.one:
                page = 1;
                type = "会计实务培训";
                changeColor(one, two, three);
                presenter.getData(type, page, true);
                break;
            case R.id.two:
                page = 1;
                type = "实务实践";
                changeColor(two, one, three);
                presenter.getData(type, page, true);
                break;
            case R.id.three:
                page = 1;
                type = "软件操作";
                changeColor(three, one, two);
                presenter.getData(type, page, true);
                break;
            case R.id.ok:
                presenter.searchData(type, 1, txt.getText().toString());
                break;
        }
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    //    向列表里首次装在数据
    @Override
    public void setData(List<KnowledgeStoreListBean.OrderMainBean> bean) {
        adapter.setData(bean);
        recycler.setAdapter(adapter);
    }

    //向列表里再次装在数据
    @Override
    public void addData(List<KnowledgeStoreListBean.OrderMainBean> bean) {
        adapter.addData(bean);
        recycler.setAdapter(adapter);
    }

    //adapter滑动到底监听
    @Override
    public void adapterDoAdd() {
        page = page + 1;
        presenter.getData(type, page, false);
    }

    //item点击跳转
    @Override
    public void goNextView(String code) {
        Intent intent = new Intent();
        intent.putExtra("code", code);
        intent.setClass(this, KnowDetailActivity.class);
        startActivity(intent);
    }

    //点击tab切换时效果变化
    private void changeColor(TextView green, TextView white, TextView whiteToo) {
        green.setTextColor(getColor(R.color.colorPrimary));
        green.setBackground(getDrawable(R.drawable.tab_for_konw_select));
        white.setTextColor(getColor(R.color.black));
        white.setBackground(getDrawable(R.drawable.tab_for_konw_no_select));
        whiteToo.setTextColor(getColor(R.color.black));
        whiteToo.setBackground(getDrawable(R.drawable.tab_for_konw_no_select));
    }
}

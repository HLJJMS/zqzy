package com.example.administrator.zqzy.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.activity.MyActivity;
import com.example.administrator.zqzy.activity.TrainingCampActivity;
import com.example.administrator.zqzy.activity.freeclasslist.FreeClassListActivity;
import com.example.administrator.zqzy.activity.knowledgestorelist.KnowledgeStoreListActivity;
import com.example.administrator.zqzy.activity.selectclassdetail.SelectClassDetailActivity;
import com.example.administrator.zqzy.activity.selectclasslist.SelectClassListActivity;
import com.example.administrator.zqzy.adpter.GridAdapter;
import com.example.administrator.zqzy.adpter.HotClassAdapter;
import com.example.administrator.zqzy.base.OneLineCommon;
import com.example.administrator.zqzy.adpter.ViewPageAdapter;
import com.example.administrator.zqzy.bean.GetRecommendClassListDataBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IHomeFragment {
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.point)
    LinearLayout point;
    Unbinder unbinder;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.grid)
    RecyclerView grid;
    @BindView(R.id.hot_class)
    RecyclerView hotClass;
    private HomePresenter presenter;
    private ImageView photo1, photo2, photo3;
    private List<ImageView> imgList = new ArrayList<ImageView>();
    private ViewPageAdapter menuAdapter = new ViewPageAdapter();
    private Handler handler = new Handler();
    private Context context;
    private Runnable runnable;
    private GridAdapter gridAdapter;
    private HotClassAdapter hotClassAdapter;
    private int oldPage = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();
        back.setVisibility(View.GONE);
        title.setText("直播培训");
        presenter = new HomePresenter(this);
        aboutRecyclerView();
        imgView();
        addPoint();
        viewpage.setAdapter(menuAdapter);
        menuAdapter.setData(imgList);
        runnableBuild();

        changeListener();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        handler.post(runnable);
        presenter.getRecommendClassListData();
    }

    private void aboutRecyclerView() {
        gridAdapter = new GridAdapter(this);
        grid.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, true));
        grid.setAdapter(gridAdapter);
        hotClassAdapter = new HotClassAdapter(this);
        hotClass.setLayoutManager(new LinearLayoutManager(getContext()));
        hotClass.addItemDecoration(new OneLineCommon(context));
        hotClass.setAdapter(hotClassAdapter);
    }

//    监听轮播图滚动

    private void changeListener() {
        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int i) {
                point.getChildAt(oldPage).setSelected(false);
                Log.e("我是三之前的页数", String.valueOf(oldPage));
                point.getChildAt(viewpage.getCurrentItem()).setSelected(true);
                Log.e("我是三现在的页数", String.valueOf(viewpage.getCurrentItem()));
                oldPage = viewpage.getCurrentItem();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                switch (i) {
                }
            }
        });
    }

//    轮播图小圆点

    private void addPoint() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
        params.leftMargin = 10;
        for (int i = 0; i < imgList.size(); i++) {
            ImageView img = new ImageView(context);
            img.setImageResource(R.drawable.shape_point_selector);
            img.setLayoutParams(params);
            point.addView(img);
        }
    }

//    runnable线程（轮播图）

    private void runnableBuild() {
        viewpage.setOffscreenPageLimit(1);
        handler.post(runnable);
        runnable = new Runnable() {
            @Override
            public void run() {
                viewpage.setCurrentItem((viewpage.getCurrentItem() + 1) % 3);
                handler.postDelayed(runnable, 3000);

            }
        };
    }

    //    添加img图片
    private void imgView() {
        photo2 = new ImageView(context);
        photo3 = new ImageView(context);
        photo1 = new ImageView(context);


        photo1.setBackgroundResource(R.mipmap.photo1);
        photo2.setBackgroundResource(R.mipmap.photo2);
        photo3.setBackgroundResource(R.mipmap.photo3);

        imgList.add(photo1);
        imgList.add(photo2);
        imgList.add(photo3);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDateToHotClassAdapter(List<GetRecommendClassListDataBean.OrderMainBean> list) {
        hotClassAdapter.setDate(list);
    }

    //    根据adapter的名字分发点击事件
    public void menuAdapterClick(String name) {
        switch (name) {
            case "会计就业训练营":
                Intent trainingCampActivity = new Intent(getContext(),TrainingCampActivity.class);
                startActivity(trainingCampActivity);
                break;
            case "知识库":
                Intent knowledgeStoreListActivity = new Intent(getContext(),KnowledgeStoreListActivity.class);
                startActivity(knowledgeStoreListActivity);
                break;
            case "我的":
                Intent myIntent = new Intent(getContext(),MyActivity.class);
                startActivity(myIntent);
                break;
            case "免费公开课":
                Intent freeClassListActivity = new Intent(getContext(), FreeClassListActivity.class);
                startActivity(freeClassListActivity);
                break;
            case "选课报名":
                Intent selectClassListActivity = new Intent(getContext(),SelectClassListActivity.class);
                startActivity(selectClassListActivity);
                break;
            case "观看回放":
                Intent freeClassListActivity2 = new Intent(getContext(), FreeClassListActivity.class);
                startActivity(freeClassListActivity2);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    public void goNextView(String id){
        Intent intent = new Intent();
        intent.putExtra("code",id);
        intent.setClass(getContext(),SelectClassDetailActivity.class);
        startActivity(intent);
    }
}

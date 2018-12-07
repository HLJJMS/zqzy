package com.example.administrator.zqzy.adpter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.fragment.home.HomeFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> txtList = new ArrayList<>();
    private List<Integer> imgList = new ArrayList<>();
    private DisplayMetrics metrics = new DisplayMetrics();
    private WeakReference<HomeFragment>  reference;
    private int width;

    public GridAdapter(HomeFragment fragment) {
       reference = new WeakReference<>(fragment);
        imgList.clear();
        this.imgList.add(R.mipmap.test);
        this.imgList.add(R.mipmap.knowledge);
        this.imgList.add(R.mipmap.my);
        this.imgList.add(R.mipmap.freeclass);
        this.imgList.add(R.mipmap.sign);
        this.imgList.add(R.mipmap.video);

        txtList.clear();
        txtList.add("会计就业训练营");
        txtList.add("知识库");
        txtList.add("我的");
        txtList.add("免费公开课");
        txtList.add("选课报名");
        txtList.add("观看回放");

        Resources resources = fragment.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        width = dm.widthPixels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        //获取单张图片宽度
//        int itemImgWidth = (width - 0 * (3 + 1)) / 3;
        int itemImgWidth = width / 3;
        //设置图片宽高
        ViewGroup.LayoutParams params = ((MyViewHolder) viewHolder).img.getLayoutParams();
        params.width = itemImgWidth/5*2;
        params.height = itemImgWidth/5*2;
        ((MyViewHolder) viewHolder).img.setLayoutParams(params);

        ViewGroup.LayoutParams paramsAll = ((MyViewHolder) viewHolder).all.getLayoutParams();
        paramsAll.width = itemImgWidth;
        paramsAll.height = itemImgWidth/3*2;
        ((MyViewHolder) viewHolder).all.setLayoutParams(paramsAll);
        ((MyViewHolder) viewHolder).img.setBackgroundResource(imgList.get(i));
        ((MyViewHolder) viewHolder).txt.setText(txtList.get(i));
        ((MyViewHolder) viewHolder).img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            reference.get().menuAdapterClick(txtList.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return txtList.size();
    }

    private  static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        LinearLayout all;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            all = (LinearLayout) itemView.findViewById(R.id.all);
            img = (ImageView) itemView.findViewById(R.id.img);
            txt = (TextView) itemView.findViewById(R.id.txt);
        }
    }
}

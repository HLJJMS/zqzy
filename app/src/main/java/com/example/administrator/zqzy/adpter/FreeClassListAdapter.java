package com.example.administrator.zqzy.adpter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zqzy.AppConfig;
import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.activity.freeclasslist.FreeClassListActivity;
import com.example.administrator.zqzy.bean.GetRecommendClassListDataBean;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FreeClassListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GetRecommendClassListDataBean.OrderMainBean> list = new ArrayList<>();
    private WeakReference <FreeClassListActivity> reference;
    public OnItemClick onItemClick;
    public FreeClassListAdapter(FreeClassListActivity activity) {
        reference = new WeakReference<>(activity);
    }

    public void setDate(List<GetRecommendClassListDataBean.OrderMainBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hot_class, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ((MyViewHolder) viewHolder).title.setText(list.get(i).getF0003());
        ((MyViewHolder) viewHolder).name.setText(list.get(i).getTeachName());
        ((MyViewHolder) viewHolder).rmb.setText("￥ " + list.get(i).getF0012());
        ((MyViewHolder) viewHolder).type.setText( list.get(i).getTeachheadship());
        ((MyViewHolder) viewHolder).number.setText(new StringBuffer().append("共").append(list.get(i).getF0021()).append("期课程").toString());
        Glide.with(reference.get()).load(AppConfig.ImgUrl + list.get(i).getF0020()).into(((MyViewHolder) viewHolder).img);
        ((MyViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onItemClick.onItemClick(list.get(i).getVideourl());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView rmb, name, title, type, number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rmb = (TextView) itemView.findViewById(R.id.rmb);
            name = (TextView) itemView.findViewById(R.id.name);
            title = (TextView) itemView.findViewById(R.id.title);
            type = (TextView) itemView.findViewById(R.id.type);
            number = (TextView) itemView.findViewById(R.id.number);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    public void setOnItemClickListener(OnItemClick onItemClickListener){
        this.onItemClick=onItemClickListener;
    }
    public interface OnItemClick{
        void onItemClick(String url) throws UnsupportedEncodingException;
    }
}

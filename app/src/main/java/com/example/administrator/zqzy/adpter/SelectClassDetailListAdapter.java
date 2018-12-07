package com.example.administrator.zqzy.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.bean.SelectClassListBean;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SelectClassDetailListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SelectClassListBean.OrderDetailBean> bean =new  ArrayList<>();
    private WeakReference<Context>  reference;

    public SelectClassDetailListAdapter(Context context) {
        reference = new WeakReference<>(context);
    }
    public void setData(List<SelectClassListBean.OrderDetailBean> bean ){
        this.bean.clear();
        this.bean.addAll(bean);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(reference.get()).inflate(R.layout.item_class_select_class,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((MyViewHolder) viewHolder).time.setText(new StringBuffer().append(bean.get(i).getF0006().substring(11,16)).append("-").append(bean.get(i).getF0007().substring(11,16)));
        ((MyViewHolder) viewHolder).title.setText(bean.get(i).getF0004());
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  title, time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

}

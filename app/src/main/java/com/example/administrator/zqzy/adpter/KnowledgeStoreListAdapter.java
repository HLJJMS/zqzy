package com.example.administrator.zqzy.adpter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.activity.knowledgestorelist.IKnowledgeStoreListView;
import com.example.administrator.zqzy.activity.knowledgestorelist.KnowledgeStoreListActivity;
import com.example.administrator.zqzy.bean.KnowledgeStoreListBean;

import java.util.ArrayList;
import java.util.List;


public class KnowledgeStoreListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<KnowledgeStoreListBean.OrderMainBean> bean = new ArrayList<>();
    private boolean isEnd = false;
    private IKnowledgeStoreListView activity;

    public KnowledgeStoreListAdapter(KnowledgeStoreListActivity activity) {
        this.activity = activity;
    }

    public void setData(List<KnowledgeStoreListBean.OrderMainBean> bean) {
        this.bean.clear();
        isEnd = false;
        this.bean.addAll(bean);
        notifyDataSetChanged();
    }

    public void addData(List<KnowledgeStoreListBean.OrderMainBean> bean) {
        if (bean.size() == 0) {
            isEnd = true;
        } else {
            isEnd = false;
        }
        this.bean.addAll(bean);
        notifyDataSetChanged();
    }

    //    他的返回值直接影响RecyclerView.ViewHolder里面的int值
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == bean.size() && isEnd == true) {
            return new EndOk(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_end, viewGroup, false));
        } else if (i == bean.size() && isEnd == false) {
            return new EndLoad(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loadding, viewGroup, false));
        } else {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_one_word, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (i < bean.size()) {
            ((MyViewHolder) viewHolder).txt.setText(bean.get(i).getF0005());
            ((MyViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.goNextView(bean.get(i).getF0000());
                }
            });

        }else if(isEnd == false){
            activity.adapterDoAdd();
            Log.e("到底啦",String.valueOf(i));
        }
    }

    @Override
    public int getItemCount() {
        return bean.size() + 1;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.select_item_value);
        }
    }

    private static class EndLoad extends RecyclerView.ViewHolder {

        public EndLoad(@NonNull View itemView) {
            super(itemView);

        }
    }

    private static class EndOk extends RecyclerView.ViewHolder {

        public EndOk(@NonNull View itemView) {
            super(itemView);

        }
    }
}

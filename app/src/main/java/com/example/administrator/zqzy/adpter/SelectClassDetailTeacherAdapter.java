package com.example.administrator.zqzy.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.zqzy.AppConfig;
import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.bean.SelectTeacherDetailBean;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class SelectClassDetailTeacherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private WeakReference<Context> reference;
    private List<SelectTeacherDetailBean.MyDynamicDataBean> bean = new ArrayList<>();
    private String detail;

    public SelectClassDetailTeacherAdapter(Context context) {
        reference = new WeakReference<>(context);
    }

    public void setdata(List<SelectTeacherDetailBean.MyDynamicDataBean> bean) {
        this.bean.clear();
        this.bean.addAll(bean);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(reference.get()).inflate(R.layout.item_teacher_select_class, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((MyViewHolder) viewHolder).name.setText(bean.get(i).getTeachname() + "    " + bean.get(i).getTeachheadship());
        Glide.with(reference.get()).load(AppConfig.ImgUrl + bean.get(i).getF0017()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(((MyViewHolder) viewHolder).img);
        try {
            detail = URLDecoder.decode(bean.get(i).getTeachIntroduction(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ((MyViewHolder) viewHolder).webView.loadData(Html.fromHtml(detail).toString(), "text/html;charset=utf-8", "UTF-8");
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        WebView webView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = (WebView) itemView.findViewById(R.id.web_view);
            name = (TextView) itemView.findViewById(R.id.name);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}

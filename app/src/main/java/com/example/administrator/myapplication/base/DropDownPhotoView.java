package com.example.administrator.myapplication.base;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.AppConfig;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.bean.DropDownPhotoItem;

import java.util.LinkedList;

public class DropDownPhotoView extends android.support.v7.widget.AppCompatTextView  {
    private String code,img;
    private String value;
    private LinkedList<DropDownPhotoItem> mData=new LinkedList<DropDownPhotoItem>();
    private Context mContext;
    private Drawable upIco, downIco;
    private PopupWindow popupWindow;
    public OnItemClickListener onItemClickListener;
    public DropDownPhotoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;

        init();
    }

    public DropDownPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        init();
    }

    public DropDownPhotoView(Context context) {
        super(context);
        this.mContext = context;

        init();
    }

    private void init() {
        // init ico
        upIco = getResources().getDrawable(R.mipmap.pic_confirm_dropup);
        upIco.setBounds(0, 0, upIco.getMinimumWidth(), upIco.getMinimumHeight());
        downIco = getResources().getDrawable(R.mipmap.pic_confirm_dropdown);
        downIco.setBounds(0, 0, downIco.getMinimumWidth(), downIco.getMinimumHeight());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        setText(value);
    }

    public LinkedList<DropDownPhotoItem> getmData() {
        return mData;
    }

    public void setmData(LinkedList<DropDownPhotoItem> mData) {
        this.mData.addAll(mData);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setCompoundDrawables(null, null, upIco, null);
            showSelectItem();
        }
        return super.onTouchEvent(event);
    }


    private void showSelectItem() {
        RecyclerView recyclerView = new RecyclerView(mContext);
        LinkedList<String> data = new LinkedList<String>();
        for (int i = 0; i < mData.size(); i++) {
            data.add(mData.get(i).getValue());
        }
        Adapter adapter = new Adapter(mData,mContext);
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(recyclerView, getWidth() - 10, 310, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(this, 5, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setCompoundDrawables(null, null, downIco, null);
//                dropStatus = false;
            }
        });

    }
    class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private LinkedList<DropDownPhotoItem> adapterData=new LinkedList<DropDownPhotoItem>();
        private Context context;
        public Adapter(LinkedList<DropDownPhotoItem> adapterData ,Context context) {
            this.adapterData.clear();
            this.adapterData.addAll(adapterData) ;
            this.context = context;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_item_photo, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
            ((MyViewHolder)viewHolder).name.setText(adapterData.get(i).getCode());
            Glide.with(context).load(AppConfig.ImgUrl +adapterData.get(i).getImg()).into(((MyViewHolder) viewHolder).img);
            ((MyViewHolder)viewHolder).name.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCode(mData.get(i).getCode());
                    setValue(mData.get(i).getCode());
                    popupWindow.dismiss();
                    onItemClickListener.onItemClick(mData.get(i).getCode(),mData.get(i).getValue(),i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return adapterData.size();
        }
        private class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView name,type;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.select_item_value);
                img = (ImageView) itemView.findViewById(R.id.photo);
            }
        }

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public  interface OnItemClickListener {
        void onItemClick(String name,String Code,int position);
    }
}

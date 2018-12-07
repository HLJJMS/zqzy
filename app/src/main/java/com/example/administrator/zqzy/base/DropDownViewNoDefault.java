package com.example.administrator.zqzy.base;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.base.bean.DropDownItem;

import java.util.LinkedList;

public class DropDownViewNoDefault extends android.support.v7.widget.AppCompatTextView  {
    private String code;
    private String value;
    private LinkedList<DropDownItem> mData=new LinkedList<DropDownItem>();
    private Context mContext;
    private Drawable upIco, downIco;
    public OnItemClickListener onItemClickListener;
    public DropDownViewNoDefault(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    public DropDownViewNoDefault(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public DropDownViewNoDefault(Context context) {
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

    public LinkedList<DropDownItem> getmData() {
        return mData;
    }

    public void setmData(LinkedList<DropDownItem> mData) {
        this.mData.addAll(mData);
        setText(mData.get(0).getValue());
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
        ListView listView = new ListView(mContext);
        LinkedList<String> data = new LinkedList<String>();
        for (int i = 0; i < mData.size(); i++) {
            data.add(mData.get(i).getValue());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                R.layout.select_item,
                data);
        listView.setVerticalScrollBarEnabled(false);
        listView.setAdapter(adapter);
        final PopupWindow popupWindow = new PopupWindow(listView, getWidth() - 10, 310, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(this, 5, 0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                setCode(mData.get(position).getCode());
                setValue(mData.get(position).getValue());
                popupWindow.dismiss();
//                onItemClickListener.onItemClick(mData.get(position).getCode(),mData.get(position).getValue(),position);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setCompoundDrawables(null, null, downIco, null);
//                dropStatus = false;
            }
        });
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public  interface OnItemClickListener {
        void onItemClick(String name, String Code, int position);
    }

}

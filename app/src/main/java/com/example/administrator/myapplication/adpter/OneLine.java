package com.example.administrator.myapplication.adpter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myapplication.R;

public class OneLine extends RecyclerView.ItemDecoration {
    Context context;
    public OneLine(Context context) {
        this.context = context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //设定底部边距为1px
        outRect.set(0, 0, 0, 1);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = 10;
        int right = parent.getWidth() - 10;
        Paint  p = new Paint();
//        LinearGradient linearGradient = new LinearGradient((float) left, 0, (float) right, 0, context.getResources().getColor(R.color.colorAccent), context.getResources().getColor(R.color.colorPrimary), Shader.TileMode.MIRROR);
        p.setColor(context.getResources().getColor(R.color.black));
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + 2;
            c.drawRect(left, top, right, bottom, p);
        }
    }
}
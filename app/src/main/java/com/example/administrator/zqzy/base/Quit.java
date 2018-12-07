package com.example.administrator.zqzy.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class Quit {
    private Boolean isEnd = false;
    public void ClickTwiceQuit(Context context){
        //点击两次退出
        Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isEnd = false;
            }
        };
        if (isEnd == true) {
            System.exit(0);
        } else {
            isEnd = true;
            Toast.makeText(context, "再按一次退出", Toast.LENGTH_LONG).show();
            h.sendEmptyMessageDelayed(1, 2000);
        }

    }
}

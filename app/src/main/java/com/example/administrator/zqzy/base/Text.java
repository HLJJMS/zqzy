package com.example.administrator.zqzy.base;

import java.util.LinkedList;
import java.util.List;

public class Text {
    List<String> list = new LinkedList<>();
    public  <T> T gggg(Class<T> clz,Object o){
        if(clz.isInstance(o)){
            return clz.cast(o);
        }
        return null;
    }

    private void ddd (){
        list.add("q");
        list.remove("q");
    }

}

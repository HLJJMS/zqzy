package com.example.administrator.myapplication.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myapplication.fragment.ErpFragment;
import com.example.administrator.myapplication.fragment.home.HomeFragment;
import com.example.administrator.myapplication.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> list = new ArrayList<Fragment>();


    public TabAdapter(FragmentManager fm) {
        super(fm);
        list.add(new HomeFragment());
        list.add(new ErpFragment());
        list.add(new MyFragment());
    }


    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

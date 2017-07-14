package com.mym.max.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class GankClassificationAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;                         //fragment列表
    private List<String> titles;                              //tab名的列表



    public GankClassificationAdapter(FragmentManager fm,List<Fragment> fragments,List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }
}

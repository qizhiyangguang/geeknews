package com.example.lenovo.geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.lenovo.geek.bean.V2exTabBean;

import java.util.ArrayList;

public class VpV2exAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<V2exTabBean> beans;

    public VpV2exAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<V2exTabBean> beans) {
        super(fm);
        this.fragments = fragments;
        this.beans = beans;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getTabs();
    }
}

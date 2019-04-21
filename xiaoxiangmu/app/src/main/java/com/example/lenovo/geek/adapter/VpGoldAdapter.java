package com.example.lenovo.geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.geek.bean.GoldShowBean;

import java.util.ArrayList;

public class VpGoldAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<GoldShowBean> list;
    private ArrayList<String> strings = new ArrayList<>();

    public VpGoldAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<GoldShowBean> list) {
        super(fm);
        this.fragments = fragments;
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            GoldShowBean goldShowBean = list.get(i);
            if (goldShowBean.isChecked) {
                strings.add(goldShowBean.title);
            }
        }
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
        return strings.get(position);
    }
}

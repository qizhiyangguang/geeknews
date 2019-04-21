package com.example.lenovo.geek.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.geek.base.BaseFragment;

import java.util.ArrayList;
/**
 * @author xts
 *         Created by asus on 2019/4/17.
 *         在viewpager不需要的Fragment需要销毁时,生命周期不一样,
 *         FragmentPagerAdapter:onDestoryView()
 *         FragmentStatePagerAdapter:onDetach();取消关联
 */
public class VpZhiHuAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> integers;
    private Context context;

    public VpZhiHuAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<Integer> integers, Context context) {
        super(fm);
        this.fragments = fragments;
        this.integers = integers;
        this.context = context;
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
        return context.getResources().getString(integers.get(position));
    }
}

package com.example.lenovo.geek.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.adapter.VpZhiHuAdapter;
import com.example.lenovo.geek.base.BaseFragment;
import com.example.lenovo.geek.presenter.ZhihuDailyNewsP;
import com.example.lenovo.geek.view.ZhihuDailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;
//马畔畔-1808D

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuDailyNewsFragment extends BaseFragment<ZhihuDailyNewsV, ZhihuDailyNewsP> {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Integer> integers;
    private ArrayList<Fragment> fragments;

    @Override
    protected ZhihuDailyNewsP initPresenter() {
        return new ZhihuDailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitles();
        initFragments();
        VpZhiHuAdapter vpZhiHuAdapter = new VpZhiHuAdapter(getChildFragmentManager(), fragments, integers, getContext());
        mVp.setAdapter(vpZhiHuAdapter);
        mTab.setupWithViewPager(mVp);

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new DailyNewsFragment());
        fragments.add(new ZhuTiFragment());
        fragments.add(new SectionsFragment());
        fragments.add(new HotFragment());
    }

    private void initTitles() {
        integers = new ArrayList<>();
        integers.add(R.string.dailyNews);
        integers.add(R.string.zhuti);
        integers.add(R.string.sections);
        integers.add(R.string.hot);
    }

}

package com.example.lenovo.geek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.base.BaseFragment;
import com.example.lenovo.geek.presenter.GankP;
import com.example.lenovo.geek.view.GankV;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment<GankV,GankP> {


    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }
}

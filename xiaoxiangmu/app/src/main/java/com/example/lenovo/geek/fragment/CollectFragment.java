package com.example.lenovo.geek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.base.BaseFragment;
import com.example.lenovo.geek.presenter.EmPtyP;
import com.example.lenovo.geek.view.EmptyV;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends BaseFragment<EmptyV, EmPtyP> {


    @Override
    protected EmPtyP initPresenter() {
        return new EmPtyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}

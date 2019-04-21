package com.example.lenovo.geek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.adapter.HotAdapter;
import com.example.lenovo.geek.bean.HotBean;
import com.example.lenovo.geek.model.HotModels;
import com.example.lenovo.geek.presenter.HotPresenters;
import com.example.lenovo.geek.view.HotView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements HotView {


    private View view;
    private XRecyclerView mXrlv;
    private HotAdapter hotAdapter;


    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_hot, container, false);
        initView(inflate);
        HotPresenters hotPresenters = new HotPresenters(new HotModels(), this);
        hotPresenters.onHot();
        return inflate;
    }

    private void initView(View inflate) {
        mXrlv = (XRecyclerView) inflate.findViewById(R.id.xrlv);
        mXrlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<HotBean.RecentBean> list = new ArrayList<>();
        hotAdapter = new HotAdapter(list, getContext());
        mXrlv.setAdapter(hotAdapter);
    }

    @Override
    public void onSuccess(HotBean bean) {
        hotAdapter.setAll(bean.getRecent());
    }

    @Override
    public void onFail(String msg) {

    }
}

package com.example.lenovo.geek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.adapter.WeChatAdapter;
import com.example.lenovo.geek.bean.WeChatBean;
import com.example.lenovo.geek.model.WeChatMs;
import com.example.lenovo.geek.presenter.WeChatPs;
import com.example.lenovo.geek.view.WeChatV;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends Fragment implements WeChatV {

    //    private String key = "52b7ec3471ac3bec6846577e79f20e4c";
//    private int num = 10;
    private int page = 1;
    private View view;
    private RecyclerView mRlv;
    private SmartRefreshLayout mSrl;
    private WeChatAdapter weChatAdapter;
    private WeChatPs weChatPs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_we_chat, container, false);
        initView(inflate);
        weChatPs = new WeChatPs(new WeChatMs(), this);
        initData();
        return inflate;
    }

    private void initData() {
        weChatPs.getWechat();
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        mSrl = (SmartRefreshLayout) inflate.findViewById(R.id.srl);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        //分割线
        mRlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        ArrayList<WeChatBean.NewslistBean> list = new ArrayList<>();
        weChatAdapter = new WeChatAdapter(list, getContext());
        mRlv.setAdapter(weChatAdapter);
        mSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                weChatAdapter.list.clear();
                initData();
                refreshlayout.finishRefresh();

            }
        });
        mSrl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                initData();
                refreshlayout.finishLoadmore();
            }
        });
        weChatAdapter.setList(new WeChatAdapter.setOnClickListener() {
            @Override
            public void setClickListener(View v, int porition) {

            }
        });

    }

    @Override
    public void onSuccess(WeChatBean bean) {
        mSrl.setEnableRefresh(true);
        mSrl.setEnableLoadmore(true);
        weChatAdapter.setAll(bean.getNewslist());
    }

    @Override
    public void onFail(String msg) {

    }

}

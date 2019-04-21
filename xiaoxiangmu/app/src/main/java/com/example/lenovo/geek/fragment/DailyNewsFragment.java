package com.example.lenovo.geek.fragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.activity.SnackBarActivity;
import com.example.lenovo.geek.adapter.RlvDailyNewsAdapter;
import com.example.lenovo.geek.activity.TimeActivity;
import com.example.lenovo.geek.base.BaseFragment;
import com.example.lenovo.geek.bean.DailyNewsBean;
import com.example.lenovo.geek.presenter.DailyNewsP;
import com.example.lenovo.geek.view.DailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyNewsFragment extends BaseFragment<DailyNewsV, DailyNewsP> implements DailyNewsV {

    private static final String TAG = "DailyNewsFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private RlvDailyNewsAdapter rlvDailyNewsAdapter;
    @BindView(R.id.floatbutton)
    FloatingActionButton floatbutton;

    public DailyNewsFragment() {
        // Required empty public constructor
    }


    @Override
    protected DailyNewsP initPresenter() {
        return new DailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    public void setData(DailyNewsBean bean) {
        rlvDailyNewsAdapter.setAll(bean);
    }

    @Override
    protected void initView() {
        super.initView();
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<DailyNewsBean.TopStoriesBean> banners = new ArrayList<>();
        ArrayList<DailyNewsBean.StoriesBean> newsList = new ArrayList<>();
        rlvDailyNewsAdapter = new RlvDailyNewsAdapter(newsList, banners, getContext());
        mRlv.setAdapter(rlvDailyNewsAdapter);
        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SnackBarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}

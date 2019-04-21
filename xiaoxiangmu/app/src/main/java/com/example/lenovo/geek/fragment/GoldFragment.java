package com.example.lenovo.geek.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.activity.ShowActivity;
import com.example.lenovo.geek.adapter.VpGoldAdapter;
import com.example.lenovo.geek.base.Constants;
import com.example.lenovo.geek.bean.GoldShowBean;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TabLayout mTab;
    private ImageView mIv;
    private ViewPager mVp;
    private ArrayList<GoldShowBean> list;
    private ArrayList<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_gold, container, false);
        initView(inflate);
        initTitle();
        setFragments();
        return inflate;
    }

    private void setFragments() {
        initFragment();
        VpGoldAdapter vpGoldAdapter = new VpGoldAdapter(getChildFragmentManager(), fragments, list);
        mVp.setAdapter(vpGoldAdapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GoldShowBean goldShowBean = list.get(i);
            if (goldShowBean.isChecked) {
                fragments.add(GoldDetailFragment.newInstance(goldShowBean.title));
            }
        }
    }

    private void initTitle() {
        list = new ArrayList<>();
        list.add(new GoldShowBean("工具资源", true));
        list.add(new GoldShowBean("Android", true));
        list.add(new GoldShowBean("IOS", true));
        list.add(new GoldShowBean("设计", true));
        list.add(new GoldShowBean("产品", true));
        list.add(new GoldShowBean("阅读", true));
        list.add(new GoldShowBean("前端", true));
        list.add(new GoldShowBean("后端", true));
    }

    private void initView(View inflate) {
        mTab = (TabLayout) inflate.findViewById(R.id.tab);
        mIv = (ImageView) inflate.findViewById(R.id.iv);
        mVp = (ViewPager) inflate.findViewById(R.id.vp);

        mIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,list);
        startActivityForResult(intent,100);
        //getActivity().startActivityForResult();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                list = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
                //刷新界面
                setFragments();
            }
        }
    }
}

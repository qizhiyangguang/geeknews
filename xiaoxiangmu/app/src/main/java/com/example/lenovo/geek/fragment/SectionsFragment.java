package com.example.lenovo.geek.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.activity.SectionsAcyivity;
import com.example.lenovo.geek.adapter.SectionsAdapter;
import com.example.lenovo.geek.bean.SectionsBean;
import com.example.lenovo.geek.model.SectionsModels;
import com.example.lenovo.geek.presenter.SectionsPresenters;
import com.example.lenovo.geek.view.SectionsView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment implements SectionsView {


    private View view;
    private RecyclerView mRlv;
    private SectionsAdapter sectionsAdapter;

    public SectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_sections, container, false);
        SectionsPresenters sectionsPresenters = new SectionsPresenters(new SectionsModels(), this);
        sectionsPresenters.getSections();
        initView(inflate);
        return inflate;
    }

    private void initView(final View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        final ArrayList<SectionsBean.DataBean> list = new ArrayList<>();
        sectionsAdapter = new SectionsAdapter(list, getContext());
        mRlv.setAdapter(sectionsAdapter);
        sectionsAdapter.setList(new SectionsAdapter.setOnClickListener() {
            @Override
            public void setClickListener(View v, int porition) {
                Intent intent = new Intent(getContext(), SectionsAcyivity.class);
                intent.putExtra("url",sectionsAdapter.list.get(porition).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSucces(SectionsBean bean) {
        sectionsAdapter.setAll(bean.getData());
    }

    @Override
    public void onFail(String msg) {

    }


}

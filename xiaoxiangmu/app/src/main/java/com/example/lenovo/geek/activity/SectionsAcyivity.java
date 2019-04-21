package com.example.lenovo.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.adapter.SecAdapter;
import com.example.lenovo.geek.bean.Sections;
import com.example.lenovo.geek.model.SectionsM;
import com.example.lenovo.geek.presenter.SectionsP;
import com.example.lenovo.geek.view.SectionsV;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class SectionsAcyivity extends AppCompatActivity implements SectionsV {

    private XRecyclerView mXrlv;
    private SectionsP sectionsP;
    private int page=1;
    private SecAdapter secAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections_acyivity);
        Intent intent = getIntent();
        int url = intent.getIntExtra("url", 0);
        sectionsP = new SectionsP(new SectionsM(), this);
//        initData();
        sectionsP.getSections(url);
        initView();
    }

    private void initData() {

    }

    private void initView() {
        mXrlv = (XRecyclerView) findViewById(R.id.xrlv);
        mXrlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Sections.StoriesBean> list = new ArrayList<>();
        secAdapter = new SecAdapter(list, this);
        mXrlv.setAdapter(secAdapter);

//        mXrlv.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                url=1;
//                secAdapter.list.clear();
//                initData();
//            }
//
//            @Override
//            public void onLoadMore() {
//                url++;
//                initData();
//            }
//        });
    }

    @Override
    public void onSuccess(Sections bean) {
        secAdapter.setAll(bean.getStories());
    }

    @Override
    public void onFail(String msg) {

    }
}

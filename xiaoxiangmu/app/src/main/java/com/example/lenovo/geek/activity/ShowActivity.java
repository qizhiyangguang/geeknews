package com.example.lenovo.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.adapter.RlvShowAdapter;
import com.example.lenovo.geek.base.Constants;
import com.example.lenovo.geek.bean.GoldShowBean;
import com.example.lenovo.geek.widget.SimpleTouchHelperCallBack;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private RecyclerView mRlv;

    private RlvShowAdapter rlvShowAdapter;
    private ArrayList<GoldShowBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        list = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra(Constants.DATA);
        mToolBar.setTitle(R.string.special_show);
        mToolBar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        rlvShowAdapter = new RlvShowAdapter(list, this);
        mRlv.setAdapter(rlvShowAdapter);
        //分割线
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack = new SimpleTouchHelperCallBack(rlvShowAdapter);
//        simpleTouchHelperCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);
        helper.attachToRecyclerView(mRlv);
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, list);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }
}

package com.example.lenovo.geek.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseMvpView, P extends BasePresenter> extends AppCompatActivity implements BaseMvpView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.bind(this);
        }
        initView();
        initListener();
        initData();
    }

    protected void initData() {
    }

    protected void initListener() {

    }

    protected void initView() {
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //切断V层和P层的联系
        mPresenter.onDestory();
        mPresenter = null;
    }
}

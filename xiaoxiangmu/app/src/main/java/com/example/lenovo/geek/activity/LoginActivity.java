package com.example.lenovo.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.base.BaseActivity;
import com.example.lenovo.geek.presenter.LoginP;
import com.example.lenovo.geek.utils.ToastUtil;
import com.example.lenovo.geek.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginView, LoginP> implements LoginView {

    @BindView(R.id.et1)
    EditText mEt1;
    @BindView(R.id.et2)
    EditText mEt2;
    @BindView(R.id.bt)
    Button mBt;


    @Override
    protected LoginP initPresenter() {
        return new LoginP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setData(String data) {
        mBt.setText(data);
    }

    @OnClick({R.id.bt})
    public void click(View v) {
        mPresenter.login();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public String getUserName() {
        return mEt1.getText().toString().trim();
    }

    @Override
    public String getPsd() {
        return mEt2.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShort(msg);
    }
}

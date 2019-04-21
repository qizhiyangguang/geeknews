package com.example.lenovo.geek.presenter;

import android.text.TextUtils;

import com.example.lenovo.geek.base.BasePresenter;
import com.example.lenovo.geek.bean.LoginBean;
import com.example.lenovo.geek.model.LoginM;
import com.example.lenovo.geek.net.ResultCallBack;
import com.example.lenovo.geek.utils.Logger;
import com.example.lenovo.geek.view.LoginView;

public class LoginP extends BasePresenter<LoginView> {

    private LoginM loginM;

    public void getData(){
        //获取数据,假设数据网络来的
        String data = "网络回来的数据";
        if (mView != null){
            //每次转换类型,麻烦
            //((LoginView)mView).setData(data);
            mView.setData(data);
        }
    }
    public void login() {
        String name = mView.getUserName();
        String psd = mView.getPsd();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psd)) {
            mView.showToast("用户名或者密码不能为空");
            return;
        }
        //进行网络请求去登陆M
        loginM.login(name, psd, new ResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (bean != null) {
                    Logger.logD("tag", bean.toString());
                    if (bean.getCode() == 200) {
                        //防止页面销毁，数据返回后设置页面的时空指针
                        if (mView != null) {
                            mView.showToast("登陆成功");
                        }
                    } else {
                        if (mView != null) {
                            mView.showToast("登陆失败");
                        }
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                Logger.logD("tag", msg);
                if (mView != null) {
                    mView.showToast("登陆失败");
                }
            }
        });
    }

    @Override
    protected void initModel() {
        loginM = new LoginM();
        models.add(loginM);
    }
}

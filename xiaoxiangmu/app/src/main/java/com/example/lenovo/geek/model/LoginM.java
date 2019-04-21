package com.example.lenovo.geek.model;

import com.example.lenovo.geek.base.BaseModel;
import com.example.lenovo.geek.bean.LoginBean;
import com.example.lenovo.geek.net.ApiService;
import com.example.lenovo.geek.net.BaseObserver;
import com.example.lenovo.geek.net.HttpUtils;
import com.example.lenovo.geek.net.ResultCallBack;
import com.example.lenovo.geek.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class LoginM extends BaseModel {
    public void login(String name, String psd, final ResultCallBack callBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.uBaseUrl, ApiService.class);
        Observable<LoginBean> login = apiserver.login(name, psd);
        login.compose(RxUtils.<LoginBean>rxObserableSchedulerHelper())//切换
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        callBack.onSuccess(loginBean);
                    }
                });

    }
}

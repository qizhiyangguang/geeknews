package com.example.lenovo.geek.model;

import com.example.lenovo.geek.base.BaseModel;
import com.example.lenovo.geek.bean.DailyNewsBean;
import com.example.lenovo.geek.net.ApiService;
import com.example.lenovo.geek.net.BaseObserver;
import com.example.lenovo.geek.net.HttpUtils;
import com.example.lenovo.geek.net.ResultCallBack;
import com.example.lenovo.geek.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DailyNewsM extends BaseModel {
    public void getData(final ResultCallBack<DailyNewsBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.url, ApiService.class);
        Observable<DailyNewsBean> lastDailyNews = apiserver.getLastDailyNews();
        lastDailyNews.compose(RxUtils.<DailyNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        resultCallBack.onSuccess(dailyNewsBean);
                    }
                });
    }
}

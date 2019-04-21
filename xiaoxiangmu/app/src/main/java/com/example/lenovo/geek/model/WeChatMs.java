package com.example.lenovo.geek.model;

import android.util.Log;

import com.example.lenovo.geek.bean.WeChatBean;
import com.example.lenovo.geek.net.ApiService;
import com.example.lenovo.geek.net.WeChatCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatMs implements WeChatM {
    @Override
    public void getWechat( final WeChatCallBack chatCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<WeChatBean> data = apiService.getWechat();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChatBean bean) {
                        chatCallBack.onSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        chatCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

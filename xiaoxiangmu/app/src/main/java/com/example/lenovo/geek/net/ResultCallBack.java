package com.example.lenovo.geek.net;

import com.example.lenovo.geek.bean.LoginBean;

public interface ResultCallBack<T> {
    void onSuccess(T bean);

    void onFail(String msg);
}

package com.example.lenovo.geek.net;

import com.example.lenovo.geek.bean.HotBean;

public interface CallBack {
    void onSuccess(HotBean bean);

    void onFail(String msg);
}

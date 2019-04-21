package com.example.lenovo.geek.net;

import com.example.lenovo.geek.bean.SectionsBean;

public interface SectionsCallBack {
    void onSucces(SectionsBean bean);

    void onFail(String msg);
}

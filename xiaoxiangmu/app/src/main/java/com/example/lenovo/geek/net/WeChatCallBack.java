package com.example.lenovo.geek.net;

import com.example.lenovo.geek.bean.WeChatBean;

public interface WeChatCallBack {
    void onSuccess(WeChatBean bean);

    void onFail(String msg);
}

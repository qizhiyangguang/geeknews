package com.example.lenovo.geek.view;

import com.example.lenovo.geek.base.BaseMvpView;
import com.example.lenovo.geek.bean.WeChatBean;

public interface WeChatV{
    void onSuccess(WeChatBean bean);

    void onFail(String msg);
}

package com.example.lenovo.geek.view;

import com.example.lenovo.geek.bean.HotBean;

public interface HotView {
    void onSuccess(HotBean bean);

    void onFail(String msg);
}

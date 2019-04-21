package com.example.lenovo.geek.net;

import com.example.lenovo.geek.bean.Sections;

public interface SectionsCall {
    void onSuccess(Sections bean);

    void onFail(String msg);
}

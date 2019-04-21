package com.example.lenovo.geek.view;

import com.example.lenovo.geek.base.BaseMvpView;

public interface LoginView extends BaseMvpView{
      void setData(String data);
    String getUserName();

    String getPsd();

    void showToast(String msg);
}

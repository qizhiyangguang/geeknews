package com.example.lenovo.geek.view;

import com.example.lenovo.geek.base.BaseMvpView;
import com.example.lenovo.geek.bean.DailyNewsBean;

public interface DailyNewsV extends BaseMvpView{
    void setData(DailyNewsBean bean);
}

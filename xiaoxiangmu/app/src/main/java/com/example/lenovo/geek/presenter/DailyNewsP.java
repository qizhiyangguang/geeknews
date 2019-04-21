package com.example.lenovo.geek.presenter;

import com.example.lenovo.geek.base.BasePresenter;
import com.example.lenovo.geek.bean.DailyNewsBean;
import com.example.lenovo.geek.model.DailyNewsM;
import com.example.lenovo.geek.net.ResultCallBack;
import com.example.lenovo.geek.view.DailyNewsV;

public class DailyNewsP extends BasePresenter<DailyNewsV> {

    private DailyNewsM dailyNewsM;

    @Override
    protected void initModel() {
        dailyNewsM = new DailyNewsM();
        models.add(dailyNewsM);
    }

    public void getData() {
        dailyNewsM.getData(new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean bean) {
                if (bean != null) {
                    if (mView != null) {
                        mView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

package com.example.lenovo.geek.presenter;

import com.example.lenovo.geek.bean.HotBean;
import com.example.lenovo.geek.model.HotModel;
import com.example.lenovo.geek.net.CallBack;
import com.example.lenovo.geek.view.HotView;

public class HotPresenters implements HotPresenter, CallBack {
    private HotModel hotModel;
    private HotView hotView;

    public HotPresenters(HotModel hotModel, HotView hotView) {
        this.hotModel = hotModel;
        this.hotView = hotView;
    }

    @Override
    public void onHot() {
        if (hotModel != null) {
            hotModel.getHot(this);
        }
    }

    @Override
    public void onSuccess(HotBean bean) {
        if (hotView != null) {
            hotView.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String msg) {
        if (hotView != null) {
            hotView.onFail(msg);
        }
    }

}

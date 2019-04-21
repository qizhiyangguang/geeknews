package com.example.lenovo.geek.presenter;

import com.example.lenovo.geek.bean.SectionsBean;
import com.example.lenovo.geek.model.SectionsModel;
import com.example.lenovo.geek.net.SectionsCallBack;
import com.example.lenovo.geek.view.SectionsView;

public class SectionsPresenters implements SectionsPresenter, SectionsCallBack {
    private SectionsModel model;
    private SectionsView view;

    public SectionsPresenters(SectionsModel model, SectionsView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getSections() {
        if (model != null) {
            model.getSections(this);
        }
    }

    @Override
    public void onSucces(SectionsBean bean) {
        if (view != null) {
            view.onSucces(bean);
        }
    }

    @Override
    public void onFail(String msg) {
        if (view != null) {
            view.onFail(msg);
        }
    }

}

package com.example.lenovo.geek.presenter;

import com.example.lenovo.geek.bean.Sections;
import com.example.lenovo.geek.model.SectionM;
import com.example.lenovo.geek.net.SectionsCall;
import com.example.lenovo.geek.view.SectionsV;

public class SectionsP implements SectionP, SectionsCall {
    private SectionM model;
    private SectionsV mView;

    public SectionsP(SectionM model, SectionsV mView) {
        this.model = model;
        this.mView = mView;
    }


    @Override
    public void onSuccess(Sections bean) {
        if (mView != null) {
            mView.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String msg) {
        if (mView != null) {
            mView.onFail(msg);
        }
    }


    @Override
    public void getSections(int page) {
        if (model != null) {
            model.getSections(page, this);
        }
    }
}

package com.example.lenovo.geek.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseMvpView> {
    protected V mView;
    protected ArrayList<BaseModel> models = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(V view) {
        this.mView = view;
    }

    public void onDestory() {
        mView = null;
        if (models.size()>0) {
            for (BaseModel model : models) {
                model.onDestroy();
            }
        }
    }
}

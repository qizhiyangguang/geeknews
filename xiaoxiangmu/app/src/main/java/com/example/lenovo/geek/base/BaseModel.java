package com.example.lenovo.geek.base;

import io.reactivex.disposables.CompositeDisposable;

public class BaseModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void onDestroy() {
        compositeDisposable.clear();
    }
}

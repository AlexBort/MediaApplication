package com.example.mediaapplication.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private WeakReference<V> view;

    public void onBindView(V view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void onUnbindView() {
        this.view = null;
    }

    V view() {
        return this.view == null ? null : this.view.get();
    }


}

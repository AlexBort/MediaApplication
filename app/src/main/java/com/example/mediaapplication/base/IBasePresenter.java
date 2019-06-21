package com.example.mediaapplication.base;

public interface IBasePresenter<V> {

    void onBindView(V view);

    void onUnbindView();


}

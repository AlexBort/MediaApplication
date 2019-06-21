package com.example.mediaapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {

    protected T presenter;

    protected abstract T createPresenter();

    protected abstract int getLayoutId();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onUnbindView();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onBindView(this);
    }
}

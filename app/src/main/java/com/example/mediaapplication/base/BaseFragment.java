package com.example.mediaapplication.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {

    protected T presenter;

    protected abstract T createPresenter();

    protected abstract int getLayoutId();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
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

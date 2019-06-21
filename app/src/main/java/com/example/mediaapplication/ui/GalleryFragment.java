package com.example.mediaapplication.ui;

import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.presenter.GalleryPresenter;
import com.example.mediaapplication.view.IGalleryView;

public class GalleryFragment extends BaseFragment<GalleryPresenter> implements IGalleryView {


    @Override
    protected GalleryPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}

package com.example.mediaapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaapplication.R;
import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.presenter.FavouritesPresenter;
import com.example.mediaapplication.view.IFavouritesView;

public class FavouritesFragment extends BaseFragment<FavouritesPresenter> implements IFavouritesView {


    @Override
    protected FavouritesPresenter createPresenter() {
        return new FavouritesPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);



        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favourites;
    }
}

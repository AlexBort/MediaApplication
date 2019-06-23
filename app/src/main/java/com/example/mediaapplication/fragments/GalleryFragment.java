package com.example.mediaapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaapplication.R;
import com.example.mediaapplication.adapters.ViewPagerAdapter;
import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.presenter.GalleryPresenter;
import com.example.mediaapplication.view.IGalleryView;

import java.util.ArrayList;

public class GalleryFragment extends BaseFragment<GalleryPresenter> implements IGalleryView {

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getContext());
        return view;
    }


    @Override
    protected GalleryPresenter createPresenter() {
        ArrayList<String> listUrls = getArguments().getStringArrayList("list");
        int selectedPosition = getArguments().getInt("position");
        return new GalleryPresenter(selectedPosition, listUrls);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gallery;
    }

    @Override
    public void showGallery(ArrayList<String> listUrls, int selectedPosition) {
        viewPagerAdapter.initData(listUrls);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(selectedPosition, false);
    }
}

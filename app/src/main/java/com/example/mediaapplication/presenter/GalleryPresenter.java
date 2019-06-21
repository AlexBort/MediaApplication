package com.example.mediaapplication.presenter;

import com.example.mediaapplication.base.BasePresenter;
import com.example.mediaapplication.view.IGalleryView;

import java.util.ArrayList;

public class GalleryPresenter extends BasePresenter<IGalleryView> {

    private int position;
    private ArrayList<String> urlList;

    public GalleryPresenter(int position, ArrayList<String> urlList) {
        this.position = position;
        this.urlList = urlList;
    }

    @Override
    public void onBindView(IGalleryView view) {
        view.showGallery(urlList, position);
    }


}

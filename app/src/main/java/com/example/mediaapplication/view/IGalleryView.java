package com.example.mediaapplication.view;

import com.example.mediaapplication.base.IBaseView;

import java.util.ArrayList;

public interface IGalleryView extends IBaseView {
    void showGallery(ArrayList<String> listUrls, int selectedPosition);
}

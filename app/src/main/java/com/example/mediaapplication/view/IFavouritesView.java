package com.example.mediaapplication.view;

import com.example.mediaapplication.base.IBaseView;
import com.example.mediaapplication.model.Picture;

import java.util.List;

public interface IFavouritesView extends IBaseView {
    void showFavouritesList(List<Picture> list);
}

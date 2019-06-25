package com.example.mediaapplication.view;

import com.example.mediaapplication.base.IBaseView;
import com.example.mediaapplication.model.Picture;

import java.util.List;

public interface IListView extends IBaseView {

    void showImagesList(List<Picture> list);

}

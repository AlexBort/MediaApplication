package com.example.mediaapplication.view;

import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.base.IBaseView;

import java.util.List;

public interface IListView extends IBaseView {


    void showContentList(List<Picture> list);
}

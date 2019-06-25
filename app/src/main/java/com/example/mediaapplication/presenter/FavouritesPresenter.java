package com.example.mediaapplication.presenter;

import com.example.mediaapplication.base.BasePresenter;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.view.IFavouritesView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavouritesPresenter extends BasePresenter<IFavouritesView> {


    private List<Picture> getSavedList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Picture> results = realm.where(Picture.class).findAll();
        return results;
    }

    @Override
    public void onBindView(IFavouritesView view) {
        super.onBindView(view);
        List<Picture> list = getSavedList();
        view.showFavouritesList(list);
    }


}

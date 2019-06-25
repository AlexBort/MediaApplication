package com.example.mediaapplication.presenter;

import com.example.mediaapplication.base.BasePresenter;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.view.IFavouritesView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavouritesPresenter extends BasePresenter<IFavouritesView> {


    private List<Picture> getSavedList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Picture> results = realm.where(Picture.class).findAll();
        return results;
    }

    public void removeFromFavourites(Picture picture, IFavouritesView view) {
        findAndDeleteItem(picture.getLarge(), view);
    }

    private void findAndDeleteItem(String largeUrl, IFavouritesView view) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Picture> pictures = realm.where(Picture.class).equalTo("large", largeUrl).findAll();

        if (!pictures.isEmpty()) {
            realm.beginTransaction();
            for (int i = 0; i < pictures.size(); i++) {
                pictures.get(i).deleteFromRealm();
            }
            String message = "remove from the FAVOURITE list";
            view.showToastMessage(message);
            realm.commitTransaction();
            realm.close();
            view.showFavouritesList(getSavedList());
        }


    }

    @Override
    public void onBindView(IFavouritesView view) {
        super.onBindView(view);
        List<Picture> list = getSavedList();
        view.showFavouritesList(list);
    }


    public void onListItemPressed(IFavouritesView iFavouritesView, int position, ArrayList<String> urlList) {
        iFavouritesView.getNavigation().openGalleryFragment(position, urlList);
    }
}

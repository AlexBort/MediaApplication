package com.example.mediaapplication.presenter;

import com.example.mediaapplication.base.BasePresenter;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.view.IFavouritesView;
import com.example.mediaapplication.view.IListView;

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
        // TODO: 25.06.2019 to play with the realm


        findAndDeleteItem(picture.getLarge(), view);

//        RealmResults<Picture> list = getSavedList();
//        for (int i = 0; i < list.size(); i++) {
//            if (picture.getLarge().equals(list.get(i).getLarge())) {
//                list.get(i).deleteFromRealm();
//                configRealm().commitTransaction();
//            }
//        }


        //        realm.delete();


    }

    private void findAndDeleteItem(String largeUrl, IFavouritesView view) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Picture> pictures = realm.where(Picture.class).equalTo("large", largeUrl).findAll();
        realm.beginTransaction();
        if (!pictures.isEmpty()) {
            for (int i = 0; i < pictures.size(); i++) {
                pictures.get(i).deleteFromRealm();
            }
            String message = "remove from the FAVOURITE list";
            view.showToastMessage(message);
        }
        realm.commitTransaction();
    }

    @Override
    public void onBindView(IFavouritesView view) {
        super.onBindView(view);
        List<Picture> list = getSavedList();
        view.showFavouritesList(list);
    }


}

package com.example.mediaapplication.presenter;

import android.util.Log;

import com.example.mediaapplication.api.ApiManager;
import com.example.mediaapplication.api.ResponseListener;
import com.example.mediaapplication.base.BasePresenter;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.model.ServerResponse;
import com.example.mediaapplication.model.User;
import com.example.mediaapplication.view.IListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ListPresenter extends BasePresenter<IListView> {

    private ApiManager apiManager = new ApiManager();


    private void findAndDeleteItem(String largeUrl, IListView view) {
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
    public void onBindView(final IListView view) {
        super.onBindView(view);

//        view.initRecycler();
        apiManager.getResults(new ResponseListener() {
            @Override
            public void successResponse(ServerResponse response) {
                List<User> users = response.getResults();
                List<Picture> pictureList = new ArrayList<>();
                for (int i = 0; i < users.size(); i++) {
                    Picture picture = users.get(i).getPicture();
                    pictureList.add(picture);
                }
//                saveToDbByList(pictureList);
                view.showContentList(pictureList);
//                getSavedList();
            }

            @Override
            public void failureResponse() {

            }
        });
    }

    private void saveToDbByList(List<Picture> listPicture) {
        Realm realm = configRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (int i = 0; i < listPicture.size(); i++) {
                    realm.copyToRealm(listPicture.get(i));
                }

            }
        });
    }

    private void saveToDbByModel(Picture picture) {
        Realm realm = configRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(picture);
            }
        });
    }

    private Realm configRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        return Realm.getDefaultInstance();
    }


    public void onListItemPressed(IListView view, int position, ArrayList<String> urlList) {
        view.getNavigation().openGalleryFragment(position, urlList);
    }

    public void removeFromFavourites(Picture picture, IListView view) {
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

    public void addToFavouritesList(Picture picture, IListView view) {
        saveToDbByModel(picture);
        String message = "added to the FAVOURITE list";
        view.showToastMessage(message);
    }

    public void onIconFavouritePressed(IListView iListView) {
        iListView.getNavigation().openFavouriteFragment();
    }
}

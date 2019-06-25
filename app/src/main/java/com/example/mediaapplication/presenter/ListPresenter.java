package com.example.mediaapplication.presenter;

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

        apiManager.getResults(new ResponseListener() {
            @Override
            public void successResponse(ServerResponse response) {
                if (response != null) {
                    List<User> users = response.getResults();
                    List<Picture> pictureList = new ArrayList<>();
                    for (int i = 0; i < users.size(); i++) {
                        Picture picture = users.get(i).getPicture();
                        pictureList.add(picture);
                    }
                    view.showImagesList(pictureList);
                }

            }

            @Override
            public void failureResponse() {
                String s = "BAD REQUEST";
                view.showToastMessage(s);
            }
        });
    }


    private void saveToDb(Picture picture) {
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
        findAndDeleteItem(picture.getLarge(), view);
    }

    public void addToFavouritesList(Picture picture, IListView view) {
        saveToDb(picture);
        String message = "added to the FAVOURITE list";
        view.showToastMessage(message);
    }

    public void onIconFavouritePressed(IListView iListView) {
        iListView.getNavigation().openFavouriteFragment();
    }
}

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

public class ListPresenter extends BasePresenter<IListView> {

    private ApiManager apiManager = new ApiManager();


    @Override
    public void onBindView(final IListView view) {
        super.onBindView(view);

        apiManager.getResults(new ResponseListener() {
            @Override
            public void successResponse(ServerResponse response) {
                List<User> users = response.getResults();
                List<Picture> pictureList = new ArrayList<>();
                for (int i = 0; i < users.size(); i++) {
                    Picture picture = users.get(i).getPicture();
                    pictureList.add(picture); }
                view.showContentList(pictureList);
            }

            @Override
            public void failureResponse() {

            }
        });
    }


    public void onListItemPressed(IListView view) {

    }

}

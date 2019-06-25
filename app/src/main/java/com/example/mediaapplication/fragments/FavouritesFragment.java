package com.example.mediaapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mediaapplication.R;
import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.presenter.FavouritesPresenter;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler_items.ImageItem;
import com.example.mediaapplication.view.IFavouritesView;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends BaseFragment<FavouritesPresenter> implements IFavouritesView {


    @Override
    protected FavouritesPresenter createPresenter() {
        return new FavouritesPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setVisibility(View.GONE);

        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void showFavouritesList(List<Picture> pictures) {
        List<RecyclerItem> list = new ArrayList<>();
        for (int i = 0; i < pictures.size(); i++) {
            list.add(new ImageItem(pictures.get(i)));
        }
        recyclerImpl.updateItems(list);
    }
}

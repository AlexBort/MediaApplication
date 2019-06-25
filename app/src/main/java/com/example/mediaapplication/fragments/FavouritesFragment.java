package com.example.mediaapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mediaapplication.R;
import com.example.mediaapplication.adapters.RecyclerAdapter;
import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.presenter.FavouritesPresenter;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler.RecyclerItemClickListener;
import com.example.mediaapplication.recycler.SwipeCallback;
import com.example.mediaapplication.recycler_items.ImageItem;
import com.example.mediaapplication.view.IFavouritesView;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends BaseFragment<FavouritesPresenter> implements IFavouritesView, RecyclerAdapter.FavouriteListener {

    private ArrayList<String> urlList = new ArrayList<>();
    private IFavouritesView iFavouritesView;

    @Override
    protected FavouritesPresenter createPresenter() {
        return new FavouritesPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        recyclerImpl.attachSwipe(new SwipeCallback(recyclerImpl.getRecyclerAdapter()));
        recyclerImpl.getRecyclerAdapter().setFavouriteListener(this);
        recyclerImpl.getRecyclerAdapter().setTag(RecyclerAdapter.Tag.DELETE);
        iFavouritesView = this;
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setVisibility(View.GONE);


        recyclerImpl.getRecyclerView().addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), (view1, position) -> {
            if (!urlList.isEmpty()) {
                presenter.onListItemPressed(iFavouritesView, position, urlList);
            }
        }));


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
            urlList.add(pictures.get(i).getLarge());
        }
        recyclerImpl.updateItems(list);
    }

    @Override
    public void deleteItem(Picture picture) {
        presenter.removeFromFavourites(picture, this);
    }

    @Override
    public void addToFavourites(Picture picture) {

    }


}

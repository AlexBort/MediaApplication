package com.example.mediaapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mediaapplication.R;
import com.example.mediaapplication.adapters.RecyclerAdapter;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.recycler.RecyclerItemClickListener;
import com.example.mediaapplication.recycler.SwipeCallback;
import com.example.mediaapplication.view.IListView;
import com.example.mediaapplication.presenter.ListPresenter;
import com.example.mediaapplication.recycler.RecyclerImpl;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler_items.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment<ListPresenter> implements IListView, RecyclerAdapter.FavouriteListener {

    private RecyclerImpl recyclerImpl;
    //    List<RecyclerItem> recyclerItems = new ArrayList<>();
    ArrayList<String> urlList = new ArrayList<>();
    ;
    private IListView iListView;

    @Override
    protected ListPresenter createPresenter() {
        return new ListPresenter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerImpl = new RecyclerImpl(recyclerView);
        recyclerImpl.attachSwipe(new SwipeCallback(recyclerImpl.getRecyclerAdapter()));
        recyclerImpl.getRecyclerAdapter().setDeleteListener(this);
        iListView = this;

        recyclerImpl.getRecyclerView().addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), (view1, position) -> {
            if (!urlList.isEmpty()) {
                presenter.onListItemPressed(iListView, position, urlList);
            }
        }));

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void showContentList(List<Picture> dataList) {
        List<RecyclerItem> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            list.add(new ImageItem(dataList.get(i)));
            Picture picture = dataList.get(i);
            urlList.add(picture.getLarge());
        }
        recyclerImpl.updateItems(list);
    }

    @Override
    public void deleteItem(int position) {

    }

    @Override
    public void addToFavourites() {

    }
}

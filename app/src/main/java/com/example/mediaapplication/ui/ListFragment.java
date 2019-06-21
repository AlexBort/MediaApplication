package com.example.mediaapplication.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaapplication.MainActivity;
import com.example.mediaapplication.R;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.recycler.RecyclerItemClickListener;
import com.example.mediaapplication.view.IListView;
import com.example.mediaapplication.presenter.ListPresenter;
import com.example.mediaapplication.recycler.RecyclerImpl;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler_items.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment<ListPresenter> implements IListView {

    private RecyclerImpl recyclerImpl;
    List<RecyclerItem> recyclerItems = new ArrayList<>();
    ArrayList<String> urlList = new ArrayList<>();
    private IListView iListView;

    @Override
    protected ListPresenter createPresenter() {
        return new ListPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        iListView = this;
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerImpl = new RecyclerImpl(recyclerView);


        recyclerImpl.getRecyclerView().addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.onListItemPressed(iListView);
            }
        }));


        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void showContentList(List<Picture> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            recyclerItems.add(new ImageItem(dataList.get(i)));
        }
        recyclerImpl.updateItems(recyclerItems);
    }
}

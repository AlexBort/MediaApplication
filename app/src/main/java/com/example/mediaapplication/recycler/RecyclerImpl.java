package com.example.mediaapplication.recycler;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.mediaapplication.adapters.RecyclerAdapter;

import java.util.List;

public class RecyclerImpl {

    private final RecyclerView recyclerView;
    private final RecyclerAdapter recyclerAdapter;
    private final LinearLayoutManager layoutManager;
    private ItemTouchHelper itemTouchHelper;

    public RecyclerImpl(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerAdapter = new RecyclerAdapter();
        this.layoutManager = new LinearLayoutManager(recyclerView.getContext());

        recyclerView.setLayoutManager(layoutManager);
        setDividerDecoration();
        recyclerView.setAdapter(recyclerAdapter);
    }


    // after set to Adapter
    public void attachSwipe(SwipeCallback swipeCallback) {
        itemTouchHelper = new ItemTouchHelper(swipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public ItemTouchHelper getItemTouchHelper() {
        return itemTouchHelper;
    }


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void updateItems(final List<RecyclerItem> items) {
        recyclerAdapter.setItems(items);
        recyclerAdapter.notifyDataSetChanged();
    }

    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    private void setDividerDecoration() {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
    }

}

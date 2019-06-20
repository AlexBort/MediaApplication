package com.example.mediaapplication.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RecyclerItem> mItems = new ArrayList<>();

    public void setItems(List<RecyclerItem> items) {
        this.mItems = items;
    }

    public boolean isEmpty() {
        return this.mItems.isEmpty();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerItemType itemType = RecyclerItemType.values()[viewType];
        return itemType.createViewHolder(viewGroup);
    }

    public RecyclerItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        getItem(i).onBindViewHolder((TypedViewHolder) viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public List<RecyclerItem> getItems() {
        return mItems;
    }
}

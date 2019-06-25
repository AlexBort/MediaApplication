package com.example.mediaapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler.RecyclerItemType;
import com.example.mediaapplication.recycler.TypedViewHolder;
import com.example.mediaapplication.recycler_items.ImageItem;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Tag tag;


    public enum Tag {
        ADD,
        DELETE
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }

    private List<RecyclerItem> mItems = new ArrayList<>();

    private FavouriteListener listener;

    public void setItems(List<RecyclerItem> items) {
        this.mItems = items;
    }

    public void setFavouriteListener(FavouriteListener listener) {
        this.listener = listener;
    }


    public interface FavouriteListener {
        void deleteItem(Picture picture);

        void addToFavourites(Picture picture);
    }

    public void deleteItem(int position) {
        ImageItem item = (ImageItem) mItems.get(position);
        listener.deleteItem(item.getPicture());

    }

    public void addToFavourites(int position) {
        notifyDataSetChanged();
        ImageItem item = (ImageItem) mItems.get(position);
        listener.addToFavourites(item.getPicture());
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

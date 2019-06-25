package com.example.mediaapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.mediaapplication.R;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler.RecyclerItemType;
import com.example.mediaapplication.recycler.TypedViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    private List<RecyclerItem> mItems = new ArrayList<>();


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private FavouriteListener listener;

    public void setItems(List<RecyclerItem> items) {
        this.mItems = items;
    }

    public void setFavouriteListener(FavouriteListener listener) {
        this.listener = listener;
    }


    public interface FavouriteListener {
        //  void deleteItem(List<RecyclerItem> list);
        void deleteItem(int position);

        void addToFavourites();
    }

    public void deleteItem(RecyclerView.ViewHolder viewHolder, int position) {
        listener.deleteItem(position);
//        viewHolder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
//        notifyDataSetChanged();
    }

    public void addToFavourites(RecyclerView.ViewHolder viewHolder, int position) {
//       viewHolder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        listener.addToFavourites();
//        notifyDataSetChanged();
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

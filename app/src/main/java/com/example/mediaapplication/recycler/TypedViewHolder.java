package com.example.mediaapplication.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TypedViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerItemType itemType;

    public TypedViewHolder(@NonNull View itemView, RecyclerItemType itemType) {
        super(itemView);
        this.itemType = itemType;
    }

}

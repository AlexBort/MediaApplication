package com.example.mediaapplication.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaapplication.R;
import com.example.mediaapplication.recycler_items.ImageItem;

public enum RecyclerItemType {


    IMAGE_ITEM() {
        @Override
        public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
            View itemView = RecyclerItemType.viewFromResource(R.layout.image_item, parent);
            return new ImageItem.Holder(itemView, this);
        }
    };

    public abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent);

    private static View viewFromResource(int resourceId, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(resourceId, parent, false);
    }

    }

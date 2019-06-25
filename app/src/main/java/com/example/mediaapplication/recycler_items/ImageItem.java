package com.example.mediaapplication.recycler_items;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.example.mediaapplication.R;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler.RecyclerItemType;
import com.example.mediaapplication.recycler.TypedViewHolder;
import com.squareup.picasso.Picasso;

public class ImageItem implements RecyclerItem<ImageItem.Holder> {

    private Picture picture;

    public Picture getPicture() {
        return picture;
    }

    public ImageItem(Picture picture) {
        this.picture = picture;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (picture.getLarge() != null && !picture.getLarge().isEmpty()) {
            Picasso.get().load(picture.getLarge()).into(holder.imageView);
        }
    }

    @Override
    public RecyclerItemType getTypeView() {
        return RecyclerItemType.IMAGE_ITEM;
    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    public static class Holder extends TypedViewHolder {

        private final ImageView imageView;

        public Holder(@NonNull View itemView, RecyclerItemType itemType) {
            super(itemView, itemType);
            imageView = itemView.findViewById(R.id.image);
        }


    }

}

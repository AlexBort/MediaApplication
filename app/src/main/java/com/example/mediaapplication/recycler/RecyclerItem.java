package com.example.mediaapplication.recycler;

public interface RecyclerItem<H extends TypedViewHolder> {

    void onBindViewHolder(H holder, int position);

    RecyclerItemType getTypeView();

    String getId();

}

package com.weather.mastertao.masterweather.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class Mapping<Pojo, Holder extends RecyclerView.ViewHolder> {

    private Class clazz;
    private int spanSize;

    public Mapping(Class clazz) {
        this.clazz = clazz;
        this.spanSize = 1;
    }

    public Class supportType(){
        return clazz;
    }

    public abstract Holder getViewHolder(ViewGroup parent);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, Pojo pojo);

    public Mapping spanSize(int spanSize) {
        this.spanSize = spanSize;
        return this;
    }

    public int getSpanSize() {
        return spanSize;
    }
}

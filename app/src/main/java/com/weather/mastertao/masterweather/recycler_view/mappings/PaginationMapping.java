package com.weather.mastertao.masterweather.recycler_view.mappings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.domain.Pagination;
import com.weather.mastertao.masterweather.recycler_view.Mapping;

public class PaginationMapping extends Mapping<Pagination, PaginationMapping.PaginationViewHolder> {

    public PaginationMapping(Class clazz) {
        super(clazz);
    }

    @Override
    public PaginationViewHolder getViewHolder(ViewGroup parent) {
        return new PaginationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pagination, parent, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, Pagination pagination) {

    }

    public static class PaginationViewHolder extends RecyclerView.ViewHolder{
        public PaginationViewHolder(View itemView) {
            super(itemView);
        }
    }

}

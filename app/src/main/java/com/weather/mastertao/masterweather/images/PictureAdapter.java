package com.weather.mastertao.masterweather.images;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.domain.TenorImageItem;

import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter {

    private final List<TenorImageItem> imageUrls = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PictureViewHolder holder = new PictureViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cards, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PictureViewHolder pictureViewHolder = (PictureViewHolder) holder;
        Glide.with(holder.itemView).load(imageUrls.get(position).url).into(pictureViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public void clearImage(){
        imageUrls.clear();
        notifyDataSetChanged();
    }

    public void addImages(List<TenorImageItem> images){
        imageUrls.addAll(images);
        notifyDataSetChanged();
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public PictureViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImage);

        }
    }
}

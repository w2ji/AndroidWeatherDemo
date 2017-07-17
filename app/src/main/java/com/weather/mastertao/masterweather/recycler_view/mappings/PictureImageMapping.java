package com.weather.mastertao.masterweather.recycler_view.mappings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.domain.TenorImageItem;
import com.weather.mastertao.masterweather.recycler_view.Mapping;

public class PictureImageMapping extends Mapping<TenorImageItem, PictureImageMapping.ImageViewHolder> {

    public PictureImageMapping(Class clazz) {
        super(clazz);
    }

    @Override
    public PictureImageMapping.ImageViewHolder getViewHolder(ViewGroup parent) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cards, parent, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, TenorImageItem imageItem) {
        ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;
        Glide.with(imageViewHolder.itemView).load(imageItem.url).into(imageViewHolder.imageView);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImage);
        }
    }
}

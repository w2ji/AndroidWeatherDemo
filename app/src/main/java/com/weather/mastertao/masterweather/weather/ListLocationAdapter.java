package com.weather.mastertao.masterweather.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.domain.GoogleAutoCompleteLocation;
import com.weather.mastertao.masterweather.domain.Predictions;

import java.util.ArrayList;
import java.util.List;

public class ListLocationAdapter extends RecyclerView.Adapter<ListLocationAdapter.ListLocationViewHolder> {

    private List<Predictions> locationList = new ArrayList<>();
    private WeatherPresenter presenter;

    public ListLocationAdapter(WeatherPresenter presenter) {
        this.presenter = presenter;
    }

    public void setLocationList(List<Predictions> locationList) {
        this.locationList = locationList;
        notifyDataSetChanged();
    }

    @Override
    public ListLocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.location_item, parent, false);
        return new ListLocationViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ListLocationViewHolder holder, int position) {
        final Predictions location = locationList.get(position);
        holder.location.setText(location.description);
        holder.itemView.setOnClickListener(view -> presenter.locationClicked(location.id));
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ListLocationViewHolder extends RecyclerView.ViewHolder{

        public TextView location;
        public View itemView;

        public ListLocationViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.location = itemView.findViewById(R.id.location);
        }
    }
}

package com.weather.mastertao.masterweather.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.weather.mastertao.masterweather.domain.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class UniversalRecyclerViewAdapter extends RecyclerView.Adapter {

    private final List<Object> objects = new ArrayList<>();
    private final HashMap<Class, Integer> registeredClasses = new HashMap<>();
    private Mapping[] mappings = new Mapping[0];

    @Inject
    public UniversalRecyclerViewAdapter() {
        objects.add(new Pagination());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mappings[viewType].getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mappings[holder.getItemViewType()].onBind(holder, objects.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        Object object = objects.get(position);
        Class clazz = object.getClass();
        Integer type = registeredClasses.get(clazz);
        if (type == null) {
            throw new RuntimeException("Class type not found!");
        }

        return type;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public int getSpanSize(int position){
        Object object = objects.get(position);
        return mappings[registeredClasses.get(object.getClass())].getSpanSize();
    }

    public void clear(){
        objects.clear();
        objects.add(new Pagination());
    }

    public void disableSpinner(){
        if (!objects.isEmpty()){
            objects.remove(objects.get(objects.size() - 1));
        }
    }

    public void appendMoreElements(List<Object> moreElements){
        Object pagination = null;
        if (!objects.isEmpty() && objects.get(objects.size() - 1) instanceof Pagination){
                pagination = objects.remove(objects.size() - 1);
        }
        objects.addAll(moreElements);
        if (pagination != null){
            objects.add(pagination);
        }
        notifyDataSetChanged();
    }

    public void registerMappings(Mapping... mappings) {
        this.mappings = mappings;
        for (int i = 0; i < mappings.length; i++) {
            registeredClasses.put(mappings[i].supportType(), i);
        }
    }
}

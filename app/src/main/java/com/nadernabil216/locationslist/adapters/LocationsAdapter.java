package com.nadernabil216.locationslist.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nadernabil216.locationslist.R;
import com.nadernabil216.locationslist.databinding.ItemLocationBinding;
import com.nadernabil216.locationslist.models.objects.Location;

import java.util.ArrayList;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    private ArrayList<Location> locations;
    private Context context ;

    public LocationsAdapter(Context context , ArrayList<Location> locations) {
        this.locations = locations;
        this.context = context ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemLocationBinding binding = DataBindingUtil.inflate(inflater,R.layout.item_location ,viewGroup ,false );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Location location = locations.get(i);
        viewHolder.itemLocationBinding.tvTitle.setText(location.getTitle());
        viewHolder.itemView.setOnClickListener(view -> {
            // TODO: 1/26/2019 go to detail screen
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemLocationBinding itemLocationBinding ;
        public ViewHolder(@NonNull ItemLocationBinding  itemLocationBinding) {
            super(itemLocationBinding.getRoot());
            this.itemLocationBinding=itemLocationBinding ;
        }
    }
}

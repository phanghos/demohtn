package com.taitascioredev.android.fhtndemo.feature.fakegps;

import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taitascioredev.android.fhtndemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrtatasciore on 13/01/18.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<SafeLocation> items;

    public LocationAdapter() {
        this(new ArrayList<SafeLocation>());
    }

    public LocationAdapter(List<SafeLocation> items) {
        this.items = items;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_row_layout, parent, false);
        return new LocationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        SafeLocation location = items.get(position);
        if (location.isMockedLocation()) {
            holder.location.setText("mocked location");
        } else {
            Location loc = location.getLocation();
            holder.location.setText("valid location=[" + loc.getLatitude() + "][" + loc.getLongitude() + "]");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(SafeLocation location) {
        items.add(0, location);
        notifyItemInserted(0);
        ScrollNotificationEvent.getInstance().post();
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView location;

        public LocationViewHolder(View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.tv_location);
        }
    }
}

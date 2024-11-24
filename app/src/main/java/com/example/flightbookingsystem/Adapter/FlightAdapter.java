package com.example.flightbookingsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<String> flightList;

    public FlightAdapter(List<String> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        holder.tvFlight.setText(flightList.get(position));
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView tvFlight;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFlight = itemView.findViewById(android.R.id.text1);
        }
    }
}

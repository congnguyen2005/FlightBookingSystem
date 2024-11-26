package com.example.flightbookingsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.model.Goi_y;

import java.util.List;

public class GoiYAdapter extends RecyclerView.Adapter<GoiYAdapter.GoiYViewHolder> {

    private List<Goi_y> flightList;

    public GoiYAdapter(List<Goi_y> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public GoiYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goi_y, parent, false);
        return new GoiYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoiYViewHolder holder, int position) {
        Goi_y flight = flightList.get(position);
        holder.tvRoute.setText(flight.getDeparture() + " → " + flight.getDestination());
        holder.tvTime.setText(flight.getDepartureTime() + " - " + flight.getArrivalTime());
        holder.tvPrice.setText(flight.getPrice() + " VND");
        holder.tvDate.setText(flight.getDepartureDate());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    static class GoiYViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoute, tvTime, tvPrice, tvDate;

        public GoiYViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoute = itemView.findViewById(R.id.tvRoute);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}

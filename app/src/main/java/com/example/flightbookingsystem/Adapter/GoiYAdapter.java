package com.example.flightbookingsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.ViewFlightInformationActivity;
import com.example.flightbookingsystem.model.Goi_y;

import java.util.List;

public class GoiYAdapter extends RecyclerView.Adapter<GoiYAdapter.ViewHolder> {

    private List<Goi_y> flightSuggestions;
    private Context context;

    public GoiYAdapter(List<Goi_y> flightSuggestions, Context context) {
        this.flightSuggestions = flightSuggestions;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight_suggestion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Goi_y flight = flightSuggestions.get(position);
        holder.tvDeparture.setText(flight.getDeparture());
        holder.tvDestination.setText(flight.getDestination());
        holder.tvTime.setText(flight.getDepartureTime() + " - " + flight.getArrivalTime());
        holder.tvPrice.setText("Giá: " + flight.getPrice() + " VNĐ");

        // Xử lý sự kiện nhấn nút "Chọn Chuyến Bay"
        holder.btnSelectFlight.setOnClickListener(v -> {
            // Khi người dùng chọn chuyến bay, có thể mở một màn hình chi tiết chuyến bay
            Intent intent = new Intent(context, ViewFlightInformationActivity.class);
            intent.putExtra("departure", flight.getDeparture());
            intent.putExtra("destination", flight.getDestination());
            intent.putExtra("departureTime", flight.getDepartureTime());
            intent.putExtra("arrivalTime", flight.getArrivalTime());
            intent.putExtra("price", flight.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return flightSuggestions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDeparture, tvDestination, tvTime, tvPrice;
        Button btnSelectFlight;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDeparture = itemView.findViewById(R.id.tvDeparture);
            tvDestination = itemView.findViewById(R.id.tvDestination);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnSelectFlight = itemView.findViewById(R.id.btnSelectFlight);  // Khai báo nút
        }
    }
}

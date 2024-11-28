package com.example.flightbookingsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentTransaction;

import com.example.flightbookingsystem.Fragnment.DatVeFragment;
import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.model.Goi_y;

import java.util.List;

public class GoiYAdapter extends RecyclerView.Adapter<GoiYAdapter.GoiYViewHolder> {

    private Context context;
    private List<Goi_y> flightList;

    public GoiYAdapter(Context context, List<Goi_y> flightList) {
        this.context = context;
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public GoiYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goi_y, parent, false);
        return new GoiYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoiYViewHolder holder, int position) {
        Goi_y flight = flightList.get(position);

        // Bind the flight data to the views
        holder.tvFlightNumber.setText(flight.getFlightNumber());
        holder.tvDeparture.setText("Departure: " + flight.getDeparture());
        holder.tvDestination.setText("Destination: " + flight.getDestination());
        holder.tvDepartureTime.setText("Departure Time: " + flight.getDepartureTime());
        holder.tvArrivalTime.setText("Arrival Time: " + flight.getArrivalTime());
        holder.tvPrice.setText("Price: " + flight.getPrice());

        // Set up the button to open the booking fragment when clicked
        holder.btnSelectFlight.setOnClickListener(v -> {
            DatVeFragment datVeFragment = DatVeFragment.newInstance(flight);

            // Use FragmentTransaction to replace the current fragment
            FragmentTransaction transaction =
                    ((androidx.appcompat.app.AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, datVeFragment);
            transaction.addToBackStack(null); // Optionally add to back stack for navigation
            transaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    // ViewHolder to hold flight item views
    public static class GoiYViewHolder extends RecyclerView.ViewHolder {
        TextView tvFlightNumber, tvDeparture, tvDestination, tvDepartureTime, tvArrivalTime, tvPrice;
        Button btnSelectFlight;

        public GoiYViewHolder(View itemView) {
            super(itemView);

            // Initialize the views
            tvFlightNumber = itemView.findViewById(R.id.tvFlightNumber);
            tvDeparture = itemView.findViewById(R.id.tvDeparture);
            tvDestination = itemView.findViewById(R.id.tvDestination);
            tvDepartureTime = itemView.findViewById(R.id.tvDepartureTime);
            tvArrivalTime = itemView.findViewById(R.id.tvArrivalTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnSelectFlight = itemView.findViewById(R.id.btnSelectFlight);
        }
    }
}

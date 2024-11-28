package com.example.flightbookingsystem.Fragnment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.model.Goi_y;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatVeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatVeFragment extends Fragment {


    private Goi_y selectedFlight;

    public static DatVeFragment newInstance(Goi_y Goi_y) {
        DatVeFragment fragment = new DatVeFragment();
        Bundle args = new Bundle();
        args.putSerializable("flight", Goi_y);  // Pass the flight object as Serializable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Get the flight object from the arguments
            selectedFlight = (Goi_y) getArguments().getSerializable("flight");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dat_ve, container, false);

        // Get references to the views in fragment_book_ticket layout
        TextView tvFlightNumber = rootView.findViewById(R.id.tvFlightNumber);
        TextView tvDeparture = rootView.findViewById(R.id.tvDeparture);
        TextView tvDestination = rootView.findViewById(R.id.tvDestination);
        TextView tvDepartureTime = rootView.findViewById(R.id.tvDepartureTime);
        TextView tvArrivalTime = rootView.findViewById(R.id.tvArrivalTime);
        TextView tvPrice = rootView.findViewById(R.id.tvPrice);

        // Set the flight details to the views
        if (selectedFlight != null) {
            tvFlightNumber.setText("Flight Number: " + selectedFlight.getFlightNumber());
            tvDeparture.setText("Departure: " + selectedFlight.getDeparture());
            tvDestination.setText("Destination: " + selectedFlight.getDestination());
            tvDepartureTime.setText("Departure Time: " + selectedFlight.getDepartureTime());
            tvArrivalTime.setText("Arrival Time: " + selectedFlight.getArrivalTime());
            tvPrice.setText("Price: " + selectedFlight.getPrice());
        }

        // Handle booking button click (Example)
        Button btnBookTicket = rootView.findViewById(R.id.btnBookTicket);
        btnBookTicket.setOnClickListener(v -> {
            // Perform booking logic here (for example, save to database, etc.)
            Toast.makeText(getContext(), "Ticket Booked Successfully", Toast.LENGTH_SHORT).show();
        });

        return rootView;
    }
}

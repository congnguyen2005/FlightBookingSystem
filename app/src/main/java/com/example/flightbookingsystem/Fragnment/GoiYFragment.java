package com.example.flightbookingsystem.Fragnment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.Adapter.GoiYAdapter;
import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.model.Goi_y;

import java.util.ArrayList;
import java.util.List;

public class GoiYFragment extends Fragment {

    private RecyclerView rvFlightSuggestions;
    private TextView tvSearchInfo;
    private GoiYAdapter goiYAdapter;
    private List<Goi_y> flightSuggestions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goi_y, container, false);

        rvFlightSuggestions = view.findViewById(R.id.rvFlightSuggestions);
        tvSearchInfo = view.findViewById(R.id.tvSearchInfo);

        rvFlightSuggestions.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            String departure = bundle.getString("departure");
            String destination = bundle.getString("destination");
            String departureDate = bundle.getString("departureDate");

            String searchInfo = "Khởi hành: " + departure + "\n" +
                    "Điểm đến: " + destination + "\n" +
                    "Ngày đi: " + departureDate;
            tvSearchInfo.setText(searchInfo);

            flightSuggestions = getFlightSuggestions(departure, destination);
            goiYAdapter = new GoiYAdapter(flightSuggestions);
            rvFlightSuggestions.setAdapter(goiYAdapter);
        }

        return view;
    }

    private List<Goi_y> getFlightSuggestions(String departure, String destination) {
        List<Goi_y> allFlights = new ArrayList<>();
        allFlights.add(new Goi_y("Hà Nội", "Hồ Chí Minh", "2024-11-26", "10:00", "12:00", 1500000));
        allFlights.add(new Goi_y("Đà Nẵng", "Hà Nội", "2024-11-26", "14:00", "16:00", 1200000));

        List<Goi_y> suggestions = new ArrayList<>();
        for (Goi_y flight : allFlights) {
            if (flight.getDeparture().equals(departure) && flight.getDestination().equals(destination)) {
                suggestions.add(flight);
            }
        }
        return suggestions;
    }
}

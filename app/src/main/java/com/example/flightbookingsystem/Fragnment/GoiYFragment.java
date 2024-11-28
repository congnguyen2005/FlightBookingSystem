package com.example.flightbookingsystem.Fragnment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private RecyclerView recyclerView;
    private GoiYAdapter adapter;
    private List<Goi_y> flightList;


     public GoiYFragment() {
        // Required empty public constructor
    }

    public static GoiYFragment newInstance(String param1, String param2) {
        GoiYFragment fragment = new GoiYFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_goi_y, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        flightList = new ArrayList<>();
        adapter = new GoiYAdapter(getContext(), flightList);
        recyclerView.setAdapter(adapter);

        // Get suggestions for flights
        getSuggestedFlights();

        return rootView;
    }

    private void getSuggestedFlights() {
        List<Goi_y> flights = loadSuggestedFlights();

        if (flights.isEmpty()) {
            Toast.makeText(getContext(), "No suggested flights.", Toast.LENGTH_SHORT).show();
        } else {
            flightList.clear();
            flightList.addAll(flights);
            adapter.notifyDataSetChanged();
        }
    }

    // Dummy function to simulate flight suggestions
    private List<Goi_y> loadSuggestedFlights() {
        List<Goi_y> flights = new ArrayList<>();

        // Sample flight suggestions for demonstration purposes
        for (int i = 0; i < 10; i++) {
            flights.add(new Goi_y("VietNamearline" + (i + 1), "New York", "Los Angeles", "2024-12-25 08:00", "2024-12-25 10:00", "$250"));
        }

        return flights;
    }
}
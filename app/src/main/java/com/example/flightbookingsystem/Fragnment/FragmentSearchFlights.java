package com.example.flightbookingsystem.Fragnment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.Adapter.GoiYMainAdapter;
import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.model.GoiYMain;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchFlights extends Fragment {

    private EditText etDeparture, etDestination;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private GoiYMainAdapter goiYMainAdapter;
    private List<GoiYMain> flightSuggestions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_flights, container, false);

        // Khởi tạo các thành phần giao diện
        etDeparture = view.findViewById(R.id.etDeparture);
        etDestination = view.findViewById(R.id.etDestination);
        btnSearch = view.findViewById(R.id.btnSearch);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Cài đặt RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        flightSuggestions = new ArrayList<>();
        goiYMainAdapter = new GoiYMainAdapter(flightSuggestions);
        recyclerView.setAdapter(goiYMainAdapter);

        // Xử lý sự kiện nút Tìm kiếm
        btnSearch.setOnClickListener(v -> {
            if (validateInputs()) {
                searchFlights();
            }
        });

        return view;
    }

    private boolean validateInputs() {
        String departure = etDeparture.getText().toString().trim();
        String destination = etDestination.getText().toString().trim();

        if (departure.isEmpty() || destination.isEmpty()) {
            Toast.makeText(getActivity(), "Vui lòng nhập sân bay khởi hành và đến!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (departure.equals(destination)) {
            Toast.makeText(getActivity(), "Sân bay khởi hành và đến không được trùng nhau!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void searchFlights() {
        // Giả lập dữ liệu gợi ý chuyến bay
        flightSuggestions.clear();
        flightSuggestions.add(new GoiYMain("VN123", "08:00 - 11:00", "1.200.000 VND"));
        flightSuggestions.add(new GoiYMain("VN234", "14:00 - 17:00", "1.500.000 VND"));
        flightSuggestions.add(new GoiYMain("VN345", "18:00 - 21:00", "1.300.000 VND"));

        // Cập nhật dữ liệu cho RecyclerView
        goiYMainAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Kết quả tìm kiếm đã được hiển thị.", Toast.LENGTH_SHORT).show();
    }
}

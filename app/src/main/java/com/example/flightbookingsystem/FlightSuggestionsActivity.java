package com.example.flightbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.Adapter.GoiYAdapter;
import com.example.flightbookingsystem.model.Goi_y;

import java.util.ArrayList;
import java.util.List;

public class FlightSuggestionsActivity extends AppCompatActivity {

    private RecyclerView rvFlightSuggestions;
    private GoiYAdapter goiYAdapter;
    private List<Goi_y> flightSuggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_suggestions);

        // Ánh xạ RecyclerView
        rvFlightSuggestions = findViewById(R.id.rvFlightSuggestions);
        rvFlightSuggestions.setLayoutManager(new LinearLayoutManager(this));

        // Nhận thông tin chuyến bay từ Intent
        Intent intent = getIntent();
        String departure = intent.getStringExtra("departure");
        String destination = intent.getStringExtra("destination");
        String departureDate = intent.getStringExtra("departureDate");
        String returnDate = intent.getStringExtra("returnDate");
        String passengers = intent.getStringExtra("passengers");

        // Gọi hàm để lọc và gợi ý chuyến bay
        flightSuggestions = getFlightSuggestions(departure, destination);

        // Đặt dữ liệu cho RecyclerView
        // Sửa cách tạo GoiYAdapter để thêm Context
        goiYAdapter = new GoiYAdapter(flightSuggestions, this);
        rvFlightSuggestions.setAdapter(goiYAdapter);

        // Có thể hiển thị thông tin tìm kiếm (tùy theo yêu cầu)
        TextView textViewSearchInfo = findViewById(R.id.textViewSearchInfo);
        String searchInfo = "Khởi hành: " + departure + "\n" +
                "Điểm đến: " + destination + "\n" +
                "Ngày đi: " + departureDate + "\n" +
                "Ngày về: " + returnDate + "\n" +
                "Số hành khách: " + passengers;
        textViewSearchInfo.setText(searchInfo);
    }

    // Hàm lọc chuyến bay gợi ý theo thông tin tìm kiếm
    private List<Goi_y> getFlightSuggestions(String departure, String destination) {
        List<Goi_y> suggestions = new ArrayList<>();

        // Dữ liệu chuyến bay mẫu
        List<Goi_y> allFlights = new ArrayList<>();
        allFlights.add(new Goi_y("Hà Nội", "Hồ Chí Minh", "10:00", "12:00", 1500000));
        allFlights.add(new Goi_y("Đà Nẵng", "Hà Nội", "14:00", "16:00", 1200000));
        allFlights.add(new Goi_y("Hồ Chí Minh", "Phú Quốc", "18:00", "19:30", 900000));
        allFlights.add(new Goi_y("Hà Nội", "Hồ Chí Minh", "12:00", "14:30", 1600000));
        allFlights.add(new Goi_y("Hà Nội", "Đà Nẵng", "08:00", "10:00", 1100000));

        // Lọc chuyến bay dựa trên điểm đi và điểm đến
        for (Goi_y flight : allFlights) {
            if (flight.getDeparture().equals(departure) && flight.getDestination().equals(destination)) {
                suggestions.add(flight);
            }
        }
        return suggestions;
    }
}

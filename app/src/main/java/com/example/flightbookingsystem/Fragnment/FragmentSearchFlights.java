package com.example.flightbookingsystem.Fragnment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.Adapter.GoiYAdapter;
import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.model.Goi_y;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class FragmentSearchFlights extends Fragment {

    private EditText etDeparture, etDestination;
    private RecyclerView recyclerView;
    private GoiYAdapter adapter;
    private List<Goi_y> flightList;

    public FragmentSearchFlights() {
        // Constructor trống bắt buộc
    }

    public static FragmentSearchFlights newInstance(String param1, String param2) {
        FragmentSearchFlights fragment = new FragmentSearchFlights();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        View rootView = inflater.inflate(R.layout.fragment_search_flights, container, false);

        etDeparture = rootView.findViewById(R.id.etDeparture);
        etDestination = rootView.findViewById(R.id.etDestination);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        // Cấu hình RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        flightList = new ArrayList<>();
        adapter = new GoiYAdapter(requireContext(), flightList);
        recyclerView.setAdapter(adapter);

        // Xử lý sự kiện nút Tìm kiếm
        Button btnSearch = rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(v -> onSearchClick());

        return rootView;
    }

    private void onSearchClick() {
        String departure = etDeparture.getText().toString().trim();
        String destination = etDestination.getText().toString().trim();

        if (departure.isEmpty() || destination.isEmpty()) {
            Toast.makeText(requireContext(), "Vui lòng nhập điểm đi và điểm đến.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mô phỏng tìm kiếm chuyến bay
        List<Goi_y> flights = searchFlights(departure, destination);

        if (flights.isEmpty()) {
            Toast.makeText(requireContext(), "Không tìm thấy chuyến bay.", Toast.LENGTH_SHORT).show();
        } else {
            flightList.clear();
            flightList.addAll(flights);
            adapter.notifyDataSetChanged();
        }
    }

    // Hàm mô phỏng tìm kiếm chuyến bay
    private List<Goi_y> searchFlights(String departure, String destination) {
        List<Goi_y> flights = new ArrayList<>();

        // Tạo dữ liệu mẫu
        for (int i = 0; i < 10; i++) {
            String departureTime = generateRandomDate();
            String arrivalTime = generateArrivalTime(departureTime);

            flights.add(new Goi_y("Vietnam Airlines " + (i + 1), departure, destination, departureTime, arrivalTime, "$200"));
        }

        return flights;
    }

    // Hàm tạo ngày ngẫu nhiên trong 30 ngày tới
    private String generateRandomDate() {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();

        // Thêm số ngày ngẫu nhiên
        int daysToAdd = random.nextInt(30);
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = random.nextInt(12) + 6; // Giờ từ 6 giờ sáng đến 6 giờ chiều
        int minute = random.nextInt(60);

        return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
    }

    // Hàm tạo thời gian đến (cộng thêm 2 giờ)
    private String generateArrivalTime(String departureTime) {
        String[] parts = departureTime.split(" ");
        String datePart = parts[0];
        String timePart = parts[1];

        String[] timeParts = timePart.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        hour += 2;
        if (hour >= 24) {
            hour -= 24;
        }

        return datePart + " " + String.format("%02d:%02d", hour, minute);
    }
}

package com.example.flightbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.Adapter.FlightAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchBar;
    private Button btnBookFlight, btnCustomerSupport;
    private TextView tvDiscounts, tvSuggestions;
    private ImageView bannerDiscount;
    private RecyclerView recyclerSuggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các thành phần giao diện
        searchBar = findViewById(R.id.search_bar);
        btnBookFlight = findViewById(R.id.btn_book_flight);
        btnCustomerSupport = findViewById(R.id.btn_customer_support);
        tvDiscounts = findViewById(R.id.ud);
        tvSuggestions = findViewById(R.id.gycb);
        bannerDiscount = findViewById(R.id.banner_discount);
        recyclerSuggestions = findViewById(R.id.recycler_suggestions);

        // Cấu hình RecyclerView
        setupRecyclerView();

        // Thêm sự kiện cho các nút
        btnBookFlight.setOnClickListener(view -> {
            // Chuyển sang màn hình Đặt vé
            Intent intent = new Intent(MainActivity.this, Datve.class);
            startActivity(intent);
        });

        btnBookFlight.setOnClickListener(view -> {
            // Chuyển sang màn hình ho tro khach hang
            Intent intent = new Intent(MainActivity.this, SupportActivity.class);
            startActivity(intent);
        });

        // Sự kiện tìm kiếm
        searchBar.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "Nhập từ khóa tìm kiếm!", Toast.LENGTH_SHORT).show());
    }

    private void setupRecyclerView() {
        // Danh sách gợi ý giả lập
        List<String> flightSuggestions = new ArrayList<>();
        flightSuggestions.add("Hà Nội - TP. Hồ Chí Minh");
        flightSuggestions.add("Đà Nẵng - Cần Thơ");
        flightSuggestions.add("Hà Nội - Đà Nẵng");
        flightSuggestions.add("TP. Hồ Chí Minh - Hải Phòng");

        // Adapter và LayoutManager cho RecyclerView
        recyclerSuggestions.setLayoutManager(new LinearLayoutManager(this));
        FlightAdapter adapter = new FlightAdapter(flightSuggestions);
        recyclerSuggestions.setAdapter(adapter);
    }
}

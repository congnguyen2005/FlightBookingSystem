package com.example.flightbookingsystem;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.Adapter.GoiYMainAdapter;
import com.example.flightbookingsystem.model.GoiYMain;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchBar;
    private Button btnBookFlight, btnCustomerSupport;
    private TextView tvDiscounts, tvSuggestions;
    private ImageView bannerDiscount;
    private RecyclerView recyclerView;
    private GoiYMainAdapter goiYMainAdapter;
    private List<GoiYMain> goiYList;

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

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.flight_suggestions_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách gợi ý chuyến bay
        goiYList = new ArrayList<>();
        goiYList.add(new GoiYMain("VN123", "08:00 - 11:00", "1.200.000 VND"));
        goiYList.add(new GoiYMain("VN234", "14:00 - 17:00", "1.500.000 VND"));
        goiYList.add(new GoiYMain("VN345", "18:00 - 21:00", "1.300.000 VND"));

        // Gắn Adapter vào RecyclerView
        goiYMainAdapter = new GoiYMainAdapter(goiYList);
        recyclerView.setAdapter(goiYMainAdapter);

        // Thêm sự kiện cho các nút
        btnBookFlight.setOnClickListener(view -> {
            // Chuyển sang màn hình đặt vé
            Intent intent = new Intent(MainActivity.this, Datve.class);
            startActivity(intent);
        });

        btnCustomerSupport.setOnClickListener(view -> {
            // Chuyển sang màn hình hỗ trợ khách hàng
            Intent intent = new Intent(MainActivity.this, SupportActivity.class);
            startActivity(intent);
        });

        // Thêm sự kiện khi nhấn vào searchBar
        searchBar.setOnClickListener(view -> {
            // Thông báo cho người dùng khi nhấn vào search bar
            Toast.makeText(MainActivity.this, "Nhập từ khóa tìm kiếm!", Toast.LENGTH_SHORT).show();
        });

        // Thêm sự kiện khi nhấn "Enter" trong search bar
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String query = searchBar.getText().toString().trim();
                if (TextUtils.isEmpty(query)) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập từ khóa tìm kiếm!", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý tìm kiếm ở đây (có thể lọc danh sách chuyến bay)
                    Toast.makeText(MainActivity.this, "Đang tìm kiếm: " + query, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });
    }
}

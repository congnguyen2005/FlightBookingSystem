package com.example.flightbookingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.flightbookingsystem.Adapter.GoiYMainAdapter;
import com.example.flightbookingsystem.Adapter.OffersAdapter;
import com.example.flightbookingsystem.Fragnment.FragmentSearchFlights;
import com.example.flightbookingsystem.model.GoiYMain;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GoiYMainAdapter goiYMainAdapter;
    private List<GoiYMain> goiYList;

    private ViewPager2 viewPagerOffers;
    private OffersAdapter offersAdapter;
    private List<Integer> offerImages; // Danh sách ID của ảnh
    private Handler handler;
    private Runnable autoScrollRunnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thiết lập RecyclerView cho danh sách chuyến bay gợi ý
        recyclerView = findViewById(R.id.flight_suggestions_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        goiYList = new ArrayList<>();
        goiYList.add(new GoiYMain("VN123", "08:00 - 11:00", "1.200.000 VND"));
        goiYList.add(new GoiYMain("VN234", "14:00 - 17:00", "1.500.000 VND"));
        goiYList.add(new GoiYMain("VN345", "18:00 - 21:00", "1.300.000 VND"));
        goiYMainAdapter = new GoiYMainAdapter(goiYList);
        recyclerView.setAdapter(goiYMainAdapter);

        // Thiết lập ViewPager2 cho ưu đãi
        viewPagerOffers = findViewById(R.id.viewPager_offers);
        offerImages = new ArrayList<>();
        offerImages.add(R.drawable.bamboo);
        offerImages.add(R.drawable.bamboo);
        offerImages.add(R.drawable.bamboo);

        offersAdapter = new OffersAdapter(offerImages);
        viewPagerOffers.setAdapter(offersAdapter);

        // Tự động cuộn ảnh
        handler = new Handler(Looper.getMainLooper());
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPagerOffers.getCurrentItem();
                int nextItem = (currentItem + 1) % offerImages.size();
                viewPagerOffers.setCurrentItem(nextItem, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(autoScrollRunnable, 3000);

        viewPagerOffers.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(autoScrollRunnable);
                handler.postDelayed(autoScrollRunnable, 3000);
            }
        });

        // Xử lý sự kiện khi nhấn nút tìm kiếm
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(v -> openFragmentSearchFlights());

        // Xử lý các sự kiện khi nhấn nút dưới thanh menu
        findViewById(R.id.btn_home).setOnClickListener(v -> reloadMain());
        findViewById(R.id.btn_messages).setOnClickListener(v -> goToSupportActivity());
        findViewById(R.id.btn_account).setOnClickListener(v -> goToAccountActivity());
        findViewById(R.id.btn_history).setOnClickListener(v -> goToHistoryActivity()); // Mới thêm

        // Thêm sự kiện khi nhấn nút Đặt Vé
        Button btnBookFlight = findViewById(R.id.btn_book_flight);
        btnBookFlight.setOnClickListener(v -> goToBookTicketActivity());
    }

    private void openFragmentSearchFlights() {
        Intent intent = new Intent(MainActivity.this, FragmentSearchFlights.class);
        startActivity(intent);
        finish();
    }

    private void reloadMain() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToSupportActivity() {
        Intent intent = new Intent(MainActivity.this, SupportActivity.class);
        startActivity(intent);
    }

    private void goToAccountActivity() {
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    private void goToBookTicketActivity() {
        Intent intent = new Intent(MainActivity.this, BookTicketActivity.class);
        startActivity(intent);
    }

    // Mới thêm: Chuyển đến Activity Lịch sử
    private void goToHistoryActivity() {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    private void openSearchFlightsFragment() {
        Fragment fragment = new FragmentSearchFlights();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Cho phép quay lại màn hình trước
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(autoScrollRunnable);
        }
    }
}

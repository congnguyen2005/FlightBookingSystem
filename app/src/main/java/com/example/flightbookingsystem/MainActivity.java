package com.example.flightbookingsystem;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.flightbookingsystem.Fragnment.ChooseFlightTicketFragment;
import com.example.flightbookingsystem.Fragnment.InformationFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayoutOffers = findViewById(R.id.your_linear_layout_id);

        // Đặt sự kiện click
        linearLayoutOffers.setOnClickListener(view -> {
            // Tạo đối tượng FragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Bắt đầu transaction
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // Thay thế Fragment hiện tại bằng InformationFragment
            transaction.replace(R.id.fragmentContainer, new InformationFragment());

            // Thêm transaction vào BackStack (tùy chọn)
            transaction.addToBackStack(null);

            // Hoàn thành transaction
            transaction.commit();
        });
    }
}
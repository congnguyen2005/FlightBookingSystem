package com.example.flightbookingsystem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flightbookingsystem.Fragnment.ChooseFlightTicketFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ChooseFlightTicketFragment fragment = new ChooseFlightTicketFragment();

            // Thêm Fragment vào container (ví dụ: một FrameLayout)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)  // fragmentContainer là ID của container
                    .commit();
        }
    }
}
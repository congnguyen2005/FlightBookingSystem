package com.example.flightbookingsystem;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;

public class SlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        ImageView gifImageView = findViewById(R.id.gifImageView);

        try {
            // Đọc tệp GIF từ thư mục res/raw (nếu có)
            InputStream inputStream = getResources().openRawResource(R.raw.welcome); // welcome.gif trong thư mục raw

            // Sử dụng Movie để đọc dữ liệu GIF
            Movie gifMovie = Movie.decodeStream(inputStream);

            // Đặt Movie vào ImageView để phát GIF
            gifImageView.setImageDrawable(new MovieDrawable(gifMovie));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Không thể tải GIF", Toast.LENGTH_SHORT).show();
        }
    }
    }

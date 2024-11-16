package com.example.flightbookingsystem;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightbookingsystem.Adapter.MovieDrawable;

import java.io.InputStream;

public class SlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        ImageView gifImageView = findViewById(R.id.gifImageView);

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.welcome); // welcome.gif trong thư mục raw

            Movie gifMovie = Movie.decodeStream(inputStream);

            // Đặt Movie vào ImageView để phát GIF
            gifImageView.setImageDrawable(new MovieDrawable(gifMovie));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Không thể tải GIF", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Sau 5 giây chuyển sang MainActivity
                Intent intent = new Intent(SlashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        }, 5000); // Delay 5 giây
    }
    }

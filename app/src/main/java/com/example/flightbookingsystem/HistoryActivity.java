package com.example.flightbookingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout historyContainer;
    private TextView noHistoryTextView;  // Dùng để hiển thị thông báo khi không có vé

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyContainer = findViewById(R.id.historyContainer);
        noHistoryTextView = findViewById(R.id.noHistoryTextView);  // Khai báo TextView cho thông báo

        // Lấy danh sách các chuyến bay từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("FlightHistory", MODE_PRIVATE);
        String json = sharedPreferences.getString("flightList", "[]");

        // Chuyển chuỗi JSON thành danh sách chuyến bay
        Gson gson = new Gson();
        Type type = new TypeToken<List<String[]>>() {}.getType();
        List<String[]> flightList = gson.fromJson(json, type);

        // Kiểm tra nếu không có vé nào
        if (flightList.isEmpty()) {
            noHistoryTextView.setVisibility(View.VISIBLE);  // Hiển thị thông báo nếu không có vé
        } else {
            noHistoryTextView.setVisibility(View.GONE);  // Ẩn thông báo nếu có vé
        }

        // Hiển thị lịch sử đặt vé
        for (String[] flight : flightList) {
            // Tạo một TextView để hiển thị thông tin chuyến bay
            TextView flightTextView = new TextView(this);
            flightTextView.setText("Từ: " + flight[0] + " Đến: " + flight[1]);
            flightTextView.setTextSize(18);
            flightTextView.setTextColor(getResources().getColor(android.R.color.black));

            // Tạo các nút "Xóa" và "Sửa"
            Button deleteButton = new Button(this);
            deleteButton.setText("Xóa");
            deleteButton.setOnClickListener(v -> {
                // Logic xóa vé
                flightList.remove(flight);
                updateHistory(flightList);
            });

            Button editButton = new Button(this);
            editButton.setText("Sửa");
            editButton.setOnClickListener(v -> {
                // Logic chuyển đến BookTicketActivity với thông tin vé cần sửa
                Intent intent = new Intent(HistoryActivity.this, BookTicketActivity.class);
                intent.putExtra("flightDetails", flight);  // Truyền thông tin chuyến bay cần sửa
                startActivity(intent);
            });

            // Thêm các TextView và nút vào layout, cho vào khung có background
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(16, 10, 16, 10);
            itemLayout.setBackgroundResource(R.drawable.khung);  // Thiết lập background khung

            LinearLayout buttonsLayout = new LinearLayout(this);
            buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);
            buttonsLayout.addView(deleteButton);
            buttonsLayout.addView(editButton);

            itemLayout.addView(flightTextView);
            itemLayout.addView(buttonsLayout);

            historyContainer.addView(itemLayout);
        }
    }

    private void updateHistory(List<String[]> flightList) {
        // Cập nhật lại SharedPreferences sau khi xóa chuyến bay
        SharedPreferences sharedPreferences = getSharedPreferences("FlightHistory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(flightList);
        editor.putString("flightList", json);
        editor.apply();

        // Cập nhật lại giao diện
        historyContainer.removeAllViews();
        for (String[] flight : flightList) {
            TextView flightTextView = new TextView(this);
            flightTextView.setText("Từ: " + flight[0] + " Đến: " + flight[1]);
            historyContainer.addView(flightTextView);
        }

        // Kiểm tra nếu không có vé nào sau khi xóa
        if (flightList.isEmpty()) {
            noHistoryTextView.setVisibility(View.VISIBLE);
        } else {
            noHistoryTextView.setVisibility(View.GONE);
        }
    }
}



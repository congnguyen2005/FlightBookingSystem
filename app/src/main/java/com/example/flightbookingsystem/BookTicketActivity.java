package com.example.flightbookingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class BookTicketActivity extends AppCompatActivity {

    private EditText startPointEditText, endPointEditText;
    private Button bookButton; // Nút đặt vé
    private Button btnFillInfo; // Nút điền thông tin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        // Ánh xạ các thành phần giao diện
        startPointEditText = findViewById(R.id.start_point);
        endPointEditText = findViewById(R.id.end_point);
        bookButton = findViewById(R.id.book_button);
        btnFillInfo = findViewById(R.id.btn_fill_info);

        // Gắn sự kiện cho nút "ĐẶT VÉ NGAY"
        bookButton.setOnClickListener(v -> {
            String startPoint = startPointEditText.getText().toString().trim();
            String endPoint = endPointEditText.getText().toString().trim();

            if (startPoint.isEmpty() || endPoint.isEmpty()) {
                Toast.makeText(BookTicketActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                // Lấy danh sách các chuyến bay đã đặt từ SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("FlightHistory", MODE_PRIVATE);
                String json = sharedPreferences.getString("flightList", "[]");

                // Chuyển đổi chuỗi JSON thành danh sách chuyến bay
                Gson gson = new Gson();
                Type type = new TypeToken<List<String[]>>() {}.getType();
                List<String[]> flightList = gson.fromJson(json, type);

                // Thêm chuyến bay mới vào danh sách
                flightList.add(new String[]{startPoint, endPoint});

                // Lưu lại danh sách chuyến bay mới vào SharedPreferences
                String updatedJson = gson.toJson(flightList);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("flightList", updatedJson);
                editor.apply();

                Toast.makeText(BookTicketActivity.this, "Đặt vé từ " + startPoint + " đến " + endPoint, Toast.LENGTH_SHORT).show();

                // Chuyển đến màn hình PaymentActivity và gửi thông tin chuyến bay
                Intent intent = new Intent(BookTicketActivity.this, ThanhToanActivity.class);
                intent.putExtra("startPoint", startPoint);
                intent.putExtra("endPoint", endPoint);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện cho nút "Điền thông tin"
        btnFillInfo.setOnClickListener(v -> {
            // Chuyển đến màn hình ChonVeActivity để điền thông tin
            Intent intent = new Intent(BookTicketActivity.this, ChonVeActivity.class);
            startActivity(intent);
        });
    }
}

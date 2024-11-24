package com.example.flightbookingsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseFlightTicketActivity extends AppCompatActivity {

    private RadioGroup radioGroupClass;
    private RadioGroup seatRadioGroup;
    private LinearLayout seatingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_flight_ticket);

        // Khởi tạo các view
        radioGroupClass = findViewById(R.id.radioGroup);
        seatingLayout = findViewById(R.id.seatingLayout);
        seatRadioGroup = findViewById(R.id.seatRadioGroup);

        // Ẩn phần chọn ghế ban đầu
        seatingLayout.setVisibility(View.GONE);

        // Lắng nghe sự kiện chọn hạng vé
        radioGroupClass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Kiểm tra nếu người dùng chọn hạng vé
                if (checkedId != -1) {
                    // Hiển thị phần chọn ghế khi đã chọn hạng vé
                    seatingLayout.setVisibility(View.VISIBLE);
                    String selectedClass = ((RadioButton) findViewById(checkedId)).getText().toString();
                    Toast.makeText(ChooseFlightTicketActivity.this, "Bạn đã chọn hạng: " + selectedClass, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Lắng nghe sự kiện chọn ghế
        seatRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    String selectedSeat = ((RadioButton) findViewById(checkedId)).getText().toString();
                    Toast.makeText(ChooseFlightTicketActivity.this, "Bạn đã chọn ghế: " + selectedSeat, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.example.flightbookingsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChonVeActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, emailEditText, birthdateEditText, addressEditText;
    private Spinner seatClassSpinner;
    private Button bookTicketButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ve);

        // Ánh xạ các thành phần giao diện
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        birthdateEditText = findViewById(R.id.birthdateEditText);
        addressEditText = findViewById(R.id.addressEditText);
        seatClassSpinner = findViewById(R.id.seatClassSpinner);
        bookTicketButton = findViewById(R.id.btnBookTicket);

        // Thiết lập Spinner cho chọn hạng ghế
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.seat_classes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatClassSpinner.setAdapter(adapter);

        // Xử lý sự kiện cho nút Đặt vé
        bookTicketButton.setOnClickListener(v -> {
            // Lấy dữ liệu từ các trường
            String name = nameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String birthdate = birthdateEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String seatClass = seatClassSpinner.getSelectedItem().toString();

            // Kiểm tra nếu thông tin chưa đầy đủ hoặc sai định dạng
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || birthdate.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else if (!isValidName(name)) {
                Toast.makeText(this, "Họ và tên phải có ít nhất 6 ký tự và chỉ chứa chữ cái!", Toast.LENGTH_SHORT).show();
            } else if (!isValidPhoneNumber(phone)) {
                Toast.makeText(this, "Số điện thoại phải gồm 10 ký tự và chỉ chứa số!", Toast.LENGTH_SHORT).show();
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Email phải chứa @gmail.com!", Toast.LENGTH_SHORT).show();
            } else {
                // Nếu thông tin hợp lệ, chuyển đến màn hình BookTicketActivity
                Intent intent = new Intent(ChonVeActivity.this, BookTicketActivity.class);
                startActivity(intent);
                finish(); // Đóng màn hình hiện tại
            }
        });

        // Sự kiện cho EditText chọn ngày sinh
        birthdateEditText.setOnClickListener(v -> {
            // Lấy ngày hiện tại
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Tạo DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ChonVeActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Cập nhật ngày sinh vào EditText
                        birthdateEditText.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    },
                    year, month, day);

            // Hiển thị DatePickerDialog
            datePickerDialog.show();
        });
    }

    // Hàm kiểm tra họ và tên hợp lệ
    private boolean isValidName(String name) {
        return name.length() >= 6 && name.matches("[a-zA-Z\\s]+");
    }

    // Hàm kiểm tra số điện thoại hợp lệ
    private boolean isValidPhoneNumber(String phone) {
        return phone.length() == 10 && phone.matches("[0-9]+");
    }

    // Hàm kiểm tra email hợp lệ
    private boolean isValidEmail(String email) {
        return email.contains("@gmail.com");
    }
}

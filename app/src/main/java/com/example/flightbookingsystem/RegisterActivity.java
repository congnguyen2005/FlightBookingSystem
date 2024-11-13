package com.example.flightbookingsystem;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText edtPassword = findViewById(R.id.edt_Repass);
        ImageView eyeIcon = findViewById(R.id.eye);  // Giả sử bạn đã thêm eye icon vào layout

        eyeIcon.setOnClickListener(v -> {
            if (edtPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);  // Hiển thị mật khẩu
            } else {
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);  // Ẩn mật khẩu
            }
            edtPassword.setSelection(edtPassword.getText().length());  // Đặt lại con trỏ sau khi thay đổi inputType
        });

    }
}
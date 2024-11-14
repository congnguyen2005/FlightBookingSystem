package com.example.flightbookingsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private TextView txtLogin;
    private EditText edtUser, edtPassword, edtRePassword, edtFullname, edtPhone, edtEmail, edtBirthday;
    private RadioGroup genderGroup;
    private RadioButton radioMale, radioFemale;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ các view
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtRegisterPass);
        edtRePassword = findViewById(R.id.edtRegisterRePass);
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthday = findViewById(R.id.edtBirthday);
        genderGroup = findViewById(R.id.genderGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        txtLogin = findViewById(R.id.txtLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Xử lý sự kiện khi nhấn nút đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();  // Gọi hàm đăng ký người dùng
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void registerUser() {
        String username = edtUser.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String rePassword = edtRePassword.getText().toString().trim();
        String fullname = edtFullname.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String birthday = edtBirthday.getText().toString().trim();
        String gender = "";

        // Kiểm tra nếu người dùng chọn giới tính
        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        if (selectedGenderId == radioMale.getId()) {
            gender = "Nam";
        } else if (selectedGenderId == radioFemale.getId()) {
            gender = "Nữ";
        }

        // Kiểm tra dữ liệu nhập vào
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword) ||
                TextUtils.isEmpty(fullname) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(birthday) || TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

//         Lưu thông tin đăng ký vào SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("username", username);
//        editor.putString("password", password);
//        editor.putString("fullname", fullname);
//        editor.putString("phone", phone);
//        editor.putString("email", email);
//        editor.putString("birthday", birthday);
//        editor.putString("gender", gender);
//        editor.apply(); // Lưu lại thay đổi

        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

        // Chuyển đến màn hình đăng nhập
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);  // Sửa thành LoginActivity nếu cần
        startActivity(intent);
        finish();
    }
}

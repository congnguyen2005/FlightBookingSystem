package com.example.flightbookingsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightbookingsystem.database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUser, edtPass;
    private CheckBox chkRemember;
    private Button btnLogin;
    private TextView txtForgot, txtSignUp;

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ view
        edtUser = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btn_login);
        txtForgot = findViewById(R.id.txtForgot);
        txtSignUp = findViewById(R.id.txtSignUp);

        // Khởi tạo database
        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();

        // Xử lý sự kiện cho các nút
        btnLogin.setOnClickListener(v -> loginUser());

        txtSignUp.setOnClickListener(v -> {
            // Chuyển đến màn hình đăng ký
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        txtForgot.setOnClickListener(v -> {
            // Chuyển đến màn hình khôi phục mật khẩu (nếu có)
            Toast.makeText(this, "Tính năng khôi phục mật khẩu đang được phát triển.", Toast.LENGTH_SHORT).show();
        });
    }

    private void loginUser() {
        String username = edtUser.getText().toString().trim();
        String password = edtPass.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra thông tin đăng nhập trong cơ sở dữ liệu
        if (isValidUser(username, password)) {
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển đến màn hình chính (MainActivity)
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        Cursor cursor = database.rawQuery(query, new String[]{username, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
}

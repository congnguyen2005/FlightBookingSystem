package com.example.flightbookingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightbookingsystem.database.DBHelper;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtNewPassword, edtReNewPassword;
    private Button btnResetPassword;
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Ánh xạ các view
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtReNewPassword = findViewById(R.id.edtReNewPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        // Khởi tạo database
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        // Xử lý sự kiện khi người dùng nhấn nút "Reset Password"
        btnResetPassword.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String newPassword = edtNewPassword.getText().toString().trim();
        String reNewPassword = edtReNewPassword.getText().toString().trim();

        // Kiểm tra nếu có trường nào bỏ trống
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(reNewPassword)) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra mật khẩu mới và mật khẩu nhập lại có khớp không
        if (!newPassword.equals(reNewPassword)) {
            Toast.makeText(this, "Mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra tính hợp lệ của email
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra thông tin người dùng trong cơ sở dữ liệu
        if (isUserExists(username, email)) {
            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            updatePassword(username, newPassword);
        } else {
            Toast.makeText(this, "Thông tin người dùng không chính xác!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isUserExists(String username, String email) {
        String query = "SELECT * FROM users WHERE username = ? AND email = ?";
        Cursor cursor = database.rawQuery(query, new String[]{username, email});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true; // Người dùng tồn tại
        }
        cursor.close();
        return false; // Người dùng không tồn tại
    }

    private void updatePassword(String username, String newPassword) {
        ContentValues values = new ContentValues();
        values.put("password", newPassword); // Cập nhật mật khẩu mới

        // Cập nhật dữ liệu trong cơ sở dữ liệu
        int rowsUpdated = database.update("users", values, "username = ?", new String[]{username});
        if (rowsUpdated > 0) {
            Toast.makeText(this, "Mật khẩu đã được cập nhật thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Lỗi khi cập nhật mật khẩu!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(String email) {
        // Kiểm tra tính hợp lệ của email sử dụng regex đơn giản
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

package com.example.flightbookingsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
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

    // Biến trạng thái hiển thị mật khẩu
    private boolean isPasswordVisible = false;

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

        // Khởi tạo biểu tượng drawable
        Drawable eyeIcon = getResources().getDrawable(R.drawable.eye, getTheme()); // Biểu tượng mắt mở
        Drawable eyeCloseIcon = getResources().getDrawable(R.drawable.eye_close, getTheme()); // Biểu tượng mắt đóng
        Drawable passwordIconStart = getResources().getDrawable(R.drawable.password, getTheme()); // Biểu tượng đầu vào mật khẩu

        // Khởi tạo database
        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();

        // Xử lý sự kiện đăng nhập
        btnLogin.setOnClickListener(v -> loginUser());

        // Xử lý sự kiện chuyển đến màn hình đăng ký
        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Xử lý sự kiện hiển thị/ẩn mật khẩu khi chạm vào icon
        edtPass.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int touchX = (int) event.getX();
                int drawableWidth = edtPass.getCompoundDrawables()[2] != null
                        ? edtPass.getCompoundDrawables()[2].getBounds().width()
                        : 0;
                if (touchX >= edtPass.getWidth() - drawableWidth) {
                    // Đổi trạng thái hiển thị mật khẩu
                    isPasswordVisible = !isPasswordVisible;
                    if (isPasswordVisible) {
                        edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        edtPass.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, eyeIcon, null);
                    } else {
                        edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        edtPass.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, eyeCloseIcon, null);
                    }
                    edtPass.setSelection(edtPass.length()); // Đặt con trỏ ở cuối văn bản
                    return true;
                }
            }
            return false;
        });

        // Xử lý sự kiện quên mật khẩu
        txtForgot.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
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



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isPasswordVisible", isPasswordVisible);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isPasswordVisible = savedInstanceState.getBoolean("isPasswordVisible", false);
        Drawable eyeIcon = getResources().getDrawable(R.drawable.eye, getTheme());
        Drawable eyeCloseIcon = getResources().getDrawable(R.drawable.eye_close, getTheme());
        Drawable passwordIconStart = getResources().getDrawable(R.drawable.password, getTheme());

        if (isPasswordVisible) {
            edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            edtPass.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, eyeIcon, null);
        } else {
            edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            edtPass.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, eyeCloseIcon, null);
        }
        edtPass.setSelection(edtPass.length());
    }
}

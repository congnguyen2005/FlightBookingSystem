package com.example.flightbookingsystem;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightbookingsystem.database.DBHelper;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private TextView txtLogin;
    private EditText edtUser, edtPassword, edtRePassword, edtFullname, edtPhone, edtEmail, edtBirthday;
    private RadioGroup genderGroup;
    private RadioButton radioMale, radioFemale;
    private Button btnRegister;

    private SQLiteDatabase database;
    private DBHelper dbHelper;

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
        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);
        // Khởi tạo SQLite database
        Drawable eyeIcon = getResources().getDrawable(R.drawable.eye, getTheme()); // Biểu tượng mắt mở
        Drawable passwordIcon = getResources().getDrawable(R.drawable.eye_close, getTheme());
        Drawable passwordIconStart = getResources().getDrawable(R.drawable.password, getTheme()); // Biểu tượng đầu vào (password)

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        edtPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int touchX = (int) event.getX();
                    int drawableWidth = edtPassword.getCompoundDrawables()[2].getBounds().width(); // Lấy độ rộng của drawableEnd
                    if (touchX >= edtPassword.getWidth() - drawableWidth) {
                        // Nếu chạm vào icon mắt, thay đổi inputType để hiển thị/ẩn mật khẩu
                        if (edtPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD); // Hiển thị mật khẩu
                            edtPassword.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, eyeIcon, null); // Giữ icon start, thay đổi icon mắt
                        } else {
                            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); // Ẩn mật khẩu
                            edtPassword.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, passwordIcon, null); // Giữ icon start, thay đổi icon mắt đóng
                        }
                        return true;
                    }
                }
                return false; // Tiếp tục xử lý sự kiện mặc định
            }
        });

        edtRePassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int touchX = (int) event.getX();
                    int drawableWidth = edtRePassword.getCompoundDrawables()[2].getBounds().width(); // Lấy độ rộng của drawableEnd
                    if (touchX >= edtRePassword.getWidth() - drawableWidth) {
                        // Nếu chạm vào icon mắt, thay đổi inputType để hiển thị/ẩn mật khẩu
                        if (edtRePassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                            edtRePassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD); // Hiển thị mật khẩu
                            edtRePassword.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, eyeIcon, null); // Giữ icon start, thay đổi icon mắt
                        } else {
                            edtRePassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); // Ẩn mật khẩu
                            edtRePassword.setCompoundDrawablesWithIntrinsicBounds(passwordIconStart, null, passwordIcon, null); // Giữ icon start, thay đổi icon mắt đóng
                        }
                        return true; // Ngừng xử lý sự kiện tiếp theo
                    }
                }
                return false; // Tiếp tục xử lý sự kiện mặc định
            }
        });
        edtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
showDatePickerDialog();
            }
        });
        // Xử lý sự kiện khi nhấn nút đăng ký
        btnRegister.setOnClickListener(v -> registerUser());
    }
    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
        (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
            // Hiển thị ngày được chọn trong EditText
            edtBirthday.setText(selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }, year, month, dayOfMonth);
        datePickerDialog.show();
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

        // Kiểm tra tên đăng nhập
        if (username.length() < 8 || username.length() > 16 || !Character.isLetter(username.charAt(0))) {
            Toast.makeText(this, "Tên đăng nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra mật khẩu
        if (password.length() < 8 || password.length() > 16) {
            Toast.makeText(this, "Mật khẩu phải không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword) || rePassword.length() < 8 || rePassword.length() > 16) {
            Toast.makeText(this, "Mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra số điện thoại
        if (phone.length() < 10 || phone.length() > 12) {
            Toast.makeText(this, "Số điện thoại phải có từ 10 đến 12 ký tự!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra email
        if (email.length() <= 5 || !email.contains("@") || !email.endsWith(".com")) {
            Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem tên đăng nhập đã tồn tại trong cơ sở dữ liệu chưa
        if (isUsernameExists(username)) {
            Toast.makeText(this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu thông tin người dùng vào cơ sở dữ liệu SQLite
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("fullname", fullname);
        values.put("phone", phone);
        values.put("email", email);
        values.put("birthday", birthday);
        values.put("gender", gender);

        long result = database.insert("users", null, values); // Insert vào bảng "users"
        if (result == -1) {
            Toast.makeText(this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            // Chuyển đến màn hình đăng nhập
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isUsernameExists(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        Cursor cursor = database.rawQuery(query, new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}

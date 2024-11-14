package com.example.flightbookingsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity {
    private EditText etFirstName, etLastName, etAge, etIdNumber;
    private RadioGroup radioGender;
    private Spinner spinnerNationality;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Link views with IDs
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAge = findViewById(R.id.etAge);
        etIdNumber = findViewById(R.id.etIdNumber);
        radioGender = findViewById(R.id.radioGender);
        spinnerNationality = findViewById(R.id.spinnerNationality);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Set up button click to update information
        btnUpdate.setOnClickListener(v -> updateCustomerInfo());
    }

    private void updateCustomerInfo() {
        // Retrieve and trim input values
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String idNumber = etIdNumber.getText().toString().trim();
        String nationality = spinnerNationality.getSelectedItem().toString();

        // Check if any required field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || ageStr.isEmpty() || idNumber.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền tất cả các trường bắt buộc", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate age is a number and within a reasonable range
        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 0 || age > 120) {
                Toast.makeText(this, "Vui lòng nhập tuổi hợp lệ từ 0 đến 120", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Tuổi phải là một số hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if gender is selected
        int selectedGenderId = radioGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve gender
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender.getText().toString();

        // Display the gathered information
        Toast.makeText(this, "Đã cập nhật thông tin:\n" +
                "Tên: " + firstName + " " + lastName + "\n" +
                "Tuổi: " + age + "\n" +
                "Giới tính: " + gender + "\n" +
                "Quốc tịch: " + nationality + "\n" +
                "CCCD/CMND: " + idNumber, Toast.LENGTH_LONG).show();
    }
}

package com.example.flightbookingsystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightbookingsystem.R;

public class AccountActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etAge;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private Spinner spNationality;
    private Button btnUpdate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Initialize views
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAge = findViewById(R.id.etAge);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Set button click listener
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String gender = rbMale.isChecked() ? "Nam" : "Nữ";

                Toast.makeText(AccountActivity.this,
                        "Updated: " + firstName + " " + lastName + ", Age: " + age + ", Gender: " + gender,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}

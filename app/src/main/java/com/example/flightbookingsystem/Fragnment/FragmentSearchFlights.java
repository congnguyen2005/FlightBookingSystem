package com.example.flightbookingsystem.Fragnment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flightbookingsystem.R;

import java.util.Calendar;

public class FragmentSearchFlights extends Fragment {

    private EditText etDeparture, etDestination, etDepartureDate, etReturnDate, etPassengers;
    private Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_search_flights, container, false);

        // Ánh xạ các thành phần
        etDeparture = view.findViewById(R.id.etDeparture);
        etDestination = view.findViewById(R.id.etDestination);
        etDepartureDate = view.findViewById(R.id.etDepartureDate);
        etReturnDate = view.findViewById(R.id.etReturnDate);
        etPassengers = view.findViewById(R.id.etPassengers);
        btnSearch = view.findViewById(R.id.btnSearch);

        // Xử lý chọn ngày đi
        etDepartureDate.setOnClickListener(v -> showDatePickerDialog(etDepartureDate));

        // Xử lý chọn ngày về
        etReturnDate.setOnClickListener(v -> showDatePickerDialog(etReturnDate));

        // Xử lý khi nhấn nút Tìm kiếm
        btnSearch.setOnClickListener(v -> {
            String departure = etDeparture.getText().toString().trim();
            String destination = etDestination.getText().toString().trim();
            String departureDate = etDepartureDate.getText().toString().trim();
            String returnDate = etReturnDate.getText().toString().trim();
            String passengers = etPassengers.getText().toString().trim();

            // Kiểm tra thông tin nhập vào
            if (departure.isEmpty() || destination.isEmpty() || departureDate.isEmpty() || passengers.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                // Xử lý logic tìm kiếm chuyến bay
                Toast.makeText(getContext(), "Tìm kiếm chuyến bay...", Toast.LENGTH_SHORT).show();
                // Gửi dữ liệu sang giao diện khác hoặc thực hiện API call
            }
        });

        return view;
    }

    private void showDatePickerDialog(EditText targetEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year1, month1, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    targetEditText.setText(selectedDate);
                },
                year, month, day
        );

        datePickerDialog.show();
    }
}

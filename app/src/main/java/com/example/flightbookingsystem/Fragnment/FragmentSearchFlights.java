package com.example.flightbookingsystem.Fragnment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flightbookingsystem.R;

import java.util.Calendar;

public class FragmentSearchFlights extends Fragment {

    private AutoCompleteTextView etDeparture, etDestination;
    private EditText etDepartureDate, etReturnDate, etPassengers;
    private Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_flights, container, false);

        // Khởi tạo các thành phần giao diện
        etDeparture = view.findViewById(R.id.etDeparture);
        etDestination = view.findViewById(R.id.etDestination);
        etDepartureDate = view.findViewById(R.id.etDepartureDate);
        etReturnDate = view.findViewById(R.id.etReturnDate);
        etPassengers = view.findViewById(R.id.etPassengers);
        btnSearch = view.findViewById(R.id.btnSearch);

        // Cài đặt danh sách sân bay
        setupAirportDropdown();

        // Cài đặt chọn ngày
        setupDatePicker(etDepartureDate, true); // Ngày đi không được nhỏ hơn ngày hiện tại
        setupDatePicker(etReturnDate, false); // Ngày về không bắt buộc

        // Xử lý sự kiện nút Tìm kiếm
        btnSearch.setOnClickListener(v -> {
            if (validateInputs()) {
                Toast.makeText(getActivity(), "Đang tìm kiếm chuyến bay...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), GoiYFragment.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setupAirportDropdown() {
        String[] airports = {"Sân bay Nội Bài", "Sân bay Tân Sơn Nhất", "Sân bay Đà Nẵng"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, airports);
        etDeparture.setAdapter(adapter);
        etDestination.setAdapter(adapter);
    }

    private void setupDatePicker(EditText editText, boolean restrictPastDates) {
        Calendar calendar = Calendar.getInstance();
        editText.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view, year, month, dayOfMonth) -> {
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editText.setText(date);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            if (restrictPastDates) {
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            }
            datePickerDialog.show();
        });
    }

    private boolean validateInputs() {
        String departure = etDeparture.getText().toString();
        String destination = etDestination.getText().toString();
        String departureDate = etDepartureDate.getText().toString();
        String passengers = etPassengers.getText().toString();

        if (departure.isEmpty() || destination.isEmpty() || departureDate.isEmpty() || passengers.isEmpty()) {
            Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (departure.equals(destination)) {
            Toast.makeText(getActivity(), "Sân bay đi và đến không được trùng nhau!", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int numPassengers = Integer.parseInt(passengers);
            if (numPassengers <= 0) {
                Toast.makeText(getActivity(), "Số lượng hành khách phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Số lượng hành khách không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

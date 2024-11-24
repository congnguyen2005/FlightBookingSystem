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

import com.example.flightbookingsystem.FlightSuggestionsActivity;
import com.example.flightbookingsystem.MainActivity;
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

        // Initialize views
        etDeparture = view.findViewById(R.id.etDeparture);
        etDestination = view.findViewById(R.id.etDestination);
        etDepartureDate = view.findViewById(R.id.etDepartureDate);
        etReturnDate = view.findViewById(R.id.etReturnDate);
        etPassengers = view.findViewById(R.id.etPassengers);
        btnSearch = view.findViewById(R.id.btnSearch);

        // Set up AutoCompleteTextView with airport suggestions
        setupAirportDropdown();

        // Set up date pickers for the date fields
        setupDatePicker(etDepartureDate);
        setupDatePicker(etReturnDate);

        // Handle the search button click
        btnSearch.setOnClickListener(v -> {
            String departure = etDeparture.getText().toString();
            String destination = etDestination.getText().toString();
            String departureDate = etDepartureDate.getText().toString();
            String returnDate = etReturnDate.getText().toString();
            String passengers = etPassengers.getText().toString();

            // Check if all required fields are filled
            if (departure.isEmpty() || destination.isEmpty() || departureDate.isEmpty() || passengers.isEmpty()) {
                Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Show a message when search is triggered
                Toast.makeText(getActivity(), "Đang tìm kiếm chuyến bay...", Toast.LENGTH_SHORT).show();

                // Navigate to the MainActivity
                Intent intent = new Intent(getActivity(), FlightSuggestionsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setupAirportDropdown() {
        // Create an array or list of airports
        String[] airports = new String[] {"Sân bay Nội Bài", "Sân bay Tân Sơn Nhất", "Sân bay Đà Nẵng"};

        // Set the adapter for the AutoCompleteTextViews
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, airports);
        etDeparture.setAdapter(adapter);
        etDestination.setAdapter(adapter);
    }

    private void setupDatePicker(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Show date picker dialog when the user clicks on the EditText
        editText.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view, year1, month1, dayOfMonth) -> {
                        // Format the date and set it to the EditText
                        String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        editText.setText(date);
                    }, year, month, day);
            datePickerDialog.show();
        });
    }
}

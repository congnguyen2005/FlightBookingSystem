package com.example.flightbookingsystem.Fragnment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.flightbookingsystem.R;

public class ChooseFlightTicketFragment extends Fragment {

    // Các tham số (nếu có)
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public ChooseFlightTicketFragment() {
        // Constructor rỗng yêu cầu cho Fragment
    }

    // Phương thức factory để tạo đối tượng mới của fragment với tham số đầu vào
    public static ChooseFlightTicketFragment newInstance(String param1, String param2) {
        ChooseFlightTicketFragment fragment = new ChooseFlightTicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout fragment_choose_flight_ticket
        View view = inflater.inflate(R.layout.fragment_choose_flight_ticket, container, false);

        // Lấy các thành phần giao diện từ layout
        RadioGroup radioGroupClasses = view.findViewById(R.id.radioGroupClasses);
        GridLayout gridLayoutEconomy = view.findViewById(R.id.gridLayoutEconomy);

        // Thêm sự kiện listener cho lựa chọn hạng vé
        radioGroupClasses.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedClass = view.findViewById(checkedId);
            if (selectedClass != null) {
                String className = selectedClass.getText().toString();
                Toast.makeText(getActivity(), "You selected: " + className, Toast.LENGTH_SHORT).show();

                // Cập nhật GridLayout cho ghế ngồi tương ứng với hạng vé đã chọn
                updateSeatGrid(className, gridLayoutEconomy);
            }
        });

        return view;
    }

    // Cập nhật GridLayout dựa trên hạng vé đã chọn
    private void updateSeatGrid(String className, GridLayout gridLayout) {
        gridLayout.removeAllViews(); // Xóa hết các ghế cũ

        int totalSeats = 30; // Mỗi hạng vé có 30 ghế
        int columns = 6;
        int rows = totalSeats / columns; // Tính số dòng (mỗi dòng 6 ghế)

        // Lặp qua và thêm các ghế vào GridLayout
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextView seatView = new TextView(getActivity());
                seatView.setText("Seat " + (i * columns + j + 1)); // Tạo tên ghế
                seatView.setPadding(8, 8, 8, 8);
                seatView.setBackgroundResource(R.drawable.button_background); // Đặt hình nền cho ghế
                gridLayout.addView(seatView);
            }
        }
    }
}

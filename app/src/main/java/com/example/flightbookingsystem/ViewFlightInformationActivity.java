package com.example.flightbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightbookingsystem.Fragnment.GoiYFragment;

public class ViewFlightInformationActivity extends AppCompatActivity {
    private TextView tvLocation, tvAllFlights, tvFlightNumber, tvAirlineName, tvFlightDate, tvDepartureTime, tvArrivalTime,
            tvFlightDuration, tvFlightRoute, tvStops, tvStopInfo, tvTicketClass, tvSeatNumber, tvBaggageInfo,
            tvExtraBaggage, tvPlaneType, tvSeatAvailability, tvTicketConditions, tvExtraServices, tvPromotion,
            tvTicketPrice;
    private ImageView ivImage;
    Button btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flight_information);
        tvLocation = findViewById(R.id.tv_location);
        tvAllFlights = findViewById(R.id.tv_all_flights);
        tvFlightNumber = findViewById(R.id.tv_flight_number);
        tvAirlineName = findViewById(R.id.tv_airline_name);
        tvFlightDate = findViewById(R.id.tv_flight_date);
        tvDepartureTime = findViewById(R.id.tv_departure_time);
        tvArrivalTime = findViewById(R.id.tv_arrival_time);
        tvFlightDuration = findViewById(R.id.tv_flight_duration);
        tvFlightRoute = findViewById(R.id.tv_flight_route);
        tvStops = findViewById(R.id.tv_stops);
        tvStopInfo = findViewById(R.id.tv_stop_info);
        tvTicketClass = findViewById(R.id.tv_ticket_class);
        tvSeatNumber = findViewById(R.id.tv_seat_number);
        tvBaggageInfo = findViewById(R.id.tv_baggage_info);
        tvExtraBaggage = findViewById(R.id.tv_extra_baggage);

        tvTicketConditions = findViewById(R.id.tv_ticket_conditions);
        tvExtraServices = findViewById(R.id.tv_extra_services);

        ivImage = findViewById(R.id.iv_image);
        btnSelect = findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewFlightInformationActivity.this, GoiYFragment.class);
                startActivity(intent);
                finish();
            }
        });

        // Cập nhật thông tin chuyến bay
    }
//    private void updateFlightDetails() {
//        tvLocation.setText("Hà Nội - Đà Nẵng");
//        ivImage.setImageResource(R.drawable.anh); // Hình ảnh chuyến bay
//        tvFlightNumber.setText("Số hiệu: PD10143");
//        tvAirlineName.setText("Hãng: Vietravel Airlines");
//        tvFlightDate.setText("Ngày bay: XX/DD/YYYY");
//        tvDepartureTime.setText("Giờ khởi hành: 10h00");
//        tvArrivalTime.setText("Giờ hạ cánh: 15h00");
//        tvFlightDuration.setText("Thời gian bay: 5h");
//        tvFlightRoute.setText("Vị trí: Hà Nội - Đà Nẵng");
//        tvStops.setText("Điểm dừng: 1");
//        tvStopInfo.setText("Thông tin điểm dừng: Lorem");
//        tvTicketClass.setText("Hạng vé: First Class");
//        tvSeatNumber.setText("Ghế: 01");
//        tvBaggageInfo.setText("Hành lý miễn phí: 20kg");
//        tvExtraBaggage.setText("Phí hành lý thừa: 100k/5kg");
//        tvPlaneType.setText("Loại máy bay: Boeing 787");
//        tvSeatAvailability.setText("Ghế trống: 15");
//        tvTicketConditions.setText("Điều kiện vé: Vé không hoàn");
//        tvExtraServices.setText("Dịch vụ: Chuyến bay có bữa ăn");
//        tvPromotion.setText("Khuyến mãi: Giảm giá 20% cho lần đặt tiếp theo");
//        tvTicketPrice.setText("Giá vé: 50000000 VND");
//    }

}
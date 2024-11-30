package com.example.flightbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ThanhToanActivity extends AppCompatActivity {

    private TextView startPointTextView, endPointTextView, totalPriceTextView, temporaryAmountTextView,
            discountAmountTextView, totalPaymentTextView, promoCodeErrorTextView;
    private EditText promoCodeEditText;
    private Button payButton, okButton;

    private static final int DEFAULT_PRICE = 1500000;
    private static final String DISCOUNT_CODE = "khuongdeptrai";

    private double discount = 0;
    private double totalPayment = DEFAULT_PRICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        // Ánh xạ các thành phần giao diện
        startPointTextView = findViewById(R.id.startPointTextView);
        endPointTextView = findViewById(R.id.endPointTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        temporaryAmountTextView = findViewById(R.id.temporaryAmountTextView);
        discountAmountTextView = findViewById(R.id.discountAmountTextView);
        totalPaymentTextView = findViewById(R.id.totalPaymentTextView);
        promoCodeEditText = findViewById(R.id.promoCodeEditText);
        promoCodeErrorTextView = findViewById(R.id.promoCodeErrorTextView);
        payButton = findViewById(R.id.payButton);
        okButton = findViewById(R.id.okButton);  // Nút OK

        // Lấy dữ liệu từ BookTicketActivity
        Intent intent = getIntent();
        String startPoint = intent.getStringExtra("startPoint");
        String endPoint = intent.getStringExtra("endPoint");

        // Hiển thị thông tin chuyến bay
        startPointTextView.setText("Điểm khởi hành: " + startPoint);
        endPointTextView.setText("Điểm đến: " + endPoint);
        totalPriceTextView.setText("Tổng tiền đặt vé: " + DEFAULT_PRICE + " VND");
        temporaryAmountTextView.setText("Tổng tiền tạm tính: " + DEFAULT_PRICE + " VND");

        // Xử lý sự kiện cho nút OK
        okButton.setOnClickListener(v -> {
            String promoCode = promoCodeEditText.getText().toString().trim();

            // Kiểm tra mã khuyến mãi
            if (promoCode.equals(DISCOUNT_CODE)) {
                discount = DEFAULT_PRICE * 0.15;  // Tính tiền khuyến mãi 15%
                discountAmountTextView.setText("Tiền khuyến mãi: " + discount + " VND");

                totalPayment = DEFAULT_PRICE - discount;  // Tổng thanh toán sau khi trừ khuyến mãi
                totalPaymentTextView.setText("Tổng thanh toán: " + totalPayment + " VND");

                promoCodeErrorTextView.setVisibility(TextView.GONE);  // Ẩn thông báo lỗi nếu mã đúng
            } else {
                promoCodeErrorTextView.setVisibility(TextView.VISIBLE);  // Hiển thị thông báo lỗi nếu mã sai
            }
        });

        // Xử lý sự kiện cho nút thanh toán
        payButton.setOnClickListener(v -> {
            // Giả sử thanh toán thành công, chuyển sang màn hình HistoryActivity
            Intent intentHistory = new Intent(ThanhToanActivity.this, HistoryActivity.class);
            startActivity(intentHistory);
        });
    }
}


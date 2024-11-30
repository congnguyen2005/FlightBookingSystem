package com.example.flightbookingsystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightbookingsystem.R;

public class SupportActivity extends AppCompatActivity {

    private Spinner spinnerQuestionCategory;
    private TextView tvFaqList;
    private EditText etInputQuestion;
    private Button btnSendQuestion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        // Ánh xạ các thành phần giao diện
        spinnerQuestionCategory = findViewById(R.id.spinner_question_category);
        tvFaqList = findViewById(R.id.tv_faq_list); // TextView để hiển thị câu hỏi thường gặp
        etInputQuestion = findViewById(R.id.et_input_question);
        btnSendQuestion = findViewById(R.id.btn_send_question);

        // Thiết lập Spinner
        String[] categories = getResources().getStringArray(R.array.question_categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuestionCategory.setAdapter(adapter);

        // Hiển thị câu hỏi thường gặp
        tvFaqList.setText(getFaqList());

        // Xử lý nút gửi
        btnSendQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = etInputQuestion.getText().toString().trim();
                if (!question.isEmpty()) {
                    Toast.makeText(SupportActivity.this, "Câu hỏi đã được gửi!", Toast.LENGTH_SHORT).show();
                    etInputQuestion.setText("");
                } else {
                    Toast.makeText(SupportActivity.this, "Vui lòng nhập câu hỏi hoặc phản hồi.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Danh sách câu hỏi thường gặp dưới dạng chuỗi
    private String getFaqList() {
        return "1. Làm thế nào để kiểm tra tình trạng đặt vé?\n" +
                "2. Tôi có thể thay đổi thông tin hành khách sau khi đặt vé không?\n" +
                "3. Phương thức thanh toán nào được chấp nhận?\n" +
                "4. Làm sao để hủy vé máy bay đã đặt?\n" +
                "5. Quy định hành lý miễn cước là gì?\n" +
                "6. Làm thế nào để đặt vé cho trẻ em hoặc em bé?\n" +
                "7. Tôi có thể yêu cầu hoàn vé không và mất phí bao nhiêu?\n" +
                "8. Làm sao để nhận vé điện tử sau khi đặt vé?\n" +
                "9. Tôi có cần phải xuất trình giấy tờ gì khi làm thủ tục lên máy bay?";
    }
}

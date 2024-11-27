package com.example.flightbookingsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.R;
import com.example.flightbookingsystem.model.GoiYMain;

import java.util.List;

public class GoiYMainAdapter extends RecyclerView.Adapter<GoiYMainAdapter.GoiYMainViewHolder> {

    private List<GoiYMain> goiYList;

    public GoiYMainAdapter(List<GoiYMain> goiYList) {
        this.goiYList = goiYList;
    }

    @NonNull
    @Override
    public GoiYMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goiy_flight, parent, false);
        return new GoiYMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoiYMainViewHolder holder, int position) {
        GoiYMain goiY = goiYList.get(position);
        holder.tvFlightName.setText(goiY.getFlightName());
        holder.tvFlightDetails.setText("Thời gian bay: " + goiY.getFlightTime());
        holder.tvFlightPrice.setText("Giá: " + goiY.getFlightPrice());

        // Đặt hình ảnh tĩnh hoặc tùy chỉnh tải hình ảnh từ URL
        holder.ivFlightImage.setImageResource(R.drawable.anh); // Đổi theo nhu cầu
    }

    @Override
    public int getItemCount() {
        return goiYList != null ? goiYList.size() : 0;
    }

    public static class GoiYMainViewHolder extends RecyclerView.ViewHolder {
        TextView tvFlightName, tvFlightDetails, tvFlightPrice;
        ImageView ivFlightImage;

        public GoiYMainViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFlightName = itemView.findViewById(R.id.tv_flight_name);
            tvFlightDetails = itemView.findViewById(R.id.tv_flight_details);
            tvFlightPrice = itemView.findViewById(R.id.tv_flight_price);
            ivFlightImage = itemView.findViewById(R.id.iv_flight_image);
        }
    }
}

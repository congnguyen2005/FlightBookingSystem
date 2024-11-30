package com.example.flightbookingsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingsystem.R;

import java.util.List;

public class FlightResultsAdapter extends RecyclerView.Adapter<FlightResultsAdapter.ViewHolder> {

    private List<String> flightResults;

    public FlightResultsAdapter(List<String> flightResults) {
        this.flightResults = flightResults;
    }

    public void updateData(List<String> newResults) {
        flightResults = newResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(flightResults.get(position));
    }

    @Override
    public int getItemCount() {
        return flightResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}

package com.example.misha.myapplication.adapter.TabDays;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;

import androidx.recyclerview.widget.RecyclerView;

public class TabDaysAdapter extends RecyclerView.Adapter<TabDaysAdapter.ViewHolder> {


    public TabDaysAdapter(SimpleItemClickListener simpleItemClickListener) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tab_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public void setSelection(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final TextView dateWeek;


        public ViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
            dateWeek = view.findViewById(R.id.day_week);
        }

        public void onBindView(int position) {
            date.setText("31");
            dateWeek.setText("ПН");

        }

    }
}
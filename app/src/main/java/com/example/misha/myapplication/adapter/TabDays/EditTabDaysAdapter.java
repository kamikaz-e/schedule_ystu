package com.example.misha.myapplication.adapter.tabDays;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;

import androidx.recyclerview.widget.RecyclerView;

public class EditTabDaysAdapter extends RecyclerView.Adapter<EditTabDaysAdapter.ViewHolder> {

    private int selectedPos;
    private SimpleItemClickListener callback;

    public EditTabDaysAdapter(SimpleItemClickListener simpleItemClickListener) {
        this.callback = simpleItemClickListener;
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
        selectedPos = position;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView date;
        private final TextView dateWeek;
        private final RelativeLayout dayRelLay;


        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            date = view.findViewById(R.id.date);
            dateWeek = view.findViewById(R.id.day_week);
            dayRelLay = view.findViewById(R.id.dayRelLay);

        }

        public void onBindView(int position) {
            date.setText("31");
            dateWeek.setText("ПН");
            dayRelLay.setBackgroundColor(selectedPos == position ? Color.parseColor("#FF4081") : Color.TRANSPARENT);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            callback.onItemClick(clickedPosition, dayRelLay);
        }
    }
}
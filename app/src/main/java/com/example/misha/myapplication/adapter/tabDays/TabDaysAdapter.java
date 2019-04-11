package com.example.misha.myapplication.adapter.tabDays;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.fragments.SchedulePageFragment;

import java.util.Calendar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class TabDaysAdapter extends RecyclerView.Adapter<TabDaysAdapter.ViewHolder> {

    private int selectedPos = RecyclerView.NO_POSITION;
    int index = -1;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tab_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBindView(position);
        holder.dayRelLay.setOnClickListener(view -> {
            index = position;
                    notifyDataSetChanged();
        });
        if (index == position) {
            holder.dayRelLay.setBackgroundColor(Color.parseColor("#FF4081"));
        } else {
            holder.dayRelLay.setBackgroundColor(Color.TRANSPARENT);

        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public void setSelection(int position) {

    }
   /* public Fragment getItem(int position) {
        return SchedulePageFragment.newInstance(position);
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final TextView dateWeek;
        private final RelativeLayout dayRelLay;


        public ViewHolder(View view) {
            super(view);

            date = view.findViewById(R.id.date);
            dateWeek = view.findViewById(R.id.day_week);
            dayRelLay = view.findViewById(R.id.tab_day);

        }

        public void onBindView(int position) {
            date.setText("31");
            dateWeek.setText("ПН");

        }



    }
}
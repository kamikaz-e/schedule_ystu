package com.example.misha.myapplication.adapter.tabDays.editSchedule;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.ScheduleApp;
import com.example.misha.myapplication.SimpleItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TabDaysAdapterEditSchedule extends RecyclerView.Adapter<TabDaysAdapterEditSchedule.ViewHolder> {

    private int selectedPos;
    private SimpleItemClickListener callback;
    ArrayList<String> dayYear = new ArrayList<>();

    public TabDaysAdapterEditSchedule(SimpleItemClickListener simpleItemClickListener) {
        this.callback = simpleItemClickListener;
        updateData(Preferences.getInstance().getSelectedWeekEditSchedule());
    }

    public void updateData(int selectedPos) {
        dayYear.clear();
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(Preferences.getInstance().getSemestStart());
        mCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat mFormatDay = new SimpleDateFormat("dd EEE");
        mCalendar.add(Calendar.WEEK_OF_YEAR, selectedPos);
        for(int day = 0; day < 6; day++){
            dayYear.add(mFormatDay.format(mCalendar.getTime()).toUpperCase());
            mCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        notifyDataSetChanged();
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


        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            date = view.findViewById(R.id.date);

        }

        public void onBindView(int position) {
            date.setText(dayYear.get(position));
            itemView.setBackgroundColor(selectedPos == position ? Color.parseColor("#FF4081") : Color.TRANSPARENT);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            callback.onItemClick(clickedPosition, v);
        }
    }
}
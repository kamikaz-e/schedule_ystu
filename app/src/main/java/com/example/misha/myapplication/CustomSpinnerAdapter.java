package com.example.misha.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.misha.myapplication.data.Preferences;
import com.example.misha.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {


    private final Context activity;
    private ArrayList<String> listItems;


    public CustomSpinnerAdapter(Context context) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(Preferences.getInstance().getSemestStart());
        mCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ArrayList<String> allDays = new ArrayList<>();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mFormat = new SimpleDateFormat("dd.MM");
        for (int week = 0; week < 17; week++) {
            for (int day = 0; day < 7; day++) {
                String startWeek = mFormat.format(mCalendar.getTime());

                mCalendar.add(Calendar.WEEK_OF_YEAR, 1);
                mCalendar.add(Calendar.DAY_OF_YEAR, -1);
                allDays.add(startWeek + " - " + mFormat.format(mCalendar.getTime()));
                mCalendar.add(Calendar.DAY_OF_YEAR, 1);
                break;
            }
        }
        this.listItems = allDays;
        activity = context;
    }


    public int getCount() {
        return listItems.size();
    }

    public Object getItem(int i) {
        return listItems.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.spinner_custom, null);
        List<String> arrayWeek = Arrays.asList(view.getResources().getStringArray(R.array.weeks));
        TextView currentNumberWeek = view.findViewById(R.id.current_numberWeek);
        TextView currentDaysWeekRange = view.findViewById(R.id.current_daysWeekRange);
        currentNumberWeek.setText(listItems.get(position));
        currentDaysWeekRange.setText(arrayWeek.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(activity, R.layout.drop_down_spinner_custom, null);
        List<String> arrayWeek = Arrays.asList(view.getResources().getStringArray(R.array.weeks));
        final TextView numberWeekTextView = view.findViewById(R.id.number_week_textView);
        final TextView dayRangeTextView = view.findViewById(R.id.day_range_textView);
        dayRangeTextView.setText(listItems.get(position));
        numberWeekTextView.setText(arrayWeek.get(position));
        return view;
    }

}
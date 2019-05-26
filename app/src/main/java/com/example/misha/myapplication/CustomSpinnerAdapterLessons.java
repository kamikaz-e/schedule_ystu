package com.example.misha.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomSpinnerAdapterLessons extends BaseAdapter implements SpinnerAdapter {


    private Context activity;
    private ArrayList<String> listItems;


    public CustomSpinnerAdapterLessons(Context context) {
        ArrayList<String> allDays = new ArrayList<>();
        for (int lesson = 0; lesson < 6; lesson++) {
            allDays.add(lesson, "");
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
        @SuppressLint("ViewHolder") View view = View.inflate(activity, R.layout.spinner_custom_lessons, null);
        List<String> arrayWeek = Arrays.asList(view.getResources().getStringArray(R.array.lessons));
        TextView current_lesson = view.findViewById(R.id.current_lesson);
        current_lesson.setText(arrayWeek.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.drop_down_spinner_custom_lessons, null);
        List<String> arrayWeek = Arrays.asList(view.getResources().getStringArray(R.array.lessons));
        final TextView number_lesson = view.findViewById(R.id.number_lesson);
        number_lesson.setText(arrayWeek.get(position));
        return view;
    }

}
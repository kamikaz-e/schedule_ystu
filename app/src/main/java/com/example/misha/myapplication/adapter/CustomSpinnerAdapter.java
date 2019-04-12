package com.example.misha.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.misha.myapplication.R;

import androidx.annotation.NonNull;

public class CustomSpinnerAdapter extends ArrayAdapter {

    public CustomSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.spinner_custom, parent,false);
        return view;
    }


   /* public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        View row = inflater.inflate(yourRowlayout, parent,
                false);
        return row;
    }*/
}

package com.example.misha.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.misha.myapplication.R;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {


        private final Context activity;
        private ArrayList<String> listItems;

        public CustomSpinnerAdapter(Context context,ArrayList<String> listItems) {
            this.listItems=listItems;
            activity = context;
        }



        public int getCount()
        {
            return listItems.size();
        }

        public Object getItem(int i)
        {
            return listItems.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  View.inflate(activity, R.layout.spinner_custom, null);
        TextView textView = (TextView) view.findViewById(R.id.firstRow);
        textView.setText(listItems.get(position));
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view;
        view =  View.inflate(activity, R.layout.drop_down_spinner_custom, null);
        final TextView textView = (TextView) view.findViewById(R.id.dropRowFirst);
        final TextView textView1 = (TextView) view.findViewById(R.id.dropRowSecond);
        textView.setText(listItems.get(position));

        return view;
    }

}
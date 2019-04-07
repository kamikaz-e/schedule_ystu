package com.example.misha.myapplication.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;

public class ScheduleMonday extends android.support.v4.app.Fragment {
    Integer position_pager = 0;
   /* ArrayList<Calls> calls_schedule = new ArrayList<>();*/

    public ScheduleMonday() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void load_calls_schedule() {
        /*calls_schedule = CallDao.getInstance().getAllData();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      //  load_calls_schedule();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("choice_week", 0);
        position_pager = Integer.valueOf(preferences.getString("position", "0"));

        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        RecyclerView rv = rootView.findViewById(R.id.rv_recycler_view);


        return rootView;
    }
}
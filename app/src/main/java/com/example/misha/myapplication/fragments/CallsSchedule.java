package com.example.misha.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CallsScheduleAdapter;
import com.example.misha.myapplication.database.entity.Calls;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CallsSchedule extends Fragment {


    public static final int CALLS_CODE = 3434;

    public static final String CALLS = "CALLS";
    public static final String CALLS_LIST = "CALLS_LIST";
    public static final String POSITION = "POSITION";


    private ArrayList<Calls> listCalls;
    private RecyclerView rvCalls;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_call_schedule, container, false);

        rvCalls = view.findViewById(R.id.rv_calls);
        rvCalls.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalls.setAdapter(new CallsScheduleAdapter(getActivity()));

        return view;
    }


}

package com.example.misha.myapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CallsScheduleAdapter;
import com.example.misha.myapplication.database.entity.Calls;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CallsSchedule extends Fragment {


    public RecyclerView rvCalls;

    public static final int CALLS_CODE = 3434;

    public static final String CALLS = "CALLS";
    public static final String CALLS_LIST = "CALLS_LIST";
    public static final String POSITION = "POSITION";


    private ArrayList<Calls> listCalls;
    CallsScheduleAdapter listCallsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View  view = inflater.inflate(R.layout.activity_call_schedule, container, false);

        rvCalls = view.findViewById(R.id.rv_calls);

      /*  listCallsAdapter =  new CallsScheduleAdapter(listCalls, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(CALLS_LIST, listCalls.get(position));
            getParentFragment().onActivityResult(CALLS_CODE, Activity.RESULT_OK, intent);
        });
        //rvCalls.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
       */

       rvCalls.setAdapter(listCallsAdapter);

        return view;
    }


}

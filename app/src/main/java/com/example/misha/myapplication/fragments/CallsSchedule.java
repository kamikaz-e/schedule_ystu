package com.example.misha.myapplication.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CallsScheduleAdapter;
import com.example.misha.myapplication.adapter.editSchedule.CallScheduleCallback;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.entity.Calls;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;


public class CallsSchedule extends BaseFragment implements CallScheduleCallback {


    private CallsScheduleAdapter callsAdapter;
    private Calendar calendarTimeCalls = Calendar.getInstance();
    private Integer start = 1;
    private String selectPartTime = "";
    private Integer lesPos = 0;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle("Расписание звонков");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callsAdapter = new CallsScheduleAdapter(this);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_call_schedule, container, false);

        RecyclerView rvCalls = view.findViewById(R.id.rv_calls);
        rvCalls.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalls.setAdapter(callsAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onCallClick(int position) {
        lesPos = position;
        start = 1;

        new TimePickerDialog(getActivity(), timeOne,
                calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                calendarTimeCalls.get(Calendar.MINUTE), true)
                .show();
    }


    private void setInitialTimeOne() {
        if (start == 1) {
            selectPartTime = (DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME) + " - ");
        } else {
            String selectFullTime = selectPartTime + DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            ArrayList<Calls> callsList = CallDao.getInstance().getAllData();
            callsList.get(lesPos).setName(selectFullTime);
            callsAdapter.setCallsList(callsList);
            callsAdapter.notifyDataSetChanged();
            CallDao.getInstance().updateItemByID(callsList.get(lesPos));

        }
        if (start == 1) {
            new TimePickerDialog(getActivity(), timeOne,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start = 0;
        }
    }

    private TimePickerDialog.OnTimeSetListener timeOne = (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeOne();
    };


}

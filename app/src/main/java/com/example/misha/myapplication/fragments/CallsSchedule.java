package com.example.misha.myapplication.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CallsScheduleAdapter;
import com.example.misha.myapplication.adapter.editSchedule.CallScheduleCallback;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.fragmentsSchedule.FragmentScheduleByDays;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CallsSchedule extends Fragment implements CallScheduleCallback {


    private RecyclerView rvCalls;
    private CallsScheduleAdapter callsAdapter;

    Calendar calendarTimeCalls = Calendar.getInstance();
    ArrayList<Calls> callsList = new ArrayList<>();
    Integer start = 1;

    String selectPartTime = "";
    String selectFullTime = "";

    Integer lesPos = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callsAdapter = new CallsScheduleAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_call_schedule, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Button buttonHome = view.findViewById(R.id.buttonHome);
        TextView title = view.findViewById(R.id.title);
        title.setText("Расписание звонков");
        buttonHome.setBackgroundResource(R.drawable.ic_home);
        buttonHome.setOnClickListener(v -> {
            FragmentScheduleByDays fragment = new FragmentScheduleByDays();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        });

        rvCalls = view.findViewById(R.id.rv_calls);
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
            selectFullTime = selectPartTime + DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            callsList = CallDao.getInstance().getAllData();
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

    TimePickerDialog.OnTimeSetListener timeOne = (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeOne();
    };


}

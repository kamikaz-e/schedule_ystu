package com.example.misha.myapplication.module.callSchedule;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.data.database.dao.CallDao;
import com.example.misha.myapplication.data.database.entity.Calls;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;


public class CallsSchedule extends BaseMainFragment implements CallsView {


    private CallsScheduleAdapter callsAdapter;
    private Calendar calendarTimeCalls = Calendar.getInstance();
    private String selectDate = "";
    private CallsPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle("Расписание звонков");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CallsPresenter();
        callsAdapter = new CallsScheduleAdapter(presenter);
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

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void onClick(int position) {
        Preferences.getInstance().setSelectedPositionLesson(position);
        Preferences.getInstance().setCallsOpened(true);

        new TimePickerDialog(getActivity(), timeOne,
                calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                calendarTimeCalls.get(Calendar.MINUTE), true)
                .show();
    }


    private void setTime() {
        if (Preferences.getInstance().isCallsOpened()) {
            selectDate = (DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME) + " - ");
        } else {
            String selectFullTime = selectDate + DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            ArrayList<Calls> callsList = CallDao.getInstance().getAllData();
            callsList.get(Preferences.getInstance().getSelectedPositionLesson()).setName(selectFullTime);
            callsAdapter.setCallsList(callsList);
            callsAdapter.notifyDataSetChanged();
            CallDao.getInstance().updateItemByID(callsList.get(Preferences.getInstance().getSelectedPositionLesson()));

        }
        if (Preferences.getInstance().isCallsOpened()) {
            new TimePickerDialog(getActivity(), timeOne,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            Preferences.getInstance().setCallsOpened(false);
        }
    }

    private TimePickerDialog.OnTimeSetListener timeOne = (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setTime();
    };

}

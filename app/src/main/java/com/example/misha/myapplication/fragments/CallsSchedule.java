package com.example.misha.myapplication.fragments;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CallsScheduleAdapter;
import com.example.misha.myapplication.adapter.editSchedule.CallScheduleCallback;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Lesson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CallsSchedule extends Fragment implements CallScheduleCallback {


    public static final int CALLS_CODE = 3434;
    Calendar calendarTimeCalls = Calendar.getInstance();
    public static final String CALLS = "CALLS";
    public static final String CALLS_LIST = "CALLS_LIST";
    public static final String POSITION = "POSITION";


    private RecyclerView rvCalls;
    CallsScheduleAdapter callsAdapter;

    private View fragmentView;
    private List<Lesson> lessonList = new ArrayList<>();
    ArrayList<Calls> callsList = new ArrayList<>();
    Integer start = 1;

    private int clickedPosition;
    private ArrayList<Calls> listCalls;
    private RecyclerView rvAudience;

    private int positionWeek;
    private int day;

    String select_time_partOne = "";
    String select_time_fullOne = "";
    String select_time_partTwo = "";
    String select_time_fullTwo = "";
    String select_time_partThree = "";
    String select_time_fullThree = "";
    String select_time_partFour = "";
    String select_time_fullFour = "";
    String select_time_partFive = "";
    String select_time_fullFive = "";
    String select_time_partSix = "";
    String select_time_fullSix = "";
    Integer lesPos = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* clickedPosition = getArguments().getInt(POSITION);
        listCalls = getArguments().getParcelableArrayList(CALLS);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_call_schedule, container, false);

        rvCalls = view.findViewById(R.id.rv_calls);
        rvCalls.setLayoutManager(new LinearLayoutManager(getActivity()));
        //ADAPTER
        rvCalls.setAdapter(new CallsScheduleAdapter(this));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onCallClick(int position) {
        lesPos = position;


        new TimePickerDialog(getActivity(), timeOne,
                calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                calendarTimeCalls.get(Calendar.MINUTE), true)
                .show();
    }

    private void setInitialTimeOne() {
        if (start == 1) {
            select_time_partOne = (DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME) + " - ");
        } else {
            select_time_fullOne = select_time_partOne + DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            callsList = CallDao.getInstance().getAllData();
            callsList.get(lesPos).setName(select_time_fullOne);
            CallDao.getInstance().updateItemByID(callsList.get(lesPos));

          /*  Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(CALLS_LIST, listCalls.get(lesPos));*/
          /*  getParentFragment().onActivityResult(CALLS_CODE, Activity.RESULT_OK, intent);*/

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


    @Override
    public void onActivityResult(int requestCode, int resultOk, Intent data) {

            int lessonPosition = data.getIntExtra(POSITION, 0);
            Calls calls = data.getParcelableExtra(CALLS_LIST);
            lessonList.get(lessonPosition).setSubject(calls.getId());
            /*rvCalls.setLessonList(lessonList);
            rvCalls.notifyDataSetChanged();*/
            CallDao.getInstance().updateItemByID(callsList.get(lessonPosition));

    }

}

package com.example.misha.myapplication.startFragments;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.misha.myapplication.activity.MainActivity;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.entity.Calls;

import java.util.Calendar;

import androidx.fragment.app.Fragment;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class FragmentCallSchedule extends Fragment {


    public FragmentCallSchedule() {

    }
    String select_time_partOne="";
    String select_time_fullOne="";
    String select_time_partTwo="";
    String select_time_fullTwo="";
    String select_time_partThree="";
    String select_time_fullThree="";
    String select_time_partFour="";
    String select_time_fullFour="";
    String select_time_partFive="";
    String select_time_fullFive="";
    String select_time_partSix="";
    String select_time_fullSix="";

    EditText oneTime;
    EditText twoTime;
    EditText threeTime;
    EditText fourTime;
    EditText fiveTime;
    EditText sixTime;

    Calendar calendarTimeCalls =Calendar.getInstance();
    Integer start=1;
    Button button_toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_call_schedule, container, false);
        Toolbar profile_toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(profile_toolbar);


        button_toolbar = view.findViewById(R.id.toolbar_but);
        button_toolbar.setBackgroundResource(R.drawable.ic_start_settings_ok);
        button_toolbar.setOnClickListener(v -> {
            save_calls();
            FragmentStartSubject fragment= new FragmentStartSubject();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        oneTime = view.findViewById(R.id.OneTime);
        twoTime = view.findViewById(R.id.TwoTime);
        threeTime = view.findViewById(R.id.ThreeTime);
        fourTime = view.findViewById(R.id.FourTime);
        fiveTime = view.findViewById(R.id.FiveTime);
        sixTime = view.findViewById(R.id.SixTime);



        oneTime.setOnClickListener(v -> {
            new TimePickerDialog(getActivity(), timeOne,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=1;
        });

        twoTime.setOnClickListener(v -> {
            new TimePickerDialog(getActivity(), timeTwo,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=1;
        });

        threeTime.setOnClickListener(v -> {
            new TimePickerDialog(getActivity(), timeThree,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=1;
        });

        fourTime.setOnClickListener(v -> {
            new TimePickerDialog(getActivity(), timeFour,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=1;
        });

        fiveTime.setOnClickListener(v -> {
            new TimePickerDialog(getActivity(), timeFive,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=1;
        });

        sixTime.setOnClickListener(v -> {
            new TimePickerDialog(getActivity(), timeSix,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=1;
        });

        return view;
    }




    private void setInitialTimeOne() {
        if (start==1) {
            select_time_partOne=(DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullOne= select_time_partOne+DateUtils.formatDateTime(getActivity(),
                calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            oneTime.setText(select_time_fullOne);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeOne,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeOne= (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeOne();
    };



    private void setInitialTimeTwo() {
        if (start==1) {
            select_time_partTwo=(DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullTwo= select_time_partTwo+DateUtils.formatDateTime(getActivity(),
                calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            twoTime.setText(select_time_fullTwo);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeTwo,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeTwo= (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeTwo();
    };



    private void setInitialTimeThree() {
        if (start==1) {
            select_time_partThree=(DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullThree= select_time_partThree+DateUtils.formatDateTime(getActivity(),
                calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            threeTime.setText(select_time_fullThree);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeThree,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeThree= (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeThree();
    };



    private void setInitialTimeFour() {
        if (start==1) {
            select_time_partFour=(DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullFour= select_time_partFour+DateUtils.formatDateTime(getActivity(),
                calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            fourTime.setText(select_time_fullFour);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeFour,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeFour= (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeFour();
    };


    private void setInitialTimeFive() {
        if (start==1) {
            select_time_partFive=(DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullFive= select_time_partFive+DateUtils.formatDateTime(getActivity(),
                calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            fiveTime.setText(select_time_fullFive);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeFive,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeFive= (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeFive();
    };


    private void setInitialTimeSix() {
        if (start==1) {
            select_time_partSix=(DateUtils.formatDateTime(getActivity(),
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullSix= select_time_partSix+DateUtils.formatDateTime(getActivity(),
                calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            sixTime.setText(select_time_fullSix);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeSix,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeSix= (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setInitialTimeSix();
    };


    void save_calls(){
        CallDao.getInstance().deleteAll();
        Calls calls = new Calls();
        calls.setName(select_time_fullOne);
        CallDao.getInstance().insertItem(calls);
        calls.setName(select_time_fullTwo);
        CallDao.getInstance().insertItem(calls);
        calls.setName(select_time_fullThree);
        CallDao.getInstance().insertItem(calls);
        calls.setName(select_time_fullFour);
        CallDao.getInstance().insertItem(calls);
        calls.setName(select_time_fullFive);
        CallDao.getInstance().insertItem(calls);
        calls.setName(select_time_fullSix);
        CallDao.getInstance().insertItem(calls);
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                    save_calls();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().finish();
                    startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

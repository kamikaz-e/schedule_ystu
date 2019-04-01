package com.example.misha.myapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.Calendar;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class FragmentCallSchedule extends android.support.v4.app.Fragment {


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
    private ScheduleDB ScheduleDB;
    Calendar Time=Calendar.getInstance();
    Integer start=1;
    Button button_toolbar;
    String hasVisited;
    SharedPreferences sp;

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
        button_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_calls();
                FragmentStartSubject fragment= new FragmentStartSubject();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        oneTime = view.findViewById(R.id.OneTime);
        twoTime = view.findViewById(R.id.TwoTime);
        threeTime = view.findViewById(R.id.ThreeTime);
        fourTime = view.findViewById(R.id.FourTime);
        fiveTime = view.findViewById(R.id.FiveTime);
        sixTime = view.findViewById(R.id.SixTime);
        ScheduleDB = new ScheduleDB(getActivity());


        CardView cardViewOne= view.findViewById(R.id.card_viewOne);


            new MaterialTapTargetPrompt.Builder(getActivity())
                    .setTarget(cardViewOne)
                    .setPromptBackground(new RectanglePromptBackground())
                    .setPromptFocal(new RectanglePromptFocal())
                    .setPrimaryText("Расписание звонков")
                    .setSecondaryText("Вы можете задать расписание звонков для занятий. При нажатии на кнопку «Выбрать» укажите время начала занятия, нажмите кнопку «Ок», затем укажите время окончания занятия и так же нажмите кнопку «Ок»")
                    .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                    .setBackgroundColour(Color.rgb(100, 100, 255))
                    .setPrimaryTextColour(Color.rgb(255, 255, 255))
                    .setSecondaryTextColour(Color.rgb(255, 255, 255))
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {

                            }
                        }
                    })
                    .show();



        oneTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), timeOne,
                        Time.get(Calendar.HOUR_OF_DAY),
                        Time.get(Calendar.MINUTE), true)
                        .show();
                start=1;
            }
        });

        twoTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), timeTwo,
                        Time.get(Calendar.HOUR_OF_DAY),
                        Time.get(Calendar.MINUTE), true)
                        .show();
                start=1;
            }
        });

        threeTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), timeThree,
                        Time.get(Calendar.HOUR_OF_DAY),
                        Time.get(Calendar.MINUTE), true)
                        .show();
                start=1;
            }
        });

        fourTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), timeFour,
                        Time.get(Calendar.HOUR_OF_DAY),
                        Time.get(Calendar.MINUTE), true)
                        .show();
                start=1;
            }
        });

        fiveTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), timeFive,
                        Time.get(Calendar.HOUR_OF_DAY),
                        Time.get(Calendar.MINUTE), true)
                        .show();
                start=1;
            }
        });

        sixTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), timeSix,
                        Time.get(Calendar.HOUR_OF_DAY),
                        Time.get(Calendar.MINUTE), true)
                        .show();
                start=1;
            }
        });

        return view;
    }




    private void setInitialTimeOne() {
        if (start==1) {
            select_time_partOne=(DateUtils.formatDateTime(getActivity(),
                    Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullOne= select_time_partOne+DateUtils.formatDateTime(getActivity(),
                Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            oneTime.setText(select_time_fullOne);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeOne,
                    Time.get(Calendar.HOUR_OF_DAY),
                    Time.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeOne=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            Time.set(Calendar.MINUTE, minute);
            setInitialTimeOne();
        }
    };



    private void setInitialTimeTwo() {
        if (start==1) {
            select_time_partTwo=(DateUtils.formatDateTime(getActivity(),
                    Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullTwo= select_time_partTwo+DateUtils.formatDateTime(getActivity(),
                Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            twoTime.setText(select_time_fullTwo);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeTwo,
                    Time.get(Calendar.HOUR_OF_DAY),
                    Time.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeTwo=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            Time.set(Calendar.MINUTE, minute);
            setInitialTimeTwo();
        }
    };



    private void setInitialTimeThree() {
        if (start==1) {
            select_time_partThree=(DateUtils.formatDateTime(getActivity(),
                    Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullThree= select_time_partThree+DateUtils.formatDateTime(getActivity(),
                Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            threeTime.setText(select_time_fullThree);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeThree,
                    Time.get(Calendar.HOUR_OF_DAY),
                    Time.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeThree=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            Time.set(Calendar.MINUTE, minute);
            setInitialTimeThree();
        }
    };



    private void setInitialTimeFour() {
        if (start==1) {
            select_time_partFour=(DateUtils.formatDateTime(getActivity(),
                    Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullFour= select_time_partFour+DateUtils.formatDateTime(getActivity(),
                Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            fourTime.setText(select_time_fullFour);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeFour,
                    Time.get(Calendar.HOUR_OF_DAY),
                    Time.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeFour=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            Time.set(Calendar.MINUTE, minute);
            setInitialTimeFour();
        }
    };


    private void setInitialTimeFive() {
        if (start==1) {
            select_time_partFive=(DateUtils.formatDateTime(getActivity(),
                    Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullFive= select_time_partFive+DateUtils.formatDateTime(getActivity(),
                Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            fiveTime.setText(select_time_fullFive);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeFive,
                    Time.get(Calendar.HOUR_OF_DAY),
                    Time.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeFive=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            Time.set(Calendar.MINUTE, minute);
            setInitialTimeFive();
        }
    };


    private void setInitialTimeSix() {
        if (start==1) {
            select_time_partSix=(DateUtils.formatDateTime(getActivity(),
                    Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
        }  else
        {   select_time_fullSix= select_time_partSix+DateUtils.formatDateTime(getActivity(),
                Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            sixTime.setText(select_time_fullSix);
        }
        if (start==1){
            new TimePickerDialog(getActivity(), timeSix,
                    Time.get(Calendar.HOUR_OF_DAY),
                    Time.get(Calendar.MINUTE), true)
                    .show();
            start=0; }
    }

    TimePickerDialog.OnTimeSetListener timeSix=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            Time.set(Calendar.MINUTE, minute);
            setInitialTimeSix();
        }
    };


    void save_calls(){
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DROP TABLE " + ScheduleClass.calls.TABLE_NAME);
            db.execSQL("CREATE TABLE " + ScheduleClass.calls.TABLE_NAME + " ("
                    + ScheduleClass.calls.id_call + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.calls.time + " STRING );");
            db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('"+ select_time_fullOne +"');");
            db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('"+ select_time_fullTwo +"');");
            db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('"+ select_time_fullThree +"');");
            db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('"+ select_time_fullFour +"');");
            db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('"+ select_time_fullFive +"');");
            db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('"+ select_time_fullSix +"');");

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (hasVisited == "nope"
                ) {
                    save_calls();
                    getActivity().finish();
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("hasVisited", "yes");
                    e.commit();
                }
                else
                {
                    save_calls();
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    getActivity().finish();
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

package com.example.misha.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.Calendar;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class fragment_date extends android.support.v4.app.Fragment {


    RelativeLayout layout_pich_week;
    public fragment_date(){}


    Calendar Date = Calendar.getInstance();
    private ScheduleDB ScheduleDB;
    String  current_date;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =inflater.inflate(R.layout.fragment_date, container, false);
        ScheduleDB = new ScheduleDB(getActivity());
        layout_pich_week = view.findViewById(R.id.oneitem);

        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(layout_pich_week)
                .setPromptBackground(new RectanglePromptBackground())
                .setPromptFocal(new RectanglePromptFocal())
                .setPrimaryText("Дата начала семестра")
                .setSecondaryText("Выберите дату начала семестра для автоматического определения текущей учебной недели")
                .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200,200,255))
                .setBackgroundColour(Color.rgb(100,100,255))
                .setPrimaryTextColour(Color.rgb(255,255,255))
                .setSecondaryTextColour(Color.rgb(255,255,255))
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                        if (state== MaterialTapTargetPrompt.STATE_DISMISSED ) {
                            fragment_call_schedule fragment= new fragment_call_schedule();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }


                })
                .show();

        layout_pich_week.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dateone,
                        Date.get(Calendar.YEAR),
                        Date.get(Calendar.MONTH),
                        Date.get(Calendar.DAY_OF_MONTH)).show();
            }
            DatePickerDialog.OnDateSetListener dateone = new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Date.set(Calendar.YEAR, year);
                    Date.set(Calendar.MONTH, monthOfYear);
                    Date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    Calendar today = Calendar.getInstance();
                    current_date = String.valueOf(Date.getTimeInMillis());
                    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                    db.execSQL("update " + ScheduleClass.date_start.TABLE_NAME + " set " + ScheduleClass.date_start.date + " = '" +
                            current_date  + "' where " + ScheduleClass.date_start.id_date + " = " + 1);
                    // Toast.makeText(context, String.valueOf(today.getTimeInMillis()), Toast.LENGTH_SHORT).show();

                    SharedPreferences settings = getActivity().getSharedPreferences("week", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putLong("current_week", (Date.getTimeInMillis()));
                    editor.commit();
                    fragment_call_schedule fragment= new fragment_call_schedule();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();

                }
            };});

        return view;
    }

}

package com.example.misha.myapplication.startFragments;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;

import java.util.Calendar;

import androidx.fragment.app.Fragment;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class FragmentDate extends Fragment {


    RelativeLayout layout_pich_week;
    public FragmentDate(){}


    private Calendar calendarDate = Calendar.getInstance();
    private String  current_date;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =inflater.inflate(R.layout.fragment_date, container, false);
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
                .setPromptStateChangeListener((prompt, state) -> {
                    if (state== MaterialTapTargetPrompt.STATE_DISMISSED ) {
                      /*  FragmentCallSchedule fragment= new FragmentCallSchedule();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, fragment)
                                .addToBackStack(null)
                                .commit();*/
                    }
                })
                .show();

        layout_pich_week.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dateone,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH)).show();
            }
            DatePickerDialog.OnDateSetListener dateone = new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    calendarDate.set(Calendar.YEAR, year);
                    calendarDate.set(Calendar.MONTH, monthOfYear);
                    calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    current_date = String.valueOf(calendarDate.getTimeInMillis());

                   /* db.execSQL("update " + DATE_START + " set " + ScheduleClass.date_start.date + " = '" +
                            selectedDate  + "' where " + ScheduleClass.date_start.id_date + " = " + 1);*/
                    // Toast.makeText(context, String.valueOf(today.getTimeInMillis()), Toast.LENGTH_SHORT).show();

                /*    Preferences.getInstance().setSemesterStart(calendarDate.getTimeInMillis());
                    FragmentCallSchedule fragment= new FragmentCallSchedule();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();*/

                }
            };});

        return view;
    }

}

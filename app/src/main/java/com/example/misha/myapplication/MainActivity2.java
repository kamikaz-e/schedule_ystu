package com.example.misha.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.audiences;
import com.example.misha.myapplication.data.ScheduleClass.calls;
import com.example.misha.myapplication.data.ScheduleClass.educators;
import com.example.misha.myapplication.data.ScheduleClass.schedule;
import com.example.misha.myapplication.data.ScheduleClass.subjects;
import com.example.misha.myapplication.data.ScheduleDB;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.CirclePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.CirclePromptFocal;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class MainActivity2 extends AppCompatActivity {

    Switch switcher_save;
    Spinner SubjectEditOne;
    Spinner SubjectEditTwo;
    Spinner SubjectEditThree;
    Spinner SubjectEditFour;
    Spinner SubjectEditFive;
    Spinner SubjectEditSix;
    Spinner AudienceEditOne;
    Spinner AudienceEditTwo;
    Spinner AudienceEditThree;
    Spinner AudienceEditFour;
    Spinner AudienceEditFive;
    Spinner AudienceEditSix;
    Spinner EducatorEditOne;
    Spinner EducatorEditTwo;
    Spinner EducatorEditThree;
    Spinner EducatorEditFour;
    Spinner EducatorEditFive;
    Spinner EducatorEditSix;
    TextView NumberOne;
    TextView NumberTwo;
    TextView NumberThree;
    TextView NumberFour;
    TextView NumberFive;
    TextView NumberSix;
    TextView TimeOne;
    TextView TimeTwo;
    TextView TimeThree;
    TextView TimeFour;
    TextView TimeFive;
    TextView TimeSix;

    RadioButton rb_lecture;
    RadioButton rb_labwork;
    RadioButton rb_practice;
    RadioButton rb_lectureTwo;
    RadioButton rb_labworkTwo;
    RadioButton rb_practiceTwo;
    RadioButton rb_lectureThree;
    RadioButton rb_labworkThree;
    RadioButton rb_practiceThree;
    RadioButton rb_lectureFour;
    RadioButton rb_labworkFour;
    RadioButton rb_practiceFour;
    RadioButton rb_lectureFive;
    RadioButton rb_labworkFive;
    RadioButton rb_practiceFive;
    RadioButton rb_lectureSix;
    RadioButton rb_labworkSix;
    RadioButton rb_practiceSix;
    RadioGroup typeEditOne_monday;
    RadioGroup typeEditTwo_monday;
    RadioGroup typeEditThree_monday;
    RadioGroup typeEditFour_monday;
    RadioGroup typeEditFive_monday;
    RadioGroup typeEditSix_monday;
    RadioGroup typeEditOne_tuesday;
    RadioGroup typeEditTwo_tuesday;
    RadioGroup typeEditThree_tuesday;
    RadioGroup typeEditFour_tuesday;
    RadioGroup typeEditFive_tuesday;
    RadioGroup typeEditSix_tuesday;
    RadioGroup typeEditOne_wednesday;
    RadioGroup typeEditTwo_wednesday;
    RadioGroup typeEditThree_wednesday;
    RadioGroup typeEditFour_wednesday;
    RadioGroup typeEditFive_wednesday;
    RadioGroup typeEditSix_wednesday;
    RadioGroup typeEditOne_thursday;
    RadioGroup typeEditTwo_thursday;
    RadioGroup typeEditThree_thursday;
    RadioGroup typeEditFour_thursday;
    RadioGroup typeEditFive_thursday;
    RadioGroup typeEditSix_thursday;
    RadioGroup typeEditOne_friday;
    RadioGroup typeEditTwo_friday;
    RadioGroup typeEditThree_friday;
    RadioGroup typeEditFour_friday;
    RadioGroup typeEditFive_friday;
    RadioGroup typeEditSix_friday;
    RadioGroup typeEditOne_saturday;
    RadioGroup typeEditTwo_saturday;
    RadioGroup typeEditThree_saturday;
    RadioGroup typeEditFour_saturday;
    RadioGroup typeEditFive_saturday;
    RadioGroup typeEditSix_saturday;

    Integer IdRadioButtonOne;
    Integer IdRadioButtonTwo;
    Integer IdRadioButtonThree;
    Integer IdRadioButtonFour;
    Integer IdRadioButtonFive;
    Integer IdRadioButtonSix;

    String MondayStringSubjectEditOne = "";
    String MondayStringSubjectEditTwo = "";
    String MondayStringSubjectEditThree = "";
    String MondayStringSubjectEditFour = "";
    String MondayStringSubjectEditFive = "";
    String MondayStringSubjectEditSix = "";
    String MondayStringAudienceEditOne = "";
    String MondayStringAudienceEditTwo = "";
    String MondayStringAudienceEditThree = "";
    String MondayStringAudienceEditFour = "";
    String MondayStringAudienceEditFive = "";
    String MondayStringAudienceEditSix = "";
    String MondayStringTypeLessonEditOne = "";
    String MondayStringTypeLessonEditTwo = "";
    String MondayStringTypeLessonEditThree = "";
    String MondayStringTypeLessonEditFour = "";
    String MondayStringTypeLessonEditFive = "";
    String MondayStringTypeLessonEditSix = "";
    String MondayStringEducatorEditOne = "";
    String MondayStringEducatorEditTwo = "";
    String MondayStringEducatorEditThree = "";
    String MondayStringEducatorEditFour = "";
    String MondayStringEducatorEditFive = "";
    String MondayStringEducatorEditSix = "";
    String TuesdayStringSubjectEditOne = "";
    String TuesdayStringSubjectEditTwo = "";
    String TuesdayStringSubjectEditThree = "";
    String TuesdayStringSubjectEditFour = "";
    String TuesdayStringSubjectEditFive = "";
    String TuesdayStringSubjectEditSix = "";
    String TuesdayStringAudienceEditOne = "";
    String TuesdayStringAudienceEditTwo = "";
    String TuesdayStringAudienceEditThree = "";
    String TuesdayStringAudienceEditFour = "";
    String TuesdayStringAudienceEditFive = "";
    String TuesdayStringAudienceEditSix = "";
    String TuesdayStringTypeLessonEditOne = "";
    String TuesdayStringTypeLessonEditTwo = "";
    String TuesdayStringTypeLessonEditThree = "";
    String TuesdayStringTypeLessonEditFour = "";
    String TuesdayStringTypeLessonEditFive = "";
    String TuesdayStringTypeLessonEditSix = "";
    String TuesdayStringEducatorEditOne = "";
    String TuesdayStringEducatorEditTwo = "";
    String TuesdayStringEducatorEditThree = "";
    String TuesdayStringEducatorEditFour = "";
    String TuesdayStringEducatorEditFive = "";
    String TuesdayStringEducatorEditSix = "";

    String WednesdayStringSubjectEditOne = "";
    String WednesdayStringSubjectEditTwo = "";
    String WednesdayStringSubjectEditThree = "";
    String WednesdayStringSubjectEditFour = "";
    String WednesdayStringSubjectEditFive = "";
    String WednesdayStringSubjectEditSix = "";
    String WednesdayStringAudienceEditOne = "";
    String WednesdayStringAudienceEditTwo = "";
    String WednesdayStringAudienceEditThree = "";
    String WednesdayStringAudienceEditFour = "";
    String WednesdayStringAudienceEditFive = "";
    String WednesdayStringAudienceEditSix = "";
    String WednesdayStringTypeLessonEditOne = "";
    String WednesdayStringTypeLessonEditTwo = "";
    String WednesdayStringTypeLessonEditThree = "";
    String WednesdayStringTypeLessonEditFour = "";
    String WednesdayStringTypeLessonEditFive = "";
    String WednesdayStringTypeLessonEditSix = "";
    String WednesdayStringEducatorEditOne = "";
    String WednesdayStringEducatorEditTwo = "";
    String WednesdayStringEducatorEditThree = "";
    String WednesdayStringEducatorEditFour = "";
    String WednesdayStringEducatorEditFive = "";
    String WednesdayStringEducatorEditSix = "";

    String ThursdayStringSubjectEditOne = "";
    String ThursdayStringSubjectEditTwo = "";
    String ThursdayStringSubjectEditThree = "";
    String ThursdayStringSubjectEditFour = "";
    String ThursdayStringSubjectEditFive = "";
    String ThursdayStringSubjectEditSix = "";
    String ThursdayStringAudienceEditOne = "";
    String ThursdayStringAudienceEditTwo = "";
    String ThursdayStringAudienceEditThree = "";
    String ThursdayStringAudienceEditFour = "";
    String ThursdayStringAudienceEditFive = "";
    String ThursdayStringAudienceEditSix = "";
    String ThursdayStringTypeLessonEditOne = "";
    String ThursdayStringTypeLessonEditTwo = "";
    String ThursdayStringTypeLessonEditThree = "";
    String ThursdayStringTypeLessonEditFour = "";
    String ThursdayStringTypeLessonEditFive = "";
    String ThursdayStringTypeLessonEditSix = "";
    String ThursdayStringEducatorEditOne = "";
    String ThursdayStringEducatorEditTwo = "";
    String ThursdayStringEducatorEditThree = "";
    String ThursdayStringEducatorEditFour = "";
    String ThursdayStringEducatorEditFive = "";
    String ThursdayStringEducatorEditSix = "";

    String FridayStringSubjectEditOne = "";
    String FridayStringSubjectEditTwo = "";
    String FridayStringSubjectEditThree = "";
    String FridayStringSubjectEditFour = "";
    String FridayStringSubjectEditFive = "";
    String FridayStringSubjectEditSix = "";
    String FridayStringAudienceEditOne = "";
    String FridayStringAudienceEditTwo = "";
    String FridayStringAudienceEditThree = "";
    String FridayStringAudienceEditFour = "";
    String FridayStringAudienceEditFive = "";
    String FridayStringAudienceEditSix = "";
    String FridayStringTypeLessonEditOne = "";
    String FridayStringTypeLessonEditTwo = "";
    String FridayStringTypeLessonEditThree = "";
    String FridayStringTypeLessonEditFour = "";
    String FridayStringTypeLessonEditFive = "";
    String FridayStringTypeLessonEditSix = "";
    String FridayStringEducatorEditOne = "";
    String FridayStringEducatorEditTwo = "";
    String FridayStringEducatorEditThree = "";
    String FridayStringEducatorEditFour = "";
    String FridayStringEducatorEditFive = "";
    String FridayStringEducatorEditSix = "";

    String SaturdayStringSubjectEditOne = "";
    String SaturdayStringSubjectEditTwo = "";
    String SaturdayStringSubjectEditThree = "";
    String SaturdayStringSubjectEditFour = "";
    String SaturdayStringSubjectEditFive = "";
    String SaturdayStringSubjectEditSix = "";
    String SaturdayStringAudienceEditOne = "";
    String SaturdayStringAudienceEditTwo = "";
    String SaturdayStringAudienceEditThree = "";
    String SaturdayStringAudienceEditFour = "";
    String SaturdayStringAudienceEditFive = "";
    String SaturdayStringAudienceEditSix = "";
    String SaturdayStringTypeLessonEditOne = "";
    String SaturdayStringTypeLessonEditTwo = "";
    String SaturdayStringTypeLessonEditThree = "";
    String SaturdayStringTypeLessonEditFour = "";
    String SaturdayStringTypeLessonEditFive = "";
    String SaturdayStringTypeLessonEditSix = "";
    String SaturdayStringEducatorEditOne = "";
    String SaturdayStringEducatorEditTwo = "";
    String SaturdayStringEducatorEditThree = "";
    String SaturdayStringEducatorEditFour = "";
    String SaturdayStringEducatorEditFive = "";
    String SaturdayStringEducatorEditSix = "";

    String MondayValueSubjectOne = "";
    String MondayValueSubjectTwo = "";
    String MondayValueSubjectThree = "";
    String MondayValueSubjectFour = "";
    String MondayValueSubjectFive = "";
    String MondayValueSubjectSix = "";
    String MondayValueAudienceOne = "";
    String MondayValueAudienceTwo = "";
    String MondayValueAudienceThree = "";
    String MondayValueAudienceFour = "";
    String MondayValueAudienceFive = "";
    String MondayValueAudienceSix = "";
    String MondayEducatorOne = "";
    String MondayEducatorTwo = "";
    String MondayEducatorThree = "";
    String MondayEducatorFour = "";
    String MondayEducatorFive = "";
    String MondayEducatorSix = "";
    String MondayTypeLessonOne = "";
    String MondayTypeLessonTwo = "";
    String MondayTypeLessonThree = "";
    String MondayTypeLessonFour = "";
    String MondayTypeLessonFive = "";
    String MondayTypeLessonSix = "";
    String TuesdayValueSubjectOne = "";
    String TuesdayValueSubjectTwo = "";
    String TuesdayValueSubjectThree = "";
    String TuesdayValueSubjectFour = "";
    String TuesdayValueSubjectFive = "";
    String TuesdayValueSubjectSix = "";
    String TuesdayValueAudienceOne = "";
    String TuesdayValueAudienceTwo = "";
    String TuesdayValueAudienceThree = "";
    String TuesdayValueAudienceFour = "";
    String TuesdayValueAudienceFive = "";
    String TuesdayValueAudienceSix = "";
    String TuesdayEducatorOne = "";
    String TuesdayEducatorTwo = "";
    String TuesdayEducatorThree = "";
    String TuesdayEducatorFour = "";
    String TuesdayEducatorFive = "";
    String TuesdayEducatorSix = "";
    String TuesdayTypeLessonOne = "";
    String TuesdayTypeLessonTwo = "";
    String TuesdayTypeLessonThree = "";
    String TuesdayTypeLessonFour = "";
    String TuesdayTypeLessonFive = "";
    String TuesdayTypeLessonSix = "";
    String WednesdayValueSubjectOne = "";
    String WednesdayValueSubjectTwo = "";
    String WednesdayValueSubjectThree = "";
    String WednesdayValueSubjectFour = "";
    String WednesdayValueSubjectFive = "";
    String WednesdayValueSubjectSix = "";
    String WednesdayValueAudienceOne = "";
    String WednesdayValueAudienceTwo = "";
    String WednesdayValueAudienceThree = "";
    String WednesdayValueAudienceFour = "";
    String WednesdayValueAudienceFive = "";
    String WednesdayValueAudienceSix = "";
    String WednesdayEducatorOne = "";
    String WednesdayEducatorTwo = "";
    String WednesdayEducatorThree = "";
    String WednesdayEducatorFour = "";
    String WednesdayEducatorFive = "";
    String WednesdayEducatorSix = "";
    String WednesdayTypeLessonOne = "";
    String WednesdayTypeLessonTwo = "";
    String WednesdayTypeLessonThree = "";
    String WednesdayTypeLessonFour = "";
    String WednesdayTypeLessonFive = "";
    String WednesdayTypeLessonSix = "";

    String ThursdayValueSubjectOne = "";
    String ThursdayValueSubjectTwo = "";
    String ThursdayValueSubjectThree = "";
    String ThursdayValueSubjectFour = "";
    String ThursdayValueSubjectFive = "";
    String ThursdayValueSubjectSix = "";
    String ThursdayValueAudienceOne = "";
    String ThursdayValueAudienceTwo = "";
    String ThursdayValueAudienceThree = "";
    String ThursdayValueAudienceFour = "";
    String ThursdayValueAudienceFive = "";
    String ThursdayValueAudienceSix = "";
    String ThursdayEducatorOne = "";
    String ThursdayEducatorTwo = "";
    String ThursdayEducatorThree = "";
    String ThursdayEducatorFour = "";
    String ThursdayEducatorFive = "";
    String ThursdayEducatorSix = "";
    String ThursdayTypeLessonOne = "";
    String ThursdayTypeLessonTwo = "";
    String ThursdayTypeLessonThree = "";
    String ThursdayTypeLessonFour = "";
    String ThursdayTypeLessonFive = "";
    String ThursdayTypeLessonSix = "";
    String FridayValueSubjectOne = "";
    String FridayValueSubjectTwo = "";
    String FridayValueSubjectThree = "";
    String FridayValueSubjectFour = "";
    String FridayValueSubjectFive = "";
    String FridayValueSubjectSix = "";
    String FridayValueAudienceOne = "";
    String FridayValueAudienceTwo = "";
    String FridayValueAudienceThree = "";
    String FridayValueAudienceFour = "";
    String FridayValueAudienceFive = "";
    String FridayValueAudienceSix = "";
    String FridayEducatorOne = "";
    String FridayEducatorTwo = "";
    String FridayEducatorThree = "";
    String FridayEducatorFour = "";
    String FridayEducatorFive = "";
    String FridayEducatorSix = "";
    String FridayTypeLessonOne = "";
    String FridayTypeLessonTwo = "";
    String FridayTypeLessonThree = "";
    String FridayTypeLessonFour = "";
    String FridayTypeLessonFive = "";
    String FridayTypeLessonSix = "";

    String SaturdayValueSubjectOne = "";
    String SaturdayValueSubjectTwo = "";
    String SaturdayValueSubjectThree = "";
    String SaturdayValueSubjectFour = "";
    String SaturdayValueSubjectFive = "";
    String SaturdayValueSubjectSix = "";
    String SaturdayValueAudienceOne = "";
    String SaturdayValueAudienceTwo = "";
    String SaturdayValueAudienceThree = "";
    String SaturdayValueAudienceFour = "";
    String SaturdayValueAudienceFive = "";
    String SaturdayValueAudienceSix = "";
    String SaturdayEducatorOne = "";
    String SaturdayEducatorTwo = "";
    String SaturdayEducatorThree = "";
    String SaturdayEducatorFour = "";
    String SaturdayEducatorFive = "";
    String SaturdayEducatorSix = "";
    String SaturdayTypeLessonOne = "";
    String SaturdayTypeLessonTwo = "";
    String SaturdayTypeLessonThree = "";
    String SaturdayTypeLessonFour = "";
    String SaturdayTypeLessonFive = "";
    String SaturdayTypeLessonSix = "";

    Integer position_week=0;
    Integer position_day=0;
    Integer flag_autosave=1;

    Integer flag_save=0;
    List<DataMonday> Monday;
    List<DataTuesday> Tuesday;
    List<DataWednesday> Wednesday;
    List<DataThursday> Thursday;
    List<DataFriday> Friday;
    List<DataSaturday> Saturday;
    ArrayList<String> calls_schedule = new ArrayList<>();
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    Boolean first = true;
    private ScheduleDB ScheduleDB;

    final Context context = this;
    final ArrayList<String> subject_list = new ArrayList<>();
    final ArrayList<String> audience_list = new ArrayList<>();
    final ArrayList<String> educator_list = new ArrayList<>();



    public void monday_fill() {
        rb_lecture = findViewById(R.id.rb_lecture_monday);
        rb_labwork = findViewById(R.id.rb_labwork_monday);
        rb_practice = findViewById(R.id.rb_practice_monday);
        rb_lectureTwo = findViewById(R.id.rb_lectureTwo_monday);
        rb_labworkTwo = findViewById(R.id.rb_labworkTwo_monday);
        rb_practiceTwo = findViewById(R.id.rb_practiceTwo_monday);
        rb_lectureThree = findViewById(R.id.rb_lectureThree_monday);
        rb_labworkThree = findViewById(R.id.rb_labworkThree_monday);
        rb_practiceThree = findViewById(R.id.rb_practiceThree_monday);
        rb_lectureFour = findViewById(R.id.rb_lectureFour_monday);
        rb_labworkFour = findViewById(R.id.rb_labworkFour_monday);
        rb_practiceFour = findViewById(R.id.rb_practiceFour_monday);
        rb_lectureFive = findViewById(R.id.rb_lectureFive_monday);
        rb_labworkFive = findViewById(R.id.rb_labworkFive_monday);
        rb_practiceFive = findViewById(R.id.rb_practiceFive_monday);
        rb_lectureSix = findViewById(R.id.rb_lectureSix_monday);
        rb_labworkSix = findViewById(R.id.rb_labworkSix_monday);
        rb_practiceSix = findViewById(R.id.rb_practiceSix_monday);

        NumberOne = findViewById(R.id.number_monday);
        NumberTwo = findViewById(R.id.numberTwo_monday);
        NumberThree = findViewById(R.id.numberThree_monday);
        NumberFour = findViewById(R.id.numberFour_monday);
        NumberFive = findViewById(R.id.numberFive_monday);
        NumberSix = findViewById(R.id.numberSix_monday);
        TimeOne = findViewById(R.id.time_monday);
        TimeTwo = findViewById(R.id.timeTwo_monday);
        TimeThree = findViewById(R.id.timeThree_monday);
        TimeFour = findViewById(R.id.timeFour_monday);
        TimeFive = findViewById(R.id.timeFive_monday);
        TimeSix = findViewById(R.id.timeSix_monday);
        SubjectEditOne = findViewById(R.id.subject_edit_monday);
        SubjectEditTwo = findViewById(R.id.subject_editTwo_monday);
        SubjectEditThree = findViewById(R.id.subject_editThree_monday);
        SubjectEditFour = findViewById(R.id.subject_editFour_monday);
        SubjectEditFive = findViewById(R.id.subject_editFive_monday);
        SubjectEditSix = findViewById(R.id.subject_editSix_monday);
        AudienceEditOne = findViewById(R.id.audience_edit_monday);
        AudienceEditTwo = findViewById(R.id.audience_editTwo_monday);
        AudienceEditThree = findViewById(R.id.audience_editThree_monday);
        AudienceEditFour = findViewById(R.id.audience_editFour_monday);
        AudienceEditFive = findViewById(R.id.audience_editFive_monday);
        AudienceEditSix = findViewById(R.id.audience_editSix_monday);
        EducatorEditOne = findViewById(R.id.educator_edit_monday);
        EducatorEditTwo = findViewById(R.id.educator_editTwo_monday);
        EducatorEditThree = findViewById(R.id.educator_editThree_monday);
        EducatorEditFour = findViewById(R.id.educator_editFour_monday);
        EducatorEditFive = findViewById(R.id.educator_editFive_monday);
        EducatorEditSix = findViewById(R.id.educator_editSix_monday);
        typeEditOne_monday = findViewById(R.id.typeEdit_monday);
        typeEditTwo_monday = findViewById(R.id.typeEditTwo_monday);
        typeEditThree_monday = findViewById(R.id.typeEditThree_monday);
        typeEditFour_monday = findViewById(R.id.typeEditFour_monday);
        typeEditFive_monday = findViewById(R.id.typeEditFive_monday);
        typeEditSix_monday = findViewById(R.id.typeEditSix_monday);
        DataMonday();
        NumberOne.setText(Monday.get(0).idcards.toString());
        NumberTwo.setText(Monday.get(1).idcards.toString());
        NumberThree.setText(Monday.get(2).idcards.toString());
        NumberFour.setText(Monday.get(3).idcards.toString());
        NumberFive.setText(Monday.get(4).idcards.toString());
        NumberSix.setText(Monday.get(5).idcards.toString());
        TimeOne.setText(Monday.get(0).timelesson.toString());
        TimeTwo.setText(Monday.get(1).timelesson.toString());
        TimeThree.setText(Monday.get(2).timelesson.toString());
        TimeFour.setText(Monday.get(3).timelesson.toString());
        TimeFive.setText(Monday.get(4).timelesson.toString());
        TimeSix.setText(Monday.get(5).timelesson.toString());
        ArrayAdapter<String> subSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, subject_list);
        subSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> audSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, audience_list);
        audSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> eduSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, educator_list);
        eduSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        SubjectEditOne.setAdapter(subSpinnerArrayAdapter);
        SubjectEditTwo.setAdapter(subSpinnerArrayAdapter);
        SubjectEditThree.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFour.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFive.setAdapter(subSpinnerArrayAdapter);
        SubjectEditSix.setAdapter(subSpinnerArrayAdapter);
        AudienceEditOne.setAdapter(audSpinnerArrayAdapter);
        AudienceEditTwo.setAdapter(audSpinnerArrayAdapter);
        AudienceEditThree.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFour.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFive.setAdapter(audSpinnerArrayAdapter);
        AudienceEditSix.setAdapter(audSpinnerArrayAdapter);
        EducatorEditOne.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditTwo.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditThree.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFour.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFive.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditSix.setAdapter(eduSpinnerArrayAdapter);

        TextView copy_downOne = findViewById(R.id.copy_downOne_monday);
        TextView copy_downTwo = findViewById(R.id.copy_downTwo_monday);
        TextView copy_downThree = findViewById(R.id.copy_downThree_monday);
        TextView copy_downFour = findViewById(R.id.copy_downFour_monday);
        TextView copy_downFive = findViewById(R.id.copy_downFive_monday);
        TextView copy_upTwo= findViewById(R.id.copy_upTwo_monday);
        TextView copy_upThree= findViewById(R.id.copy_upThree_monday);
        TextView copy_upFour= findViewById(R.id.copy_upFour_monday);
        TextView copy_upFive= findViewById(R.id.copy_upFive_monday);
        TextView copy_upSix= findViewById(R.id.copy_upSix_monday);
        TextView clearOne = findViewById(R.id.clear_cardOne_monday);
        TextView clearTwo = findViewById(R.id.clear_cardTwo_monday);
        TextView clearThree = findViewById(R.id.clear_cardThree_monday);
        TextView clearFour = findViewById(R.id.clear_cardFour_monday);
        TextView clearFive = findViewById(R.id.clear_cardFive_monday);
        TextView clearSix = findViewById(R.id.clear_cardSix_monday);


        typeEditOne_monday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonOne = typeEditOne_monday.indexOfChild(findViewById(typeEditOne_monday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditOne_monday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    MondayTypeLessonOne = radioButton.getText().toString();
                }
                else { MondayTypeLessonOne = "";}
            }
        });

        typeEditTwo_monday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonTwo = typeEditTwo_monday.indexOfChild(findViewById(typeEditTwo_monday.getCheckedRadioButtonId()));
                int IdRadioButtonTwo = typeEditTwo_monday.getCheckedRadioButtonId();
                RadioButton radioButtonTwo = findViewById(IdRadioButtonTwo);
                if (radioButtonTwo != null) {
                    MondayTypeLessonTwo = radioButtonTwo.getText().toString();
                }
                else { MondayTypeLessonTwo = "";}
            }
        });

        typeEditThree_monday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonThree = typeEditThree_monday.indexOfChild(findViewById(typeEditThree_monday.getCheckedRadioButtonId()));
                int IdRadioButtonThree = typeEditThree_monday.getCheckedRadioButtonId();
                RadioButton radioButtonThree = findViewById(IdRadioButtonThree);
                if (radioButtonThree != null) {
                    MondayTypeLessonThree = radioButtonThree.getText().toString();
                }
                else { MondayTypeLessonThree = "";}
            }
        });

        typeEditFour_monday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFour = typeEditFour_monday.indexOfChild(findViewById(typeEditFour_monday.getCheckedRadioButtonId()));
                int IdRadioButtonFour = typeEditFour_monday.getCheckedRadioButtonId();
                RadioButton radioButtonFour = findViewById(IdRadioButtonFour);
                if (radioButtonFour != null) {
                    MondayTypeLessonFour = radioButtonFour.getText().toString();
                }
                else { MondayTypeLessonFour = "";}
            }
        });

        typeEditFive_monday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFive = typeEditFive_monday.indexOfChild(findViewById(typeEditFive_monday.getCheckedRadioButtonId()));
                int IdRadioButtonFive = typeEditFive_monday.getCheckedRadioButtonId();
                RadioButton radioButtonFive = findViewById(IdRadioButtonFive);
                if (radioButtonFive != null) {
                    MondayTypeLessonFive = radioButtonFive.getText().toString();
                }
                else { MondayTypeLessonFive = "";}
            }
        });
        typeEditSix_monday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonSix = typeEditSix_monday.indexOfChild(findViewById(typeEditSix_monday.getCheckedRadioButtonId()));
                int IdRadioButtonSix = typeEditSix_monday.getCheckedRadioButtonId();
                RadioButton radioButtonSix = findViewById(IdRadioButtonSix);
                if (radioButtonSix != null) {
                    MondayTypeLessonSix = radioButtonSix.getText().toString();
                }
                else { MondayTypeLessonSix = "";}
            }
        });



    copy_downOne.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
            SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(0).subjectEdit));
            AudienceEditTwo.setSelection(audience_list.indexOf(Monday.get(0).audienceEdit));
            EducatorEditTwo.setSelection(educator_list.indexOf(Monday.get(0).educator));
            switch (IdRadioButtonOne) {
                case 0:
                    rb_lectureTwo.setChecked(true);
                    break;
                case 1:
                    rb_labworkTwo.setChecked(true);
                    break;
                case 2:
                    rb_practiceTwo.setChecked(true);
                    break;
            }
            } catch   (NullPointerException e) {}
        }
    });

    copy_downTwo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          try{
              SubjectEditThree.setSelection(subject_list.indexOf(Monday.get(1).subjectEdit));
            AudienceEditThree.setSelection(audience_list.indexOf(Monday.get(1).audienceEdit));
            EducatorEditThree.setSelection(educator_list.indexOf(Monday.get(1).educator));
            switch (IdRadioButtonTwo) {
                case 0:
                    rb_lectureThree.setChecked(true);
                    break;
                case 1:
                    rb_labworkThree.setChecked(true);
                    break;
                case 2:
                    rb_practiceThree.setChecked(true);
                    break;
            }
        } catch   (NullPointerException e) {}
        }
    });

    copy_downThree.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
            SubjectEditFour.setSelection(subject_list.indexOf(Monday.get(2).subjectEdit));
            AudienceEditFour.setSelection(audience_list.indexOf(Monday.get(2).audienceEdit));
            EducatorEditFour.setSelection(educator_list.indexOf(Monday.get(2).educator));
            switch (IdRadioButtonThree) {
                case 0:
                    rb_lectureFour.setChecked(true);
                    break;
                case 1:
                    rb_labworkFour.setChecked(true);
                    break;
                case 2:
                    rb_practiceFour.setChecked(true);
                    break;
            }
            } catch   (NullPointerException e) {}
        }
    });
    copy_downFour.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
            SubjectEditFive.setSelection(subject_list.indexOf(Monday.get(3).subjectEdit));
            AudienceEditFive.setSelection(audience_list.indexOf(Monday.get(3).audienceEdit));
            EducatorEditFive.setSelection(educator_list.indexOf(Monday.get(3).educator));
            switch (IdRadioButtonFour) {
                case 0:
                    rb_lectureFive.setChecked(true);
                    break;
                case 1:
                    rb_labworkFive.setChecked(true);
                    break;
                case 2:
                    rb_practiceFive.setChecked(true);
                    break;
            }
            } catch   (NullPointerException e) {}
        }
    });

    copy_downFive.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
            SubjectEditSix.setSelection(subject_list.indexOf(Monday.get(4).subjectEdit));
            AudienceEditSix.setSelection(audience_list.indexOf(Monday.get(4).audienceEdit));
            EducatorEditSix.setSelection(educator_list.indexOf(Monday.get(4).educator));
            switch (IdRadioButtonFive) {
                case 0:
                    rb_lectureSix.setChecked(true);
                    break;
                case 1:
                    rb_labworkSix.setChecked(true);
                    break;
                case 2:
                    rb_practiceSix.setChecked(true);
                    break;
            }
            } catch   (NullPointerException e) {}
        }
    });

    copy_upTwo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
            SubjectEditOne.setSelection(subject_list.indexOf(Monday.get(1).subjectEdit));
            AudienceEditOne.setSelection(audience_list.indexOf(Monday.get(1).audienceEdit));
            EducatorEditOne.setSelection(educator_list.indexOf(Monday.get(1).educator));
            switch (IdRadioButtonTwo) {
                case 0:
                    rb_lecture.setChecked(true);
                    break;
                case 1:
                    rb_labwork.setChecked(true);
                    break;
                case 2:
                    rb_practice.setChecked(true);
                    break;
            }
            } catch   (NullPointerException e) {}
        }
    });

    copy_upThree.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        try{
            SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(2).subjectEdit));
            AudienceEditTwo.setSelection(audience_list.indexOf(Monday.get(2).audienceEdit));
            EducatorEditTwo.setSelection(educator_list.indexOf(Monday.get(2).educator));
            switch (IdRadioButtonThree) {
                case 0:
                    rb_lectureTwo.setChecked(true);
                    break;
                case 1:
                    rb_labworkTwo.setChecked(true);
                    break;
                case 2:
                    rb_practiceTwo.setChecked(true);
                    break;
            }
        } catch   (NullPointerException e) {}
        }
    });

    copy_upFour.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
            SubjectEditThree.setSelection(subject_list.indexOf(Monday.get(3).subjectEdit));
            AudienceEditThree.setSelection(audience_list.indexOf(Monday.get(3).audienceEdit));
            EducatorEditThree.setSelection(educator_list.indexOf(Monday.get(3).educator));
            switch (IdRadioButtonFour) {
                case 0:
                    rb_lectureThree.setChecked(true);
                    break;
                case 1:
                    rb_labworkThree.setChecked(true);
                    break;
                case 2:
                    rb_practiceThree.setChecked(true);
                    break;
            }
            } catch   (NullPointerException e) {}
        }
    });

    copy_upFive.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
            SubjectEditFour.setSelection(subject_list.indexOf(Monday.get(4).subjectEdit));
            AudienceEditFour.setSelection(audience_list.indexOf(Monday.get(4).audienceEdit));
            EducatorEditFour.setSelection(educator_list.indexOf(Monday.get(4).educator));
            switch (IdRadioButtonFive) {
                case 0:
                    rb_lectureFour.setChecked(true);
                    break;
                case 1:
                    rb_labworkFour.setChecked(true);
                    break;
                case 2:
                    rb_practiceFour.setChecked(true);
                    break;
            }
            } catch   (NullPointerException e) {}
        }
    });

    copy_upSix.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                try{
            SubjectEditFive.setSelection(subject_list.indexOf(Monday.get(5).subjectEdit));
            AudienceEditFive.setSelection(audience_list.indexOf(Monday.get(5).audienceEdit));
            EducatorEditFive.setSelection(educator_list.indexOf(Monday.get(5).educator));
            switch (IdRadioButtonSix) {
                case 0:
                    rb_lectureFive.setChecked(true);
                    break;
                case 1:
                    rb_labworkFive.setChecked(true);
                    break;
                case 2:
                    rb_practiceFive.setChecked(true);
                    break;
            }
                } catch   (NullPointerException e) {}
        }
    });


        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubjectEditOne.setSelection(0);
                AudienceEditOne.setSelection(0);
                EducatorEditOne.setSelection(0);
                typeEditOne_monday.clearCheck();
            }
        });

        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditTwo.setSelection(0);
                AudienceEditTwo.setSelection(0);
                EducatorEditTwo.setSelection(0);
                typeEditTwo_monday.clearCheck();
            }
        });

        clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditThree.setSelection(0);
                AudienceEditThree.setSelection(0);
                EducatorEditThree.setSelection(0);
                typeEditThree_monday.clearCheck();
            }
        });

        clearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFour.setSelection(0);
                AudienceEditFour.setSelection(0);
                EducatorEditFour.setSelection(0);
                typeEditFour_monday.clearCheck();
            }
        });

        clearFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFive.setSelection(0);
                AudienceEditFive.setSelection(0);
                EducatorEditFive.setSelection(0);
                typeEditFive_monday.clearCheck();
            }
        });

        clearSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(0);
                AudienceEditSix.setSelection(0);
                EducatorEditSix.setSelection(0);
                typeEditSix_monday.clearCheck();
            }
        });


        SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(0).subjectEdit.toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(1).subjectEdit.toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Monday.get(2).subjectEdit.toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Monday.get(3).subjectEdit.toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Monday.get(4).subjectEdit.toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Monday.get(5).subjectEdit.toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Monday.get(0).audienceEdit.toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Monday.get(1).audienceEdit.toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Monday.get(2).audienceEdit.toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Monday.get(3).audienceEdit.toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Monday.get(4).audienceEdit.toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Monday.get(5).audienceEdit.toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Monday.get(0).educator.toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Monday.get(1).educator.toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Monday.get(2).educator.toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Monday.get(3).educator.toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Monday.get(4).educator.toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Monday.get(5).educator.toString()));


        if (Monday.get(0).typelesson.toString().equals("Лекция")) {
            rb_lecture.setChecked(true);
        } else if (Monday.get(0).typelesson.toString().equals("Лаб./раб.")) {
            rb_labwork.setChecked(true);
        } else if (Monday.get(0).typelesson.toString().equals("Практика")) {
            rb_practice.setChecked(true);
        }
        if (Monday.get(1).typelesson.toString().equals("Лекция")) {
            rb_lectureTwo.setChecked(true);
        } else if (Monday.get(1).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkTwo.setChecked(true);
        } else if (Monday.get(1).typelesson.toString().equals("Практика")) {
            rb_practiceTwo.setChecked(true);
        }
        if (Monday.get(2).typelesson.toString().equals("Лекция")) {
            rb_lectureThree.setChecked(true);
        } else if (Monday.get(2).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkThree.setChecked(true);
        } else if (Monday.get(2).typelesson.toString().equals("Практика")) {
            rb_practiceThree.setChecked(true);
        }
        if (Monday.get(3).typelesson.toString().equals("Лекция")) {
            rb_lectureFour.setChecked(true);
        } else if (Monday.get(3).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFour.setChecked(true);
        } else if (Monday.get(3).typelesson.toString().equals("Практика")) {
            rb_practiceFour.setChecked(true);
        }
        if (Monday.get(4).typelesson.toString().equals("Лекция")) {
            rb_lectureFive.setChecked(true);
        } else if (Monday.get(4).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFive.setChecked(true);
        } else if (Monday.get(4).typelesson.toString().equals("Практика")) {
            rb_practiceFive.setChecked(true);
        }
        if (Monday.get(5).typelesson.toString().equals("Лекция")) {
            rb_lectureSix.setChecked(true);
        } else if (Monday.get(5).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkSix.setChecked(true);
        } else if (Monday.get(5).typelesson.toString().equals("Практика")) {
            rb_practiceSix.setChecked(true);
        }
        //S1
        try {
              SubjectEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                      MondayValueSubjectOne = SubjectEditOne.getSelectedItem().toString();
                      Monday.set(0, new DataMonday("1", "8:30-10:00", MondayValueSubjectOne, Monday.get(0).audienceEdit.toString(), Monday.get(0).educator.toString(), Monday.get(0).typelesson.toString()));
                  }
                  @Override
                  public void onNothingSelected(AdapterView<?> parent) {

                  }
              }); } catch (NullPointerException e) { }

                //S2
        try {
            SubjectEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MondayValueSubjectTwo = SubjectEditTwo.getSelectedItem().toString();
                    Monday.set(1, new DataMonday("2", "10:10-11:40", MondayValueSubjectTwo, Monday.get(1).audienceEdit.toString(), Monday.get(1).educator.toString(), Monday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S3
        try {
            SubjectEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectThree = SubjectEditThree.getSelectedItem().toString();
                    Monday.set(2, new DataMonday("3", "12:20-13:50", MondayValueSubjectThree, Monday.get(2).audienceEdit.toString(), Monday.get(2).educator.toString(), Monday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S4
        try {
            SubjectEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectFour = SubjectEditFour.getSelectedItem().toString();
                    Monday.set(3, new DataMonday("4", "14:00-15:30", MondayValueSubjectFour, Monday.get(3).audienceEdit.toString(), Monday.get(3).educator.toString(), Monday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S5
        try {
            SubjectEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectFive = SubjectEditFive.getSelectedItem().toString();
                    Monday.set(4, new DataMonday("5", "15:40-17:10", MondayValueSubjectFive, Monday.get(4).audienceEdit.toString(), Monday.get(4).educator.toString(), Monday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S6
        try {
            SubjectEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectSix = SubjectEditSix.getSelectedItem().toString();
                    Monday.set(5, new DataMonday("6", "17:30-19:00", MondayValueSubjectSix, Monday.get(5).audienceEdit.toString(), Monday.get(5).educator.toString(), Monday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A1
        try {
            AudienceEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceOne = AudienceEditOne.getSelectedItem().toString();
                    Monday.set(0, new DataMonday("1", "8:30-10:00", Monday.get(0).subjectEdit.toString(), MondayValueAudienceOne, Monday.get(0).educator.toString(), Monday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A2
        try {
            AudienceEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceTwo = AudienceEditTwo.getSelectedItem().toString();
                    Monday.set(1, new DataMonday("2", "10:10-11:40", Monday.get(1).subjectEdit.toString(), MondayValueAudienceTwo, Monday.get(1).educator.toString(), Monday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A3
        try {
            AudienceEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceThree = AudienceEditThree.getSelectedItem().toString();
                    Monday.set(2, new DataMonday("3", "12:20-13:50", Monday.get(2).subjectEdit.toString(), MondayValueAudienceThree, Monday.get(2).educator.toString(), Monday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A4
        try {
            AudienceEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceFour = AudienceEditFour.getSelectedItem().toString();
                    Monday.set(3, new DataMonday("4", "14:00-15:30", Monday.get(3).subjectEdit.toString(), MondayValueAudienceFour, Monday.get(3).educator.toString(), Monday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A5
        try {
            AudienceEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceFive = AudienceEditFive.getSelectedItem().toString();
                    Monday.set(4, new DataMonday("5", "15:40-17:10", Monday.get(4).subjectEdit.toString(), MondayValueAudienceFive, Monday.get(4).educator.toString(), Monday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A6
        try {
            AudienceEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceSix = AudienceEditSix.getSelectedItem().toString();
                    Monday.set(5, new DataMonday("6", "17:30-19:00", Monday.get(5).subjectEdit.toString(), MondayValueAudienceSix, Monday.get(5).educator.toString(), Monday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //E1
        try {
            EducatorEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorOne = EducatorEditOne.getSelectedItem().toString();
                    Monday.set(0, new DataMonday("1", "8:30-10:00", Monday.get(0).subjectEdit.toString(), Monday.get(0).audienceEdit.toString(), MondayEducatorOne, Monday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E2
        try {
            EducatorEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorTwo = EducatorEditTwo.getSelectedItem().toString();
                    Monday.set(1, new DataMonday("2", "10:10-11:40", Monday.get(1).subjectEdit.toString(), Monday.get(1).audienceEdit.toString(), MondayEducatorTwo, Monday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //E3
        try {
            EducatorEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorThree = EducatorEditThree.getSelectedItem().toString();
                    Monday.set(2, new DataMonday("3", "12:20-13:50", Monday.get(2).subjectEdit.toString(), Monday.get(2).audienceEdit.toString(), MondayEducatorThree, Monday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E4
        try {
            EducatorEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorFour = EducatorEditFour.getSelectedItem().toString();
                    Monday.set(3, new DataMonday("4", "14:00-15:30", Monday.get(3).subjectEdit.toString(), Monday.get(3).audienceEdit.toString(), MondayEducatorFour, Monday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E5
        try {
            EducatorEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorFive = EducatorEditFive.getSelectedItem().toString();
                    Monday.set(4, new DataMonday("5", "15:40-17:10", Monday.get(4).subjectEdit.toString(), Monday.get(4).audienceEdit.toString(), MondayEducatorFive, Monday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E6
        try {
            EducatorEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorSix = EducatorEditSix.getSelectedItem().toString();
                    Monday.set(5, new DataMonday("6", "17:30-19:00", Monday.get(5).subjectEdit.toString(), Monday.get(5).audienceEdit.toString(), MondayEducatorSix, Monday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

    }

    public void tuesday_fill() {
        rb_lecture = findViewById(R.id.rb_lecture_tuesday);
        rb_labwork = findViewById(R.id.rb_labwork_tuesday);
        rb_practice = findViewById(R.id.rb_practice_tuesday);
        rb_lectureTwo = findViewById(R.id.rb_lectureTwo_tuesday);
        rb_labworkTwo = findViewById(R.id.rb_labworkTwo_tuesday);
        rb_practiceTwo = findViewById(R.id.rb_practiceTwo_tuesday);
        rb_lectureThree = findViewById(R.id.rb_lectureThree_tuesday);
        rb_labworkThree = findViewById(R.id.rb_labworkThree_tuesday);
        rb_practiceThree = findViewById(R.id.rb_practiceThree_tuesday);
        rb_lectureFour = findViewById(R.id.rb_lectureFour_tuesday);
        rb_labworkFour = findViewById(R.id.rb_labworkFour_tuesday);
        rb_practiceFour = findViewById(R.id.rb_practiceFour_tuesday);
        rb_lectureFive = findViewById(R.id.rb_lectureFive_tuesday);
        rb_labworkFive = findViewById(R.id.rb_labworkFive_tuesday);
        rb_practiceFive = findViewById(R.id.rb_practiceFive_tuesday);
        rb_lectureSix = findViewById(R.id.rb_lectureSix_tuesday);
        rb_labworkSix = findViewById(R.id.rb_labworkSix_tuesday);
        rb_practiceSix = findViewById(R.id.rb_practiceSix_tuesday);
        NumberOne = findViewById(R.id.number_tuesday);
        NumberTwo = findViewById(R.id.numberTwo_tuesday);
        NumberThree = findViewById(R.id.numberThree_tuesday);
        NumberFour = findViewById(R.id.numberFour_tuesday);
        NumberFive = findViewById(R.id.numberFive_tuesday);
        NumberSix = findViewById(R.id.numberSix_tuesday);
        TimeOne = findViewById(R.id.time_tuesday);
        TimeTwo = findViewById(R.id.timeTwo_tuesday);
        TimeThree = findViewById(R.id.timeThree_tuesday);
        TimeFour = findViewById(R.id.timeFour_tuesday);
        TimeFive = findViewById(R.id.timeFive_tuesday);
        TimeSix = findViewById(R.id.timeSix_tuesday);
        SubjectEditOne = findViewById(R.id.subject_edit_tuesday);
        SubjectEditTwo = findViewById(R.id.subject_editTwo_tuesday);
        SubjectEditThree = findViewById(R.id.subject_editThree_tuesday);
        SubjectEditFour = findViewById(R.id.subject_editFour_tuesday);
        SubjectEditFive = findViewById(R.id.subject_editFive_tuesday);
        SubjectEditSix = findViewById(R.id.subject_editSix_tuesday);
        AudienceEditOne = findViewById(R.id.audience_edit_tuesday);
        AudienceEditTwo = findViewById(R.id.audience_editTwo_tuesday);
        AudienceEditThree = findViewById(R.id.audience_editThree_tuesday);
        AudienceEditFour = findViewById(R.id.audience_editFour_tuesday);
        AudienceEditFive = findViewById(R.id.audience_editFive_tuesday);
        AudienceEditSix = findViewById(R.id.audience_editSix_tuesday);
        EducatorEditOne = findViewById(R.id.educator_edit_tuesday);
        EducatorEditTwo = findViewById(R.id.educator_editTwo_tuesday);
        EducatorEditThree = findViewById(R.id.educator_editThree_tuesday);
        EducatorEditFour = findViewById(R.id.educator_editFour_tuesday);
        EducatorEditFive = findViewById(R.id.educator_editFive_tuesday);
        EducatorEditSix = findViewById(R.id.educator_editSix_tuesday);
        typeEditOne_tuesday = findViewById(R.id.typeEdit_tuesday);
        typeEditTwo_tuesday = findViewById(R.id.typeEditTwo_tuesday);
        typeEditThree_tuesday = findViewById(R.id.typeEditThree_tuesday);
        typeEditFour_tuesday = findViewById(R.id.typeEditFour_tuesday);
        typeEditFive_tuesday = findViewById(R.id.typeEditFive_tuesday);
        typeEditSix_tuesday = findViewById(R.id.typeEditSix_tuesday);

        DataTuesday();
        NumberOne.setText(Tuesday.get(0).idcards.toString());
        NumberTwo.setText(Tuesday.get(1).idcards.toString());
        NumberThree.setText(Tuesday.get(2).idcards.toString());
        NumberFour.setText(Tuesday.get(3).idcards.toString());
        NumberFive.setText(Tuesday.get(4).idcards.toString());
        NumberSix.setText(Tuesday.get(5).idcards.toString());
        TimeOne.setText(Tuesday.get(0).timelesson.toString());
        TimeTwo.setText(Tuesday.get(1).timelesson.toString());
        TimeThree.setText(Tuesday.get(2).timelesson.toString());
        TimeFour.setText(Tuesday.get(3).timelesson.toString());
        TimeFive.setText(Tuesday.get(4).timelesson.toString());
        TimeSix.setText(Tuesday.get(5).timelesson.toString());
        ArrayAdapter<String> subSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, subject_list);
        subSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> audSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, audience_list);
        audSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> eduSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, educator_list);
        eduSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        SubjectEditOne.setAdapter(subSpinnerArrayAdapter);
        SubjectEditTwo.setAdapter(subSpinnerArrayAdapter);
        SubjectEditThree.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFour.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFive.setAdapter(subSpinnerArrayAdapter);
        SubjectEditSix.setAdapter(subSpinnerArrayAdapter);
        AudienceEditOne.setAdapter(audSpinnerArrayAdapter);
        AudienceEditTwo.setAdapter(audSpinnerArrayAdapter);
        AudienceEditThree.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFour.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFive.setAdapter(audSpinnerArrayAdapter);
        AudienceEditSix.setAdapter(audSpinnerArrayAdapter);
        EducatorEditOne.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditTwo.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditThree.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFour.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFive.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditSix.setAdapter(eduSpinnerArrayAdapter);

        TextView copy_downOne = findViewById(R.id.copy_downOne_tuesday);
        TextView copy_downTwo = findViewById(R.id.copy_downTwo_tuesday);
        TextView copy_downThree = findViewById(R.id.copy_downThree_tuesday);
        TextView copy_downFour = findViewById(R.id.copy_downFour_tuesday);
        TextView copy_downFive = findViewById(R.id.copy_downFive_tuesday);
        TextView copy_upTwo= findViewById(R.id.copy_upTwo_tuesday);
        TextView copy_upThree= findViewById(R.id.copy_upThree_tuesday);
        TextView copy_upFour= findViewById(R.id.copy_upFour_tuesday);
        TextView copy_upFive= findViewById(R.id.copy_upFive_tuesday);
        TextView copy_upSix= findViewById(R.id.copy_upSix_tuesday);
        TextView clearOne = findViewById(R.id.clear_cardOne_tuesday);
        TextView clearTwo = findViewById(R.id.clear_cardTwo_tuesday);
        TextView clearThree = findViewById(R.id.clear_cardThree_tuesday);
        TextView clearFour = findViewById(R.id.clear_cardFour_tuesday);
        TextView clearFive = findViewById(R.id.clear_cardFive_tuesday);
        TextView clearSix = findViewById(R.id.clear_cardSix_tuesday);

        typeEditOne_tuesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonOne = typeEditOne_tuesday.indexOfChild(findViewById(typeEditOne_tuesday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditOne_tuesday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    TuesdayTypeLessonOne = radioButton.getText().toString();
                }
                else { TuesdayTypeLessonOne = "";}
            }
        });

        typeEditTwo_tuesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonTwo = typeEditTwo_tuesday.indexOfChild(findViewById(typeEditTwo_tuesday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditTwo_tuesday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    TuesdayTypeLessonTwo = radioButton.getText().toString();
                }
                else { TuesdayTypeLessonTwo = "";}
            }
        });

        typeEditThree_tuesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonThree = typeEditThree_tuesday.indexOfChild(findViewById(typeEditThree_tuesday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditThree_tuesday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    TuesdayTypeLessonThree = radioButton.getText().toString();
                }
                else { TuesdayTypeLessonThree = "";}
            }
        });

        typeEditFour_tuesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFour = typeEditFour_tuesday.indexOfChild(findViewById(typeEditFour_tuesday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditFour_tuesday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    TuesdayTypeLessonFour = radioButton.getText().toString();
                }
                else { TuesdayTypeLessonFour = "";}
            }
        });

        typeEditFive_tuesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFive = typeEditFive_tuesday.indexOfChild(findViewById(typeEditFive_tuesday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditFive_tuesday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    TuesdayTypeLessonFive = radioButton.getText().toString();
                }
                else { TuesdayTypeLessonFive = "";}
            }
        });
        typeEditSix_tuesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonSix = typeEditSix_tuesday.indexOfChild(findViewById(typeEditSix_tuesday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditSix_tuesday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    TuesdayTypeLessonSix = radioButton.getText().toString();
                }
                else { TuesdayTypeLessonSix = "";}
            }
        });

        copy_downOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditTwo.setSelection(subject_list.indexOf(Tuesday.get(0).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Tuesday.get(0).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Tuesday.get(0).educator));
                switch (IdRadioButtonOne){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditThree.setSelection(subject_list.indexOf(Tuesday.get(1).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Tuesday.get(1).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Tuesday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFour.setSelection(subject_list.indexOf(Tuesday.get(2).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Tuesday.get(2).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Tuesday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });
        copy_downFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFive.setSelection(subject_list.indexOf(Tuesday.get(3).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Tuesday.get(3).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Tuesday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditSix.setSelection(subject_list.indexOf(Tuesday.get(4).subjectEdit));
                AudienceEditSix.setSelection(audience_list.indexOf(Tuesday.get(4).audienceEdit));
                EducatorEditSix.setSelection(educator_list.indexOf(Tuesday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureSix.setChecked(true);
                        break;
                    case 1:
                        rb_labworkSix.setChecked(true);
                        break;
                    case 2:
                        rb_practiceSix.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try{
                SubjectEditOne.setSelection(subject_list.indexOf(Tuesday.get(1).subjectEdit));
                AudienceEditOne.setSelection(audience_list.indexOf(Tuesday.get(1).audienceEdit));
                EducatorEditOne.setSelection(educator_list.indexOf(Tuesday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lecture.setChecked(true);
                        break;
                    case 1:
                        rb_labwork.setChecked(true);
                        break;
                    case 2:
                        rb_practice.setChecked(true);
                        break;
                }
            } catch   (NullPointerException e) {}
            }
        });

        copy_upThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try{
                SubjectEditTwo.setSelection(subject_list.indexOf(Tuesday.get(2).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Tuesday.get(2).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Tuesday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                    } catch   (NullPointerException e) {}
            }
        });

        copy_upFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditThree.setSelection(subject_list.indexOf(Tuesday.get(3).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Tuesday.get(3).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Tuesday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFour.setSelection(subject_list.indexOf(Tuesday.get(4).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Tuesday.get(4).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Tuesday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try{
                SubjectEditFive.setSelection(subject_list.indexOf(Tuesday.get(5).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Tuesday.get(5).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Tuesday.get(5).educator));
                switch (IdRadioButtonSix){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
            } catch   (NullPointerException e) {}
            }
        });



        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditOne.setSelection(0);
                AudienceEditOne.setSelection(0);
                EducatorEditOne.setSelection(0);
                typeEditOne_tuesday.clearCheck();
            }
        });

        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditTwo.setSelection(0);
                AudienceEditTwo.setSelection(0);
                EducatorEditTwo.setSelection(0);
                typeEditTwo_tuesday.clearCheck();
            }
        });

        clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditThree.setSelection(0);
                AudienceEditThree.setSelection(0);
                EducatorEditThree.setSelection(0);
                typeEditThree_tuesday.clearCheck();
            }
        });

        clearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFour.setSelection(0);
                AudienceEditFour.setSelection(0);
                EducatorEditFour.setSelection(0);
                typeEditFour_tuesday.clearCheck();
            }
        });

        clearFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFive.setSelection(0);
                AudienceEditFive.setSelection(0);
                EducatorEditFive.setSelection(0);
                typeEditFive_tuesday.clearCheck();
            }
        });

        clearSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(0);
                AudienceEditSix.setSelection(0);
                EducatorEditSix.setSelection(0);
                typeEditSix_tuesday.clearCheck();
            }
        });


        SubjectEditOne.setSelection(subject_list.indexOf(Tuesday.get(0).subjectEdit.toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Tuesday.get(1).subjectEdit.toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Tuesday.get(2).subjectEdit.toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Tuesday.get(3).subjectEdit.toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Tuesday.get(4).subjectEdit.toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Tuesday.get(5).subjectEdit.toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Tuesday.get(0).audienceEdit.toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Tuesday.get(1).audienceEdit.toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Tuesday.get(2).audienceEdit.toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Tuesday.get(3).audienceEdit.toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Tuesday.get(4).audienceEdit.toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Tuesday.get(5).audienceEdit.toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Tuesday.get(0).educator.toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Tuesday.get(1).educator.toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Tuesday.get(2).educator.toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Tuesday.get(3).educator.toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Tuesday.get(4).educator.toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Tuesday.get(5).educator.toString()));


        if (Tuesday.get(0).typelesson.toString().equals("Лекция")) {
            rb_lecture.setChecked(true);
        } else if (Tuesday.get(0).typelesson.toString().equals("Лаб./раб.")) {
            rb_labwork.setChecked(true);
        } else if (Tuesday.get(0).typelesson.toString().equals("Практика")) {
            rb_practice.setChecked(true);
        }
        if (Tuesday.get(1).typelesson.toString().equals("Лекция")) {
            rb_lectureTwo.setChecked(true);
        } else if (Tuesday.get(1).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkTwo.setChecked(true);
        } else if (Tuesday.get(1).typelesson.toString().equals("Практика")) {
            rb_practiceTwo.setChecked(true);
        }
        if (Tuesday.get(2).typelesson.toString().equals("Лекция")) {
            rb_lectureThree.setChecked(true);
        } else if (Tuesday.get(2).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkThree.setChecked(true);
        } else if (Tuesday.get(2).typelesson.toString().equals("Практика")) {
            rb_practiceThree.setChecked(true);
        }
        if (Tuesday.get(3).typelesson.toString().equals("Лекция")) {
            rb_lectureFour.setChecked(true);
        } else if (Tuesday.get(3).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFour.setChecked(true);
        } else if (Tuesday.get(3).typelesson.toString().equals("Практика")) {
            rb_practiceFour.setChecked(true);
        }
        if (Tuesday.get(4).typelesson.toString().equals("Лекция")) {
            rb_lectureFive.setChecked(true);
        } else if (Tuesday.get(4).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFive.setChecked(true);
        } else if (Tuesday.get(4).typelesson.toString().equals("Практика")) {
            rb_practiceFive.setChecked(true);
        }
        if (Tuesday.get(5).typelesson.toString().equals("Лекция")) {
            rb_lectureSix.setChecked(true);
        } else if (Tuesday.get(5).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkSix.setChecked(true);
        } else if (Tuesday.get(5).typelesson.toString().equals("Практика")) {
            rb_practiceSix.setChecked(true);
        }
        //S1
       try {
            SubjectEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueSubjectOne = SubjectEditOne.getSelectedItem().toString();
                    Tuesday.set(0, new DataTuesday("1", "8:30-10:00", TuesdayValueSubjectOne, Tuesday.get(0).audienceEdit.toString(), Tuesday.get(0).educator.toString(), Tuesday.get(0).typelesson.toString()));
                }
              @Override
              public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S2
        try {
            SubjectEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueSubjectTwo = SubjectEditTwo.getSelectedItem().toString();
                    Tuesday.set(1, new DataTuesday("2", "10:10-11:40", TuesdayValueSubjectTwo, Tuesday.get(1).audienceEdit.toString(), Tuesday.get(1).educator.toString(), Tuesday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S3
        try {
            SubjectEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueSubjectThree = SubjectEditThree.getSelectedItem().toString();
                    Tuesday.set(2, new DataTuesday("3", "12:20-13:50", TuesdayValueSubjectThree, Tuesday.get(2).audienceEdit.toString(), Tuesday.get(2).educator.toString(), Tuesday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S4
        try {
            SubjectEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueSubjectFour = SubjectEditFour.getSelectedItem().toString();
                    Tuesday.set(3, new DataTuesday("4", "14:00-15:30", TuesdayValueSubjectFour, Tuesday.get(3).audienceEdit.toString(), Tuesday.get(3).educator.toString(), Tuesday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S5
        try {
            SubjectEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueSubjectFive = SubjectEditFive.getSelectedItem().toString();
                    Tuesday.set(4, new DataTuesday("5", "15:40-17:10", TuesdayValueSubjectFive, Tuesday.get(4).audienceEdit.toString(), Tuesday.get(4).educator.toString(), Tuesday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S6
        try {
            SubjectEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueSubjectSix = SubjectEditSix.getSelectedItem().toString();
                    Tuesday.set(5, new DataTuesday("6", "17:30-19:00", TuesdayValueSubjectSix, Tuesday.get(5).audienceEdit.toString(), Tuesday.get(5).educator.toString(), Tuesday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A1
        try {
            AudienceEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueAudienceOne = AudienceEditOne.getSelectedItem().toString();
                    Tuesday.set(0, new DataTuesday("1", "8:30-10:00", Tuesday.get(0).subjectEdit.toString(), TuesdayValueAudienceOne, Tuesday.get(0).educator.toString(), Tuesday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A2
        try {
            AudienceEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueAudienceTwo = AudienceEditTwo.getSelectedItem().toString();
                    Tuesday.set(1, new DataTuesday("2", "10:10-11:40", Tuesday.get(1).subjectEdit.toString(), TuesdayValueAudienceTwo, Tuesday.get(1).educator.toString(), Tuesday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A3
        try {
            AudienceEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueAudienceThree = AudienceEditThree.getSelectedItem().toString();
                    Tuesday.set(2, new DataTuesday("3", "12:20-13:50", Tuesday.get(2).subjectEdit.toString(), TuesdayValueAudienceThree, Tuesday.get(2).educator.toString(), Tuesday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A4
        try {
            AudienceEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueAudienceFour = AudienceEditFour.getSelectedItem().toString();
                    Tuesday.set(3, new DataTuesday("4", "14:00-15:30", Tuesday.get(3).subjectEdit.toString(), TuesdayValueAudienceFour, Tuesday.get(3).educator.toString(), Tuesday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A5
        try {
            AudienceEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueAudienceFive = AudienceEditFive.getSelectedItem().toString();
                    Tuesday.set(4, new DataTuesday("5", "15:40-17:10", Tuesday.get(4).subjectEdit.toString(), TuesdayValueAudienceFive, Tuesday.get(4).educator.toString(), Tuesday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A6
        try {
            AudienceEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayValueAudienceSix = AudienceEditSix.getSelectedItem().toString();
                    Tuesday.set(5, new DataTuesday("6", "17:30-19:00", Tuesday.get(5).subjectEdit.toString(), TuesdayValueAudienceSix, Tuesday.get(5).educator.toString(), Tuesday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E1
        try {
            EducatorEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayEducatorOne = EducatorEditOne.getSelectedItem().toString();
                    Tuesday.set(0, new DataTuesday("1", "8:30-10:00", Tuesday.get(0).subjectEdit.toString(), Tuesday.get(0).audienceEdit.toString(), TuesdayEducatorOne, Tuesday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E2
        try {
            EducatorEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayEducatorTwo = EducatorEditTwo.getSelectedItem().toString();
                    Tuesday.set(1, new DataTuesday("2", "10:10-11:40", Tuesday.get(1).subjectEdit.toString(), Tuesday.get(1).audienceEdit.toString(), TuesdayEducatorTwo, Tuesday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //E3
        try {
            EducatorEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayEducatorThree = EducatorEditThree.getSelectedItem().toString();
                    Tuesday.set(2, new DataTuesday("3", "12:20-13:50", Tuesday.get(2).subjectEdit.toString(), Tuesday.get(2).audienceEdit.toString(), TuesdayEducatorThree, Tuesday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E4
        try {
            EducatorEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayEducatorFour = EducatorEditFour.getSelectedItem().toString();
                    Tuesday.set(3, new DataTuesday("4", "14:00-15:30", Tuesday.get(3).subjectEdit.toString(), Tuesday.get(3).audienceEdit.toString(), TuesdayEducatorFour, Tuesday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E5
        try {
            EducatorEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayEducatorFive = EducatorEditFive.getSelectedItem().toString();
                    Tuesday.set(4, new DataTuesday("5", "15:40-17:10", Tuesday.get(4).subjectEdit.toString(), Tuesday.get(4).audienceEdit.toString(), TuesdayEducatorFive, Tuesday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E6
        try {
            EducatorEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    TuesdayEducatorSix = EducatorEditSix.getSelectedItem().toString();
                    Tuesday.set(5, new DataTuesday("6", "17:30-19:00", Tuesday.get(5).subjectEdit.toString(), Tuesday.get(5).audienceEdit.toString(), TuesdayEducatorSix, Tuesday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
    }


    public void wednesday_fill() {
        rb_lecture = findViewById(R.id.rb_lecture_wednesday);
        rb_labwork = findViewById(R.id.rb_labwork_wednesday);
        rb_practice = findViewById(R.id.rb_practice_wednesday);
        rb_lectureTwo = findViewById(R.id.rb_lectureTwo_wednesday);
        rb_labworkTwo = findViewById(R.id.rb_labworkTwo_wednesday);
        rb_practiceTwo = findViewById(R.id.rb_practiceTwo_wednesday);
        rb_lectureThree = findViewById(R.id.rb_lectureThree_wednesday);
        rb_labworkThree = findViewById(R.id.rb_labworkThree_wednesday);
        rb_practiceThree = findViewById(R.id.rb_practiceThree_wednesday);
        rb_lectureFour = findViewById(R.id.rb_lectureFour_wednesday);
        rb_labworkFour = findViewById(R.id.rb_labworkFour_wednesday);
        rb_practiceFour = findViewById(R.id.rb_practiceFour_wednesday);
        rb_lectureFive = findViewById(R.id.rb_lectureFive_wednesday);
        rb_labworkFive = findViewById(R.id.rb_labworkFive_wednesday);
        rb_practiceFive = findViewById(R.id.rb_practiceFive_wednesday);
        rb_lectureSix = findViewById(R.id.rb_lectureSix_wednesday);
        rb_labworkSix = findViewById(R.id.rb_labworkSix_wednesday);
        rb_practiceSix = findViewById(R.id.rb_practiceSix_wednesday);
        NumberOne = findViewById(R.id.number_wednesday);
        NumberTwo = findViewById(R.id.numberTwo_wednesday);
        NumberThree = findViewById(R.id.numberThree_wednesday);
        NumberFour = findViewById(R.id.numberFour_wednesday);
        NumberFive = findViewById(R.id.numberFive_wednesday);
        NumberSix = findViewById(R.id.numberSix_wednesday);
        TimeOne = findViewById(R.id.time_wednesday);
        TimeTwo = findViewById(R.id.timeTwo_wednesday);
        TimeThree = findViewById(R.id.timeThree_wednesday);
        TimeFour = findViewById(R.id.timeFour_wednesday);
        TimeFive = findViewById(R.id.timeFive_wednesday);
        TimeSix = findViewById(R.id.timeSix_wednesday);
        SubjectEditOne = findViewById(R.id.subject_edit_wednesday);
        SubjectEditTwo = findViewById(R.id.subject_editTwo_wednesday);
        SubjectEditThree = findViewById(R.id.subject_editThree_wednesday);
        SubjectEditFour = findViewById(R.id.subject_editFour_wednesday);
        SubjectEditFive = findViewById(R.id.subject_editFive_wednesday);
        SubjectEditSix = findViewById(R.id.subject_editSix_wednesday);
        AudienceEditOne = findViewById(R.id.audience_edit_wednesday);
        AudienceEditTwo = findViewById(R.id.audience_editTwo_wednesday);
        AudienceEditThree = findViewById(R.id.audience_editThree_wednesday);
        AudienceEditFour = findViewById(R.id.audience_editFour_wednesday);
        AudienceEditFive = findViewById(R.id.audience_editFive_wednesday);
        AudienceEditSix = findViewById(R.id.audience_editSix_wednesday);
        EducatorEditOne = findViewById(R.id.educator_edit_wednesday);
        EducatorEditTwo = findViewById(R.id.educator_editTwo_wednesday);
        EducatorEditThree = findViewById(R.id.educator_editThree_wednesday);
        EducatorEditFour = findViewById(R.id.educator_editFour_wednesday);
        EducatorEditFive = findViewById(R.id.educator_editFive_wednesday);
        EducatorEditSix = findViewById(R.id.educator_editSix_wednesday);
        typeEditOne_wednesday = findViewById(R.id.typeEdit_wednesday);
        typeEditTwo_wednesday = findViewById(R.id.typeEditTwo_wednesday);
        typeEditThree_wednesday = findViewById(R.id.typeEditThree_wednesday);
        typeEditFour_wednesday = findViewById(R.id.typeEditFour_wednesday);
        typeEditFive_wednesday = findViewById(R.id.typeEditFive_wednesday);
        typeEditSix_wednesday = findViewById(R.id.typeEditSix_wednesday);

        DataWednesday();
        NumberOne.setText(Wednesday.get(0).idcards.toString());
        NumberTwo.setText(Wednesday.get(1).idcards.toString());
        NumberThree.setText(Wednesday.get(2).idcards.toString());
        NumberFour.setText(Wednesday.get(3).idcards.toString());
        NumberFive.setText(Wednesday.get(4).idcards.toString());
        NumberSix.setText(Wednesday.get(5).idcards.toString());
        TimeOne.setText(Wednesday.get(0).timelesson.toString());
        TimeTwo.setText(Wednesday.get(1).timelesson.toString());
        TimeThree.setText(Wednesday.get(2).timelesson.toString());
        TimeFour.setText(Wednesday.get(3).timelesson.toString());
        TimeFive.setText(Wednesday.get(4).timelesson.toString());
        TimeSix.setText(Wednesday.get(5).timelesson.toString());
        ArrayAdapter<String> subSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, subject_list);
        subSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> audSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, audience_list);
        audSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> eduSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, educator_list);
        eduSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        SubjectEditOne.setAdapter(subSpinnerArrayAdapter);
        SubjectEditTwo.setAdapter(subSpinnerArrayAdapter);
        SubjectEditThree.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFour.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFive.setAdapter(subSpinnerArrayAdapter);
        SubjectEditSix.setAdapter(subSpinnerArrayAdapter);
        AudienceEditOne.setAdapter(audSpinnerArrayAdapter);
        AudienceEditTwo.setAdapter(audSpinnerArrayAdapter);
        AudienceEditThree.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFour.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFive.setAdapter(audSpinnerArrayAdapter);
        AudienceEditSix.setAdapter(audSpinnerArrayAdapter);
        EducatorEditOne.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditTwo.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditThree.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFour.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFive.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditSix.setAdapter(eduSpinnerArrayAdapter);

        SubjectEditOne.setSelection(subject_list.indexOf(Wednesday.get(0).subjectEdit.toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Wednesday.get(1).subjectEdit.toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Wednesday.get(2).subjectEdit.toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Wednesday.get(3).subjectEdit.toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Wednesday.get(4).subjectEdit.toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Wednesday.get(5).subjectEdit.toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Wednesday.get(0).audienceEdit.toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Wednesday.get(1).audienceEdit.toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Wednesday.get(2).audienceEdit.toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Wednesday.get(3).audienceEdit.toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Wednesday.get(4).audienceEdit.toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Wednesday.get(5).audienceEdit.toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Wednesday.get(0).educator.toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Wednesday.get(1).educator.toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Wednesday.get(2).educator.toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Wednesday.get(3).educator.toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Wednesday.get(4).educator.toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Wednesday.get(5).educator.toString()));

        TextView copy_downOne = findViewById(R.id.copy_downOne_wednesday);
        TextView copy_downTwo = findViewById(R.id.copy_downTwo_wednesday);
        TextView copy_downThree = findViewById(R.id.copy_downThree_wednesday);
        TextView copy_downFour = findViewById(R.id.copy_downFour_wednesday);
        TextView copy_downFive = findViewById(R.id.copy_downFive_wednesday);
        TextView copy_upTwo= findViewById(R.id.copy_upTwo_wednesday);
        TextView copy_upThree= findViewById(R.id.copy_upThree_wednesday);
        TextView copy_upFour= findViewById(R.id.copy_upFour_wednesday);
        TextView copy_upFive= findViewById(R.id.copy_upFive_wednesday);
        TextView copy_upSix= findViewById(R.id.copy_upSix_wednesday);
        TextView clearOne = findViewById(R.id.clear_cardOne_wednesday);
        TextView clearTwo = findViewById(R.id.clear_cardTwo_wednesday);
        TextView clearThree = findViewById(R.id.clear_cardThree_wednesday);
        TextView clearFour = findViewById(R.id.clear_cardFour_wednesday);
        TextView clearFive = findViewById(R.id.clear_cardFive_wednesday);
        TextView clearSix = findViewById(R.id.clear_cardSix_wednesday);

        typeEditOne_wednesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonOne = typeEditOne_wednesday.indexOfChild(findViewById(typeEditOne_wednesday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditOne_wednesday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    WednesdayTypeLessonOne = radioButton.getText().toString();
                }
                else { WednesdayTypeLessonOne = "";}

            }
        });

        typeEditTwo_wednesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonTwo = typeEditTwo_wednesday.indexOfChild(findViewById(typeEditTwo_wednesday.getCheckedRadioButtonId()));
                int IdRadioButtonTwo = typeEditTwo_wednesday.getCheckedRadioButtonId();
                RadioButton radioButtonTwo = findViewById(IdRadioButtonTwo);
                if (radioButtonTwo != null) {
                    WednesdayTypeLessonTwo = radioButtonTwo.getText().toString();
                }
                else { WednesdayTypeLessonTwo = "";}
            }
        });

        typeEditThree_wednesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonThree = typeEditThree_wednesday.indexOfChild(findViewById(typeEditThree_wednesday.getCheckedRadioButtonId()));
                int IdRadioButtonThree = typeEditThree_wednesday.getCheckedRadioButtonId();
                RadioButton radioButtonThree = findViewById(IdRadioButtonThree);
                if (radioButtonThree != null) {
                    WednesdayTypeLessonThree = radioButtonThree.getText().toString();
                }
                else { WednesdayTypeLessonThree = "";}
            }
        });

        typeEditFour_wednesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFour = typeEditFour_wednesday.indexOfChild(findViewById(typeEditFour_wednesday.getCheckedRadioButtonId()));
                int IdRadioButtonFour = typeEditFour_wednesday.getCheckedRadioButtonId();
                RadioButton radioButtonFour = findViewById(IdRadioButtonFour);
                if (radioButtonFour != null) {
                    WednesdayTypeLessonFour = radioButtonFour.getText().toString();
                }
                else { WednesdayTypeLessonFour = "";}
            }
        });

        typeEditFive_wednesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFive = typeEditFive_wednesday.indexOfChild(findViewById(typeEditFive_wednesday.getCheckedRadioButtonId()));
                int IdRadioButtonFive = typeEditFive_wednesday.getCheckedRadioButtonId();
                RadioButton radioButtonFive = findViewById(IdRadioButtonFive);
                if (radioButtonFive != null) {
                    WednesdayTypeLessonFive = radioButtonFive.getText().toString();
                }
                else { WednesdayTypeLessonFive = "";}
            }
        });
        typeEditSix_wednesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonSix = typeEditSix_wednesday.indexOfChild(findViewById(typeEditSix_wednesday.getCheckedRadioButtonId()));
                int IdRadioButtonSix = typeEditSix_wednesday.getCheckedRadioButtonId();
                RadioButton radioButtonSix = findViewById(IdRadioButtonSix);
                if (radioButtonSix != null) {
                    WednesdayTypeLessonSix = radioButtonSix.getText().toString();
                }
                else { WednesdayTypeLessonSix = "";}
            }
        });

        copy_downOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditTwo.setSelection(subject_list.indexOf(Wednesday.get(0).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Wednesday.get(0).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Wednesday.get(0).educator));
                switch (IdRadioButtonOne){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditThree.setSelection(subject_list.indexOf(Wednesday.get(1).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Wednesday.get(1).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Wednesday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFour.setSelection(subject_list.indexOf(Wednesday.get(2).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Wednesday.get(2).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Wednesday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });
        copy_downFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFive.setSelection(subject_list.indexOf(Wednesday.get(3).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Wednesday.get(3).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Wednesday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditSix.setSelection(subject_list.indexOf(Wednesday.get(4).subjectEdit));
                AudienceEditSix.setSelection(audience_list.indexOf(Wednesday.get(4).audienceEdit));
                EducatorEditSix.setSelection(educator_list.indexOf(Wednesday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureSix.setChecked(true);
                        break;
                    case 1:
                        rb_labworkSix.setChecked(true);
                        break;
                    case 2:
                        rb_practiceSix.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditOne.setSelection(subject_list.indexOf(Wednesday.get(1).subjectEdit));
                AudienceEditOne.setSelection(audience_list.indexOf(Wednesday.get(1).audienceEdit));
                EducatorEditOne.setSelection(educator_list.indexOf(Wednesday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lecture.setChecked(true);
                        break;
                    case 1:
                        rb_labwork.setChecked(true);
                        break;
                    case 2:
                        rb_practice.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditTwo.setSelection(subject_list.indexOf(Wednesday.get(2).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Wednesday.get(2).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Wednesday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditThree.setSelection(subject_list.indexOf(Wednesday.get(3).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Wednesday.get(3).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Wednesday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFour.setSelection(subject_list.indexOf(Wednesday.get(4).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Wednesday.get(4).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Wednesday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFive.setSelection(subject_list.indexOf(Wednesday.get(5).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Wednesday.get(5).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Wednesday.get(5).educator));
                switch (IdRadioButtonSix){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });



        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditOne.setSelection(0);
                AudienceEditOne.setSelection(0);
                EducatorEditOne.setSelection(0);
                typeEditOne_wednesday.clearCheck();
            }
        });

        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditTwo.setSelection(0);
                AudienceEditTwo.setSelection(0);
                EducatorEditTwo.setSelection(0);
                typeEditTwo_wednesday.clearCheck();
            }
        });

        clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditThree.setSelection(0);
                AudienceEditThree.setSelection(0);
                EducatorEditThree.setSelection(0);
                typeEditThree_wednesday.clearCheck();
            }
        });

        clearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFour.setSelection(0);
                AudienceEditFour.setSelection(0);
                EducatorEditFour.setSelection(0);
                typeEditFour_wednesday.clearCheck();
            }
        });

        clearFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFive.setSelection(0);
                AudienceEditFive.setSelection(0);
                EducatorEditFive.setSelection(0);
                typeEditFive_wednesday.clearCheck();
            }
        });

        clearSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(0);
                AudienceEditSix.setSelection(0);
                EducatorEditSix.setSelection(0);
                typeEditSix_wednesday.clearCheck();
            }
        });


        SubjectEditOne.setSelection(subject_list.indexOf(Wednesday.get(0).subjectEdit.toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Wednesday.get(1).subjectEdit.toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Wednesday.get(2).subjectEdit.toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Wednesday.get(3).subjectEdit.toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Wednesday.get(4).subjectEdit.toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Wednesday.get(5).subjectEdit.toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Wednesday.get(0).audienceEdit.toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Wednesday.get(1).audienceEdit.toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Wednesday.get(2).audienceEdit.toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Wednesday.get(3).audienceEdit.toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Wednesday.get(4).audienceEdit.toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Wednesday.get(5).audienceEdit.toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Wednesday.get(0).educator.toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Wednesday.get(1).educator.toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Wednesday.get(2).educator.toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Wednesday.get(3).educator.toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Wednesday.get(4).educator.toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Wednesday.get(5).educator.toString()));


        if (Wednesday.get(0).typelesson.toString().equals("Лекция")) {
            rb_lecture.setChecked(true);
        } else if (Wednesday.get(0).typelesson.toString().equals("Лаб./раб.")) {
            rb_labwork.setChecked(true);
        } else if (Wednesday.get(0).typelesson.toString().equals("Практика")) {
            rb_practice.setChecked(true);
        }
        if (Wednesday.get(1).typelesson.toString().equals("Лекция")) {
            rb_lectureTwo.setChecked(true);
        } else if (Wednesday.get(1).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkTwo.setChecked(true);
        } else if (Wednesday.get(1).typelesson.toString().equals("Практика")) {
            rb_practiceTwo.setChecked(true);
        }
        if (Wednesday.get(2).typelesson.toString().equals("Лекция")) {
            rb_lectureThree.setChecked(true);
        } else if (Wednesday.get(2).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkThree.setChecked(true);
        } else if (Wednesday.get(2).typelesson.toString().equals("Практика")) {
            rb_practiceThree.setChecked(true);
        }
        if (Wednesday.get(3).typelesson.toString().equals("Лекция")) {
            rb_lectureFour.setChecked(true);
        } else if (Wednesday.get(3).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFour.setChecked(true);
        } else if (Wednesday.get(3).typelesson.toString().equals("Практика")) {
            rb_practiceFour.setChecked(true);
        }
        if (Wednesday.get(4).typelesson.toString().equals("Лекция")) {
            rb_lectureFive.setChecked(true);
        } else if (Wednesday.get(4).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFive.setChecked(true);
        } else if (Wednesday.get(4).typelesson.toString().equals("Практика")) {
            rb_practiceFive.setChecked(true);
        }
        if (Wednesday.get(5).typelesson.toString().equals("Лекция")) {
            rb_lectureSix.setChecked(true);
        } else if (Wednesday.get(5).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkSix.setChecked(true);
        } else if (Wednesday.get(5).typelesson.toString().equals("Практика")) {
            rb_practiceSix.setChecked(true);
        }

        //S1
        try {
            SubjectEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueSubjectOne = SubjectEditOne.getSelectedItem().toString();
                    Wednesday.set(0, new DataWednesday("1", "8:30-10:00", WednesdayValueSubjectOne, Wednesday.get(0).audienceEdit.toString(), Wednesday.get(0).educator.toString(), Wednesday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S2
        try {
            SubjectEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueSubjectTwo = SubjectEditTwo.getSelectedItem().toString();
                    Wednesday.set(1, new DataWednesday("2", "10:10-11:40", WednesdayValueSubjectTwo, Wednesday.get(1).audienceEdit.toString(), Wednesday.get(1).educator.toString(), Wednesday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S3
        try {
            SubjectEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueSubjectThree = SubjectEditThree.getSelectedItem().toString();
                    Wednesday.set(2, new DataWednesday("3", "12:20-13:50", WednesdayValueSubjectThree, Wednesday.get(2).audienceEdit.toString(), Wednesday.get(2).educator.toString(), Wednesday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S4
        try {
            SubjectEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueSubjectFour = SubjectEditFour.getSelectedItem().toString();
                    Wednesday.set(3, new DataWednesday("4", "14:00-15:30", WednesdayValueSubjectFour, Wednesday.get(3).audienceEdit.toString(), Wednesday.get(3).educator.toString(), Wednesday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S5
        try {
            SubjectEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueSubjectFive = SubjectEditFive.getSelectedItem().toString();
                    Wednesday.set(4, new DataWednesday("5", "15:40-17:10", WednesdayValueSubjectFive, Wednesday.get(4).audienceEdit.toString(), Wednesday.get(4).educator.toString(), Wednesday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S6
        try {
            SubjectEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueSubjectSix = SubjectEditSix.getSelectedItem().toString();
                    Wednesday.set(5, new DataWednesday("6", "17:30-19:00", WednesdayValueSubjectSix, Wednesday.get(5).audienceEdit.toString(), Wednesday.get(5).educator.toString(), Wednesday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A1
        try {
            AudienceEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueAudienceOne = AudienceEditOne.getSelectedItem().toString();
                    Wednesday.set(0, new DataWednesday("1", "8:30-10:00", Wednesday.get(0).subjectEdit.toString(), WednesdayValueAudienceOne, Wednesday.get(0).educator.toString(), Wednesday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A2
        try {
            AudienceEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueAudienceTwo = AudienceEditTwo.getSelectedItem().toString();
                    Wednesday.set(1, new DataWednesday("2", "10:10-11:40", Wednesday.get(1).subjectEdit.toString(), WednesdayValueAudienceTwo, Wednesday.get(1).educator.toString(), Wednesday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A3
        try {
            AudienceEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueAudienceThree = AudienceEditThree.getSelectedItem().toString();
                    Wednesday.set(2, new DataWednesday("3", "12:20-13:50", Wednesday.get(2).subjectEdit.toString(), WednesdayValueAudienceThree, Wednesday.get(2).educator.toString(), Wednesday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A4
        try {
            AudienceEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueAudienceFour = AudienceEditFour.getSelectedItem().toString();
                    Wednesday.set(3, new DataWednesday("4", "14:00-15:30", Wednesday.get(3).subjectEdit.toString(), WednesdayValueAudienceFour, Wednesday.get(3).educator.toString(), Wednesday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A5
        try {
            AudienceEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueAudienceFive = AudienceEditFive.getSelectedItem().toString();
                    Wednesday.set(4, new DataWednesday("5", "15:40-17:10", Wednesday.get(4).subjectEdit.toString(), WednesdayValueAudienceFive, Wednesday.get(4).educator.toString(), Wednesday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A6
        try {
            AudienceEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayValueAudienceSix = AudienceEditSix.getSelectedItem().toString();
                    Wednesday.set(5, new DataWednesday("6", "17:30-19:00", Wednesday.get(5).subjectEdit.toString(), WednesdayValueAudienceSix, Wednesday.get(5).educator.toString(), Wednesday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E1
        try {
            EducatorEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayEducatorOne = EducatorEditOne.getSelectedItem().toString();
                    Wednesday.set(0, new DataWednesday("1", "8:30-10:00", Wednesday.get(0).subjectEdit.toString(), Wednesday.get(0).audienceEdit.toString(), WednesdayEducatorOne, Wednesday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E2
        try {
            EducatorEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayEducatorTwo = EducatorEditTwo.getSelectedItem().toString();
                    Wednesday.set(1, new DataWednesday("2", "10:10-11:40", Wednesday.get(1).subjectEdit.toString(), Wednesday.get(1).audienceEdit.toString(), WednesdayEducatorTwo, Wednesday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //E3
        try {
            EducatorEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayEducatorThree = EducatorEditThree.getSelectedItem().toString();
                    Wednesday.set(2, new DataWednesday("3", "12:20-13:50", Wednesday.get(2).subjectEdit.toString(), Wednesday.get(2).audienceEdit.toString(), WednesdayEducatorThree, Wednesday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E4
        try {
            EducatorEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayEducatorFour = EducatorEditFour.getSelectedItem().toString();
                    Wednesday.set(3, new DataWednesday("4", "14:00-15:30", Wednesday.get(3).subjectEdit.toString(), Wednesday.get(3).audienceEdit.toString(), WednesdayEducatorFour, Wednesday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E5
        try {
            EducatorEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayEducatorFive = EducatorEditFive.getSelectedItem().toString();
                    Wednesday.set(4, new DataWednesday("5", "15:40-17:10", Wednesday.get(4).subjectEdit.toString(), Wednesday.get(4).audienceEdit.toString(), WednesdayEducatorFive, Wednesday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E6
        try {
            EducatorEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    WednesdayEducatorSix = EducatorEditSix.getSelectedItem().toString();
                    Wednesday.set(5, new DataWednesday("6", "17:30-19:00", Wednesday.get(5).subjectEdit.toString(), Wednesday.get(5).audienceEdit.toString(), WednesdayEducatorSix, Wednesday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
    }
    public void thursday_fill() {
        rb_lecture = findViewById(R.id.rb_lecture_thursday);
        rb_labwork = findViewById(R.id.rb_labwork_thursday);
        rb_practice = findViewById(R.id.rb_practice_thursday);
        rb_lectureTwo = findViewById(R.id.rb_lectureTwo_thursday);
        rb_labworkTwo = findViewById(R.id.rb_labworkTwo_thursday);
        rb_practiceTwo = findViewById(R.id.rb_practiceTwo_thursday);
        rb_lectureThree = findViewById(R.id.rb_lectureThree_thursday);
        rb_labworkThree = findViewById(R.id.rb_labworkThree_thursday);
        rb_practiceThree = findViewById(R.id.rb_practiceThree_thursday);
        rb_lectureFour = findViewById(R.id.rb_lectureFour_thursday);
        rb_labworkFour = findViewById(R.id.rb_labworkFour_thursday);
        rb_practiceFour = findViewById(R.id.rb_practiceFour_thursday);
        rb_lectureFive = findViewById(R.id.rb_lectureFive_thursday);
        rb_labworkFive = findViewById(R.id.rb_labworkFive_thursday);
        rb_practiceFive = findViewById(R.id.rb_practiceFive_thursday);
        rb_lectureSix = findViewById(R.id.rb_lectureSix_thursday);
        rb_labworkSix = findViewById(R.id.rb_labworkSix_thursday);
        rb_practiceSix = findViewById(R.id.rb_practiceSix_thursday);
        NumberOne = findViewById(R.id.number_thursday);
        NumberTwo = findViewById(R.id.numberTwo_thursday);
        NumberThree = findViewById(R.id.numberThree_thursday);
        NumberFour = findViewById(R.id.numberFour_thursday);
        NumberFive = findViewById(R.id.numberFive_thursday);
        NumberSix = findViewById(R.id.numberSix_thursday);
        TimeOne = findViewById(R.id.time_thursday);
        TimeTwo = findViewById(R.id.timeTwo_thursday);
        TimeThree = findViewById(R.id.timeThree_thursday);
        TimeFour = findViewById(R.id.timeFour_thursday);
        TimeFive = findViewById(R.id.timeFive_thursday);
        TimeSix = findViewById(R.id.timeSix_thursday);
        SubjectEditOne = findViewById(R.id.subject_edit_thursday);
        SubjectEditTwo = findViewById(R.id.subject_editTwo_thursday);
        SubjectEditThree = findViewById(R.id.subject_editThree_thursday);
        SubjectEditFour = findViewById(R.id.subject_editFour_thursday);
        SubjectEditFive = findViewById(R.id.subject_editFive_thursday);
        SubjectEditSix = findViewById(R.id.subject_editSix_thursday);
        AudienceEditOne = findViewById(R.id.audience_edit_thursday);
        AudienceEditTwo = findViewById(R.id.audience_editTwo_thursday);
        AudienceEditThree = findViewById(R.id.audience_editThree_thursday);
        AudienceEditFour = findViewById(R.id.audience_editFour_thursday);
        AudienceEditFive = findViewById(R.id.audience_editFive_thursday);
        AudienceEditSix = findViewById(R.id.audience_editSix_thursday);
        EducatorEditOne = findViewById(R.id.educator_edit_thursday);
        EducatorEditTwo = findViewById(R.id.educator_editTwo_thursday);
        EducatorEditThree = findViewById(R.id.educator_editThree_thursday);
        EducatorEditFour = findViewById(R.id.educator_editFour_thursday);
        EducatorEditFive = findViewById(R.id.educator_editFive_thursday);
        EducatorEditSix = findViewById(R.id.educator_editSix_thursday);
        typeEditOne_thursday = findViewById(R.id.typeEdit_thursday);
        typeEditTwo_thursday = findViewById(R.id.typeEditTwo_thursday);
        typeEditThree_thursday = findViewById(R.id.typeEditThree_thursday);
        typeEditFour_thursday = findViewById(R.id.typeEditFour_thursday);
        typeEditFive_thursday = findViewById(R.id.typeEditFive_thursday);
        typeEditSix_thursday = findViewById(R.id.typeEditSix_thursday);

        DataThursday();
        NumberOne.setText(Thursday.get(0).idcards.toString());
        NumberTwo.setText(Thursday.get(1).idcards.toString());
        NumberThree.setText(Thursday.get(2).idcards.toString());
        NumberFour.setText(Thursday.get(3).idcards.toString());
        NumberFive.setText(Thursday.get(4).idcards.toString());
        NumberSix.setText(Thursday.get(5).idcards.toString());
        TimeOne.setText(Thursday.get(0).timelesson.toString());
        TimeTwo.setText(Thursday.get(1).timelesson.toString());
        TimeThree.setText(Thursday.get(2).timelesson.toString());
        TimeFour.setText(Thursday.get(3).timelesson.toString());
        TimeFive.setText(Thursday.get(4).timelesson.toString());
        TimeSix.setText(Thursday.get(5).timelesson.toString());
        ArrayAdapter<String> subSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, subject_list);
        subSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> audSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, audience_list);
        audSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> eduSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, educator_list);
        eduSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        SubjectEditOne.setAdapter(subSpinnerArrayAdapter);
        SubjectEditTwo.setAdapter(subSpinnerArrayAdapter);
        SubjectEditThree.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFour.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFive.setAdapter(subSpinnerArrayAdapter);
        SubjectEditSix.setAdapter(subSpinnerArrayAdapter);
        AudienceEditOne.setAdapter(audSpinnerArrayAdapter);
        AudienceEditTwo.setAdapter(audSpinnerArrayAdapter);
        AudienceEditThree.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFour.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFive.setAdapter(audSpinnerArrayAdapter);
        AudienceEditSix.setAdapter(audSpinnerArrayAdapter);
        EducatorEditOne.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditTwo.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditThree.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFour.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFive.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditSix.setAdapter(eduSpinnerArrayAdapter);

        TextView copy_downOne = findViewById(R.id.copy_downOne_thursday);
        TextView copy_downTwo = findViewById(R.id.copy_downTwo_thursday);
        TextView copy_downThree = findViewById(R.id.copy_downThree_thursday);
        TextView copy_downFour = findViewById(R.id.copy_downFour_thursday);
        TextView copy_downFive = findViewById(R.id.copy_downFive_thursday);
        TextView copy_upTwo= findViewById(R.id.copy_upTwo_thursday);
        TextView copy_upThree= findViewById(R.id.copy_upThree_thursday);
        TextView copy_upFour= findViewById(R.id.copy_upFour_thursday);
        TextView copy_upFive= findViewById(R.id.copy_upFive_thursday);
        TextView copy_upSix= findViewById(R.id.copy_upSix_thursday);
        TextView clearOne = findViewById(R.id.clear_cardOne_thursday);
        TextView clearTwo = findViewById(R.id.clear_cardTwo_thursday);
        TextView clearThree = findViewById(R.id.clear_cardThree_thursday);
        TextView clearFour = findViewById(R.id.clear_cardFour_thursday);
        TextView clearFive = findViewById(R.id.clear_cardFive_thursday);
        TextView clearSix = findViewById(R.id.clear_cardSix_thursday);

        typeEditOne_thursday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonOne = typeEditOne_thursday.indexOfChild(findViewById(typeEditOne_thursday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditOne_thursday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    ThursdayTypeLessonOne = radioButton.getText().toString();
                }
                else { ThursdayTypeLessonOne = "";}

            }
        });

        typeEditTwo_thursday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonTwo = typeEditTwo_thursday.indexOfChild(findViewById(typeEditTwo_thursday.getCheckedRadioButtonId()));
                int IdRadioButtonTwo = typeEditTwo_thursday.getCheckedRadioButtonId();
                RadioButton radioButtonTwo = findViewById(IdRadioButtonTwo);
                if (radioButtonTwo != null) {
                    ThursdayTypeLessonTwo = radioButtonTwo.getText().toString();
                }
                else { ThursdayTypeLessonTwo = "";}
            }
        });

        typeEditThree_thursday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonThree = typeEditThree_thursday.indexOfChild(findViewById(typeEditThree_thursday.getCheckedRadioButtonId()));
                int IdRadioButtonThree = typeEditThree_thursday.getCheckedRadioButtonId();
                RadioButton radioButtonThree = findViewById(IdRadioButtonThree);
                if (radioButtonThree != null) {
                    ThursdayTypeLessonThree = radioButtonThree.getText().toString();
                }
                else { ThursdayTypeLessonThree = "";}
            }
        });

        typeEditFour_thursday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFour = typeEditFour_thursday.indexOfChild(findViewById(typeEditFour_thursday.getCheckedRadioButtonId()));
                int IdRadioButtonFour = typeEditFour_thursday.getCheckedRadioButtonId();
                RadioButton radioButtonFour = findViewById(IdRadioButtonFour);
                if (radioButtonFour != null) {
                    ThursdayTypeLessonFour = radioButtonFour.getText().toString();
                }
                else { ThursdayTypeLessonFour = "";}
            }
        });

        typeEditFive_thursday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFive = typeEditFive_thursday.indexOfChild(findViewById(typeEditFive_thursday.getCheckedRadioButtonId()));
                int IdRadioButtonFive = typeEditFive_thursday.getCheckedRadioButtonId();
                RadioButton radioButtonFive = findViewById(IdRadioButtonFive);
                if (radioButtonFive != null) {
                    ThursdayTypeLessonFive = radioButtonFive.getText().toString();
                }
                else { ThursdayTypeLessonFive = "";}
            }
        });
        typeEditSix_thursday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonSix = typeEditSix_thursday.indexOfChild(findViewById(typeEditSix_thursday.getCheckedRadioButtonId()));
                int IdRadioButtonSix = typeEditSix_thursday.getCheckedRadioButtonId();
                RadioButton radioButtonSix = findViewById(IdRadioButtonSix);
                if (radioButtonSix != null) {
                    ThursdayTypeLessonSix = radioButtonSix.getText().toString();
                }
                else { ThursdayTypeLessonSix = "";}
            }
        });

        copy_downOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditTwo.setSelection(subject_list.indexOf(Thursday.get(0).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Thursday.get(0).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Thursday.get(0).educator));
                switch (IdRadioButtonOne){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditThree.setSelection(subject_list.indexOf(Thursday.get(1).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Thursday.get(1).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Thursday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFour.setSelection(subject_list.indexOf(Thursday.get(2).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Thursday.get(2).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Thursday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });
        copy_downFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFive.setSelection(subject_list.indexOf(Thursday.get(3).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Thursday.get(3).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Thursday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditSix.setSelection(subject_list.indexOf(Thursday.get(4).subjectEdit));
                AudienceEditSix.setSelection(audience_list.indexOf(Thursday.get(4).audienceEdit));
                EducatorEditSix.setSelection(educator_list.indexOf(Thursday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureSix.setChecked(true);
                        break;
                    case 1:
                        rb_labworkSix.setChecked(true);
                        break;
                    case 2:
                        rb_practiceSix.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditOne.setSelection(subject_list.indexOf(Thursday.get(1).subjectEdit));
                AudienceEditOne.setSelection(audience_list.indexOf(Thursday.get(1).audienceEdit));
                EducatorEditOne.setSelection(educator_list.indexOf(Thursday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lecture.setChecked(true);
                        break;
                    case 1:
                        rb_labwork.setChecked(true);
                        break;
                    case 2:
                        rb_practice.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditTwo.setSelection(subject_list.indexOf(Thursday.get(2).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Thursday.get(2).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Thursday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditThree.setSelection(subject_list.indexOf(Thursday.get(3).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Thursday.get(3).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Thursday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFour.setSelection(subject_list.indexOf(Thursday.get(4).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Thursday.get(4).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Thursday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFive.setSelection(subject_list.indexOf(Thursday.get(5).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Thursday.get(5).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Thursday.get(5).educator));
                switch (IdRadioButtonSix){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }

                } catch   (NullPointerException e) {}
            }
        });



        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditOne.setSelection(0);
                AudienceEditOne.setSelection(0);
                EducatorEditOne.setSelection(0);
                typeEditOne_thursday.clearCheck();
            }
        });

        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditTwo.setSelection(0);
                AudienceEditTwo.setSelection(0);
                EducatorEditTwo.setSelection(0);
                typeEditTwo_thursday.clearCheck();
            }
        });

        clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditThree.setSelection(0);
                AudienceEditThree.setSelection(0);
                EducatorEditThree.setSelection(0);
                typeEditThree_thursday.clearCheck();
            }
        });

        clearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFour.setSelection(0);
                AudienceEditFour.setSelection(0);
                EducatorEditFour.setSelection(0);
                typeEditFour_thursday.clearCheck();
            }
        });

        clearFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFive.setSelection(0);
                AudienceEditFive.setSelection(0);
                EducatorEditFive.setSelection(0);
                typeEditFive_thursday.clearCheck();
            }
        });

        clearSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(0);
                AudienceEditSix.setSelection(0);
                EducatorEditSix.setSelection(0);
                typeEditSix_thursday.clearCheck();
            }
        });



        SubjectEditOne.setSelection(subject_list.indexOf(Thursday.get(0).subjectEdit.toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Thursday.get(1).subjectEdit.toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Thursday.get(2).subjectEdit.toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Thursday.get(3).subjectEdit.toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Thursday.get(4).subjectEdit.toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Thursday.get(5).subjectEdit.toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Thursday.get(0).audienceEdit.toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Thursday.get(1).audienceEdit.toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Thursday.get(2).audienceEdit.toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Thursday.get(3).audienceEdit.toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Thursday.get(4).audienceEdit.toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Thursday.get(5).audienceEdit.toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Thursday.get(0).educator.toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Thursday.get(1).educator.toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Thursday.get(2).educator.toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Thursday.get(3).educator.toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Thursday.get(4).educator.toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Thursday.get(5).educator.toString()));

        if (Thursday.get(0).typelesson.toString().equals("Лекция")) {
            rb_lecture.setChecked(true);
        } else if (Thursday.get(0).typelesson.toString().equals("Лаб./раб.")) {
            rb_labwork.setChecked(true);
        } else if (Thursday.get(0).typelesson.toString().equals("Практика")) {
            rb_practice.setChecked(true);
        }
        if (Thursday.get(1).typelesson.toString().equals("Лекция")) {
            rb_lectureTwo.setChecked(true);
        } else if (Thursday.get(1).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkTwo.setChecked(true);
        } else if (Thursday.get(1).typelesson.toString().equals("Практика")) {
            rb_practiceTwo.setChecked(true);
        }
        if (Thursday.get(2).typelesson.toString().equals("Лекция")) {
            rb_lectureThree.setChecked(true);
        } else if (Thursday.get(2).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkThree.setChecked(true);
        } else if (Thursday.get(2).typelesson.toString().equals("Практика")) {
            rb_practiceThree.setChecked(true);
        }
        if (Thursday.get(3).typelesson.toString().equals("Лекция")) {
            rb_lectureFour.setChecked(true);
        } else if (Thursday.get(3).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFour.setChecked(true);
        } else if (Thursday.get(3).typelesson.toString().equals("Практика")) {
            rb_practiceFour.setChecked(true);
        }
        if (Thursday.get(4).typelesson.toString().equals("Лекция")) {
            rb_lectureFive.setChecked(true);
        } else if (Thursday.get(4).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFive.setChecked(true);
        } else if (Thursday.get(4).typelesson.toString().equals("Практика")) {
            rb_practiceFive.setChecked(true);
        }
        if (Thursday.get(5).typelesson.toString().equals("Лекция")) {
            rb_lectureSix.setChecked(true);
        } else if (Thursday.get(5).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkSix.setChecked(true);
        } else if (Thursday.get(5).typelesson.toString().equals("Практика")) {
            rb_practiceSix.setChecked(true);
        }

        //S1
          try {
            SubjectEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueSubjectOne = SubjectEditOne.getSelectedItem().toString();
                    Thursday.set(0, new DataThursday("1", "8:30-10:00", ThursdayValueSubjectOne, Thursday.get(0).audienceEdit.toString(), Thursday.get(0).educator.toString(), Thursday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S2
        try {
            SubjectEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueSubjectTwo = SubjectEditTwo.getSelectedItem().toString();
                    Thursday.set(1, new DataThursday("2", "10:10-11:40", ThursdayValueSubjectTwo, Thursday.get(1).audienceEdit.toString(), Thursday.get(1).educator.toString(), Thursday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S3
        try {
            SubjectEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueSubjectThree = SubjectEditThree.getSelectedItem().toString();
                    Thursday.set(2, new DataThursday("3", "12:20-13:50", ThursdayValueSubjectThree, Thursday.get(2).audienceEdit.toString(), Thursday.get(2).educator.toString(), Thursday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S4
        try {
            SubjectEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueSubjectFour = SubjectEditFour.getSelectedItem().toString();
                    Thursday.set(3, new DataThursday("4", "14:00-15:30", ThursdayValueSubjectFour, Thursday.get(3).audienceEdit.toString(), Thursday.get(3).educator.toString(), Thursday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S5
        try {
            SubjectEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueSubjectFive = SubjectEditFive.getSelectedItem().toString();
                    Thursday.set(4, new DataThursday("5", "15:40-17:10", ThursdayValueSubjectFive, Thursday.get(4).audienceEdit.toString(), Thursday.get(4).educator.toString(), Thursday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S6
        try {
            SubjectEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueSubjectSix = SubjectEditSix.getSelectedItem().toString();
                    Thursday.set(5, new DataThursday("6", "17:30-19:00", ThursdayValueSubjectSix, Thursday.get(5).audienceEdit.toString(), Thursday.get(5).educator.toString(), Thursday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A1
        try {
            AudienceEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueAudienceOne = AudienceEditOne.getSelectedItem().toString();
                    Thursday.set(0, new DataThursday("1", "8:30-10:00", Thursday.get(0).subjectEdit.toString(), ThursdayValueAudienceOne, Thursday.get(0).educator.toString(), Thursday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A2
        try {
            AudienceEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueAudienceTwo = AudienceEditTwo.getSelectedItem().toString();
                    Thursday.set(1, new DataThursday("2", "10:10-11:40", Thursday.get(1).subjectEdit.toString(), ThursdayValueAudienceTwo, Thursday.get(1).educator.toString(), Thursday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A3
        try {
            AudienceEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueAudienceThree = AudienceEditThree.getSelectedItem().toString();
                    Thursday.set(2, new DataThursday("3", "12:20-13:50", Thursday.get(2).subjectEdit.toString(), ThursdayValueAudienceThree, Thursday.get(2).educator.toString(), Thursday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A4
        try {
            AudienceEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueAudienceFour = AudienceEditFour.getSelectedItem().toString();
                    Thursday.set(3, new DataThursday("4", "14:00-15:30", Thursday.get(3).subjectEdit.toString(), ThursdayValueAudienceFour, Thursday.get(3).educator.toString(), Thursday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A5
        try {
            AudienceEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueAudienceFive = AudienceEditFive.getSelectedItem().toString();
                    Thursday.set(4, new DataThursday("5", "15:40-17:10", Thursday.get(4).subjectEdit.toString(), ThursdayValueAudienceFive, Thursday.get(4).educator.toString(), Thursday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A6
        try {
            AudienceEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayValueAudienceSix = AudienceEditSix.getSelectedItem().toString();
                    Thursday.set(5, new DataThursday("6", "17:30-19:00", Thursday.get(5).subjectEdit.toString(), ThursdayValueAudienceSix, Thursday.get(5).educator.toString(), Thursday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E1
        try {
            EducatorEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayEducatorOne = EducatorEditOne.getSelectedItem().toString();
                    Thursday.set(0, new DataThursday("1", "8:30-10:00", Thursday.get(0).subjectEdit.toString(), Thursday.get(0).audienceEdit.toString(), ThursdayEducatorOne, Thursday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E2
        try {
            EducatorEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayEducatorTwo = EducatorEditTwo.getSelectedItem().toString();
                    Thursday.set(1, new DataThursday("2", "10:10-11:40", Thursday.get(1).subjectEdit.toString(), Thursday.get(1).audienceEdit.toString(), ThursdayEducatorTwo, Thursday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //E3
        try {
            EducatorEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayEducatorThree = EducatorEditThree.getSelectedItem().toString();
                    Thursday.set(2, new DataThursday("3", "12:20-13:50", Thursday.get(2).subjectEdit.toString(), Thursday.get(2).audienceEdit.toString(), ThursdayEducatorThree, Thursday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E4
        try {
            EducatorEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayEducatorFour = EducatorEditFour.getSelectedItem().toString();
                    Thursday.set(3, new DataThursday("4", "14:00-15:30", Thursday.get(3).subjectEdit.toString(), Thursday.get(3).audienceEdit.toString(), ThursdayEducatorFour, Thursday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E5
        try {
            EducatorEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayEducatorFive = EducatorEditFive.getSelectedItem().toString();
                    Thursday.set(4, new DataThursday("5", "15:40-17:10", Thursday.get(4).subjectEdit.toString(), Thursday.get(4).audienceEdit.toString(), ThursdayEducatorFive, Thursday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E6
        try {
            EducatorEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    ThursdayEducatorSix = EducatorEditSix.getSelectedItem().toString();
                    Thursday.set(5, new DataThursday("6", "17:30-19:00", Thursday.get(5).subjectEdit.toString(), Thursday.get(5).audienceEdit.toString(), ThursdayEducatorSix, Thursday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
    }
    public void friday_fill() {
        rb_lecture = findViewById(R.id.rb_lecture_friday);
        rb_labwork = findViewById(R.id.rb_labwork_friday);
        rb_practice = findViewById(R.id.rb_practice_friday);
        rb_lectureTwo = findViewById(R.id.rb_lectureTwo_friday);
        rb_labworkTwo = findViewById(R.id.rb_labworkTwo_friday);
        rb_practiceTwo = findViewById(R.id.rb_practiceTwo_friday);
        rb_lectureThree = findViewById(R.id.rb_lectureThree_friday);
        rb_labworkThree = findViewById(R.id.rb_labworkThree_friday);
        rb_practiceThree = findViewById(R.id.rb_practiceThree_friday);
        rb_lectureFour = findViewById(R.id.rb_lectureFour_friday);
        rb_labworkFour = findViewById(R.id.rb_labworkFour_friday);
        rb_practiceFour = findViewById(R.id.rb_practiceFour_friday);
        rb_lectureFive = findViewById(R.id.rb_lectureFive_friday);
        rb_labworkFive = findViewById(R.id.rb_labworkFive_friday);
        rb_practiceFive = findViewById(R.id.rb_practiceFive_friday);
        rb_lectureSix = findViewById(R.id.rb_lectureSix_friday);
        rb_labworkSix = findViewById(R.id.rb_labworkSix_friday);
        rb_practiceSix = findViewById(R.id.rb_practiceSix_friday);
        NumberOne = findViewById(R.id.number_friday);
        NumberTwo = findViewById(R.id.numberTwo_friday);
        NumberThree = findViewById(R.id.numberThree_friday);
        NumberFour = findViewById(R.id.numberFour_friday);
        NumberFive = findViewById(R.id.numberFive_friday);
        NumberSix = findViewById(R.id.numberSix_friday);
        TimeOne = findViewById(R.id.time_friday);
        TimeTwo = findViewById(R.id.timeTwo_friday);
        TimeThree = findViewById(R.id.timeThree_friday);
        TimeFour = findViewById(R.id.timeFour_friday);
        TimeFive = findViewById(R.id.timeFive_friday);
        TimeSix = findViewById(R.id.timeSix_friday);
        SubjectEditOne = findViewById(R.id.subject_edit_friday);
        SubjectEditTwo = findViewById(R.id.subject_editTwo_friday);
        SubjectEditThree = findViewById(R.id.subject_editThree_friday);
        SubjectEditFour = findViewById(R.id.subject_editFour_friday);
        SubjectEditFive = findViewById(R.id.subject_editFive_friday);
        SubjectEditSix = findViewById(R.id.subject_editSix_friday);
        AudienceEditOne = findViewById(R.id.audience_edit_friday);
        AudienceEditTwo = findViewById(R.id.audience_editTwo_friday);
        AudienceEditThree = findViewById(R.id.audience_editThree_friday);
        AudienceEditFour = findViewById(R.id.audience_editFour_friday);
        AudienceEditFive = findViewById(R.id.audience_editFive_friday);
        AudienceEditSix = findViewById(R.id.audience_editSix_friday);
        EducatorEditOne = findViewById(R.id.educator_edit_friday);
        EducatorEditTwo = findViewById(R.id.educator_editTwo_friday);
        EducatorEditThree = findViewById(R.id.educator_editThree_friday);
        EducatorEditFour = findViewById(R.id.educator_editFour_friday);
        EducatorEditFive = findViewById(R.id.educator_editFive_friday);
        EducatorEditSix = findViewById(R.id.educator_editSix_friday);
        typeEditOne_friday = findViewById(R.id.typeEdit_friday);
        typeEditTwo_friday = findViewById(R.id.typeEditTwo_friday);
        typeEditThree_friday = findViewById(R.id.typeEditThree_friday);
        typeEditFour_friday = findViewById(R.id.typeEditFour_friday);
        typeEditFive_friday = findViewById(R.id.typeEditFive_friday);
        typeEditSix_friday = findViewById(R.id.typeEditSix_friday);

        DataFriday();
        NumberOne.setText(Friday.get(0).idcards.toString());
        NumberTwo.setText(Friday.get(1).idcards.toString());
        NumberThree.setText(Friday.get(2).idcards.toString());
        NumberFour.setText(Friday.get(3).idcards.toString());
        NumberFive.setText(Friday.get(4).idcards.toString());
        NumberSix.setText(Friday.get(5).idcards.toString());
        TimeOne.setText(Friday.get(0).timelesson.toString());
        TimeTwo.setText(Friday.get(1).timelesson.toString());
        TimeThree.setText(Friday.get(2).timelesson.toString());
        TimeFour.setText(Friday.get(3).timelesson.toString());
        TimeFive.setText(Friday.get(4).timelesson.toString());
        TimeSix.setText(Friday.get(5).timelesson.toString());
        ArrayAdapter<String> subSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, subject_list);
        subSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> audSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, audience_list);
        audSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> eduSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, educator_list);
        eduSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        SubjectEditOne.setAdapter(subSpinnerArrayAdapter);
        SubjectEditTwo.setAdapter(subSpinnerArrayAdapter);
        SubjectEditThree.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFour.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFive.setAdapter(subSpinnerArrayAdapter);
        SubjectEditSix.setAdapter(subSpinnerArrayAdapter);
        AudienceEditOne.setAdapter(audSpinnerArrayAdapter);
        AudienceEditTwo.setAdapter(audSpinnerArrayAdapter);
        AudienceEditThree.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFour.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFive.setAdapter(audSpinnerArrayAdapter);
        AudienceEditSix.setAdapter(audSpinnerArrayAdapter);
        EducatorEditOne.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditTwo.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditThree.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFour.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFive.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditSix.setAdapter(eduSpinnerArrayAdapter);

        TextView copy_downOne = findViewById(R.id.copy_downOne_friday);
        TextView copy_downTwo = findViewById(R.id.copy_downTwo_friday);
        TextView copy_downThree = findViewById(R.id.copy_downThree_friday);
        TextView copy_downFour = findViewById(R.id.copy_downFour_friday);
        TextView copy_downFive = findViewById(R.id.copy_downFive_friday);
        TextView copy_upTwo= findViewById(R.id.copy_upTwo_friday);
        TextView copy_upThree= findViewById(R.id.copy_upThree_friday);
        TextView copy_upFour= findViewById(R.id.copy_upFour_friday);
        TextView copy_upFive= findViewById(R.id.copy_upFive_friday);
        TextView copy_upSix= findViewById(R.id.copy_upSix_friday);
        TextView clearOne = findViewById(R.id.clear_cardOne_friday);
        TextView clearTwo = findViewById(R.id.clear_cardTwo_friday);
        TextView clearThree = findViewById(R.id.clear_cardThree_friday);
        TextView clearFour = findViewById(R.id.clear_cardFour_friday);
        TextView clearFive = findViewById(R.id.clear_cardFive_friday);
        TextView clearSix = findViewById(R.id.clear_cardSix_friday);


        typeEditOne_friday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonOne = typeEditOne_friday.indexOfChild(findViewById(typeEditOne_friday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditOne_friday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    FridayTypeLessonOne = radioButton.getText().toString();
                }
                else { FridayTypeLessonOne = "";}

            }
        });

        typeEditTwo_friday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonTwo = typeEditTwo_friday.indexOfChild(findViewById(typeEditTwo_friday.getCheckedRadioButtonId()));
                int IdRadioButtonTwo = typeEditTwo_friday.getCheckedRadioButtonId();
                RadioButton radioButtonTwo = findViewById(IdRadioButtonTwo);
                if (radioButtonTwo != null) {
                    FridayTypeLessonTwo = radioButtonTwo.getText().toString();
                }
                else { FridayTypeLessonTwo = "";}
            }
        });

        typeEditThree_friday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonThree = typeEditThree_friday.indexOfChild(findViewById(typeEditThree_friday.getCheckedRadioButtonId()));
                int IdRadioButtonThree = typeEditThree_friday.getCheckedRadioButtonId();
                RadioButton radioButtonThree = findViewById(IdRadioButtonThree);
                if (radioButtonThree != null) {
                    FridayTypeLessonThree = radioButtonThree.getText().toString();
                }
                else { FridayTypeLessonThree = "";}
            }
        });

        typeEditFour_friday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFour = typeEditFour_friday.indexOfChild(findViewById(typeEditFour_friday.getCheckedRadioButtonId()));
                int IdRadioButtonFour = typeEditFour_friday.getCheckedRadioButtonId();
                RadioButton radioButtonFour = findViewById(IdRadioButtonFour);
                if (radioButtonFour != null) {
                    FridayTypeLessonFour = radioButtonFour.getText().toString();
                }
                else { FridayTypeLessonFour = "";}
            }
        });

        typeEditFive_friday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFive = typeEditFive_friday.indexOfChild(findViewById(typeEditFive_friday.getCheckedRadioButtonId()));
                int IdRadioButtonFive = typeEditFive_friday.getCheckedRadioButtonId();
                RadioButton radioButtonFive = findViewById(IdRadioButtonFive);
                if (radioButtonFive != null) {
                    FridayTypeLessonFive = radioButtonFive.getText().toString();
                }
                else { FridayTypeLessonFive = "";}
            }
        });
        typeEditSix_friday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonSix = typeEditSix_friday.indexOfChild(findViewById(typeEditSix_friday.getCheckedRadioButtonId()));
                int IdRadioButtonSix = typeEditSix_friday.getCheckedRadioButtonId();
                RadioButton radioButtonSix = findViewById(IdRadioButtonSix);
                if (radioButtonSix != null) {
                    FridayTypeLessonSix = radioButtonSix.getText().toString();
                }
                else { FridayTypeLessonSix = "";}
            }
        });

        copy_downOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditTwo.setSelection(subject_list.indexOf(Friday.get(0).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Friday.get(0).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Friday.get(0).educator));
                switch (IdRadioButtonOne){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditThree.setSelection(subject_list.indexOf(Friday.get(1).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Friday.get(1).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Friday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFour.setSelection(subject_list.indexOf(Friday.get(2).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Friday.get(2).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Friday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });
        copy_downFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFive.setSelection(subject_list.indexOf(Friday.get(3).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Friday.get(3).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Friday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(subject_list.indexOf(Friday.get(4).subjectEdit));
                AudienceEditSix.setSelection(audience_list.indexOf(Friday.get(4).audienceEdit));
                EducatorEditSix.setSelection(educator_list.indexOf(Friday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureSix.setChecked(true);
                        break;
                    case 1:
                        rb_labworkSix.setChecked(true);
                        break;
                    case 2:
                        rb_practiceSix.setChecked(true);
                        break;
                }
            }
        });

        copy_upTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditOne.setSelection(subject_list.indexOf(Friday.get(1).subjectEdit));
                AudienceEditOne.setSelection(audience_list.indexOf(Friday.get(1).audienceEdit));
                EducatorEditOne.setSelection(educator_list.indexOf(Friday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lecture.setChecked(true);
                        break;
                    case 1:
                        rb_labwork.setChecked(true);
                        break;
                    case 2:
                        rb_practice.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditTwo.setSelection(subject_list.indexOf(Friday.get(2).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Friday.get(2).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Friday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditThree.setSelection(subject_list.indexOf(Friday.get(3).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Friday.get(3).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Friday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFour.setSelection(subject_list.indexOf(Friday.get(4).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Friday.get(4).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Friday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFive.setSelection(subject_list.indexOf(Friday.get(5).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Friday.get(5).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Friday.get(5).educator));
                switch (IdRadioButtonSix){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });



        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditOne.setSelection(0);
                AudienceEditOne.setSelection(0);
                EducatorEditOne.setSelection(0);
                typeEditOne_friday.clearCheck();
            }
        });

        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditTwo.setSelection(0);
                AudienceEditTwo.setSelection(0);
                EducatorEditTwo.setSelection(0);
                typeEditTwo_friday.clearCheck();
            }
        });

        clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditThree.setSelection(0);
                AudienceEditThree.setSelection(0);
                EducatorEditThree.setSelection(0);
                typeEditThree_friday.clearCheck();
            }
        });

        clearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFour.setSelection(0);
                AudienceEditFour.setSelection(0);
                EducatorEditFour.setSelection(0);
                typeEditFour_friday.clearCheck();
            }
        });

        clearFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFive.setSelection(0);
                AudienceEditFive.setSelection(0);
                EducatorEditFive.setSelection(0);
                typeEditFive_friday.clearCheck();
            }
        });

        clearSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(0);
                AudienceEditSix.setSelection(0);
                EducatorEditSix.setSelection(0);
                typeEditSix_friday.clearCheck();
            }
        });


        SubjectEditOne.setSelection(subject_list.indexOf(Friday.get(0).subjectEdit.toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Friday.get(1).subjectEdit.toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Friday.get(2).subjectEdit.toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Friday.get(3).subjectEdit.toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Friday.get(4).subjectEdit.toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Friday.get(5).subjectEdit.toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Friday.get(0).audienceEdit.toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Friday.get(1).audienceEdit.toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Friday.get(2).audienceEdit.toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Friday.get(3).audienceEdit.toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Friday.get(4).audienceEdit.toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Friday.get(5).audienceEdit.toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Friday.get(0).educator.toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Friday.get(1).educator.toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Friday.get(2).educator.toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Friday.get(3).educator.toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Friday.get(4).educator.toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Friday.get(5).educator.toString()));

        if (Friday.get(0).typelesson.toString().equals("Лекция")) {
            rb_lecture.setChecked(true);
        } else if (Friday.get(0).typelesson.toString().equals("Лаб./раб.")) {
            rb_labwork.setChecked(true);
        } else if (Friday.get(0).typelesson.toString().equals("Практика")) {
            rb_practice.setChecked(true);
        }
        if (Friday.get(1).typelesson.toString().equals("Лекция")) {
            rb_lectureTwo.setChecked(true);
        } else if (Friday.get(1).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkTwo.setChecked(true);
        } else if (Friday.get(1).typelesson.toString().equals("Практика")) {
            rb_practiceTwo.setChecked(true);
        }
        if (Friday.get(2).typelesson.toString().equals("Лекция")) {
            rb_lectureThree.setChecked(true);
        } else if (Friday.get(2).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkThree.setChecked(true);
        } else if (Friday.get(2).typelesson.toString().equals("Практика")) {
            rb_practiceThree.setChecked(true);
        }
        if (Friday.get(3).typelesson.toString().equals("Лекция")) {
            rb_lectureFour.setChecked(true);
        } else if (Friday.get(3).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFour.setChecked(true);
        } else if (Friday.get(3).typelesson.toString().equals("Практика")) {
            rb_practiceFour.setChecked(true);
        }
        if (Friday.get(4).typelesson.toString().equals("Лекция")) {
            rb_lectureFive.setChecked(true);
        } else if (Friday.get(4).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFive.setChecked(true);
        } else if (Friday.get(4).typelesson.toString().equals("Практика")) {
            rb_practiceFive.setChecked(true);
        }
        if (Friday.get(5).typelesson.toString().equals("Лекция")) {
            rb_lectureSix.setChecked(true);
        } else if (Friday.get(5).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkSix.setChecked(true);
        } else if (Friday.get(5).typelesson.toString().equals("Практика")) {
            rb_practiceSix.setChecked(true);
        }

        //S1
          try {
            SubjectEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueSubjectOne = SubjectEditOne.getSelectedItem().toString();
                    Friday.set(0, new DataFriday("1", "8:30-10:00", FridayValueSubjectOne, Friday.get(0).audienceEdit.toString(), Friday.get(0).educator.toString(), Friday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S2
        try {
            SubjectEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueSubjectTwo = SubjectEditTwo.getSelectedItem().toString();
                    Friday.set(1, new DataFriday("2", "10:10-11:40", FridayValueSubjectTwo, Friday.get(1).audienceEdit.toString(), Friday.get(1).educator.toString(), Friday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S3
        try {
            SubjectEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueSubjectThree = SubjectEditThree.getSelectedItem().toString();
                    Friday.set(2, new DataFriday("3", "12:20-13:50", FridayValueSubjectThree, Friday.get(2).audienceEdit.toString(), Friday.get(2).educator.toString(), Friday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S4
        try {
            SubjectEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueSubjectFour = SubjectEditFour.getSelectedItem().toString();
                    Friday.set(3, new DataFriday("4", "14:00-15:30", FridayValueSubjectFour, Friday.get(3).audienceEdit.toString(), Friday.get(3).educator.toString(), Friday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S5
        try {
            SubjectEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueSubjectFive = SubjectEditFive.getSelectedItem().toString();
                    Friday.set(4, new DataFriday("5", "15:40-17:10", FridayValueSubjectFive, Friday.get(4).audienceEdit.toString(), Friday.get(4).educator.toString(), Friday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S6
        try {
            SubjectEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueSubjectSix = SubjectEditSix.getSelectedItem().toString();
                    Friday.set(5, new DataFriday("6", "17:30-19:00", FridayValueSubjectSix, Friday.get(5).audienceEdit.toString(), Friday.get(5).educator.toString(), Friday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A1
        try {
            AudienceEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueAudienceOne = AudienceEditOne.getSelectedItem().toString();
                    Friday.set(0, new DataFriday("1", "8:30-10:00", Friday.get(0).subjectEdit.toString(), FridayValueAudienceOne, Friday.get(0).educator.toString(), Friday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A2
        try {
            AudienceEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueAudienceTwo = AudienceEditTwo.getSelectedItem().toString();
                    Friday.set(1, new DataFriday("2", "10:10-11:40", Friday.get(1).subjectEdit.toString(), FridayValueAudienceTwo, Friday.get(1).educator.toString(), Friday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A3
        try {
            AudienceEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueAudienceThree = AudienceEditThree.getSelectedItem().toString();
                    Friday.set(2, new DataFriday("3", "12:20-13:50", Friday.get(2).subjectEdit.toString(), FridayValueAudienceThree, Friday.get(2).educator.toString(), Friday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A4
        try {
            AudienceEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueAudienceFour = AudienceEditFour.getSelectedItem().toString();
                    Friday.set(3, new DataFriday("4", "14:00-15:30", Friday.get(3).subjectEdit.toString(), FridayValueAudienceFour, Friday.get(3).educator.toString(), Friday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A5
        try {
            AudienceEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueAudienceFive = AudienceEditFive.getSelectedItem().toString();
                    Friday.set(4, new DataFriday("5", "15:40-17:10", Friday.get(4).subjectEdit.toString(), FridayValueAudienceFive, Friday.get(4).educator.toString(), Friday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A6
        try {
            AudienceEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayValueAudienceSix = AudienceEditSix.getSelectedItem().toString();
                    Friday.set(5, new DataFriday("6", "17:30-19:00", Friday.get(5).subjectEdit.toString(), FridayValueAudienceSix, Friday.get(5).educator.toString(), Friday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E1
        try {
            EducatorEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayEducatorOne = EducatorEditOne.getSelectedItem().toString();
                    Friday.set(0, new DataFriday("1", "8:30-10:00", Friday.get(0).subjectEdit.toString(), Friday.get(0).audienceEdit.toString(), FridayEducatorOne, Friday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E2
        try {
            EducatorEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayEducatorTwo = EducatorEditTwo.getSelectedItem().toString();
                    Friday.set(1, new DataFriday("2", "10:10-11:40", Friday.get(1).subjectEdit.toString(), Friday.get(1).audienceEdit.toString(), FridayEducatorTwo, Friday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //E3
        try {
            EducatorEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayEducatorThree = EducatorEditThree.getSelectedItem().toString();
                    Friday.set(2, new DataFriday("3", "12:20-13:50", Friday.get(2).subjectEdit.toString(), Friday.get(2).audienceEdit.toString(), FridayEducatorThree, Friday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E4
        try {
            EducatorEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayEducatorFour = EducatorEditFour.getSelectedItem().toString();
                    Friday.set(3, new DataFriday("4", "14:00-15:30", Friday.get(3).subjectEdit.toString(), Friday.get(3).audienceEdit.toString(), FridayEducatorFour, Friday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E5
        try {
            EducatorEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayEducatorFive = EducatorEditFive.getSelectedItem().toString();
                    Friday.set(4, new DataFriday("5", "15:40-17:10", Friday.get(4).subjectEdit.toString(), Friday.get(4).audienceEdit.toString(), FridayEducatorFive, Friday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E6
        try {
            EducatorEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    FridayEducatorSix = EducatorEditSix.getSelectedItem().toString();
                    Friday.set(5, new DataFriday("6", "17:30-19:00", Friday.get(5).subjectEdit.toString(), Friday.get(5).audienceEdit.toString(), FridayEducatorSix, Friday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
    }
    public void saturday_fill() {
        rb_lecture = findViewById(R.id.rb_lecture_saturday);
        rb_labwork = findViewById(R.id.rb_labwork_saturday);
        rb_practice = findViewById(R.id.rb_practice_saturday);
        rb_lectureTwo = findViewById(R.id.rb_lectureTwo_saturday);
        rb_labworkTwo = findViewById(R.id.rb_labworkTwo_saturday);
        rb_practiceTwo = findViewById(R.id.rb_practiceTwo_saturday);
        rb_lectureThree = findViewById(R.id.rb_lectureThree_saturday);
        rb_labworkThree = findViewById(R.id.rb_labworkThree_saturday);
        rb_practiceThree = findViewById(R.id.rb_practiceThree_saturday);
        rb_lectureFour = findViewById(R.id.rb_lectureFour_saturday);
        rb_labworkFour = findViewById(R.id.rb_labworkFour_saturday);
        rb_practiceFour = findViewById(R.id.rb_practiceFour_saturday);
        rb_lectureFive = findViewById(R.id.rb_lectureFive_saturday);
        rb_labworkFive = findViewById(R.id.rb_labworkFive_saturday);
        rb_practiceFive = findViewById(R.id.rb_practiceFive_saturday);
        rb_lectureSix = findViewById(R.id.rb_lectureSix_saturday);
        rb_labworkSix = findViewById(R.id.rb_labworkSix_saturday);
        rb_practiceSix = findViewById(R.id.rb_practiceSix_saturday);
        NumberOne = findViewById(R.id.number_saturday);
        NumberTwo = findViewById(R.id.numberTwo_saturday);
        NumberThree = findViewById(R.id.numberThree_saturday);
        NumberFour = findViewById(R.id.numberFour_saturday);
        NumberFive = findViewById(R.id.numberFive_saturday);
        NumberSix = findViewById(R.id.numberSix_saturday);
        TimeOne = findViewById(R.id.time_saturday);
        TimeTwo = findViewById(R.id.timeTwo_saturday);
        TimeThree = findViewById(R.id.timeThree_saturday);
        TimeFour = findViewById(R.id.timeFour_saturday);
        TimeFive = findViewById(R.id.timeFive_saturday);
        TimeSix = findViewById(R.id.timeSix_saturday);
        SubjectEditOne = findViewById(R.id.subject_edit_saturday);
        SubjectEditTwo = findViewById(R.id.subject_editTwo_saturday);
        SubjectEditThree = findViewById(R.id.subject_editThree_saturday);
        SubjectEditFour = findViewById(R.id.subject_editFour_saturday);
        SubjectEditFive = findViewById(R.id.subject_editFive_saturday);
        SubjectEditSix = findViewById(R.id.subject_editSix_saturday);
        AudienceEditOne = findViewById(R.id.audience_edit_saturday);
        AudienceEditTwo = findViewById(R.id.audience_editTwo_saturday);
        AudienceEditThree = findViewById(R.id.audience_editThree_saturday);
        AudienceEditFour = findViewById(R.id.audience_editFour_saturday);
        AudienceEditFive = findViewById(R.id.audience_editFive_saturday);
        AudienceEditSix = findViewById(R.id.audience_editSix_saturday);
        EducatorEditOne = findViewById(R.id.educator_edit_saturday);
        EducatorEditTwo = findViewById(R.id.educator_editTwo_saturday);
        EducatorEditThree = findViewById(R.id.educator_editThree_saturday);
        EducatorEditFour = findViewById(R.id.educator_editFour_saturday);
        EducatorEditFive = findViewById(R.id.educator_editFive_saturday);
        EducatorEditSix = findViewById(R.id.educator_editSix_saturday);
        typeEditOne_saturday = findViewById(R.id.typeEdit_saturday);
        typeEditTwo_saturday = findViewById(R.id.typeEditTwo_saturday);
        typeEditThree_saturday = findViewById(R.id.typeEditThree_saturday);
        typeEditFour_saturday = findViewById(R.id.typeEditFour_saturday);
        typeEditFive_saturday = findViewById(R.id.typeEditFive_saturday);
        typeEditSix_saturday = findViewById(R.id.typeEditSix_saturday);

        DataSaturday();
        NumberOne.setText(Saturday.get(0).idcards.toString());
        NumberTwo.setText(Saturday.get(1).idcards.toString());
        NumberThree.setText(Saturday.get(2).idcards.toString());
        NumberFour.setText(Saturday.get(3).idcards.toString());
        NumberFive.setText(Saturday.get(4).idcards.toString());
        NumberSix.setText(Saturday.get(5).idcards.toString());
        TimeOne.setText(Saturday.get(0).timelesson.toString());
        TimeTwo.setText(Saturday.get(1).timelesson.toString());
        TimeThree.setText(Saturday.get(2).timelesson.toString());
        TimeFour.setText(Saturday.get(3).timelesson.toString());
        TimeFive.setText(Saturday.get(4).timelesson.toString());
        TimeSix.setText(Saturday.get(5).timelesson.toString());
        ArrayAdapter<String> subSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, subject_list);
        subSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> audSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, audience_list);
        audSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> eduSpinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, educator_list);
        eduSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        SubjectEditOne.setAdapter(subSpinnerArrayAdapter);
        SubjectEditTwo.setAdapter(subSpinnerArrayAdapter);
        SubjectEditThree.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFour.setAdapter(subSpinnerArrayAdapter);
        SubjectEditFive.setAdapter(subSpinnerArrayAdapter);
        SubjectEditSix.setAdapter(subSpinnerArrayAdapter);
        AudienceEditOne.setAdapter(audSpinnerArrayAdapter);
        AudienceEditTwo.setAdapter(audSpinnerArrayAdapter);
        AudienceEditThree.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFour.setAdapter(audSpinnerArrayAdapter);
        AudienceEditFive.setAdapter(audSpinnerArrayAdapter);
        AudienceEditSix.setAdapter(audSpinnerArrayAdapter);
        EducatorEditOne.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditTwo.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditThree.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFour.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditFive.setAdapter(eduSpinnerArrayAdapter);
        EducatorEditSix.setAdapter(eduSpinnerArrayAdapter);

        TextView copy_downOne = findViewById(R.id.copy_downOne_saturday);
        TextView copy_downTwo = findViewById(R.id.copy_downTwo_saturday);
        TextView copy_downThree = findViewById(R.id.copy_downThree_saturday);
        TextView copy_downFour = findViewById(R.id.copy_downFour_saturday);
        TextView copy_downFive = findViewById(R.id.copy_downFive_saturday);
        TextView copy_upTwo= findViewById(R.id.copy_upTwo_saturday);
        TextView copy_upThree= findViewById(R.id.copy_upThree_saturday);
        TextView copy_upFour= findViewById(R.id.copy_upFour_saturday);
        TextView copy_upFive= findViewById(R.id.copy_upFive_saturday);
        TextView copy_upSix= findViewById(R.id.copy_upSix_saturday);
        TextView clearOne = findViewById(R.id.clear_cardOne_saturday);
        TextView clearTwo = findViewById(R.id.clear_cardTwo_saturday);
        TextView clearThree = findViewById(R.id.clear_cardThree_saturday);
        TextView clearFour = findViewById(R.id.clear_cardFour_saturday);
        TextView clearFive = findViewById(R.id.clear_cardFive_saturday);
        TextView clearSix = findViewById(R.id.clear_cardSix_saturday);

        typeEditOne_saturday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonOne = typeEditOne_saturday.indexOfChild(findViewById(typeEditOne_saturday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditOne_saturday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    SaturdayTypeLessonOne = radioButton.getText().toString();
                }
                else { SaturdayTypeLessonOne = "";}

            }
        });

        typeEditTwo_saturday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonTwo = typeEditTwo_saturday.indexOfChild(findViewById(typeEditTwo_saturday.getCheckedRadioButtonId()));
                int IdRadioButtonTwo = typeEditTwo_saturday.getCheckedRadioButtonId();
                RadioButton radioButtonTwo = findViewById(IdRadioButtonTwo);
                if (radioButtonTwo != null) {
                    SaturdayTypeLessonTwo = radioButtonTwo.getText().toString();
                }
                else { SaturdayTypeLessonTwo = "";}
            }
        });

        typeEditThree_saturday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonThree = typeEditThree_saturday.indexOfChild(findViewById(typeEditThree_saturday.getCheckedRadioButtonId()));
                int IdRadioButtonThree = typeEditThree_saturday.getCheckedRadioButtonId();
                RadioButton radioButtonThree = findViewById(IdRadioButtonThree);
                if (radioButtonThree != null) {
                    SaturdayTypeLessonThree = radioButtonThree.getText().toString();
                }
                else { SaturdayTypeLessonThree = "";}
            }
        });

        typeEditFour_saturday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFour = typeEditFour_saturday.indexOfChild(findViewById(typeEditFour_saturday.getCheckedRadioButtonId()));
                int IdRadioButtonFour = typeEditFour_saturday.getCheckedRadioButtonId();
                RadioButton radioButtonFour = findViewById(IdRadioButtonFour);
                if (radioButtonFour != null) {
                    SaturdayTypeLessonFour = radioButtonFour.getText().toString();
                }
                else { SaturdayTypeLessonFour = "";}
            }
        });

        typeEditFive_saturday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFive = typeEditFive_saturday.indexOfChild(findViewById(typeEditFive_saturday.getCheckedRadioButtonId()));
                int IdRadioButtonFive = typeEditFive_saturday.getCheckedRadioButtonId();
                RadioButton radioButtonFive = findViewById(IdRadioButtonFive);
                if (radioButtonFive != null) {
                    SaturdayTypeLessonFive = radioButtonFive.getText().toString();
                }
                else { SaturdayTypeLessonFive = "";}
            }
        });
        typeEditSix_saturday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonSix = typeEditSix_saturday.indexOfChild(findViewById(typeEditSix_saturday.getCheckedRadioButtonId()));
                int IdRadioButtonSix = typeEditSix_saturday.getCheckedRadioButtonId();
                RadioButton radioButtonSix = findViewById(IdRadioButtonSix);
                if (radioButtonSix != null) {
                    SaturdayTypeLessonSix = radioButtonSix.getText().toString();
                }
                else { SaturdayTypeLessonSix = "";}
            }
        });

        copy_downOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditTwo.setSelection(subject_list.indexOf(Saturday.get(0).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Saturday.get(0).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Saturday.get(0).educator));
                switch (IdRadioButtonOne){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditThree.setSelection(subject_list.indexOf(Saturday.get(1).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Saturday.get(1).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Saturday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_downThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFour.setSelection(subject_list.indexOf(Saturday.get(2).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Saturday.get(2).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Saturday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });
        copy_downFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditFive.setSelection(subject_list.indexOf(Saturday.get(3).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Saturday.get(3).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Saturday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }

                } catch   (NullPointerException e) {}
            }
        });

        copy_downFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditSix.setSelection(subject_list.indexOf(Saturday.get(4).subjectEdit));
                AudienceEditSix.setSelection(audience_list.indexOf(Saturday.get(4).audienceEdit));
                EducatorEditSix.setSelection(educator_list.indexOf(Saturday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureSix.setChecked(true);
                        break;
                    case 1:
                        rb_labworkSix.setChecked(true);
                        break;
                    case 2:
                        rb_practiceSix.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditOne.setSelection(subject_list.indexOf(Saturday.get(1).subjectEdit));
                AudienceEditOne.setSelection(audience_list.indexOf(Saturday.get(1).audienceEdit));
                EducatorEditOne.setSelection(educator_list.indexOf(Saturday.get(1).educator));
                switch (IdRadioButtonTwo){
                    case 0:
                        rb_lecture.setChecked(true);
                        break;
                    case 1:
                        rb_labwork.setChecked(true);
                        break;
                    case 2:
                        rb_practice.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SubjectEditTwo.setSelection(subject_list.indexOf(Saturday.get(2).subjectEdit));
                AudienceEditTwo.setSelection(audience_list.indexOf(Saturday.get(2).audienceEdit));
                EducatorEditTwo.setSelection(educator_list.indexOf(Saturday.get(2).educator));
                switch (IdRadioButtonThree){
                    case 0:
                        rb_lectureTwo.setChecked(true);
                        break;
                    case 1:
                        rb_labworkTwo.setChecked(true);
                        break;
                    case 2:
                        rb_practiceTwo.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditThree.setSelection(subject_list.indexOf(Saturday.get(3).subjectEdit));
                AudienceEditThree.setSelection(audience_list.indexOf(Saturday.get(3).audienceEdit));
                EducatorEditThree.setSelection(educator_list.indexOf(Saturday.get(3).educator));
                switch (IdRadioButtonFour){
                    case 0:
                        rb_lectureThree.setChecked(true);
                        break;
                    case 1:
                        rb_labworkThree.setChecked(true);
                        break;
                    case 2:
                        rb_practiceThree.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFour.setSelection(subject_list.indexOf(Saturday.get(4).subjectEdit));
                AudienceEditFour.setSelection(audience_list.indexOf(Saturday.get(4).audienceEdit));
                EducatorEditFour.setSelection(educator_list.indexOf(Saturday.get(4).educator));
                switch (IdRadioButtonFive){
                    case 0:
                        rb_lectureFour.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFour.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFour.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });

        copy_upSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                SubjectEditFive.setSelection(subject_list.indexOf(Saturday.get(5).subjectEdit));
                AudienceEditFive.setSelection(audience_list.indexOf(Saturday.get(5).audienceEdit));
                EducatorEditFive.setSelection(educator_list.indexOf(Saturday.get(5).educator));
                switch (IdRadioButtonSix){
                    case 0:
                        rb_lectureFive.setChecked(true);
                        break;
                    case 1:
                        rb_labworkFive.setChecked(true);
                        break;
                    case 2:
                        rb_practiceFive.setChecked(true);
                        break;
                }
                } catch   (NullPointerException e) {}
            }
        });



        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditOne.setSelection(0);
                AudienceEditOne.setSelection(0);
                EducatorEditOne.setSelection(0);
                typeEditOne_saturday.clearCheck();
            }
        });

        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditTwo.setSelection(0);
                AudienceEditTwo.setSelection(0);
                EducatorEditTwo.setSelection(0);
                typeEditTwo_saturday.clearCheck();
            }
        });

        clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditThree.setSelection(0);
                AudienceEditThree.setSelection(0);
                EducatorEditThree.setSelection(0);
                typeEditThree_saturday.clearCheck();
            }
        });

        clearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFour.setSelection(0);
                AudienceEditFour.setSelection(0);
                EducatorEditFour.setSelection(0);
                typeEditFour_saturday.clearCheck();
            }
        });

        clearFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFive.setSelection(0);
                AudienceEditFive.setSelection(0);
                EducatorEditFive.setSelection(0);
                typeEditFive_saturday.clearCheck();
            }
        });

        clearSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(0);
                AudienceEditSix.setSelection(0);
                EducatorEditSix.setSelection(0);
                typeEditSix_saturday.clearCheck();
            }
        });


        SubjectEditOne.setSelection(subject_list.indexOf(Saturday.get(0).subjectEdit.toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Saturday.get(1).subjectEdit.toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Saturday.get(2).subjectEdit.toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Saturday.get(3).subjectEdit.toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Saturday.get(4).subjectEdit.toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Saturday.get(5).subjectEdit.toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Saturday.get(0).audienceEdit.toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Saturday.get(1).audienceEdit.toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Saturday.get(2).audienceEdit.toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Saturday.get(3).audienceEdit.toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Saturday.get(4).audienceEdit.toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Saturday.get(5).audienceEdit.toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Saturday.get(0).educator.toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Saturday.get(1).educator.toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Saturday.get(2).educator.toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Saturday.get(3).educator.toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Saturday.get(4).educator.toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Saturday.get(5).educator.toString()));

        if (Saturday.get(0).typelesson.toString().equals("Лекция")) {
            rb_lecture.setChecked(true);
        } else if (Saturday.get(0).typelesson.toString().equals("Лаб./раб.")) {
            rb_labwork.setChecked(true);
        } else if (Saturday.get(0).typelesson.toString().equals("Практика")) {
            rb_practice.setChecked(true);
        }
        if (Saturday.get(1).typelesson.toString().equals("Лекция")) {
            rb_lectureTwo.setChecked(true);
        } else if (Saturday.get(1).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkTwo.setChecked(true);
        } else if (Saturday.get(1).typelesson.toString().equals("Практика")) {
            rb_practiceTwo.setChecked(true);
        }
        if (Saturday.get(2).typelesson.toString().equals("Лекция")) {
            rb_lectureThree.setChecked(true);
        } else if (Saturday.get(2).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkThree.setChecked(true);
        } else if (Saturday.get(2).typelesson.toString().equals("Практика")) {
            rb_practiceThree.setChecked(true);
        }
        if (Saturday.get(3).typelesson.toString().equals("Лекция")) {
            rb_lectureFour.setChecked(true);
        } else if (Saturday.get(3).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFour.setChecked(true);
        } else if (Saturday.get(3).typelesson.toString().equals("Практика")) {
            rb_practiceFour.setChecked(true);
        }
        if (Saturday.get(4).typelesson.toString().equals("Лекция")) {
            rb_lectureFive.setChecked(true);
        } else if (Saturday.get(4).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkFive.setChecked(true);
        } else if (Saturday.get(4).typelesson.toString().equals("Практика")) {
            rb_practiceFive.setChecked(true);
        }
        if (Saturday.get(5).typelesson.toString().equals("Лекция")) {
            rb_lectureSix.setChecked(true);
        } else if (Saturday.get(5).typelesson.toString().equals("Лаб./раб.")) {
            rb_labworkSix.setChecked(true);
        } else if (Saturday.get(5).typelesson.toString().equals("Практика")) {
            rb_practiceSix.setChecked(true);
        }

        //S1
        try {
            SubjectEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueSubjectOne =  SubjectEditOne.getSelectedItem().toString();
                    Saturday.set(0, new DataSaturday("1", "8:30-10:00", SaturdayValueSubjectOne, Saturday.get(0).audienceEdit.toString(), Saturday.get(0).educator.toString(), Saturday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S2
        try {
            SubjectEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueSubjectTwo = SubjectEditTwo.getSelectedItem().toString();
                    Saturday.set(1, new DataSaturday("2", "10:10-11:40", SaturdayValueSubjectTwo, Saturday.get(1).audienceEdit.toString(), Saturday.get(1).educator.toString(), Saturday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //S3
        try {
            SubjectEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueSubjectThree = SubjectEditThree.getSelectedItem().toString();
                    Saturday.set(2, new DataSaturday("3", "12:20-13:50", SaturdayValueSubjectThree, Saturday.get(2).audienceEdit.toString(), Saturday.get(2).educator.toString(), Saturday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S4
        try {
            SubjectEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueSubjectFour = SubjectEditFour.getSelectedItem().toString();
                    Saturday.set(3, new DataSaturday("4", "14:00-15:30", SaturdayValueSubjectFour, Saturday.get(3).audienceEdit.toString(), Saturday.get(3).educator.toString(), Saturday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S5
        try {
            SubjectEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueSubjectFive = SubjectEditFive.getSelectedItem().toString();
                    Saturday.set(4, new DataSaturday("5", "15:40-17:10", SaturdayValueSubjectFive, Saturday.get(4).audienceEdit.toString(), Saturday.get(4).educator.toString(), Saturday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //S6
        try {
            SubjectEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueSubjectSix = SubjectEditSix.getSelectedItem().toString();
                    Saturday.set(5, new DataSaturday("6", "17:30-19:00", SaturdayValueSubjectSix, Saturday.get(5).audienceEdit.toString(), Saturday.get(5).educator.toString(), Saturday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A1
        try {
            AudienceEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueAudienceOne = AudienceEditOne.getSelectedItem().toString();
                    Saturday.set(0, new DataSaturday("1", "8:30-10:00", Saturday.get(0).subjectEdit.toString(), SaturdayValueAudienceOne, Saturday.get(0).educator.toString(), Saturday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A2
        try {
            AudienceEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueAudienceTwo = AudienceEditTwo.getSelectedItem().toString();
                    Saturday.set(1, new DataSaturday("2", "10:10-11:40", Saturday.get(1).subjectEdit.toString(), SaturdayValueAudienceTwo, Saturday.get(1).educator.toString(), Saturday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //A3
        try {
            AudienceEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueAudienceThree = AudienceEditThree.getSelectedItem().toString();
                    Saturday.set(2, new DataSaturday("3", "12:20-13:50", Saturday.get(2).subjectEdit.toString(), SaturdayValueAudienceThree, Saturday.get(2).educator.toString(), Saturday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A4
        try {
            AudienceEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueAudienceFour = AudienceEditFour.getSelectedItem().toString();
                    Saturday.set(3, new DataSaturday("4", "14:00-15:30", Saturday.get(3).subjectEdit.toString(), SaturdayValueAudienceFour, Saturday.get(3).educator.toString(), Saturday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A5
        try {
            AudienceEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueAudienceFive = AudienceEditFive.getSelectedItem().toString();
                    Saturday.set(4, new DataSaturday("5", "15:40-17:10", Saturday.get(4).subjectEdit.toString(), SaturdayValueAudienceFive, Saturday.get(4).educator.toString(), Saturday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //A6
        try {
            AudienceEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayValueAudienceSix = AudienceEditSix.getSelectedItem().toString();
                    Saturday.set(5, new DataSaturday("6", "17:30-19:00", Saturday.get(5).subjectEdit.toString(), SaturdayValueAudienceSix, Saturday.get(5).educator.toString(), Saturday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E1
        try {
            EducatorEditOne.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayEducatorOne = EducatorEditOne.getSelectedItem().toString();
                    Saturday.set(0, new DataSaturday("1", "8:30-10:00", Saturday.get(0).subjectEdit.toString(), Saturday.get(0).audienceEdit.toString(), SaturdayEducatorOne, Saturday.get(0).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E2
        try {
            EducatorEditTwo.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayEducatorTwo = EducatorEditTwo.getSelectedItem().toString();
                    Saturday.set(1, new DataSaturday("2", "10:10-11:40", Saturday.get(1).subjectEdit.toString(), Saturday.get(1).audienceEdit.toString(), SaturdayEducatorTwo, Saturday.get(1).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }

        //E3
        try {
            EducatorEditThree.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayEducatorThree = EducatorEditThree.getSelectedItem().toString();
                    Saturday.set(2, new DataSaturday("3", "12:20-13:50", Saturday.get(2).subjectEdit.toString(), Saturday.get(2).audienceEdit.toString(), SaturdayEducatorThree, Saturday.get(2).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E4
        try {
            EducatorEditFour.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayEducatorFour = EducatorEditFour.getSelectedItem().toString();
                    Saturday.set(3, new DataSaturday("4", "14:00-15:30", Saturday.get(3).subjectEdit.toString(), Saturday.get(3).audienceEdit.toString(), SaturdayEducatorFour, Saturday.get(3).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E5
        try {
            EducatorEditFive.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayEducatorFive = EducatorEditFive.getSelectedItem().toString();
                    Saturday.set(4, new DataSaturday("5", "15:40-17:10", Saturday.get(4).subjectEdit.toString(), Saturday.get(4).audienceEdit.toString(), SaturdayEducatorFive, Saturday.get(4).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
        //E6
        try {
            EducatorEditSix.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    SaturdayEducatorSix = EducatorEditSix.getSelectedItem().toString();
                    Saturday.set(5, new DataSaturday("6", "17:30-19:00", Saturday.get(5).subjectEdit.toString(), Saturday.get(5).audienceEdit.toString(), SaturdayEducatorSix, Saturday.get(5).typelesson.toString()));
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }}); } catch (NullPointerException e) { }
    }

    void update_data(){
        MondayValueSubjectOne = "";
        MondayValueSubjectTwo = "";
        MondayValueSubjectThree = "";
        MondayValueSubjectFour = "";
        MondayValueSubjectFive = "";
        MondayValueSubjectSix = "";
        MondayValueAudienceOne = "";
        MondayValueAudienceTwo = "";
        MondayValueAudienceThree = "";
        MondayValueAudienceFour = "";
        MondayValueAudienceFive = "";
        MondayValueAudienceSix = "";
        MondayEducatorOne = "";
        MondayEducatorTwo = "";
        MondayEducatorThree = "";
        MondayEducatorFour = "";
        MondayEducatorFive = "";
        MondayEducatorSix = "";
        MondayTypeLessonOne = "";
        MondayTypeLessonTwo = "";
        MondayTypeLessonThree = "";
        MondayTypeLessonFour = "";
        MondayTypeLessonFive = "";
        MondayTypeLessonSix = "";
        TuesdayValueSubjectOne = "";
        TuesdayValueSubjectTwo = "";
        TuesdayValueSubjectThree = "";
        TuesdayValueSubjectFour = "";
        TuesdayValueSubjectFive = "";
        TuesdayValueSubjectSix = "";
        TuesdayValueAudienceOne = "";
        TuesdayValueAudienceTwo = "";
        TuesdayValueAudienceThree = "";
        TuesdayValueAudienceFour = "";
        TuesdayValueAudienceFive = "";
        TuesdayValueAudienceSix = "";
        TuesdayEducatorOne = "";
        TuesdayEducatorTwo = "";
        TuesdayEducatorThree = "";
        TuesdayEducatorFour = "";
        TuesdayEducatorFive = "";
        TuesdayEducatorSix = "";
        TuesdayTypeLessonOne = "";
        TuesdayTypeLessonTwo = "";
        TuesdayTypeLessonThree = "";
        TuesdayTypeLessonFour = "";
        TuesdayTypeLessonFive = "";
        TuesdayTypeLessonSix = "";
        WednesdayValueSubjectOne = "";
        WednesdayValueSubjectTwo = "";
        WednesdayValueSubjectThree = "";
        WednesdayValueSubjectFour = "";
        WednesdayValueSubjectFive = "";
        WednesdayValueSubjectSix = "";
        WednesdayValueAudienceOne = "";
        WednesdayValueAudienceTwo = "";
        WednesdayValueAudienceThree = "";
        WednesdayValueAudienceFour = "";
        WednesdayValueAudienceFive = "";
        WednesdayValueAudienceSix = "";
        WednesdayEducatorOne = "";
        WednesdayEducatorTwo = "";
        WednesdayEducatorThree = "";
        WednesdayEducatorFour = "";
        WednesdayEducatorFive = "";
        WednesdayEducatorSix = "";
        WednesdayTypeLessonOne = "";
        WednesdayTypeLessonTwo = "";
        WednesdayTypeLessonThree = "";
        WednesdayTypeLessonFour = "";
        WednesdayTypeLessonFive = "";
        WednesdayTypeLessonSix = "";

        ThursdayValueSubjectOne = "";
        ThursdayValueSubjectTwo = "";
        ThursdayValueSubjectThree = "";
        ThursdayValueSubjectFour = "";
        ThursdayValueSubjectFive = "";
        ThursdayValueSubjectSix = "";
        ThursdayValueAudienceOne = "";
        ThursdayValueAudienceTwo = "";
        ThursdayValueAudienceThree = "";
        ThursdayValueAudienceFour = "";
        ThursdayValueAudienceFive = "";
        ThursdayValueAudienceSix = "";
        ThursdayEducatorOne = "";
        ThursdayEducatorTwo = "";
        ThursdayEducatorThree = "";
        ThursdayEducatorFour = "";
        ThursdayEducatorFive = "";
        ThursdayEducatorSix = "";
        ThursdayTypeLessonOne = "";
        ThursdayTypeLessonTwo = "";
        ThursdayTypeLessonThree = "";
        ThursdayTypeLessonFour = "";
        ThursdayTypeLessonFive = "";
        ThursdayTypeLessonSix = "";

        FridayValueSubjectOne = "";
        FridayValueSubjectTwo = "";
        FridayValueSubjectThree = "";
        FridayValueSubjectFour = "";
        FridayValueSubjectFive = "";
        FridayValueSubjectSix = "";
        FridayValueAudienceOne = "";
        FridayValueAudienceTwo = "";
        FridayValueAudienceThree = "";
        FridayValueAudienceFour = "";
        FridayValueAudienceFive = "";
        FridayValueAudienceSix = "";
        FridayEducatorOne = "";
        FridayEducatorTwo = "";
        FridayEducatorThree = "";
        FridayEducatorFour = "";
        FridayEducatorFive = "";
        FridayEducatorSix = "";
        FridayTypeLessonOne = "";
        FridayTypeLessonTwo = "";
        FridayTypeLessonThree = "";
        FridayTypeLessonFour = "";
        FridayTypeLessonFive = "";
        FridayTypeLessonSix = "";

        SaturdayValueSubjectOne = "";
        SaturdayValueSubjectTwo = "";
        SaturdayValueSubjectThree = "";
        SaturdayValueSubjectFour = "";
        SaturdayValueSubjectFive = "";
        SaturdayValueSubjectSix = "";
        SaturdayValueAudienceOne = "";
        SaturdayValueAudienceTwo = "";
        SaturdayValueAudienceThree = "";
        SaturdayValueAudienceFour = "";
        SaturdayValueAudienceFive = "";
        SaturdayValueAudienceSix = "";
        SaturdayEducatorOne = "";
        SaturdayEducatorTwo = "";
        SaturdayEducatorThree = "";
        SaturdayEducatorFour = "";
        SaturdayEducatorFive = "";
        SaturdayEducatorSix = "";
        SaturdayTypeLessonOne = "";
        SaturdayTypeLessonTwo = "";
        SaturdayTypeLessonThree = "";
        SaturdayTypeLessonFour = "";
        SaturdayTypeLessonFive = "";
        SaturdayTypeLessonSix = "";
        typeEditOne_monday.clearCheck();
        typeEditTwo_monday.clearCheck();
        typeEditThree_monday.clearCheck();
        typeEditFour_monday.clearCheck();
        typeEditFive_monday.clearCheck();
        typeEditSix_monday.clearCheck();
        typeEditOne_tuesday.clearCheck();
        typeEditTwo_tuesday.clearCheck();
        typeEditThree_tuesday.clearCheck();
        typeEditFour_tuesday.clearCheck();
        typeEditFive_tuesday.clearCheck();
        typeEditSix_tuesday.clearCheck();
        typeEditOne_wednesday.clearCheck();
        typeEditTwo_wednesday.clearCheck();
        typeEditThree_wednesday.clearCheck();
        typeEditFour_wednesday.clearCheck();
        typeEditFive_wednesday.clearCheck();
        typeEditSix_wednesday.clearCheck();
        typeEditOne_thursday.clearCheck();
        typeEditTwo_thursday.clearCheck();
        typeEditThree_thursday.clearCheck();
        typeEditFour_thursday.clearCheck();
        typeEditFive_thursday.clearCheck();
        typeEditSix_thursday.clearCheck();
        typeEditOne_friday.clearCheck();
        typeEditTwo_friday.clearCheck();
        typeEditThree_friday.clearCheck();
        typeEditFour_friday.clearCheck();
        typeEditFive_friday.clearCheck();
        typeEditSix_friday.clearCheck();
        typeEditOne_saturday.clearCheck();
        typeEditTwo_saturday.clearCheck();
        typeEditThree_saturday.clearCheck();
        typeEditFour_saturday.clearCheck();
        typeEditFive_saturday.clearCheck();
        typeEditSix_saturday.clearCheck();
        start(position_week);

    }

    public void start(Integer select_week) {

        position_week=select_week;
        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        //1 занятие
        Cursor cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idSubjectEditOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                MondayValueSubjectOne = cursor.getString(idSubjectEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idAudienceEditOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                MondayValueAudienceOne = cursor.getString(idAudienceEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                MondayEducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                MondayTypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2) + "));", null);
        try {
            int idSubjectEditTwo = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                MondayValueSubjectTwo = cursor.getString(idSubjectEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2) + "));", null);
        try {
            int idAudienceEditTwo = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                MondayValueAudienceTwo = cursor.getString(idAudienceEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                MondayEducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                MondayTypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3) + "));", null);
        try {
            int idSubjectEditThree = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                MondayValueSubjectThree = cursor.getString(idSubjectEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3) + "));", null);
        try {
            int idAudienceEditThree = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                MondayValueAudienceThree = cursor.getString(idAudienceEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                MondayEducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                MondayTypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4) + "));", null);
        try {
            int idSubjectEditFour = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                MondayValueSubjectFour = cursor.getString(idSubjectEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4) + "));", null);
        try {
            int idAudienceEditFour = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                MondayValueAudienceFour = cursor.getString(idAudienceEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                MondayEducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                MondayTypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5) + "));", null);
        try {
            int idSubjectEditFive = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                MondayValueSubjectFive = cursor.getString(idSubjectEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5) + "));", null);
        try {
            int idAudienceEditFive = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                MondayValueAudienceFive = cursor.getString(idAudienceEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                MondayEducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                MondayTypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6) + "));", null);
        try {
            int idSubjectEditSix = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                MondayValueSubjectSix = cursor.getString(idSubjectEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6) + "));", null);
        try {
            int idAudienceEditSix = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                MondayValueAudienceSix = cursor.getString(idAudienceEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                MondayEducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                MondayTypeLessonSix = cursor.getString(idTypeLessonSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7) + "));", null);
        try {
            int idSubjectEditOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                TuesdayValueSubjectOne = cursor.getString(idSubjectEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7) + "));", null);
        try {
            int idAudienceEditOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                TuesdayValueAudienceOne = cursor.getString(idAudienceEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                TuesdayEducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TuesdayTypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8) + "));", null);
        try {
            int idSubjectEditTwo = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                TuesdayValueSubjectTwo = cursor.getString(idSubjectEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8) + "));", null);
        try {
            int idAudienceEditTwo = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                TuesdayValueAudienceTwo = cursor.getString(idAudienceEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                TuesdayEducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TuesdayTypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9) + "));", null);
        try {
            int idSubjectEditThree = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                TuesdayValueSubjectThree = cursor.getString(idSubjectEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9) + "));", null);
        try {
            int idAudienceEditThree = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                TuesdayValueAudienceThree = cursor.getString(idAudienceEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                TuesdayEducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TuesdayTypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10) + "));", null);
        try {
            int idSubjectEditFour = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                TuesdayValueSubjectFour = cursor.getString(idSubjectEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10) + "));", null);
        try {
            int idAudienceEditFour = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                TuesdayValueAudienceFour = cursor.getString(idAudienceEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                TuesdayEducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TuesdayTypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11) + "));", null);
        try {
            int idSubjectEditFive = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                TuesdayValueSubjectFive = cursor.getString(idSubjectEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11) + "));", null);
        try {
            int idAudienceEditFive = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                TuesdayValueAudienceFive = cursor.getString(idAudienceEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                TuesdayEducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TuesdayTypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12) + "));", null);
        try {
            int idSubjectEditSix = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                TuesdayValueSubjectSix = cursor.getString(idSubjectEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12) + "));", null);
        try {
            int idAudienceEditSix = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                TuesdayValueAudienceSix = cursor.getString(idAudienceEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                TuesdayEducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TuesdayTypeLessonSix = cursor.getString(idTypeLessonSix);
            }
        } finally {
            cursor.close();
        }
//Среда
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13) + "));", null);
        try {
            int idSubjectEditOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                WednesdayValueSubjectOne = cursor.getString(idSubjectEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13) + "));", null);
        try {
            int idAudienceEditOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                WednesdayValueAudienceOne = cursor.getString(idAudienceEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                WednesdayEducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                WednesdayTypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14) + "));", null);
        try {
            int idSubjectEditTwo = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                WednesdayValueSubjectTwo = cursor.getString(idSubjectEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14) + "));", null);
        try {
            int idAudienceEditTwo = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                WednesdayValueAudienceTwo = cursor.getString(idAudienceEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                WednesdayEducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                WednesdayTypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15) + "));", null);
        try {
            int idSubjectEditThree = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                WednesdayValueSubjectThree = cursor.getString(idSubjectEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15) + "));", null);
        try {
            int idAudienceEditThree = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                WednesdayValueAudienceThree = cursor.getString(idAudienceEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                WednesdayEducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                WednesdayTypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16) + "));", null);
        try {
            int idSubjectEditFour = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                WednesdayValueSubjectFour = cursor.getString(idSubjectEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16) + "));", null);
        try {
            int idAudienceEditFour = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                WednesdayValueAudienceFour = cursor.getString(idAudienceEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                WednesdayEducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                WednesdayTypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17) + "));", null);
        try {
            int idSubjectEditFive = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                WednesdayValueSubjectFive = cursor.getString(idSubjectEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17) + "));", null);
        try {
            int idAudienceEditFive = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                WednesdayValueAudienceFive = cursor.getString(idAudienceEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                WednesdayEducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                WednesdayTypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18) + "));", null);
        try {
            int idSubjectEditSix = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                WednesdayValueSubjectSix = cursor.getString(idSubjectEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18) + "));", null);
        try {
            int idAudienceEditSix = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                WednesdayValueAudienceSix = cursor.getString(idAudienceEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                WednesdayEducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                WednesdayTypeLessonSix = cursor.getString(idTypeLessonSix);
            }
        } finally {
            cursor.close();
        }
//четверг
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19) + "));", null);
        try {
            int idSubjectEditOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                ThursdayValueSubjectOne = cursor.getString(idSubjectEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19) + "));", null);
        try {
            int idAudienceEditOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                ThursdayValueAudienceOne = cursor.getString(idAudienceEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                ThursdayEducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                ThursdayTypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20) + "));", null);
        try {
            int idSubjectEditTwo = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                ThursdayValueSubjectTwo = cursor.getString(idSubjectEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20) + "));", null);
        try {
            int idAudienceEditTwo = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                ThursdayValueAudienceTwo = cursor.getString(idAudienceEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                ThursdayEducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                ThursdayTypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21) + "));", null);
        try {
            int idSubjectEditThree = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                ThursdayValueSubjectThree = cursor.getString(idSubjectEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21) + "));", null);
        try {
            int idAudienceEditThree = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                ThursdayValueAudienceThree = cursor.getString(idAudienceEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                ThursdayEducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                ThursdayTypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22) + "));", null);
        try {
            int idSubjectEditFour = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                ThursdayValueSubjectFour = cursor.getString(idSubjectEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22) + "));", null);
        try {
            int idAudienceEditFour = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                ThursdayValueAudienceFour = cursor.getString(idAudienceEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                ThursdayEducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                ThursdayTypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23) + "));", null);
        try {
            int idSubjectEditFive = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                ThursdayValueSubjectFive = cursor.getString(idSubjectEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23) + "));", null);
        try {
            int idAudienceEditFive = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                ThursdayValueAudienceFive = cursor.getString(idAudienceEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                ThursdayEducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                ThursdayTypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24) + "));", null);
        try {
            int idSubjectEditSix = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                ThursdayValueSubjectSix = cursor.getString(idSubjectEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24) + "));", null);
        try {
            int idAudienceEditSix = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                ThursdayValueAudienceSix = cursor.getString(idAudienceEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                ThursdayEducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                ThursdayTypeLessonSix = cursor.getString(idTypeLessonSix);
            }
        } finally {
            cursor.close();
        }

        //пятница
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25) + "));", null);
        try {
            int idSubjectEditOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                FridayValueSubjectOne = cursor.getString(idSubjectEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25) + "));", null);
        try {
            int idAudienceEditOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                FridayValueAudienceOne = cursor.getString(idAudienceEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                FridayEducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                FridayTypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26) + "));", null);
        try {
            int idSubjectEditTwo = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                FridayValueSubjectTwo = cursor.getString(idSubjectEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26) + "));", null);
        try {
            int idAudienceEditTwo = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                FridayValueAudienceTwo = cursor.getString(idAudienceEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                FridayEducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                FridayTypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27) + "));", null);
        try {
            int idSubjectEditThree = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                FridayValueSubjectThree = cursor.getString(idSubjectEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27) + "));", null);
        try {
            int idAudienceEditThree = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                FridayValueAudienceThree = cursor.getString(idAudienceEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                FridayEducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                FridayTypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28) + "));", null);
        try {
            int idSubjectEditFour = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                FridayValueSubjectFour = cursor.getString(idSubjectEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28) + "));", null);
        try {
            int idAudienceEditFour = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                FridayValueAudienceFour = cursor.getString(idAudienceEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                FridayEducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                FridayTypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29) + "));", null);
        try {
            int idSubjectEditFive = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                FridayValueSubjectFive = cursor.getString(idSubjectEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29) + "));", null);
        try {
            int idAudienceEditFive = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                FridayValueAudienceFive = cursor.getString(idAudienceEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                FridayEducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                FridayTypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30) + "));", null);
        try {
            int idSubjectEditSix = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                FridayValueSubjectSix = cursor.getString(idSubjectEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30) + "));", null);
        try {
            int idAudienceEditSix = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                FridayValueAudienceSix = cursor.getString(idAudienceEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                FridayEducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                FridayTypeLessonSix = cursor.getString(idTypeLessonSix);
            }
        } finally {
            cursor.close();
        }

        //суббота
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31) + "));", null);
        try {
            int idSubjectEditOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SaturdayValueSubjectOne = cursor.getString(idSubjectEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31) + "));", null);
        try {
            int idAudienceEditOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                SaturdayValueAudienceOne = cursor.getString(idAudienceEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                SaturdayEducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                SaturdayTypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32) + "));", null);
        try {
            int idSubjectEditTwo = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SaturdayValueSubjectTwo = cursor.getString(idSubjectEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32) + "));", null);
        try {
            int idAudienceEditTwo = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                SaturdayValueAudienceTwo = cursor.getString(idAudienceEditTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                SaturdayEducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                SaturdayTypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33) + "));", null);
        try {
            int idSubjectEditThree = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SaturdayValueSubjectThree = cursor.getString(idSubjectEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33) + "));", null);
        try {
            int idAudienceEditThree = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                SaturdayValueAudienceThree = cursor.getString(idAudienceEditThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                SaturdayEducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                SaturdayTypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34) + "));", null);
        try {
            int idSubjectEditFour = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SaturdayValueSubjectFour = cursor.getString(idSubjectEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34) + "));", null);
        try {
            int idAudienceEditFour = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                SaturdayValueAudienceFour = cursor.getString(idAudienceEditFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                SaturdayEducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                SaturdayTypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35) + "));", null);
        try {
            int idSubjectEditFive = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SaturdayValueSubjectFive = cursor.getString(idSubjectEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35) + "));", null);
        try {
            int idAudienceEditFive = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                SaturdayValueAudienceFive = cursor.getString(idAudienceEditFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                SaturdayEducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                SaturdayTypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36) + "));", null);
        try {
            int idSubjectEditSix = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SaturdayValueSubjectSix = cursor.getString(idSubjectEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36) + "));", null);
        try {
            int idAudienceEditSix = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                SaturdayValueAudienceSix = cursor.getString(idAudienceEditSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                SaturdayEducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                SaturdayTypeLessonSix = cursor.getString(idTypeLessonSix);
            }
        } finally {
            cursor.close();
        }

    }

    void filling_array_list(){
        SQLiteDatabase myDataBase = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT "+ subjects.subject +" FROM " + subjects.TABLE_NAME;
        Cursor cursor = myDataBase.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            subject_list.add(cursor.getString(0));
        }
        cursor.close();

        myDataBase = ScheduleDB.getReadableDatabase();
        searchQuery = "SELECT "+ audiences.audience +" FROM " + audiences.TABLE_NAME;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            audience_list.add(cursor.getString(0));
        }
        cursor.close();

        myDataBase = ScheduleDB.getReadableDatabase();
        searchQuery = "SELECT "+ educators.educator +" FROM " + educators.TABLE_NAME;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            educator_list.add(cursor.getString(0));
        }
        cursor.close();}





    public void onCreate(Bundle savedInstanceState) {

        ScheduleDB = new ScheduleDB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        switcher_save = findViewById(R.id.switcher_save);
        filling_array_list();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_editor_menu);
        toolbar.setOverflowIcon(drawable);

        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), MainActivity2.this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(6);

        radiobutton_class();

        final MaterialBetterSpinner spinner =  findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,  getResources().getStringArray(R.array.weeks));
        spinner.setAdapter(arrayAdapter);


        SharedPreferences settings = getSharedPreferences("choice_week", 0);
        Integer current_week = Integer.valueOf(settings.getString("position", "0"));

        spinner.setText(arrayAdapter.getItem(current_week));
        start(current_week);
       /* if (!(pagerAdapter == null)) {
            pagerAdapter.notifyDataSetChanged();
        }*/
        load_calls_schedule();
        switcher_save.setChecked(true);
        switcher_save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked==true){
                flag_autosave=1;
                }
                else {
                flag_autosave=0;
                }
            }
        });

        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);



       /* SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String hasVisited = sp.getString("hasVisited", "nope");
        if (hasVisited=="nope") {
            LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
            tabStrip.setEnabled(false);
            for (int i = 0; i < tabStrip.getChildCount(); i++) {
                tabStrip.getChildAt(i).setClickable(false);
            }

            new MaterialTapTargetPrompt.Builder(MainActivity2.this)
                    .setTarget(spinner)
                    .setPromptBackground(new CirclePromptBackground())
                    .setPromptFocal(new RectanglePromptFocal())
                    .setPrimaryText("Выбор недели)")
                    .setSecondaryText("Вы можете выбрать номер недели при редактировании расписания. В настройках также можете выбрать дату начала семестра для автоопределения текущей учебной недели")
                    .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170, 170, 255))
                    .setBackgroundColour(Color.rgb(100, 100, 255))
                    .setPrimaryTextColour(Color.rgb(255, 255, 255))
                    .setSecondaryTextColour(Color.rgb(255, 255, 255))
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            if (state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                new MaterialTapTargetPrompt.Builder(MainActivity2.this)
                                        .setTarget(R.id.switcher_save)
                                        .setPromptBackground(new CirclePromptBackground())
                                        .setPromptFocal(new RectanglePromptFocal())
                                        .setPrimaryText("Автосохранение расписания")
                                        .setSecondaryText("В активированном состоянии при смене учебной недели или выходе из редактора расписание сохраняется автоматически")
                                        .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170, 170, 255))
                                        .setBackgroundColour(Color.rgb(100, 100, 255))
                                        .setPrimaryTextColour(Color.rgb(255, 255, 255))
                                        .setSecondaryTextColour(Color.rgb(255, 255, 255))
                                        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                            public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {

                                                if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                                    new MaterialTapTargetPrompt.Builder(MainActivity2.this)
                                                            .setTarget(R.id.card_viewTwo)
                                                            .setPromptBackground(new RectanglePromptBackground())
                                                            .setPromptFocal(new RectanglePromptFocal())
                                                            .setPrimaryText("Редактирование занятия")
                                                            .setSecondaryText("Вы можете выбрать ранее добавленные учебный предмет, аудиторию, преподавателя, а так же тип занятия")
                                                            .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170, 170, 255))
                                                            .setBackgroundColour(Color.rgb(100, 100, 255))
                                                            .setPrimaryTextColour(Color.rgb(255, 255, 255))
                                                            .setSecondaryTextColour(Color.rgb(255, 255, 255))
                                                            .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                                                public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {

                                                                    if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                                                        new MaterialTapTargetPrompt.Builder(MainActivity2.this)
                                                                                .setTarget(R.id.clear_cardTwo_monday)
                                                                                .setPromptBackground(new CirclePromptBackground())
                                                                                .setPromptFocal(new CirclePromptFocal())
                                                                                .setPrimaryText("Очистка занятия")
                                                                                .setSecondaryText("Вы можете очистить выбранные данные занятия")
                                                                                .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170, 170, 255))
                                                                                .setBackgroundColour(Color.rgb(100, 100, 255))
                                                                                .setPrimaryTextColour(Color.rgb(255, 255, 255))
                                                                                .setSecondaryTextColour(Color.rgb(255, 255, 255))
                                                                                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                                                                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {

                                                                                        if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                                                                            new MaterialTapTargetPrompt.Builder(MainActivity2.this)
                                                                                                    .setTarget(R.id.copy_upTwo_monday)
                                                                                                    .setPromptBackground(new CirclePromptBackground())
                                                                                                    .setPromptFocal(new CirclePromptFocal())
                                                                                                    .setPrimaryText("Копирование данных занятия")
                                                                                                    .setSecondaryText("Копирование данных занятия предыдущему занятию")
                                                                                                    .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170, 170, 255))
                                                                                                    .setBackgroundColour(Color.rgb(100, 100, 255))
                                                                                                    .setPrimaryTextColour(Color.rgb(255, 255, 255))
                                                                                                    .setSecondaryTextColour(Color.rgb(255, 255, 255))
                                                                                                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                                                                                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                                                                                                            if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                                                                                                new MaterialTapTargetPrompt.Builder(MainActivity2.this)
                                                                                                                        .setTarget(R.id.copy_downTwo_monday)
                                                                                                                        .setPromptBackground(new CirclePromptBackground())
                                                                                                                        .setPromptFocal(new CirclePromptFocal())
                                                                                                                        .setPrimaryText("Копирование данных занятия")
                                                                                                                        .setSecondaryText("Копирование данных занятия следующему занятию")
                                                                                                                        .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170, 170, 255))
                                                                                                                        .setBackgroundColour(Color.rgb(100, 100, 255))
                                                                                                                        .setPrimaryTextColour(Color.rgb(255, 255, 255))
                                                                                                                        .setSecondaryTextColour(Color.rgb(255, 255, 255))
                                                                                                                        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                                                                                                            public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                                                                                                                                if (state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                                                                                                                    LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
                                                                                                                                    tabStrip.setEnabled(true);
                                                                                                                                    for (int i = 0; i < tabStrip.getChildCount(); i++) {
                                                                                                                                        tabStrip.getChildAt(i).setClickable(true);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        })
                                                                                                                        .show();
                                                                                                            }
                                                                                                        }
                                                                                                    })
                                                                                                    .show();

                                                                                        }
                                                                                    }
                                                                                })
                                                                                .show();
                                                                    }
                                                                }
                                                            })
                                                            .show();
                                                }
                                            }
                                        })
                                        .show();
                            }
                        }
                    })
                    .show();

            SharedPreferences.Editor e = sp.edit();
            e.putString("hasVisited", "yes");
            e.commit();
        }*/

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (first && positionOffset == 0 && positionOffsetPixels == 0)
                    onPageSelected(0);
                first = false;

            }

            @Override
            public void onPageSelected(int position) {

                position_day=position;
                switch (position) {
                    case 0:
                        monday_fill();
                        break;
                    case 1:
                        tuesday_fill();
                        break;
                    case 2:
                        wednesday_fill();
                        break;
                    case 3:
                        thursday_fill();
                        break;
                    case 4:
                        friday_fill();
                        break;
                    case 5:
                        saturday_fill();
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }});

        spinner.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                spinner.clearFocus();
            }
        });

        final AdapterView.OnItemClickListener itemClickdListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag_autosave==1){
                    saveschedule(position_week);
                }
                position_week = position;

                    update_data();

                switch (tabLayout.getSelectedTabPosition()) {
                    case 0:
                        monday_fill();
                        break;
                    case 1:
                        tuesday_fill();
                        break;
                    case 2:
                        wednesday_fill();
                        break;
                    case 3:
                        thursday_fill();
                        break;
                    case 4:
                        friday_fill();
                        break;
                    case 5:
                        saturday_fill();
                        break;
                }

            }

        };

        spinner.setOnItemClickListener(itemClickdListener);

    }
    @SuppressLint("ResourceType")
    @Override
    public void onResume() {
        super.onResume();
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        String tabTitles[] = new String[]{"ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"};
        Context context;

        @SuppressLint("ResourceType")
        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }



        @SuppressLint("ResourceType")
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Monday();
                case 1:
                    return new Tuesday();
                case 2:
                    return new Wednesday();
                case 3:
                    return new Thursday();
                case 4:
                    return new Friday();
                case 5:
                    return new Saturday();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = "ПН";
            } else if (position == 1) {
                title = "ВТ";
            } else if (position == 2) {
                title = "СР";
            }
             else if (position == 3) {
                title = "ЧТ";
                }
             else if (position == 4) {
                title = "ПТ";
            }
            else if (position == 5) {
                title = "СБ";
            }

            return title;
        }


    }

    private void clear_day() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save=1;
        Integer number_day= (position_day+1);
        Integer number_week= (position_week+1);
        ContentValues values = new ContentValues();
        values.put("id_subject","");
        values.put("id_audience","");
        values.put("id_educator","");
        values.put("id_typelesson","");
        db.update(schedule.TABLE_NAME, values," ( "+schedule.id_day+"="+ number_day +" ) AND ( "+ schedule.id_week+ "=" + number_week+ " )", null);
        radiobutton_class();

        switch(position_day) {
            case 0:
                MondayValueSubjectOne = "";
                MondayValueSubjectTwo = "";
                MondayValueSubjectThree = "";
                MondayValueSubjectFour = "";
                MondayValueSubjectFive = "";
                MondayValueSubjectSix = "";
                MondayValueAudienceOne = "";
                MondayValueAudienceTwo = "";
                MondayValueAudienceThree = "";
                MondayValueAudienceFour = "";
                MondayValueAudienceFive = "";
                MondayValueAudienceSix = "";
                MondayEducatorOne = "";
                MondayEducatorTwo = "";
                MondayEducatorThree = "";
                MondayEducatorFour = "";
                MondayEducatorFive = "";
                MondayEducatorSix = "";
                MondayTypeLessonOne = "";
                MondayTypeLessonTwo = "";
                MondayTypeLessonThree = "";
                MondayTypeLessonFour = "";
                MondayTypeLessonFive = "";
                MondayTypeLessonSix = "";
                start(position_week);
                monday_fill();
                typeEditOne_monday.clearCheck();
                typeEditTwo_monday.clearCheck();
                typeEditThree_monday.clearCheck();
                typeEditFour_monday.clearCheck();
                typeEditFive_monday.clearCheck();
                typeEditSix_monday.clearCheck();
                break;
            case 1:
                TuesdayValueSubjectOne = "";
                TuesdayValueSubjectTwo = "";
                TuesdayValueSubjectThree = "";
                TuesdayValueSubjectFour = "";
                TuesdayValueSubjectFive = "";
                TuesdayValueSubjectSix = "";
                TuesdayValueAudienceOne = "";
                TuesdayValueAudienceTwo = "";
                TuesdayValueAudienceThree = "";
                TuesdayValueAudienceFour = "";
                TuesdayValueAudienceFive = "";
                TuesdayValueAudienceSix = "";
                TuesdayEducatorOne = "";
                TuesdayEducatorTwo = "";
                TuesdayEducatorThree = "";
                TuesdayEducatorFour = "";
                TuesdayEducatorFive = "";
                TuesdayEducatorSix = "";
                TuesdayTypeLessonOne = "";
                TuesdayTypeLessonTwo = "";
                TuesdayTypeLessonThree = "";
                TuesdayTypeLessonFour = "";
                TuesdayTypeLessonFive = "";
                TuesdayTypeLessonSix = "";
                start(position_week);
                tuesday_fill();
                typeEditOne_tuesday.clearCheck();
                typeEditTwo_tuesday.clearCheck();
                typeEditThree_tuesday.clearCheck();
                typeEditFour_tuesday.clearCheck();
                typeEditFive_tuesday.clearCheck();
                typeEditSix_tuesday.clearCheck();

                break;
            case 2:
                WednesdayValueSubjectOne = "";
                WednesdayValueSubjectTwo = "";
                WednesdayValueSubjectThree = "";
                WednesdayValueSubjectFour = "";
                WednesdayValueSubjectFive = "";
                WednesdayValueSubjectSix = "";
                WednesdayValueAudienceOne = "";
                WednesdayValueAudienceTwo = "";
                WednesdayValueAudienceThree = "";
                WednesdayValueAudienceFour = "";
                WednesdayValueAudienceFive = "";
                WednesdayValueAudienceSix = "";
                WednesdayEducatorOne = "";
                WednesdayEducatorTwo = "";
                WednesdayEducatorThree = "";
                WednesdayEducatorFour = "";
                WednesdayEducatorFive = "";
                WednesdayEducatorSix = "";
                WednesdayTypeLessonOne = "";
                WednesdayTypeLessonTwo = "";
                WednesdayTypeLessonThree = "";
                WednesdayTypeLessonFour = "";
                WednesdayTypeLessonFive = "";
                WednesdayTypeLessonSix = "";
                start(position_week);
                wednesday_fill();
                typeEditOne_wednesday.clearCheck();
                typeEditTwo_wednesday.clearCheck();
                typeEditThree_wednesday.clearCheck();
                typeEditFour_wednesday.clearCheck();
                typeEditFive_wednesday.clearCheck();
                typeEditSix_wednesday.clearCheck();
                break;
            case 3:
                ThursdayValueSubjectOne = "";
                ThursdayValueSubjectTwo = "";
                ThursdayValueSubjectThree = "";
                ThursdayValueSubjectFour = "";
                ThursdayValueSubjectFive = "";
                ThursdayValueSubjectSix = "";
                ThursdayValueAudienceOne = "";
                ThursdayValueAudienceTwo = "";
                ThursdayValueAudienceThree = "";
                ThursdayValueAudienceFour = "";
                ThursdayValueAudienceFive = "";
                ThursdayValueAudienceSix = "";
                ThursdayEducatorOne = "";
                ThursdayEducatorTwo = "";
                ThursdayEducatorThree = "";
                ThursdayEducatorFour = "";
                ThursdayEducatorFive = "";
                ThursdayEducatorSix = "";
                ThursdayTypeLessonOne = "";
                ThursdayTypeLessonTwo = "";
                ThursdayTypeLessonThree = "";
                ThursdayTypeLessonFour = "";
                ThursdayTypeLessonFive = "";
                ThursdayTypeLessonSix = "";
                start(position_week);
                thursday_fill();
                typeEditOne_thursday.clearCheck();
                typeEditTwo_thursday.clearCheck();
                typeEditThree_thursday.clearCheck();
                typeEditFour_thursday.clearCheck();
                typeEditFive_thursday.clearCheck();
                typeEditSix_thursday.clearCheck();
                break;
            case 4:
                FridayValueSubjectOne = "";
                FridayValueSubjectTwo = "";
                FridayValueSubjectThree = "";
                FridayValueSubjectFour = "";
                FridayValueSubjectFive = "";
                FridayValueSubjectSix = "";
                FridayValueAudienceOne = "";
                FridayValueAudienceTwo = "";
                FridayValueAudienceThree = "";
                FridayValueAudienceFour = "";
                FridayValueAudienceFive = "";
                FridayValueAudienceSix = "";
                FridayEducatorOne = "";
                FridayEducatorTwo = "";
                FridayEducatorThree = "";
                FridayEducatorFour = "";
                FridayEducatorFive = "";
                FridayEducatorSix = "";
                FridayTypeLessonOne = "";
                FridayTypeLessonTwo = "";
                FridayTypeLessonThree = "";
                FridayTypeLessonFour = "";
                FridayTypeLessonFive = "";
                FridayTypeLessonSix = "";
                start(position_week);
                friday_fill();
                typeEditOne_friday.clearCheck();
                typeEditTwo_friday.clearCheck();
                typeEditThree_friday.clearCheck();
                typeEditFour_friday.clearCheck();
                typeEditFive_friday.clearCheck();
                typeEditSix_friday.clearCheck();
                break;
            case 5:
                SaturdayValueSubjectOne = "";
                SaturdayValueSubjectTwo = "";
                SaturdayValueSubjectThree = "";
                SaturdayValueSubjectFour = "";
                SaturdayValueSubjectFive = "";
                SaturdayValueSubjectSix = "";
                SaturdayValueAudienceOne = "";
                SaturdayValueAudienceTwo = "";
                SaturdayValueAudienceThree = "";
                SaturdayValueAudienceFour = "";
                SaturdayValueAudienceFive = "";
                SaturdayValueAudienceSix = "";
                SaturdayEducatorOne = "";
                SaturdayEducatorTwo = "";
                SaturdayEducatorThree = "";
                SaturdayEducatorFour = "";
                SaturdayEducatorFive = "";
                SaturdayEducatorSix = "";
                SaturdayTypeLessonOne = "";
                SaturdayTypeLessonTwo = "";
                SaturdayTypeLessonThree = "";
                SaturdayTypeLessonFour = "";
                SaturdayTypeLessonFive = "";
                SaturdayTypeLessonSix = "";
                start(position_week);
                saturday_fill();
                typeEditOne_saturday.clearCheck();
                typeEditTwo_saturday.clearCheck();
                typeEditThree_saturday.clearCheck();
                typeEditFour_saturday.clearCheck();
                typeEditFive_saturday.clearCheck();
                typeEditSix_saturday.clearCheck();
                break;
        }
    }

    private void clear_week() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save=1;
        //Integer number_day= (position_day+1);
        Integer number_week= (position_week+1);
        ContentValues values = new ContentValues();
        values.put("id_subject","");
        values.put("id_audience","");
        values.put("id_educator","");
        values.put("id_typelesson","");
        db.update(schedule.TABLE_NAME, values,schedule.id_week+ "=" + number_week, null);
        radiobutton_class();
            update_data();
            monday_fill();
            tuesday_fill();
            wednesday_fill();
            thursday_fill();
            friday_fill();
            saturday_fill();
    }

    private void clear_full() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save=1;
       // Integer number_week= (position_week+1);
        ContentValues values = new ContentValues();
        values.put("id_subject","");
        values.put("id_audience","");
        values.put("id_educator","");
        values.put("id_typelesson","");
        db.update(schedule.TABLE_NAME, values,null, null);
        radiobutton_class();
        update_data();
        monday_fill();
        tuesday_fill();
        wednesday_fill();
        thursday_fill();
        friday_fill();
        saturday_fill();
    }


    public Dialog onCreateDialogSaveSchedule() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Оставить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setNegativeButton("Отключить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               switcher_save.setChecked(false);
               saveschedule(position_week);
               flag_save=1;
            }
        }).setTitle("В данный момент активирована функция автосохранения");
        return builder.create();}



  public Dialog onCreateDialogClearDay() {
    String name_day="";
    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
    switch(position_day) {
      case 0:
        name_day= "Понедельник?";
        break;
      case 1:
        name_day="Вторник?";
        break;
      case 2:
        name_day="Среду?";
        break;
      case 3:
        name_day="Четверг?";
        break;
      case 4:
        name_day="Пятницу?";
        break;
      case 5:
        name_day="Субботу?";
        break;
    }
    builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        clear_day();
      }
    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    }).setTitle("Очистить "+ name_day);
    return builder.create();}



    public Dialog onCreateDialogClearWeek() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_week();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить "+ (position_week+1)+ " неделю?");
        return builder.create();}



    public Dialog onCreateDialogClearFull() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_full();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить расписание полностью?");
        return builder.create();}



    public Dialog onCreateDialogSaveOnExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                saveschedule(position_week);
                finish();
            }
        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        }).setTitle("Сохранить расписание?");
        return builder.create();}



    public Dialog onCreateDialogSaveUnevenWeeks() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
        saveschedule(0);
        saveschedule(2);
        saveschedule(4);
        saveschedule(6);
        saveschedule(8);
        saveschedule(10);
        saveschedule(12);
        saveschedule(14);
        saveschedule(16);
            }
        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Сохранить расписание текущей недели в нечетные недели?");
        return builder.create();}


    public Dialog onCreateDialogSaveEvenWeeks() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                saveschedule(1);
                saveschedule(3);
                saveschedule(5);
                saveschedule(7);
                saveschedule(9);
                saveschedule(11);
                saveschedule(13);
                saveschedule(15);
            }
        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Сохранить расписание текущей недели в четные недели?");

        return builder.create();}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (flag_save==1) {Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    finish();
                    startActivity(intent);} else {
                    switch (flag_autosave) {
                        case 0:
                            onCreateDialogSaveOnExit().show();
                            return true;
                        case 1:
                            saveschedule(position_week);
                            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                            return true;
                    }
                }
                return true;
            case R.id.save_schedule:
                switch(flag_autosave) {
                    case 0:
                        saveschedule(position_week);
                        flag_save=1;
                        return true;
                    case 1:
                        onCreateDialogSaveSchedule().show();
                        return true;
                }return true;
            case R.id.save_uneven_weeks:
                onCreateDialogSaveUnevenWeeks().show();
                return true;
            case R.id.save_even_weeks:
                onCreateDialogSaveEvenWeeks().show();
                return true;
            case R.id.clear_day:
            onCreateDialogClearDay().show();
                return true;
            case R.id.clear_week:
            onCreateDialogClearWeek().show();
                return true;
            case R.id.clear_full:
                onCreateDialogClearFull().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}

    @Override
    public void onBackPressed() {
        if (flag_save==1) {Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            finish();
            startActivity(intent);}
            else {
            switch (flag_autosave) {
                case 0:
                    onCreateDialogSaveOnExit().show();
                    return;
                case 1:
                    saveschedule(position_week);
                    finish();
                    return;
            }
        }
    }

    void load_calls_schedule(){

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT "+ calls.time +" FROM " + calls.TABLE_NAME;
        Cursor cursor = db.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
         calls_schedule.add(cursor.getString(0));
        }
        cursor.close();
    }

    private void DataMonday() {
        Monday = new ArrayList<>();
        try { Monday.add(new DataMonday("1", String.valueOf(calls_schedule.get(0)), MondayValueSubjectOne, MondayValueAudienceOne, MondayEducatorOne, MondayTypeLessonOne));
        } catch (NullPointerException e) { }
        try { Monday.add(new DataMonday("2", String.valueOf(calls_schedule.get(1)), MondayValueSubjectTwo, MondayValueAudienceTwo, MondayEducatorTwo, MondayTypeLessonTwo));
        } catch (NullPointerException e) { }
        try { Monday.add(new DataMonday("3", String.valueOf(calls_schedule.get(2)), MondayValueSubjectThree, MondayValueAudienceThree, MondayEducatorThree, MondayTypeLessonThree));
        } catch (NullPointerException e) { }
        try { Monday.add(new DataMonday("4", String.valueOf(calls_schedule.get(3)), MondayValueSubjectFour, MondayValueAudienceFour, MondayEducatorFour, MondayTypeLessonFour));
        } catch (NullPointerException e) { }
        try { Monday.add(new DataMonday("5", String.valueOf(calls_schedule.get(4)), MondayValueSubjectFive, MondayValueAudienceFive, MondayEducatorFive, MondayTypeLessonFive));
        } catch (NullPointerException e) { }
        try { Monday.add(new DataMonday("6", String.valueOf(calls_schedule.get(5)), MondayValueSubjectSix, MondayValueAudienceSix, MondayEducatorSix, MondayTypeLessonSix));
        } catch (NullPointerException e) { }
    }

    private void DataTuesday() {
        Tuesday = new ArrayList<>();
        try { Tuesday.add(new DataTuesday("1", String.valueOf(calls_schedule.get(0)), TuesdayValueSubjectOne, TuesdayValueAudienceOne, TuesdayEducatorOne, TuesdayTypeLessonOne));
        } catch (NullPointerException e) { }
        try { Tuesday.add(new DataTuesday("2", String.valueOf(calls_schedule.get(1)), TuesdayValueSubjectTwo, TuesdayValueAudienceTwo, TuesdayEducatorTwo, TuesdayTypeLessonTwo));
        } catch (NullPointerException e) { }
        try { Tuesday.add(new DataTuesday("3", String.valueOf(calls_schedule.get(2)), TuesdayValueSubjectThree, TuesdayValueAudienceThree, TuesdayEducatorThree, TuesdayTypeLessonThree));
        } catch (NullPointerException e) { }
        try { Tuesday.add(new DataTuesday("4", String.valueOf(calls_schedule.get(3)), TuesdayValueSubjectFour, TuesdayValueAudienceFour, TuesdayEducatorFour, TuesdayTypeLessonFour));
        } catch (NullPointerException e) { }
        try { Tuesday.add(new DataTuesday("5", String.valueOf(calls_schedule.get(4)), TuesdayValueSubjectFive, TuesdayValueAudienceFive, TuesdayEducatorFive, TuesdayTypeLessonFive));
        } catch (NullPointerException e) { }
        try {
            Tuesday.add(new DataTuesday("6", String.valueOf(calls_schedule.get(5)), TuesdayValueSubjectSix, TuesdayValueAudienceSix, TuesdayEducatorSix, TuesdayTypeLessonSix));
        } catch (NullPointerException e) {
        }
    }

    private void DataWednesday() {
        Wednesday = new ArrayList<>(); //Вторник
        try { Wednesday.add(new DataWednesday("1", String.valueOf(calls_schedule.get(0)), WednesdayValueSubjectOne, WednesdayValueAudienceOne, WednesdayEducatorOne, WednesdayTypeLessonOne));
        } catch (NullPointerException e) { }
        try { Wednesday.add(new DataWednesday("2", String.valueOf(calls_schedule.get(1)), WednesdayValueSubjectTwo, WednesdayValueAudienceTwo, WednesdayEducatorTwo, WednesdayTypeLessonTwo));
        } catch (NullPointerException e) { }
        try { Wednesday.add(new DataWednesday("3", String.valueOf(calls_schedule.get(2)), WednesdayValueSubjectThree, WednesdayValueAudienceThree, WednesdayEducatorThree, WednesdayTypeLessonThree));
        } catch (NullPointerException e) { }
        try { Wednesday.add(new DataWednesday("4", String.valueOf(calls_schedule.get(3)), WednesdayValueSubjectFour, WednesdayValueAudienceFour, WednesdayEducatorFour, WednesdayTypeLessonFour));
        } catch (NullPointerException e) { }
        try { Wednesday.add(new DataWednesday("5", String.valueOf(calls_schedule.get(4)), WednesdayValueSubjectFive, WednesdayValueAudienceFive, WednesdayEducatorFive, WednesdayTypeLessonFive));
        } catch (NullPointerException e) { }
        try { Wednesday.add(new DataWednesday("6", String.valueOf(calls_schedule.get(5)), WednesdayValueSubjectSix, WednesdayValueAudienceSix, WednesdayEducatorSix, WednesdayTypeLessonSix));
        } catch (NullPointerException e) { }
    }

    private void DataThursday() {
        Thursday = new ArrayList<>(); //Вторник
        try { Thursday.add(new DataThursday("1", String.valueOf(calls_schedule.get(0)), ThursdayValueSubjectOne, ThursdayValueAudienceOne, ThursdayEducatorOne, ThursdayTypeLessonOne));
        } catch (NullPointerException e) { }
        try { Thursday.add(new DataThursday("2", String.valueOf(calls_schedule.get(1)), ThursdayValueSubjectTwo, ThursdayValueAudienceTwo, ThursdayEducatorTwo, ThursdayTypeLessonTwo));
        } catch (NullPointerException e) { }
        try { Thursday.add(new DataThursday("3", String.valueOf(calls_schedule.get(2)), ThursdayValueSubjectThree, ThursdayValueAudienceThree, ThursdayEducatorThree, ThursdayTypeLessonThree));
        } catch (NullPointerException e) { }
        try { Thursday.add(new DataThursday("4", String.valueOf(calls_schedule.get(3)), ThursdayValueSubjectFour, ThursdayValueAudienceFour, ThursdayEducatorFour, ThursdayTypeLessonFour));
        } catch (NullPointerException e) { }
        try { Thursday.add(new DataThursday("5", String.valueOf(calls_schedule.get(4)), ThursdayValueSubjectFive, ThursdayValueAudienceFive, ThursdayEducatorFive, ThursdayTypeLessonFive));
        } catch (NullPointerException e) { }
        try { Thursday.add(new DataThursday("6", String.valueOf(calls_schedule.get(5)), ThursdayValueSubjectSix, ThursdayValueAudienceSix, ThursdayEducatorSix, ThursdayTypeLessonSix));
        } catch (NullPointerException e) { }
    }

    private void DataFriday() {
        Friday = new ArrayList<>(); //Вторник
        try { Friday.add(new DataFriday("1", String.valueOf(calls_schedule.get(0)), FridayValueSubjectOne, FridayValueAudienceOne, FridayEducatorOne, FridayTypeLessonOne));
        } catch (NullPointerException e) { }
        try { Friday.add(new DataFriday("2", String.valueOf(calls_schedule.get(1)), FridayValueSubjectTwo, FridayValueAudienceTwo, FridayEducatorTwo, FridayTypeLessonTwo));
        } catch (NullPointerException e) { }
        try { Friday.add(new DataFriday("3", String.valueOf(calls_schedule.get(2)), FridayValueSubjectThree, FridayValueAudienceThree, FridayEducatorThree, FridayTypeLessonThree));
        } catch (NullPointerException e) { }
        try { Friday.add(new DataFriday("4", String.valueOf(calls_schedule.get(3)), FridayValueSubjectFour, FridayValueAudienceFour, FridayEducatorFour, FridayTypeLessonFour));
        } catch (NullPointerException e) { }
        try { Friday.add(new DataFriday("5", String.valueOf(calls_schedule.get(4)), FridayValueSubjectFive, FridayValueAudienceFive, FridayEducatorFive, FridayTypeLessonFive));
        } catch (NullPointerException e) { }
        try { Friday.add(new DataFriday("6", String.valueOf(calls_schedule.get(5)), FridayValueSubjectSix, FridayValueAudienceSix, FridayEducatorSix, FridayTypeLessonSix));
        } catch (NullPointerException e) { }
    }

    private void DataSaturday() {
        Saturday = new ArrayList<>(); //Вторник
        try { Saturday.add(new DataSaturday("1", String.valueOf(calls_schedule.get(0)), SaturdayValueSubjectOne, SaturdayValueAudienceOne, SaturdayEducatorOne, SaturdayTypeLessonOne));
        } catch (NullPointerException e) { }
        try { Saturday.add(new DataSaturday("2", String.valueOf(calls_schedule.get(1)), SaturdayValueSubjectTwo, SaturdayValueAudienceTwo, SaturdayEducatorTwo, SaturdayTypeLessonTwo));
        } catch (NullPointerException e) { }
        try { Saturday.add(new DataSaturday("3", String.valueOf(calls_schedule.get(2)), SaturdayValueSubjectThree, SaturdayValueAudienceThree, SaturdayEducatorThree, SaturdayTypeLessonThree));
        } catch (NullPointerException e) { }
        try { Saturday.add(new DataSaturday("4", String.valueOf(calls_schedule.get(3)), SaturdayValueSubjectFour, SaturdayValueAudienceFour, SaturdayEducatorFour, SaturdayTypeLessonFour));
        } catch (NullPointerException e) { }
        try { Saturday.add(new DataSaturday("5", String.valueOf(calls_schedule.get(4)), SaturdayValueSubjectFive, SaturdayValueAudienceFive, SaturdayEducatorFive, SaturdayTypeLessonFive));
        } catch (NullPointerException e) { }
        try { Saturday.add(new DataSaturday("6", String.valueOf(calls_schedule.get(5)), SaturdayValueSubjectSix, SaturdayValueAudienceSix, SaturdayEducatorSix, SaturdayTypeLessonSix));
        } catch (NullPointerException e) { }
    }


    void radiobutton_class(){
        typeEditOne_monday = findViewById(R.id.typeEdit_monday);
        typeEditTwo_monday = findViewById(R.id.typeEditTwo_monday);
        typeEditThree_monday = findViewById(R.id.typeEditThree_monday);
        typeEditFour_monday = findViewById(R.id.typeEditFour_monday);
        typeEditFive_monday = findViewById(R.id.typeEditFive_monday);
        typeEditSix_monday = findViewById(R.id.typeEditSix_monday);
        typeEditOne_tuesday = findViewById(R.id.typeEdit_tuesday);
        typeEditTwo_tuesday = findViewById(R.id.typeEditTwo_tuesday);
        typeEditThree_tuesday = findViewById(R.id.typeEditThree_tuesday);
        typeEditFour_tuesday = findViewById(R.id.typeEditFour_tuesday);
        typeEditFive_tuesday = findViewById(R.id.typeEditFive_tuesday);
        typeEditSix_tuesday = findViewById(R.id.typeEditSix_tuesday);
        typeEditOne_wednesday = findViewById(R.id.typeEdit_wednesday);
        typeEditTwo_wednesday = findViewById(R.id.typeEditTwo_wednesday);
        typeEditThree_wednesday = findViewById(R.id.typeEditThree_wednesday);
        typeEditFour_wednesday = findViewById(R.id.typeEditFour_wednesday);
        typeEditFive_wednesday = findViewById(R.id.typeEditFive_wednesday);
        typeEditSix_wednesday = findViewById(R.id.typeEditSix_wednesday);
        typeEditOne_thursday = findViewById(R.id.typeEdit_thursday);
        typeEditTwo_thursday = findViewById(R.id.typeEditTwo_thursday);
        typeEditThree_thursday = findViewById(R.id.typeEditThree_thursday);
        typeEditFour_thursday = findViewById(R.id.typeEditFour_thursday);
        typeEditFive_thursday = findViewById(R.id.typeEditFive_thursday);
        typeEditSix_thursday = findViewById(R.id.typeEditSix_thursday);
        typeEditOne_friday = findViewById(R.id.typeEdit_friday);
        typeEditTwo_friday = findViewById(R.id.typeEditTwo_friday);
        typeEditThree_friday = findViewById(R.id.typeEditThree_friday);
        typeEditFour_friday = findViewById(R.id.typeEditFour_friday);
        typeEditFive_friday = findViewById(R.id.typeEditFive_friday);
        typeEditSix_friday = findViewById(R.id.typeEditSix_friday);
        typeEditOne_saturday = findViewById(R.id.typeEdit_saturday);
        typeEditTwo_saturday = findViewById(R.id.typeEditTwo_saturday);
        typeEditThree_saturday = findViewById(R.id.typeEditThree_saturday);
        typeEditFour_saturday = findViewById(R.id.typeEditFour_saturday);
        typeEditFive_saturday = findViewById(R.id.typeEditFive_saturday);
        typeEditSix_saturday = findViewById(R.id.typeEditSix_saturday);
    }

    public void saveschedule(Integer number_week) {

        radiobutton_class();
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        position_week=number_week;

        DataMonday();
        try {
            MondayStringSubjectEditOne = Monday.get(0).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditTwo = Monday.get(1).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditThree = Monday.get(2).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditFour = Monday.get(3).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditFive = Monday.get(4).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditSix = Monday.get(5).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditOne = Monday.get(0).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditTwo = Monday.get(1).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditThree = Monday.get(2).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditFour = Monday.get(3).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditFive = Monday.get(4).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditSix = Monday.get(5).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditOne = Monday.get(0).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditTwo = Monday.get(1).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditThree = Monday.get(2).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditFour = Monday.get(3).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditFive = Monday.get(4).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditSix = Monday.get(5).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditOne = Monday.get(0).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditTwo = Monday.get(1).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditThree = Monday.get(2).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditFour = Monday.get(3).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditFive = Monday.get(4).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditSix = Monday.get(5).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }

        DataTuesday();

        try {
            TuesdayStringSubjectEditOne = Tuesday.get(0).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringSubjectEditTwo = Tuesday.get(1).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringSubjectEditThree = Tuesday.get(2).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringSubjectEditFour = Tuesday.get(3).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringSubjectEditFive = Tuesday.get(4).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringSubjectEditSix = Tuesday.get(5).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringAudienceEditOne = Tuesday.get(0).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringAudienceEditTwo = Tuesday.get(1).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringAudienceEditThree = Tuesday.get(2).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringAudienceEditFour = Tuesday.get(3).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringAudienceEditFive = Tuesday.get(4).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringAudienceEditSix = Tuesday.get(5).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringEducatorEditOne = Tuesday.get(0).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringEducatorEditTwo = Tuesday.get(1).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringEducatorEditThree = Tuesday.get(2).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringEducatorEditFour = Tuesday.get(3).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringEducatorEditFive = Tuesday.get(4).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringEducatorEditSix = Tuesday.get(5).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringTypeLessonEditOne = Tuesday.get(0).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringTypeLessonEditTwo = Tuesday.get(1).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringTypeLessonEditThree = Tuesday.get(2).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringTypeLessonEditFour = Tuesday.get(3).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringTypeLessonEditFive = Tuesday.get(4).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            TuesdayStringTypeLessonEditSix = Tuesday.get(5).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        DataWednesday();

        try {
            WednesdayStringSubjectEditOne = Wednesday.get(0).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringSubjectEditTwo = Wednesday.get(1).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringSubjectEditThree = Wednesday.get(2).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringSubjectEditFour = Wednesday.get(3).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringSubjectEditFive = Wednesday.get(4).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringSubjectEditSix = Wednesday.get(5).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringAudienceEditOne = Wednesday.get(0).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringAudienceEditTwo = Wednesday.get(1).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringAudienceEditThree = Wednesday.get(2).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringAudienceEditFour = Wednesday.get(3).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringAudienceEditFive = Wednesday.get(4).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringAudienceEditSix = Wednesday.get(5).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringEducatorEditOne = Wednesday.get(0).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringEducatorEditTwo = Wednesday.get(1).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringEducatorEditThree = Wednesday.get(2).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringEducatorEditFour = Wednesday.get(3).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringEducatorEditFive = Wednesday.get(4).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringEducatorEditSix = Wednesday.get(5).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringTypeLessonEditOne = Wednesday.get(0).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringTypeLessonEditTwo = Wednesday.get(1).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringTypeLessonEditThree = Wednesday.get(2).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringTypeLessonEditFour = Wednesday.get(3).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringTypeLessonEditFive = Wednesday.get(4).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            WednesdayStringTypeLessonEditSix = Wednesday.get(5).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        DataThursday();

        try {
            ThursdayStringSubjectEditOne = Thursday.get(0).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringSubjectEditTwo = Thursday.get(1).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringSubjectEditThree = Thursday.get(2).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringSubjectEditFour = Thursday.get(3).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringSubjectEditFive = Thursday.get(4).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringSubjectEditSix = Thursday.get(5).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringAudienceEditOne = Thursday.get(0).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringAudienceEditTwo = Thursday.get(1).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringAudienceEditThree = Thursday.get(2).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringAudienceEditFour = Thursday.get(3).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringAudienceEditFive = Thursday.get(4).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringAudienceEditSix = Thursday.get(5).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringEducatorEditOne = Thursday.get(0).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringEducatorEditTwo = Thursday.get(1).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringEducatorEditThree = Thursday.get(2).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringEducatorEditFour = Thursday.get(3).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringEducatorEditFive = Thursday.get(4).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringEducatorEditSix = Thursday.get(5).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringTypeLessonEditOne = Thursday.get(0).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringTypeLessonEditTwo = Thursday.get(1).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringTypeLessonEditThree = Thursday.get(2).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringTypeLessonEditFour = Thursday.get(3).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringTypeLessonEditFive = Thursday.get(4).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            ThursdayStringTypeLessonEditSix = Thursday.get(5).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }

        DataFriday();

        try {
            FridayStringSubjectEditOne = Friday.get(0).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringSubjectEditTwo = Friday.get(1).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringSubjectEditThree = Friday.get(2).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringSubjectEditFour = Friday.get(3).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringSubjectEditFive = Friday.get(4).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringSubjectEditSix = Friday.get(5).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringAudienceEditOne = Friday.get(0).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringAudienceEditTwo = Friday.get(1).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringAudienceEditThree = Friday.get(2).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringAudienceEditFour = Friday.get(3).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringAudienceEditFive = Friday.get(4).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringAudienceEditSix = Friday.get(5).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringEducatorEditOne = Friday.get(0).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringEducatorEditTwo = Friday.get(1).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringEducatorEditThree = Friday.get(2).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringEducatorEditFour = Friday.get(3).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringEducatorEditFive = Friday.get(4).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringEducatorEditSix = Friday.get(5).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringTypeLessonEditOne = Friday.get(0).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringTypeLessonEditTwo = Friday.get(1).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringTypeLessonEditThree = Friday.get(2).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringTypeLessonEditFour = Friday.get(3).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringTypeLessonEditFive = Friday.get(4).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            FridayStringTypeLessonEditSix = Friday.get(5).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }

        DataSaturday();

        try {
            SaturdayStringSubjectEditOne = Saturday.get(0).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringSubjectEditTwo = Saturday.get(1).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringSubjectEditThree = Saturday.get(2).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringSubjectEditFour = Saturday.get(3).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringSubjectEditFive = Saturday.get(4).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringSubjectEditSix = Saturday.get(5).subjectEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringAudienceEditOne = Saturday.get(0).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringAudienceEditTwo = Saturday.get(1).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringAudienceEditThree = Saturday.get(2).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringAudienceEditFour = Saturday.get(3).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringAudienceEditFive = Saturday.get(4).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringAudienceEditSix = Saturday.get(5).audienceEdit.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringEducatorEditOne = Saturday.get(0).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringEducatorEditTwo = Saturday.get(1).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringEducatorEditThree = Saturday.get(2).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringEducatorEditFour = Saturday.get(3).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringEducatorEditFive = Saturday.get(4).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringEducatorEditSix = Saturday.get(5).educator.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringTypeLessonEditOne = Saturday.get(0).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringTypeLessonEditTwo = Saturday.get(1).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringTypeLessonEditThree = Saturday.get(2).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringTypeLessonEditFour = Saturday.get(3).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringTypeLessonEditFive = Saturday.get(4).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            SaturdayStringTypeLessonEditSix = Saturday.get(5).typelesson.toString();
        } catch (IndexOutOfBoundsException e) {
        }

        db.beginTransaction();
        try{


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + MondayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));



            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + MondayStringTypeLessonEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 2));



            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + MondayStringTypeLessonEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 3));



            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + MondayStringTypeLessonEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 4));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + MondayStringTypeLessonEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 5));

            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + MondayStringTypeLessonEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 6));



            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + TuesdayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + TuesdayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + TuesdayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + TuesdayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 7));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + TuesdayStringSubjectEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + TuesdayStringAudienceEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + TuesdayStringEducatorEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + TuesdayStringTypeLessonEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 8));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + TuesdayStringSubjectEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + TuesdayStringAudienceEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + TuesdayStringEducatorEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + TuesdayStringTypeLessonEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 9));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + TuesdayStringSubjectEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + TuesdayStringAudienceEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + TuesdayStringEducatorEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + TuesdayStringTypeLessonEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 10));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + TuesdayStringSubjectEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + TuesdayStringAudienceEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + TuesdayStringEducatorEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + TuesdayStringTypeLessonEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 11));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + TuesdayStringSubjectEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + TuesdayStringAudienceEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + TuesdayStringEducatorEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + TuesdayStringTypeLessonEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 12));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + WednesdayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + WednesdayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + WednesdayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + WednesdayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 13));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + WednesdayStringSubjectEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + WednesdayStringAudienceEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + WednesdayStringEducatorEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + WednesdayStringTypeLessonEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 14));



            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + WednesdayStringSubjectEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + WednesdayStringAudienceEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + WednesdayStringEducatorEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + WednesdayStringTypeLessonEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 15));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + WednesdayStringSubjectEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + WednesdayStringAudienceEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + WednesdayStringEducatorEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + WednesdayStringTypeLessonEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 16));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + WednesdayStringSubjectEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + WednesdayStringAudienceEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + WednesdayStringEducatorEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + WednesdayStringTypeLessonEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 17));

            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + WednesdayStringSubjectEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + WednesdayStringAudienceEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + WednesdayStringEducatorEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + WednesdayStringTypeLessonEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 18));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + ThursdayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + ThursdayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + ThursdayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + ThursdayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 19));

            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + ThursdayStringSubjectEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + ThursdayStringAudienceEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + ThursdayStringEducatorEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + ThursdayStringTypeLessonEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 20));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + ThursdayStringSubjectEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + ThursdayStringAudienceEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + ThursdayStringEducatorEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + ThursdayStringTypeLessonEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 21));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + ThursdayStringSubjectEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + ThursdayStringAudienceEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + ThursdayStringEducatorEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + ThursdayStringTypeLessonEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 22));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + ThursdayStringSubjectEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + ThursdayStringAudienceEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + ThursdayStringEducatorEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + ThursdayStringTypeLessonEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 23));



            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + ThursdayStringSubjectEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + ThursdayStringAudienceEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + ThursdayStringEducatorEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + ThursdayStringTypeLessonEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 24));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + FridayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + FridayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + FridayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + FridayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 25));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + FridayStringSubjectEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + FridayStringAudienceEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + FridayStringEducatorEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + FridayStringTypeLessonEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 26));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + FridayStringSubjectEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + FridayStringAudienceEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + FridayStringEducatorEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + FridayStringTypeLessonEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 27));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + FridayStringSubjectEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + FridayStringAudienceEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + FridayStringEducatorEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + FridayStringTypeLessonEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 28));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + FridayStringSubjectEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + FridayStringAudienceEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + FridayStringEducatorEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + FridayStringTypeLessonEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 29));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + FridayStringSubjectEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + FridayStringAudienceEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + FridayStringEducatorEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + FridayStringTypeLessonEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 30));



            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + SaturdayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + SaturdayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + SaturdayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + SaturdayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 31));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + SaturdayStringSubjectEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + SaturdayStringAudienceEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + SaturdayStringEducatorEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + SaturdayStringTypeLessonEditTwo + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 32));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + SaturdayStringSubjectEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + SaturdayStringAudienceEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + SaturdayStringEducatorEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + SaturdayStringTypeLessonEditThree + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 33));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + SaturdayStringSubjectEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + SaturdayStringAudienceEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + SaturdayStringEducatorEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + SaturdayStringTypeLessonEditFour + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 34));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + SaturdayStringSubjectEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + SaturdayStringAudienceEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + SaturdayStringEducatorEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + SaturdayStringTypeLessonEditFive + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 35));


            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.subjects.subject + "='" + SaturdayStringSubjectEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.audiences.audience + "='" + SaturdayStringAudienceEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.educators.educator + "='" + SaturdayStringEducatorEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                "WHERE " + ScheduleClass.typelessons.typelesson + "='" + SaturdayStringTypeLessonEditSix + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 36));

            db.setTransactionSuccessful(); }
        finally {
            db.endTransaction();
        }

    }

}
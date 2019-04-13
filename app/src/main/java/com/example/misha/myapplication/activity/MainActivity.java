package com.example.misha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.FragmentTwo;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.activitySchedule.FragmentEditSchedule;
import com.example.misha.myapplication.activitySchedule.FragmentScheduleByDays;
import com.example.misha.myapplication.adapter.CustomSpinnerAdapter;
import com.example.misha.myapplication.database.DatabaseHelper;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int WEEK_CODE = 2221;
    DrawerLayout drawer;
    TextView text_main;
    Spinner spinner;
    long differentBetweenDate = 0;
    long selectDate = 0;
    long curr_week = 0;


    TextView output = null;
    CustomSpinnerAdapter adapter;



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_main = findViewById(R.id.text_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        drawer = findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        spinner = findViewById(R.id.spinner);


        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(Preferences.getInstance().getSemestStart());
        ArrayList<String> allDays = new ArrayList<>();
        SimpleDateFormat mFormat = new SimpleDateFormat("dd.MM");
        for(int week = 0; week < 17; week++){
            for(int day = 0; day < 7; day++){
            String startWeek = mFormat.format(mCalendar.getTime());
            mCalendar.add(Calendar.WEEK_OF_YEAR, 1);
            mCalendar.add(Calendar.DAY_OF_YEAR, -1);
            allDays.add(startWeek + " - " + mFormat.format(mCalendar.getTime()));
            mCalendar.add(Calendar.DAY_OF_YEAR, 1);
            break;
        }}

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this,
               allDays);


        // attaching data adapter to spinner
        spinner.setAdapter(customSpinnerAdapter);



        calculateDate();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_WEEK, position);
                sendResultToTarget(FragmentScheduleByDays.class, WEEK_CODE, Activity.RESULT_OK, intent);
                sendResultToTarget(FragmentEditSchedule.class, WEEK_CODE, Activity.RESULT_OK, intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        spinner.setSelection((int) curr_week);

        Button buttonToolbar = findViewById(R.id.toolbar_but);
        buttonToolbar.setBackgroundResource(R.drawable.ic_editor);
        buttonToolbar.setOnClickListener(v -> {


            Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);

            if (f instanceof FragmentScheduleByDays) {
                replaceFragment(new FragmentEditSchedule());
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_WEEK, (int) curr_week);
                sendResultToTarget(FragmentEditSchedule.class, WEEK_CODE, Activity.RESULT_OK, intent);
                buttonToolbar.setBackgroundResource(R.drawable.ic_ok);

            } else {
                replaceFragment(new FragmentScheduleByDays());
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_WEEK, Preferences.getInstance().getSelectedWeekEditSchedule());
                sendResultToTarget(FragmentScheduleByDays.class, WEEK_CODE, Activity.RESULT_OK, intent);
                buttonToolbar.setBackgroundResource(R.drawable.ic_editor);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(R.id.rasp_day);


        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String hasVisited = sp.getString("hasVisited", "nope");
        if (hasVisited == "nope") {

            Intent intent = new Intent(MainActivity.this, ActivityStart.class);
            startActivity(intent);


            Editor e = sp.edit();
            e.putString("hasVisited", "yes");
            e.commit();
        }
    }


    private void calculateDate() {
        Calendar calendar = Calendar.getInstance();
        selectDate = Preferences.getInstance().getSemestStart();
        differentBetweenDate = calendar.getTimeInMillis() - selectDate;
        curr_week = (differentBetweenDate / (7 * 24 * 60 * 60 * 1000));
        Preferences.getInstance().setSelectedWeekEditSchedule((int) curr_week);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new DatabaseHelper(this).getWritableDatabase();
        LessonDao.getInstance().initTable();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.rasp_day) {
        } else if (id == R.id.rasp_list) {
        } else if (id == R.id.edit_schedule) {
        } else if (id == R.id.settings) {
        } else if (id == R.id.nav_share) {
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        switch (viewId) {
            case R.id.rasp_day:
                fragment = new FragmentScheduleByDays();
                text_main.setText("По дням");
                break;
            case R.id.rasp_list:
                fragment = new FragmentTwo();
                text_main.setText("Списком");
                break;
            case R.id.edit_schedule:
                fragment = new FragmentEditSchedule();
                break;
            case R.id.edit_data:
                Intent intent = new Intent(this, ActivityEditData.class);
                startActivity(intent);
                break;
            case R.id.call_schedule:
                intent = new Intent(this, ActivityCallSchedule.class);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(this, ActivitySettings.class);
                startActivity(intent);
                break;
            case R.id.nav_share:
                onCreateDialog().show();
                break;
            default:
                fragment = new FragmentScheduleByDays();
                setTitle("По дням");
                break;
        }

        if (fragment != null) {
            replaceFragment(fragment);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public Dialog onCreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setPositiveButton("Профиль Вконтакте", (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mikhailvolkov1"));
            finish();
            startActivity(browserIntent);
        }).setNeutralButton("Отмена", (dialog, id) -> dialog.cancel()).setNegativeButton("Электронная почта", (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:mikhailvolkov2014-2014@ya.ru"));
            finish();
            startActivity(browserIntent);
        }).setTitle("Обратная связь с разработчиком");
        return builder.create();
    }

    //button_toolbar.setEnabled(false);

           /* new MaterialTapTargetPrompt.Builder(MainActivity.this)
                    .setTarget(spinner)
                    .setPromptBackground(new CirclePromptBackground())
                    .setPromptFocal(new RectanglePromptFocal())
                    .setPrimaryText("Выбор недели")
                    .setSecondaryText("Вы можете выбрать номер недели при просмотре расписания. В настройках также можно выбрать дату начала семестра для автоопределения текущей учебной недели")
                    .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170,170,255))
                    .setBackgroundColour(Color.rgb(100,100,255))
                    .setPrimaryTextColour(Color.rgb(255,255,255))
                    .setSecondaryTextColour(Color.rgb(255,255,255))
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                    {
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            button_toolbar.setEnabled(true);
                            if (state == MaterialTapTargetPrompt.STATE_FINISHED || state== MaterialTapTargetPrompt.STATE_DISMISSED ) {
                                new MaterialTapTargetPrompt.Builder(MainActivity.this)
                                        .setTarget(button_toolbar)
                                        .setPromptBackground(new CirclePromptBackground())
                                        .setPromptFocal(new CirclePromptFocal())
                                        .setPrimaryText("Редактор расписания")
                                        .setSecondaryText("Нажав эту кнопку, Вы перейдете в окно редактирования расписания")
                                        .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(170,170,255))
                                        .setBackgroundColour(Color.rgb(100,100,255))
                                        .setPrimaryTextColour(Color.rgb(255,255,255))
                                        .setSecondaryTextColour(Color.rgb(255,255,255))
                                        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                                        {
                                            public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                                                if (state == MaterialTapTargetPrompt.STATE_FINISHED || state== MaterialTapTargetPrompt.STATE_DISMISSED ) {
                                                   //         drawer.openDrawer(Gravity.LEFT);

                                                }
                                            }})
                                        .show();
                            }

                        }})
                    .show();*/

}

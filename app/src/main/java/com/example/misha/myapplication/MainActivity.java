package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misha.myapplication.data.ScheduleDB;

import java.util.Calendar;
import java.util.Set;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.CirclePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.CirclePromptFocal;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class MainActivity extends AppCompatActivity
 implements NavigationView.OnNavigationItemSelectedListener,FragmentOne.OnFragmentInteractionListener, FragmentTwo.OnFragmentInteractionListener {
    private com.example.misha.myapplication.data.ScheduleDB ScheduleDB;

    long diff=0;
    long days=0;
    DrawerLayout drawer;
    Long current_week;
    TextView text_main;
    Button button_toolbar;
    Integer dayy=0;
    public void onCreate(Bundle savedInstanceState) {
        ScheduleDB = new ScheduleDB(this);
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

        final Spinner spinner = findViewById(R.id.rasp_weeks);

        button_toolbar= findViewById(R.id.toolbar_but);
        button_toolbar.setBackgroundResource(R.drawable.ic_editor);
        button_toolbar.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(MainActivity.this,MainActivity2.class);
          startActivity(intent);
        }
      });

        final OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences settings = getSharedPreferences("choice_week", 0);
                Editor editor = settings.edit();
                editor.putString("position", String.valueOf(position));
                editor.commit();
                displayView(R.id.rasp_day);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(R.id.rasp_day);


        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String hasVisited = sp.getString("hasVisited", "nope");
        if (hasVisited=="nope") {

            Intent intent = new Intent(MainActivity.this,welcome_activity.class);
            startActivity(intent);

            button_toolbar.setEnabled(false);

            new MaterialTapTargetPrompt.Builder(MainActivity.this)
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
                    .show();

            Editor e = sp.edit();
            e.putString("hasVisited", "yes");
            e.commit();
        }

        Calendar today = Calendar.getInstance();
        SharedPreferences settings = getSharedPreferences("week", 0);
        current_week = (settings.getLong("current_week", today.getTimeInMillis()));
        diff= today.getTimeInMillis()- current_week;
        // Toast.makeText(this, String.valueOf(diff), Toast.LENGTH_SHORT).show();
        days = (diff / (7 * 24 * 60 * 60 * 1000));
        dayy= (int) days;
        spinner.setSelection(dayy);
}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.rasp_day) {
        } else if (id == R.id.rasp_list) {
        } else if (id == R.id.edit_schedule) {
        } else if (id == R.id.settings) {
        } else if (id == R.id.help) {
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

                fragment = new FragmentOne();

                text_main.setText("По дням");
                break;
            case R.id.rasp_list:
                fragment = new FragmentTwo();
                text_main.setText("Списком");
                break;
            case R.id.help:
                Toast.makeText(getApplication(), "Разработчик:\nВолков Михаил Евгеньевич", Toast.LENGTH_LONG).show();
                break;
            case R.id.edit_schedule:
                Intent intent = new Intent(this, MainActivity2.class);
                finish();
                startActivity(intent);
                break;
            case R.id.edit_data:
                intent = new Intent(this, activityEditData.class);
                finish();
                startActivity(intent);
                break;
            case R.id.call_schedule:
                intent = new Intent(this, call_schedule.class);
                finish();
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(this, Settings.class);
                finish();
                startActivity(intent);
                break;
            case R.id.nav_share:
            onCreateDialog().show();
                break;
            default:
                fragment = new FragmentOne();
                setTitle("По дням");
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public Dialog onCreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);

        builder.setPositiveButton("Открыть профиль", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mikhailvolkov1"));
                finish();
                startActivity(browserIntent);
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("VK-страница разработчика");
        return builder.create();
    }

@Override
public void onFragmentInteraction(Uri uri)
{}
}

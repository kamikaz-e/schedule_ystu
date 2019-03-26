package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class activityEditData extends AppCompatActivity
        implements fragment_subject.OnFragmentInteractionListener,fragment_audience.OnFragmentInteractionListener,fragment_educator.OnFragmentInteractionListener{
  EditText input_subject;
  ListView list_subjects;
  private ScheduleDB ScheduleDB;
  final Context context = this;
  final ArrayList<String> subject_list = new ArrayList<>();
  Button clear_subjects;

  Integer position_spinner=0;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activityeditdata);
    final Spinner spinner = findViewById(R.id.rasp_weeks);
    ScheduleDB = new ScheduleDB(this);
    Fragment fragment = new fragment_subject();
    if (fragment != null) {
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.content_frame, fragment);
      ft.commit();


      SharedPreferences sp = getPreferences(MODE_PRIVATE);
      String hasVisited = sp.getString("hasVisited", "nope");

      if (hasVisited == "nope") {
        new MaterialTapTargetPrompt.Builder(this)
                .setTarget(spinner)
                .setPromptBackground(new RectanglePromptBackground())
                .setPromptFocal(new RectanglePromptFocal())
                .setPrimaryText("Переключение между данными")
                .setSecondaryText("Нажав на раскрывающийся список, Вы можете выбрать нужный раздел редактирования данных")
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

        SharedPreferences.Editor e = sp.edit();
        e.putString("hasVisited", "yes");
        e.commit();
      }

      final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowTitleEnabled(false);
      getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
      input_subject = findViewById(R.id.input_subject);
      list_subjects = findViewById(R.id.list_subjects);


      final AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          switch (position)
          { case 0:
           select_subject();
           position_spinner=position;
           break;
            case 1:
              select_audience();
              position_spinner=position;
              break;
            case 2:
              select_educator();
              position_spinner=position;
              break;
          }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
      };
      spinner.setOnItemSelectedListener(itemSelectedListener);

    clear_subjects= findViewById(R.id.clear_subjects);
    clear_subjects.setBackgroundResource(R.drawable.ic_clear);
    clear_subjects.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        switch(position_spinner) {
          case 0:
          onCreateDialogClearSubjects().show();
          break;
          case 1:
          onCreateDialogClearAudiences().show();
          break;
          case 2:
          onCreateDialogClearEducators().show();
          break;
        }}
    });
    }
  }

  void select_subject(){
    fragment_subject fragment= new fragment_subject();
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_frame, fragment)
            .addToBackStack(null)
            .commit();
  }
  void select_audience(){
    fragment_audience fragment= new fragment_audience();
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_frame, fragment)
            .addToBackStack(null)
            .commit();
  }
  void select_educator(){
    fragment_educator fragment= new fragment_educator();
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_frame, fragment)
            .addToBackStack(null)
            .commit();
  }
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:
        Intent intent = new Intent(activityEditData.this,MainActivity.class);
        finish();
        startActivity(intent);
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }


  public Dialog onCreateDialogClearSubjects() {

    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
    builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        clear_subjects();
      }
    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    }).setTitle("Очистить предметы?");
    return builder.create();
  }

  public Dialog onCreateDialogClearAudiences() {

    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
    builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
       clear_audiences();
      }
    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    }).setTitle("Очистить аудитории?");
    return builder.create();
  }

  public Dialog onCreateDialogClearEducators() {

    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
    builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        clear_educators();
      }
    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    }).setTitle("Очистить преподавателей?");
    return builder.create();
  }
  void clear_subjects () {

    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + ScheduleClass.subjects.TABLE_NAME);
    db.execSQL("CREATE TABLE " + ScheduleClass.subjects.TABLE_NAME + " ("
            + ScheduleClass.subjects.idd_subject + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.subjects.subject + " STRING UNIQUE ON CONFLICT IGNORE );");
    db.execSQL("INSERT INTO " + ScheduleClass.subjects.TABLE_NAME + " (" + ScheduleClass.subjects.subject + ") VALUES ('Предмет');");
    select_subject();
  }

  void clear_audiences(){
    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + ScheduleClass.audiences.TABLE_NAME);
    db.execSQL("CREATE TABLE " + ScheduleClass.audiences.TABLE_NAME + " ("
            + ScheduleClass.audiences.idd_audience + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.audiences.audience + " STRING UNIQUE ON CONFLICT IGNORE );");
    db.execSQL("INSERT INTO " + ScheduleClass.audiences.TABLE_NAME + " (" + ScheduleClass.audiences.audience + ") VALUES ('Аудитория');");
    select_audience();

  }

  void clear_educators(){
    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + ScheduleClass.educators.TABLE_NAME);
    db.execSQL("CREATE TABLE " + ScheduleClass.educators.TABLE_NAME + " ("
            + ScheduleClass.educators.idd_educator + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.educators.educator + " STRING UNIQUE ON CONFLICT IGNORE );");
    db.execSQL("INSERT INTO " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator + ") VALUES ('Преподаватель');");
    select_educator();

  }



  @Override
    public void onFragmentInteraction(Uri uri) {

    }
  }


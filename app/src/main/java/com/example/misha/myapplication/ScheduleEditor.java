package com.example.misha.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.misha.myapplication.adapter.SchedulePagerAdapter;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import static com.example.misha.myapplication.data.ScheduleClass.calls.CALLS;
import static com.example.misha.myapplication.data.ScheduleClass.calls.time;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.SCHEDULE;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_day;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_week;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.TYPELESSON;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.typelesson;


public class ScheduleEditor extends AppCompatActivity {

    Switch switcher_save;



    Integer position_week = 0;
    Integer position_day = 0;
    Integer flag_autosave = 1;

    Integer flag_save = 0;

    ViewPager viewPager;
    SchedulePagerAdapter pagerAdapter;
    private ScheduleDB ScheduleDB;

    final Context context = this;


/*
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

          copy_downOne.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  try{
                  SubjectEditTwo.setSelection(subject_list.indexOf(Tuesday.get(0).subject()));
                  AudienceEditTwo.setSelection(audience_list.indexOf(Tuesday.get(0).audience));
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




          clearOne.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  SubjectEditOne.setSelection(0);
                  AudienceEditOne.setSelection(0);
                  EducatorEditOne.setSelection(0);
                  typeEditOne_tuesday.clearCheck();
              }
          });


/*


    }

  /*  public void start(Integer select_week) {

        position_week = select_week;
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
        }*/


    public void onCreate(Bundle savedInstanceState) {

        ScheduleDB = new ScheduleDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        switcher_save = findViewById(R.id.switcher_save);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_editor_menu);
        toolbar.setOverflowIcon(drawable);

        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new SchedulePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(6);



        final MaterialBetterSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.weeks));
        spinner.setAdapter(arrayAdapter);


        SharedPreferences settings = getSharedPreferences("choice_week", 0);
        Integer current_week = Integer.valueOf(settings.getString("position", "0"));

        spinner.setText(arrayAdapter.getItem(current_week));


        switcher_save.setChecked(true);
        switcher_save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    flag_autosave = 1;
                } else {
                    flag_autosave = 0;
                }
            }
        });

        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position_day = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        spinner.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                spinner.clearFocus();
            }
        });

        final AdapterView.OnItemClickListener itemClickdListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag_autosave == 1) {
                }
                position_week = position;
            }
        };
        spinner.setOnItemClickListener(itemClickdListener);
    }



    @Override
    public void onResume() {
        super.onResume();
    }



    private void clear_day() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save = 1;
        Integer number_day = (position_day + 1);
        Integer number_week = (position_week + 1);
        ContentValues values = new ContentValues();
        values.put("id_subject", "");
        values.put("id_audience", "");
        values.put("id_educator", "");
        values.put("id_typelesson", "");
        db.update(SCHEDULE, values, " ( " + id_day + "=" + number_day + " ) AND ( " + id_week + "=" + number_week + " )", null);
    }


    private void clear_week() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save = 1;
        Integer number_week = (position_week + 1);
        ContentValues values = new ContentValues();
        values.put("id_subject", "");
        values.put("id_audience", "");
        values.put("id_educator", "");
        values.put("id_typelesson", "");
        db.update(SCHEDULE, values, id_week + "=" + number_week, null);
    }


    private void clear_full() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save = 1;
        ContentValues values = new ContentValues();
        values.put("id_subject", "");
        values.put("id_audience", "");
        values.put("id_educator", "");
        values.put("id_typelesson", "");
        db.update(SCHEDULE, values, null, null);
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
                flag_save = 1;
            }
        }).setTitle("В данный момент активирована функция автосохранения");
        return builder.create();
    }


    public Dialog onCreateDialogClearDay() {
        String name_day = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        switch (position_day) {
            case 0:
                name_day = "Понедельник?";
                break;
            case 1:
                name_day = "Вторник?";
                break;
            case 2:
                name_day = "Среду?";
                break;
            case 3:
                name_day = "Четверг?";
                break;
            case 4:
                name_day = "Пятницу?";
                break;
            case 5:
                name_day = "Субботу?";
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
        }).setTitle("Очистить " + name_day);
        return builder.create();
    }


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
        }).setTitle("Очистить " + (position_week + 1) + " неделю?");
        return builder.create();
    }


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
        return builder.create();
    }


    public Dialog onCreateDialogSaveOnExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        }).setTitle("Сохранить расписание?");
        return builder.create();
    }


    public Dialog onCreateDialogSaveUnevenWeeks() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Сохранить расписание текущей недели в нечетные недели?");
        return builder.create();
    }


    public Dialog onCreateDialogSaveEvenWeeks() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Сохранить расписание текущей недели в четные недели?");

        return builder.create();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (flag_save == 1) {
                    Intent intent = new Intent(ScheduleEditor.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    switch (flag_autosave) {
                        case 0:
                            onCreateDialogSaveOnExit().show();
                            return true;
                        case 1:
                            Intent intent = new Intent(ScheduleEditor.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                            return true;
                    }
                }
                return true;
            case R.id.save_schedule:
                switch (flag_autosave) {
                    case 0:
                        flag_save = 1;
                        return true;
                    case 1:
                        onCreateDialogSaveSchedule().show();
                        return true;
                }
                return true;
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
        }
    }

    @Override
    public void onBackPressed() {
        if (flag_save == 1) {
            Intent intent = new Intent(ScheduleEditor.this, MainActivity.class);
            finish();
            startActivity(intent);
        } else {
            switch (flag_autosave) {
                case 0:
                    onCreateDialogSaveOnExit().show();
                    return;
                case 1:
                    finish();
                    return;
            }
        }
    }

  /*  void load_calls_schedule() {

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT " + time+ " FROM " + CALLS;
        Cursor cursor = db.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            calls_scheduleList.add(cursor.getString(0));
        }
        cursor.close();
    }

    void load_type_lesson() {

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "select " + typelesson + " from " + TYPELESSON;
        Cursor cursor = db.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            typelessonList.add(cursor.getString(0));
        }
        cursor.close();
    }*/



    /*public void saveschedule(Integer number_week) {


        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        position_week = number_week;




            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                    "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                    "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                    "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                    "WHERE " + typelesson + "='" + MondayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.id + "=" + ((position_week * 36) + 1));

*/
        /*    db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }*/

    }


package com.example.misha.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.misha.myapplication.adapter.SchedulePagerAdapter;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;
import java.util.List;


public class ScheduleEditor extends AppCompatActivity {

    Switch switcher_save;


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


    Integer position_week = 0;
    Integer position_day = 0;
    Integer flag_autosave = 1;

    Integer flag_save = 0;
    List<Lesson> Monday;

    ArrayList<String> calls_scheduleList = new ArrayList<>();
    ArrayList<String> typelessonList = new ArrayList<>();
    ViewPager viewPager;
    SchedulePagerAdapter pagerAdapter;
    Boolean first = true;
    private ScheduleDB ScheduleDB;

    final Context context = this;


    public void monday_fill() {

    }

    /*  public void tuesday_fill() {
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

          typeEditOne_tuesday = findViewById(R.id.typeEdit_tuesday);
          typeEditTwo_tuesday = findViewById(R.id.typeEditTwo_tuesday);
          typeEditThree_tuesday = findViewById(R.id.typeEditThree_tuesday);
          typeEditFour_tuesday = findViewById(R.id.typeEditFour_tuesday);
          typeEditFive_tuesday = findViewById(R.id.typeEditFive_tuesday);
          typeEditSix_tuesday = findViewById(R.id.typeEditSix_tuesday);

          Lesson();

          rb_lecture.setText(typelesson.get(0));
          rb_lectureTwo.setText(typelesson.get(0));
          rb_lectureThree.setText(typelesson.get(0));
          rb_lectureFour.setText(typelesson.get(0));
          rb_lectureFive.setText(typelesson.get(0));
          rb_lectureSix.setText(typelesson.get(0));
          rb_labwork.setText(typelesson.get(1));
          rb_labworkTwo.setText(typelesson.get(1));
          rb_labworkThree.setText(typelesson.get(1));
          rb_labworkFour.setText(typelesson.get(1));
          rb_labworkFive.setText(typelesson.get(1));
          rb_labworkSix.setText(typelesson.get(1));
          rb_practice.setText(typelesson.get(2));
          rb_practice.setText(typelesson.get(2));
          rb_practiceThree.setText(typelesson.get(2));
          rb_practiceFour.setText(typelesson.get(2));
          rb_practiceFive.setText(typelesson.get(2));
          rb_practiceSix.setText(typelesson.get(2));



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
    void update_data() {
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

        //   start(position_week);

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
        }


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


        radiobutton_class();

        final MaterialBetterSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.weeks));
        spinner.setAdapter(arrayAdapter);


        SharedPreferences settings = getSharedPreferences("choice_week", 0);
        Integer current_week = Integer.valueOf(settings.getString("position", "0"));

        spinner.setText(arrayAdapter.getItem(current_week));


        load_calls_schedule();
        load_type_lesson();
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
                if (first && positionOffset == 0 && positionOffsetPixels == 0)
                    onPageSelected(0);
                first = false;

            }

            @Override
            public void onPageSelected(int position) {

                position_day = position;
                switch (position) {
                    case 0:
                        monday_fill();
                        break;
                    case 1:
                        monday_fill();
                        break;
                    case 2:
                        monday_fill();
                        break;
                    case 3:
                        monday_fill();
                        break;
                    case 4:
                        monday_fill();
                        break;
                    case 5:
                        monday_fill();
                        break;
                }
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
                    saveschedule(position_week);
                }
                position_week = position;


                switch (tabLayout.getSelectedTabPosition()) {
                    case 0:
                        monday_fill();
                        break;
                    case 1:
                        monday_fill();
                        break;
                    case 2:
                        monday_fill();
                        break;
                    case 3:
                        monday_fill();
                        break;
                    case 4:
                        monday_fill();
                        break;
                    case 5:
                        monday_fill();
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
        db.update(SCHEDULE, values, " ( " + schedule.id_day + "=" + number_day + " ) AND ( " + schedule.id_week + "=" + number_week + " )", null);
        radiobutton_class();

    }

    private void clear_week() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save = 1;
        //Integer number_day= (position_day+1);
        Integer number_week = (position_week + 1);
        ContentValues values = new ContentValues();
        values.put("id_subject", "");
        values.put("id_audience", "");
        values.put("id_educator", "");
        values.put("id_typelesson", "");
        db.update(SCHEDULE, values, schedule.id_week + "=" + number_week, null);
        radiobutton_class();
        monday_fill();
    }

    private void clear_full() {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        flag_save = 1;
        // Integer number_week= (position_week+1);
        ContentValues values = new ContentValues();
        values.put("id_subject", "");
        values.put("id_audience", "");
        values.put("id_educator", "");
        values.put("id_typelesson", "");
        db.update(SCHEDULE, values, null, null);
        radiobutton_class();
        monday_fill();

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
                saveschedule(position_week);
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
        return builder.create();
    }


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
                            saveschedule(position_week);
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
                        saveschedule(position_week);
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
                    saveschedule(position_week);
                    finish();
                    return;
            }
        }
    }

    void load_calls_schedule() {

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT " + calls.time + " FROM " + CALLS;
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
    }

  /*  private void DataMonday() {
        Monday = new ArrayList<>();
        try {
            Monday.add(new Lesson("1", String.valueOf(calls_schedule.get(0)), MondayValueSubjectOne, MondayValueAudienceOne, MondayEducatorOne, MondayTypeLessonOne));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("2", String.valueOf(calls_schedule.get(1)), MondayValueSubjectTwo, MondayValueAudienceTwo, MondayEducatorTwo, MondayTypeLessonTwo));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("3", String.valueOf(calls_schedule.get(2)), MondayValueSubjectThree, MondayValueAudienceThree, MondayEducatorThree, MondayTypeLessonThree));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("4", String.valueOf(calls_schedule.get(3)), MondayValueSubjectFour, MondayValueAudienceFour, MondayEducatorFour, MondayTypeLessonFour));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("5", String.valueOf(calls_schedule.get(4)), MondayValueSubjectFive, MondayValueAudienceFive, MondayEducatorFive, MondayTypeLessonFive));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("6", String.valueOf(calls_schedule.get(5)), MondayValueSubjectSix, MondayValueAudienceSix, MondayEducatorSix, MondayTypeLessonSix));
        } catch (NullPointerException e) {
        }
    }
*/

    void radiobutton_class() {
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
        position_week = number_week;

        try {
            MondayStringSubjectEditOne = Monday.get(0).getSubjectEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditTwo = Monday.get(1).getSubjectEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditThree = Monday.get(2).getSubjectEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditFour = Monday.get(3).getSubjectEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditFive = Monday.get(4).getSubjectEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringSubjectEditSix = Monday.get(5).getSubjectEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditOne = Monday.get(0).getAudienceEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditTwo = Monday.get(1).getAudienceEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditThree = Monday.get(2).getAudienceEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditFour = Monday.get(3).getAudienceEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditFive = Monday.get(4).getAudienceEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringAudienceEditSix = Monday.get(5).getAudienceEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditOne = Monday.get(0).getEducatorEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditTwo = Monday.get(1).getEducatorEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditThree = Monday.get(2).getEducatorEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditFour = Monday.get(3).getEducatorEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditFive = Monday.get(4).getEducatorEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringEducatorEditSix = Monday.get(5).getEducatorEdit().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditOne = Monday.get(0).getTypeLesson().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditTwo = Monday.get(1).getTypeLesson().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditThree = Monday.get(2).getTypeLesson().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditFour = Monday.get(3).getTypeLesson().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditFive = Monday.get(4).getTypeLesson().toString();
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            MondayStringTypeLessonEditSix = Monday.get(5).getTypeLesson().toString();
        } catch (IndexOutOfBoundsException e) {
        }
/*

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

}
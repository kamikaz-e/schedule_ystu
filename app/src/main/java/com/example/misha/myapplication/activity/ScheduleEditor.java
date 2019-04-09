package com.example.misha.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.PopupMenu;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.SchedulePagerAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


public class ScheduleEditor extends AppCompatActivity {

    Integer position_week = 0;
    Integer position_day = 0;
    Integer flag_autosave = 1;
    ViewPager viewPager;
    SchedulePagerAdapter pagerAdapter;

/*
          copy_downOne.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  try{
                  SubjectEditTwo.setSelection(subject_list.indexOf(Tuesday.get(0).subject()));
                  AudienceEditTwo.setSelection(audience_list.indexOf(Tuesday.get(0).audience));
                  EducatorEditTwo.setSelection(typelessonList.indexOf(Tuesday.get(0).educator));
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
                  EducatorEditOne.setSelection(typelessonList.indexOf(Tuesday.get(1).educator));
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

  /*  public void updateListView(Integer select_week) {

        position_week = select_week;
        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        //1 занятие
        Cursor cursor = db.rawQuery("SELECT " + subject + " FROM " + subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idSubjectEditOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                MondayValueSubjectOne = cursor.getString(idSubjectEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idAudienceEditOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                MondayValueAudienceOne = cursor.getString(idAudienceEditOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                MondayEducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                MondayTypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }*/


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_editor_menu);


        toolbar.setOverflowIcon(drawable);

        viewPager = findViewById(R.id.viewpager);
        PopupMenu menu = new PopupMenu(this, viewPager);
       // menu.setOnMenuItemClickListener(ScheduleEditor.this::onMenuItemClicked);
        pagerAdapter = new SchedulePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(6);

        final MaterialBetterSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.weeks));
        spinner.setAdapter(arrayAdapter);

        SharedPreferences settings = getSharedPreferences("choice_week", 0);
        int current_week = Integer.valueOf(settings.getString("position", "0"));

        spinner.setText(arrayAdapter.getItem(current_week));
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


    private boolean onMenuItemClicked(MenuItem menuItem) {
        return  false;
    }


/*
    public Dialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить расписание полностью?");
        return builder.create();
    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ScheduleEditor.this, MainActivity.class);
                finish();
                startActivity(intent);
        }
        return true;
    }}

 /*   public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (flag_save == 1) {

                return true;
            case R.id.save_schedule:

                return true;
            case R.id.save_uneven_weeks:

                return true;
            case R.id.save_even_weeks:

                return true;
            case R.id.clear_day:

                return true;
            case R.id.clear_week:

                return true;
            case R.id.clear_full:

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
       }
    }


    public void saveschedule(Integer number_week) {


        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        position_week = number_week;




            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_subject + "= (SELECT " + ScheduleClass.subjects.idd_subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + " " +
                    "WHERE " + ScheduleClass.subjects.subject + "='" + MondayStringSubjectEditOne + "') WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_audience + "= (SELECT " + ScheduleClass.audiences.idd_audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + " " +
                    "WHERE " + ScheduleClass.audiences.audience + "='" + MondayStringAudienceEditOne + "') WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_educator + "= (SELECT " + ScheduleClass.educators.idd_educator + " FROM " + ScheduleClass.educators.TABLE_NAME + " " +
                    "WHERE " + ScheduleClass.educators.educator + "='" + MondayStringEducatorEditOne + "') WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1));
            db.execSQL("UPDATE " + ScheduleClass.schedule.TABLE_NAME + " SET " + ScheduleClass.schedule.id_typelesson + "= (SELECT " + ScheduleClass.typelessons.idd_typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + " " +
                    "WHERE " + typelesson + "='" + MondayStringTypeLessonEditOne + "') WHERE " + ScheduleClass.schedule.ID + "=" + ((position_week * 36) + 1));


      db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

*/




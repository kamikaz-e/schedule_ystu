package com.example.misha.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

import static android.support.v4.view.PagerAdapter.POSITION_NONE;

public class activityEditData extends AppCompatActivity {


  EditText input_subject;
  ListView list_subjects;
  private ScheduleDB ScheduleDB;
  final Context context = this;

  Button clear_subjects;
  Integer position_spinner = 0;

  TabLayout tabLayout;
  ViewPager viewPager;
  Edit_Data_ViewPagerAdapter viewPagerAdapter;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activityeditdata);

   /* final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowTitleEnabled(false);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);*/



     /* SharedPreferences sp = getPreferences(MODE_PRIVATE);
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
      }*/



    ScheduleDB = new ScheduleDB(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);


    viewPager = (ViewPager) findViewById(R.id.viewPager);
    viewPagerAdapter = new Edit_Data_ViewPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(viewPagerAdapter);
    tabLayout = (TabLayout) findViewById(R.id.tabs);

    tabLayout.setupWithViewPager(viewPager);

    clear_subjects = findViewById(R.id.clear_subjects);
    clear_subjects.setBackgroundResource(R.drawable.ic_clear);
    clear_subjects.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        switch (tabLayout.getSelectedTabPosition()) {
          case 0:
            onCreateDialogClearSubjects().show();
            break;
           case 1:
             onCreateDialogClearAudiences().show();
            break;
          case 2:
            onCreateDialogClearEducators().show();
            break;
        }
      }
    });

  }


  void clear_subjects () {

    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + ScheduleClass.subjects.TABLE_NAME);
    db.execSQL("CREATE TABLE " + ScheduleClass.subjects.TABLE_NAME + " ("
            + ScheduleClass.subjects.idd_subject + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.subjects.subject + " STRING UNIQUE ON CONFLICT IGNORE );");
    db.execSQL("INSERT INTO " + ScheduleClass.subjects.TABLE_NAME + " (" + ScheduleClass.subjects.subject + ") VALUES ('Предмет');");
      if (!(viewPagerAdapter == null)) {
          viewPagerAdapter.notifyDataSetChanged();
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

  void clear_audiences () {
    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + ScheduleClass.audiences.TABLE_NAME);
    db.execSQL("CREATE TABLE " + ScheduleClass.audiences.TABLE_NAME + " ("
            + ScheduleClass.audiences.idd_audience + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.audiences.audience + " STRING UNIQUE ON CONFLICT IGNORE );");
    db.execSQL("INSERT INTO " + ScheduleClass.audiences.TABLE_NAME + " (" + ScheduleClass.audiences.audience + ") VALUES ('Аудитория');");
    if (!(viewPagerAdapter == null)) {
      viewPagerAdapter.notifyDataSetChanged();
    }

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



  void clear_educators () {
    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + ScheduleClass.educators.TABLE_NAME);
    db.execSQL("CREATE TABLE " + ScheduleClass.educators.TABLE_NAME + " ("
            + ScheduleClass.educators.idd_educator + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.educators.educator + " STRING UNIQUE ON CONFLICT IGNORE );");
    db.execSQL("INSERT INTO " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator + ") VALUES ('Преподаватель');");
    if (!(viewPagerAdapter == null)) {
      viewPagerAdapter.notifyDataSetChanged();
    }
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


















  public class Edit_Data_ViewPagerAdapter extends FragmentStatePagerAdapter {

    public Edit_Data_ViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      Fragment fragment = null;
      if (position == 0) {
        fragment = new fragment_subject();
      } else if (position == 1) {
        fragment = new fragment_audience();
      } else if (position == 2) {
        fragment = new fragment_educator();
      }
      return fragment;

    }
      @Override
      public int getItemPosition(Object object) {
          return POSITION_NONE;
      }

    @Override
    public int getCount() {
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      String title = null;
      if (position == 0) {
        title = "Предметы";
      } else if (position == 1) {
        title = "Аудитории";
      } else if (position == 2) {
        title = "Преподаватели";
      }
      return title;
    }

  }


  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:
        Intent intent = new Intent(activityEditData.this, MainActivity.class);
        finish();
        startActivity(intent);
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }


}




package com.example.misha.myapplication.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.misha.myapplication.MainActivity;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.calls;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.Calendar;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class ActivityCallSchedule extends AppCompatActivity {

  String select_time_partOne;
  String select_time_fullOne;
  String select_time_partTwo;
  String select_time_fullTwo;
  String select_time_partThree;
  String select_time_fullThree;
  String select_time_partFour;
  String select_time_fullFour;
  String select_time_partFive;
  String select_time_fullFive;
  String select_time_partSix;
  String select_time_fullSix;
  Integer flag=0;
  EditText oneTime;
  EditText twoTime;
  EditText threeTime;
  EditText fourTime;
  EditText fiveTime;
  EditText sixTime;
  private ScheduleDB ScheduleDB;
  Calendar Time=Calendar.getInstance();
  Integer start=1;
  Button button_toolbar;
  String hasVisited;
  SharedPreferences sp;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // STOPSHIP: 23.03.2019
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_call_schedule);
    android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);


    button_toolbar = findViewById(R.id.toolbar_but);
    button_toolbar.setBackgroundResource(R.drawable.ic_clear);
    button_toolbar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onCreateDialogClear().show();
      }
    });
    oneTime = findViewById(R.id.OneTime);
    twoTime = findViewById(R.id.TwoTime);
    threeTime = findViewById(R.id.ThreeTime);
    fourTime = findViewById(R.id.FourTime);
    fiveTime = findViewById(R.id.FiveTime);
    sixTime = findViewById(R.id.SixTime);
    ScheduleDB = new ScheduleDB(this);
    start();


    sp = getPreferences(MODE_PRIVATE);
    hasVisited = sp.getString("hasVisited", "nope");
    if (hasVisited == "nope") {


      new MaterialTapTargetPrompt.Builder(ActivityCallSchedule.this)
              .setTarget(R.id.card_viewOneTime)
              .setPromptBackground(new RectanglePromptBackground())
              .setPromptFocal(new RectanglePromptFocal())
              .setPrimaryText("Расписание звонков")
              .setSecondaryText("Вы можете задать расписание звонков для занятий. При нажатии на кнопку «Выбрать» укажите время начала занятия, нажмите кнопку «Ок», затем укажите время окончания занятия и так же нажмите кнопку «Ок»")
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

    }

  }


  public Dialog onCreateDialogClear() {

    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
    builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        clear_calls();
      }
    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    }).setTitle("Очистить расписание звонков?");
    return builder.create();
  }

  public void getTimeOne(View v) {
    new TimePickerDialog(ActivityCallSchedule.this, timeone,
            Time.get(Calendar.HOUR_OF_DAY),
            Time.get(Calendar.MINUTE), true)
            .show();
    start=1;
  }

  private void setInitialTimeOne() {
    if (start==1) {
      select_time_partOne=(DateUtils.formatDateTime(this,
              Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
    }  else
    {   select_time_fullOne= select_time_partOne+DateUtils.formatDateTime(this,
            Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
      oneTime.setText(select_time_fullOne);
    }
    if (start==1){
      new TimePickerDialog(ActivityCallSchedule.this, timeone,
              Time.get(Calendar.HOUR_OF_DAY),
              Time.get(Calendar.MINUTE), true)
              .show();
      start=0; }
  }

  TimePickerDialog.OnTimeSetListener timeone=new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
      Time.set(Calendar.MINUTE, minute);
      setInitialTimeOne();
    }
  };

  public void getTimeTwo(View v) {
    new TimePickerDialog(ActivityCallSchedule.this, timetwo,
            Time.get(Calendar.HOUR_OF_DAY),
            Time.get(Calendar.MINUTE), true)
            .show();
    start=1;
  }


  private void setInitialTimeTwo() {
    if (start==1) {
      select_time_partTwo=(DateUtils.formatDateTime(this,
              Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
    }  else
    {   select_time_fullTwo= select_time_partTwo+DateUtils.formatDateTime(this,
            Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
      twoTime.setText(select_time_fullTwo);
      }
    if (start==1){
      new TimePickerDialog(ActivityCallSchedule.this, timetwo,
              Time.get(Calendar.HOUR_OF_DAY),
              Time.get(Calendar.MINUTE), true)
              .show();
      start=0; }
  }

  TimePickerDialog.OnTimeSetListener timetwo=new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
      Time.set(Calendar.MINUTE, minute);
      setInitialTimeTwo();
    }
  };


  public void getTimeThree(View v) {
    new TimePickerDialog(ActivityCallSchedule.this, timeThree,
            Time.get(Calendar.HOUR_OF_DAY),
            Time.get(Calendar.MINUTE), true)
            .show();
    start=1;
  }


  private void setInitialTimeThree() {
    if (start==1) {
      select_time_partThree=(DateUtils.formatDateTime(this,
              Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
    }  else
    {   select_time_fullThree= select_time_partThree+DateUtils.formatDateTime(this,
            Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
      threeTime.setText(select_time_fullThree);
   }
    if (start==1){
      new TimePickerDialog(ActivityCallSchedule.this, timeThree,
              Time.get(Calendar.HOUR_OF_DAY),
              Time.get(Calendar.MINUTE), true)
              .show();
      start=0; }
  }

  TimePickerDialog.OnTimeSetListener timeThree=new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
      Time.set(Calendar.MINUTE, minute);
      setInitialTimeThree();
    }
  };


  public void getTimeFour(View v) {
    new TimePickerDialog(ActivityCallSchedule.this, timeFour,
            Time.get(Calendar.HOUR_OF_DAY),
            Time.get(Calendar.MINUTE), true)
            .show();
    start=1;
  }


  private void setInitialTimeFour() {
    if (start==1) {
      select_time_partFour=(DateUtils.formatDateTime(this,
              Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
    }  else
    {   select_time_fullFour= select_time_partFour+DateUtils.formatDateTime(this,
            Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
      fourTime.setText(select_time_fullFour);
    }
    if (start==1){
      new TimePickerDialog(ActivityCallSchedule.this, timeFour,
              Time.get(Calendar.HOUR_OF_DAY),
              Time.get(Calendar.MINUTE), true)
              .show();
      start=0; }
  }

  TimePickerDialog.OnTimeSetListener timeFour=new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
      Time.set(Calendar.MINUTE, minute);
      setInitialTimeFour();
    }
  };


  public void getTimeFive(View v) {
    new TimePickerDialog(ActivityCallSchedule.this, timeFive,
            Time.get(Calendar.HOUR_OF_DAY),
            Time.get(Calendar.MINUTE), true)
            .show();
    start=1;
  }

  private void setInitialTimeFive() {
    if (start==1) {
      select_time_partFive=(DateUtils.formatDateTime(this,
              Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
    }  else
    {   select_time_fullFive= select_time_partFive+DateUtils.formatDateTime(this,
            Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
      fiveTime.setText(select_time_fullFive);
      }
    if (start==1){
      new TimePickerDialog(ActivityCallSchedule.this, timeFive,
              Time.get(Calendar.HOUR_OF_DAY),
              Time.get(Calendar.MINUTE), true)
              .show();
      start=0; }
  }

  TimePickerDialog.OnTimeSetListener timeFive=new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
      Time.set(Calendar.MINUTE, minute);
      setInitialTimeFive();
    }
  };

  public void getTimeSix(View v) {
    new TimePickerDialog(ActivityCallSchedule.this, timeSix,
            Time.get(Calendar.HOUR_OF_DAY),
            Time.get(Calendar.MINUTE), true)
            .show();
    start=1;
  }


  private void setInitialTimeSix() {
    if (start==1) {
      select_time_partSix=(DateUtils.formatDateTime(this,
              Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)+ " - ");
    }  else
    {   select_time_fullSix= select_time_partSix+DateUtils.formatDateTime(this,
            Time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
      sixTime.setText(select_time_fullSix);
    }
    if (start==1){
      new TimePickerDialog(ActivityCallSchedule.this, timeSix,
              Time.get(Calendar.HOUR_OF_DAY),
              Time.get(Calendar.MINUTE), true)
              .show();
      start=0; }
  }

  TimePickerDialog.OnTimeSetListener timeSix=new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      Time.set(Calendar.HOUR_OF_DAY, hourOfDay);
      Time.set(Calendar.MINUTE, minute);
      setInitialTimeSix();
    }
  };


  void start(){
    SQLiteDatabase db = ScheduleDB.getReadableDatabase();
    flag=0;
    String searchQuery = "SELECT "+ calls.time +" FROM " + calls.TABLE_NAME;
    Cursor cursor = db.rawQuery(searchQuery, null);
    while(cursor.moveToNext()) {
      switch (flag){
        case 0: oneTime.setText(String.valueOf(cursor.getString(0)));
          select_time_fullOne=(cursor.getString(0));
          flag++;break;
        case 1: twoTime.setText(String.valueOf(cursor.getString(0)));
          select_time_fullTwo=(cursor.getString(0));
          flag++;break;
        case 2: threeTime.setText(String.valueOf(cursor.getString(0)));
          select_time_fullThree=(cursor.getString(0));
          flag++;break;
        case 3: fourTime.setText(String.valueOf(cursor.getString(0)));
          select_time_fullFour=(cursor.getString(0));
          flag++;break;
        case 4: fiveTime.setText(String.valueOf(cursor.getString(0)));
          select_time_fullFive=(cursor.getString(0));
          flag++;break;
        case 5: sixTime.setText(String.valueOf(cursor.getString(0)));
          select_time_fullSix=(cursor.getString(0));
          flag++;break;}
    }
    cursor.close();
  }


  void save_calls(){
    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.beginTransaction();
    try {
      db.execSQL("DROP TABLE " + calls.TABLE_NAME);
      db.execSQL("CREATE TABLE " + calls.TABLE_NAME + " ("
              + calls.id_call + " INTEGER PRIMARY KEY AUTOINCREMENT, "
              + calls.time + " STRING );");
      db.execSQL("INSERT INTO " + calls.TABLE_NAME + " (" + calls.time + ") VALUES ('"+ select_time_fullOne +"');");
      db.execSQL("INSERT INTO " + calls.TABLE_NAME + " (" + calls.time + ") VALUES ('"+ select_time_fullTwo +"');");
      db.execSQL("INSERT INTO " + calls.TABLE_NAME + " (" + calls.time + ") VALUES ('"+ select_time_fullThree +"');");
      db.execSQL("INSERT INTO " + calls.TABLE_NAME + " (" + calls.time + ") VALUES ('"+ select_time_fullFour +"');");
      db.execSQL("INSERT INTO " + calls.TABLE_NAME + " (" + calls.time + ") VALUES ('"+ select_time_fullFive +"');");
      db.execSQL("INSERT INTO " + calls.TABLE_NAME + " (" + calls.time + ") VALUES ('"+ select_time_fullSix +"');");

      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
  }

  void clear_calls(){
    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + calls.TABLE_NAME);
    String calls_schedule = "CREATE TABLE " + ScheduleClass.calls.TABLE_NAME + " ("
            + ScheduleClass.calls.id_call + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.calls.time + " STRING );";
    db.execSQL(calls_schedule);
    for (int i=0;i<6;i++){
      db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('');");}
    oneTime.setText("");
    twoTime.setText("");
    threeTime.setText("");
    fourTime.setText("");
    fiveTime.setText("");
    sixTime.setText("");
    select_time_fullOne="";
    select_time_fullTwo="";
    select_time_fullThree="";
    select_time_fullFour="";
    select_time_fullFive="";
    select_time_fullSix="";
  }

  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:

          save_calls();
          Intent intent = new Intent(ActivityCallSchedule.this, MainActivity.class);
          finish();
          startActivity(intent);

        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityCallSchedule.this, MainActivity.class);
        finish();
        startActivity(intent);
    }


}
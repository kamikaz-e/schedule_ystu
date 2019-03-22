package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.audiences;
import com.example.misha.myapplication.data.ScheduleDB;
import java.util.ArrayList;

public class activityaudience extends AppCompatActivity {
  EditText input_audience;
  ListView list_audiences;
  private ScheduleDB ScheduleDB;
  final ArrayList<String> audience_list = new ArrayList<>();
  public ArrayAdapter<String> adapter;
  Button button_toolbar;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activityaudience);

    android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    ScheduleDB = new ScheduleDB(this);
    input_audience = findViewById(R.id.input_audience);
    list_audiences = findViewById(R.id.list_audiences);
    button_toolbar= findViewById(R.id.toolbar_but);
    button_toolbar.setBackgroundResource(R.drawable.ic_clear);
    button_toolbar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
onCreateDialogClear().show();
      }
    });

    start();

    input_audience.setOnKeyListener(new View.OnKeyListener() {

      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
          if (keyCode == KeyEvent.KEYCODE_ENTER) {
            String audience = input_audience.getText().toString();
            SQLiteDatabase db = ScheduleDB.getWritableDatabase();
            db.execSQL("INSERT INTO " + audiences.TABLE_NAME + " (" + audiences.audience + ") VALUES ('" + audience + "');");
            input_audience.setText("");
            start();
            adapter.notifyDataSetChanged();
            return true;
          }
        return true;
      }
    });
  }

  public Dialog onCreateDialogClear() {

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

  void clear_audiences(){
    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
    db.execSQL("DROP TABLE " + audiences.TABLE_NAME);
    db.execSQL("CREATE TABLE " + ScheduleClass.audiences.TABLE_NAME + " ("
        + ScheduleClass.audiences.idd_audience + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + ScheduleClass.audiences.audience + " STRING UNIQUE ON CONFLICT IGNORE );");
    db.execSQL("INSERT INTO " + audiences.TABLE_NAME + " (" + audiences.audience + ") VALUES ('Аудитория');");
    adapter.notifyDataSetChanged();
    start();
  }

  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
  public void start(){

    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, audience_list);
    list_audiences.setAdapter(adapter);

    SQLiteDatabase db = ScheduleDB.getReadableDatabase();
    String searchQuery = "SELECT "+ audiences.audience +" FROM " + audiences.TABLE_NAME + " WHERE "+ audiences.idd_audience +">1;";
    audience_list.clear();
    Cursor cursor = db.rawQuery(searchQuery, null);
    while(cursor.moveToNext()) {
      audience_list.add(cursor.getString(0));
    }
    cursor.close();
  }
}
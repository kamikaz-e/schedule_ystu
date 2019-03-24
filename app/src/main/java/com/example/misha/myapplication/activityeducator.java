package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.educators;
import com.example.misha.myapplication.data.ScheduleDB;
import java.util.ArrayList;

public class activityeducator extends AppCompatActivity {
  EditText input_educator;
  ListView list_educators;
  private ScheduleDB ScheduleDB;

  final Context context = this;
  final ArrayList<String> educator_list = new ArrayList<>();
  public ArrayAdapter<String> adapter;
  Button button_toolbar;
  String select_item="";
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activityeducator);

    android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    ScheduleDB = new ScheduleDB(this);
    input_educator = findViewById(R.id.input_educator);
    list_educators = findViewById(R.id.list_educators);
    button_toolbar= findViewById(R.id.toolbar_but);

    adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, educator_list);
    list_educators.setAdapter(adapter);

    list_educators.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                              long id) {
        TextView textView = (TextView) itemClicked;
        select_item = textView.getText().toString();
        onCreateDialogDeleteItem().show();
      }
    });

    button_toolbar.setBackgroundResource(R.drawable.ic_clear);
    button_toolbar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onCreateDialogClear().show();
      }
    });
    start();

    input_educator.setOnKeyListener(new View.OnKeyListener() {

      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
          if (keyCode == KeyEvent.KEYCODE_ENTER) {
            String educator = input_educator.getText().toString();
            SQLiteDatabase db = ScheduleDB.getWritableDatabase();
            db.execSQL("INSERT INTO " + educators.TABLE_NAME + " (" + educators.educator + ") VALUES ('" + educator + "');");
            input_educator.setText("");
            start();
            adapter.notifyDataSetChanged();
            return true;
          }
        return true;
      }
    });
  }

  public Dialog onCreateDialogDeleteItem() {

    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
    builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        db.execSQL("DELETE FROM " + educators.TABLE_NAME + " WHERE "+ educators.educator + "='"+ select_item+"'");
        start();
        adapter.notifyDataSetChanged();
      }
    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    }).setTitle("Удалить преподавателя «"+select_item+"»?");
    return builder.create();
  }


  public Dialog onCreateDialogClear() {

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

 void clear_educators(){
   SQLiteDatabase db = ScheduleDB.getWritableDatabase();
   db.execSQL("DROP TABLE " + educators.TABLE_NAME);
   db.execSQL("CREATE TABLE " + ScheduleClass.educators.TABLE_NAME + " ("
       + ScheduleClass.educators.idd_educator + " INTEGER PRIMARY KEY AUTOINCREMENT, "
       + ScheduleClass.educators.educator + " STRING UNIQUE ON CONFLICT IGNORE );");
   db.execSQL("INSERT INTO " + educators.TABLE_NAME + " (" + educators.educator + ") VALUES ('Преподаватель');");
   adapter.notifyDataSetChanged();
   start();
 }
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:
        Intent intent = new Intent(activityeducator.this,MainActivity.class);
        finish();
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  public void start(){

    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, educator_list);
    list_educators.setAdapter(adapter);

    SQLiteDatabase db = ScheduleDB.getReadableDatabase();
    String searchQuery = "SELECT "+ educators.educator +" FROM " + educators.TABLE_NAME + " WHERE "+ educators.idd_educator +">1;";
    educator_list.clear();
    Cursor cursor = db.rawQuery(searchQuery, null);
    while(cursor.moveToNext()) {
      educator_list.add(cursor.getString(0));
    }
    cursor.close();
  }
}
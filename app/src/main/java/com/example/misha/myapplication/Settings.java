package com.example.misha.myapplication;

import static com.example.misha.myapplication.data.ScheduleClass.calls.id_call;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.audiences;
import com.example.misha.myapplication.data.ScheduleClass.calls;
import com.example.misha.myapplication.data.ScheduleClass.date_start;
import com.example.misha.myapplication.data.ScheduleClass.educators;
import com.example.misha.myapplication.data.ScheduleClass.schedule;
import com.example.misha.myapplication.data.ScheduleClass.subjects;
import com.example.misha.myapplication.data.ScheduleClass.typelessons;
import com.example.misha.myapplication.data.ScheduleDB;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.CirclePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.FullscreenPromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.CirclePromptFocal;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class Settings extends AppCompatActivity {

  private static final String schedule_import= "http://192.168.0.61/phpmyadmin/requests/schedule_table.php";
  private static final String subjects_import = "http://192.168.0.61/phpmyadmin/requests/subjects_table.php";
  private static final String audiences_import = "http://192.168.0.61/phpmyadmin/requests/audiences_table.php";
  private static final String educators_import= "http://192.168.0.61/phpmyadmin/requests/educators_table.php";
  private static final String typelessons_import = "http://192.168.0.61/phpmyadmin/requests/typelessons_table.php";
  private static final String call_schedule = "http://192.168.0.61/phpmyadmin/requests/call_schedule.php";
  private static final String date = "http://192.168.0.61/phpmyadmin/requests/date_start.php";
  private static final String insert = "http://192.168.0.61/phpmyadmin/requests/export.php";
  final String sch="schedule";
  final String sub="subject";
  final String aud="audience";
  final String edu="educator";
  final String typ="typelesson";
  final String cal="calls";
  final String dat="date_start";

  Calendar Date = Calendar.getInstance();

  String database_name="";

  private ScheduleDB ScheduleDB;
  RequestQueue requestQueue;
  ProgressDialog progressDialog;
  String json_schedule = "";
  String json_subjects = "";
  String json_audiences = "";
  String json_educators = "";
  String json_typelessons = "";
  String json_calls = "";
  String json_date = "";
  String name_db_string="database";
  final Context context = this;
  public ArrayAdapter<String> adapter;
  String  current_date;
  RelativeLayout layout_pick_week;
  RelativeLayout layout_import;
  RelativeLayout layout_export;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ScheduleDB = new ScheduleDB(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.settings);
    android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
/*
    SQLiteDatabase db = ScheduleDB.getReadableDatabase();
    db.isOpen();*/
    requestQueue = Volley.newRequestQueue(Settings.this);
    progressDialog = new ProgressDialog(Settings.this);

    layout_pick_week = findViewById(R.id.oneitem);
    layout_import = findViewById(R.id.twoitem);
    layout_export = findViewById(R.id.threeitem);

    SharedPreferences sp = getPreferences(MODE_PRIVATE);
    String hasVisited = sp.getString("hasVisited", "nope");
    if (hasVisited == "nope") {


      new MaterialTapTargetPrompt.Builder(Settings.this)
              .setTarget(layout_pick_week)
              .setPromptBackground(new RectanglePromptBackground())
              .setPromptFocal(new RectanglePromptFocal())
              .setPrimaryText("Дата начала семестра")
              .setSecondaryText("Выберите дату начала семестра для автоматического определения текущей учебной недели")
              .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
              .setBackgroundColour(Color.rgb(100, 100, 255))
              .setPrimaryTextColour(Color.rgb(255, 255, 255))
              .setSecondaryTextColour(Color.rgb(255, 255, 255))
              .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                  if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {

                    new MaterialTapTargetPrompt.Builder(Settings.this)
                            .setTarget(layout_import)
                            .setPromptBackground(new RectanglePromptBackground())
                            .setPromptFocal(new RectanglePromptFocal())
                            .setPrimaryText("Импорт расписания")
                            .setSecondaryText("Загрузка готового расписания по названию с сервера")
                            .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                            .setBackgroundColour(Color.rgb(100, 100, 255))
                            .setPrimaryTextColour(Color.rgb(255, 255, 255))
                            .setSecondaryTextColour(Color.rgb(255, 255, 255))
                            .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {


                              public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                                if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                  new MaterialTapTargetPrompt.Builder(Settings.this)
                                          .setTarget(layout_export)
                                          .setPromptBackground(new RectanglePromptBackground())
                                          .setPromptFocal(new RectanglePromptFocal())
                                          .setPrimaryText("Экспорт расписания")
                                          .setSecondaryText("Выгрузка расписания с заданным названием на сервер")
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
                            })
                            .show();
                  }
                }
              })
              .show();

      SharedPreferences.Editor e = sp.edit();
      e.putString("hasVisited", "yes");
      e.commit();
    }

    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);


    layout_pick_week.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        get_current_week();
      }
    });

    layout_import.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        onCreateDialogImport().show();
      }
    });

    layout_export.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        onCreateDialogExport().show();
      }
    });
  }


  void get_current_week(){
    new DatePickerDialog(Settings.this, dateone,
        Date.get(Calendar.YEAR),
        Date.get(Calendar.MONTH),
        Date.get(Calendar.DAY_OF_MONTH)).show();
  }
  DatePickerDialog.OnDateSetListener dateone = new DatePickerDialog.OnDateSetListener() {
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      Date.set(Calendar.YEAR, year);
      Date.set(Calendar.MONTH, monthOfYear);
      Date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      Calendar today = Calendar.getInstance();
      current_date = String.valueOf(Date.getTimeInMillis());
      SQLiteDatabase db = ScheduleDB.getWritableDatabase();
      db.execSQL("update " + date_start.TABLE_NAME + " set " + date_start.date + " = '" +
        current_date  + "' where " + date_start.id_date + " = " + 1);
     // Toast.makeText(context, String.valueOf(today.getTimeInMillis()), Toast.LENGTH_SHORT).show();





      SharedPreferences settings = getSharedPreferences("week", 0);
      SharedPreferences.Editor editor = settings.edit();
      editor.putLong("current_week", (Date.getTimeInMillis()));
      editor.commit();

    }
  };

  public Dialog onCreateDialogImport() {
    LayoutInflater li = LayoutInflater.from(context);
    View view = li.inflate(R.layout.dialog_signin, null);
    AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AppCompatAlertDialogStyle);
    builder.setView(view);
    final EditText name_db =  view.findViewById(R.id.name_schedule);
    builder.setCancelable(false).setPositiveButton("Импортировать", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        database_name= name_db.getText().toString();
        load_db(sub, subjects_import);
        load_db(aud,audiences_import);
        load_db(edu,educators_import);
        load_db(typ,typelessons_import);
        load_db(sch,schedule_import);
        load_db(cal,call_schedule);
        load_db(dat,date);
      }
    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
      }
    });
    return builder.create();
  }

  void load_db (final String table, final String url) {

    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            try {
              JSONArray jsonArray = new JSONArray(response);
              JSONObject obj;
              SQLiteDatabase db = ScheduleDB.getWritableDatabase();
              db.beginTransaction();
              try {
                for (int i = 0; i < jsonArray.length(); i++) {
                  obj = jsonArray.getJSONObject(i);
                  if (table == aud) {
                    String audience = obj.getString("audience");
                    db.execSQL("insert into " + audiences.TABLE_NAME + " (" + audiences.audience
                        + ") values ('" + audience + "');");
                  }
                  if (table == edu) {
                    String educator = obj.getString("educator");
                    db.execSQL("insert into " + educators.TABLE_NAME + " (" + educators.educator
                        + ") values ('" + educator + "');");
                  }

                  if (table == typ) {
                    String typelesson = obj.getString("typelesson");
                    db.execSQL(
                        "insert into " + typelessons.TABLE_NAME + " (" + typelessons.typelesson
                            + ") values ('" + typelesson + "');");
                  }
                  if (table == sub) {
                    String subject = obj.getString("subject");
                    db.execSQL("insert into " + subjects.TABLE_NAME + " (" + subjects.subject
                        + ") values ('" + subject + "');");
                  }
                  if (table==sch) {
                    String id = obj.getString("id");
                    String id_subject = obj.getString("id_subject");
                    String id_audience = obj.getString("id_audience");
                    String id_educator = obj.getString("id_educator");
                    String id_typelesson = obj.getString("id_typelesson");
                    db.execSQL(
                        "update " + schedule.TABLE_NAME + " set " + schedule.id_subject + " = "
                            + id_subject + ", " + schedule.id_audience + " = " + id_audience + ", "
                            + schedule.id_educator + " = " + id_educator + ", "
                            + schedule.id_typelesson + " = " + id_typelesson + " where "
                            + schedule.id + " = " + id + ";");
                  }
                  if (table == cal) {
                    String id_call = obj.getString("id_call");
                    String time = obj.getString("time");
                    db.execSQL("update " + calls.TABLE_NAME + " set " + calls.time + " = '" +
                        time + "' where " + calls.id_call + " = " + id_call);
                  }
                  if (table == dat) {
                    String id_date = obj.getString("id_date");
                    String date = obj.getString("date");
                    db.execSQL("update " + date_start.TABLE_NAME + " set " + date_start.date + " = '" +
                        date + "' where " + date_start.id_date + " = " + id_date);

                    SharedPreferences settings = getSharedPreferences("week", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putLong("current_week",Long.valueOf(date).longValue());
                    editor.commit();
                  }

                } db.setTransactionSuccessful();
              } finally {

                db.endTransaction();
              }

            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
          }
        }
    ) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name_db",database_name);
        return params;
      }
    };
    Volley.newRequestQueue(this).add(postRequest);

  }




  public Dialog onCreateDialogExport() {
      LayoutInflater li = LayoutInflater.from(context);
      View view = li.inflate(R.layout.dialog_signin, null);
      AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AppCompatAlertDialogStyle);
      builder.setView(view);
      final EditText name_db =  view.findViewById(R.id.name_schedule);
      builder.setCancelable(false).setPositiveButton("Экспортировать", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int id) {
          name_db_string= name_db.getText().toString();
          upload();
        }
      }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
        }
      });
      return builder.create();
    }


  void upload() {
    SQLiteDatabase myDataBase = ScheduleDB.getReadableDatabase();

    String searchQuery = "SELECT  * FROM " + schedule.TABLE_NAME;
    Cursor cursor = myDataBase.rawQuery(searchQuery, null);
    JSONArray resultSet = new JSONArray();
    cursor.moveToFirst();
    while (cursor.isAfterLast() == false) {

      int totalColumn = cursor.getColumnCount();
      JSONObject rowObject = new JSONObject();

      for (int i = 0; i < totalColumn; i++) {
        if (cursor.getColumnName(i) != null) {
          try {
            if (cursor.getString(i) != null) {
              rowObject.put(cursor.getColumnName(i), cursor.getString(i));
            } else {
              rowObject.put(cursor.getColumnName(i), "");
            }
          } catch (Exception e) {
          }
        }
      }
      resultSet.put(rowObject);
      cursor.moveToNext();
    }
    cursor.close();

    json_schedule = resultSet.toString();

    String searchQuery1 = "SELECT  * FROM " + subjects.TABLE_NAME;
    Cursor cursor1 = myDataBase.rawQuery(searchQuery1, null);
    JSONArray resultSet1 = new JSONArray();
    cursor1.moveToFirst();
    while (cursor1.isAfterLast() == false) {

      int totalColumn = cursor1.getColumnCount();
      JSONObject rowObject1 = new JSONObject();

      for (int i = 0; i < totalColumn; i++) {
        if (cursor1.getColumnName(i) != null) {
          try {
            if (cursor1.getString(i) != null) {
              rowObject1.put(cursor1.getColumnName(i), cursor1.getString(i));
            } else {
              rowObject1.put(cursor1.getColumnName(i), "");
            }
          } catch (Exception e) {
          }
        }
      }
      resultSet1.put(rowObject1);
      cursor1.moveToNext();
    }
    cursor1.close();
    json_subjects = resultSet1.toString();

    String searchQuery2 = "SELECT  * FROM " + audiences.TABLE_NAME;
    Cursor cursor2 = myDataBase.rawQuery(searchQuery2, null);
    JSONArray resultSet2 = new JSONArray();
    cursor2.moveToFirst();
    while (cursor2.isAfterLast() == false) {

      int totalColumn = cursor2.getColumnCount();
      JSONObject rowObject2 = new JSONObject();

      for (int i = 0; i < totalColumn; i++) {
        if (cursor2.getColumnName(i) != null) {
          try {
            if (cursor2.getString(i) != null) {
              rowObject2.put(cursor2.getColumnName(i), cursor2.getString(i));
            } else {
              rowObject2.put(cursor2.getColumnName(i), "");
            }
          } catch (Exception e) {
          }
        }
      }
      resultSet2.put(rowObject2);
      cursor2.moveToNext();
    }
    cursor2.close();
    json_audiences = resultSet2.toString();

    String searchQuery3 = "SELECT  * FROM " + educators.TABLE_NAME;
    Cursor cursor3 = myDataBase.rawQuery(searchQuery3, null);
    JSONArray resultSet3 = new JSONArray();
    cursor3.moveToFirst();
    while (cursor3.isAfterLast() == false) {

      int totalColumn = cursor3.getColumnCount();
      JSONObject rowObject3 = new JSONObject();

      for (int i = 0; i < totalColumn; i++) {
        if (cursor3.getColumnName(i) != null) {
          try {
            if (cursor3.getString(i) != null) {
              rowObject3.put(cursor3.getColumnName(i), cursor3.getString(i));
            } else {
              rowObject3.put(cursor3.getColumnName(i), "");
            }
          } catch (Exception e) {
          }
        }
      }
      resultSet3.put(rowObject3);
      cursor3.moveToNext();
    }
    cursor3.close();
    json_educators = resultSet3.toString();

    String searchQuery4 = "SELECT  * FROM " + typelessons.TABLE_NAME;
    Cursor cursor4 = myDataBase.rawQuery(searchQuery4, null);
    JSONArray resultSet4 = new JSONArray();
    cursor4.moveToFirst();
    while (cursor4.isAfterLast() == false) {

      int totalColumn = cursor4.getColumnCount();
      JSONObject rowObject4 = new JSONObject();

      for (int i = 0; i < totalColumn; i++) {
        if (cursor4.getColumnName(i) != null) {
          try {
            if (cursor4.getString(i) != null) {
              rowObject4.put(cursor4.getColumnName(i), cursor4.getString(i));
            } else {
              rowObject4.put(cursor4.getColumnName(i), "");
            }
          } catch (Exception e) {
          }
        }
      }
      resultSet4.put(rowObject4);
      cursor4.moveToNext();
    }
    cursor4.close();
    json_typelessons = resultSet4.toString();

    String searchQuery5 = "SELECT  * FROM " + calls.TABLE_NAME;
    Cursor cursor5 = myDataBase.rawQuery(searchQuery5, null);
    JSONArray resultSet5 = new JSONArray();
    cursor5.moveToFirst();
    while (cursor5.isAfterLast() == false) {

      int totalColumn = cursor5.getColumnCount();
      JSONObject rowObject5 = new JSONObject();

      for (int i = 0; i < totalColumn; i++) {
        if (cursor5.getColumnName(i) != null) {
          try {
            if (cursor5.getString(i) != null) {
              rowObject5.put(cursor5.getColumnName(i), cursor5.getString(i));
            } else {
              rowObject5.put(cursor5.getColumnName(i), "");
            }
          } catch (Exception e) {
          }
        }
      }
      resultSet5.put(rowObject5);
      cursor5.moveToNext();
    }
    cursor5.close();
    json_calls = resultSet5.toString();

    String searchQuery6 = "SELECT  * FROM " + date_start.TABLE_NAME;
    Cursor cursor6 = myDataBase.rawQuery(searchQuery6, null);
    JSONArray resultSet6 = new JSONArray();
    cursor6.moveToFirst();
    while (cursor6.isAfterLast() == false) {

      int totalColumn = cursor6.getColumnCount();
      JSONObject rowObject6 = new JSONObject();

      for (int i = 0; i < totalColumn; i++) {
        if (cursor6.getColumnName(i) != null) {
          try {
            if (cursor6.getString(i) != null) {
              rowObject6.put(cursor6.getColumnName(i), cursor6.getString(i));
            } else {
              rowObject6.put(cursor6.getColumnName(i), "");
            }
          } catch (Exception e) {
          }
        }
      }
      resultSet6.put(rowObject6);
      cursor6.moveToNext();
    }
    cursor6.close();
    json_date = resultSet6.toString();

    progressDialog.setMessage("Пожалуйста подождите. Идет выгрузка данных на сервер");
    progressDialog.show();


    StringRequest stringRequest = new StringRequest(Request.Method.POST, insert,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String ServerResponse) {
            progressDialog.dismiss();
            Toast.makeText(Settings.this, ServerResponse, Toast.LENGTH_LONG).show();
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
            Toast.makeText(Settings.this, volleyError.toString(), Toast.LENGTH_LONG).show();
          }
        }) {
      @Override
      protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("name_db", name_db_string );
        params.put("schedule", json_schedule);
        params.put("subjects", json_subjects);
        params.put("audiences", json_audiences);
        params.put("educators", json_educators);
        params.put("typelessons", json_typelessons);
        params.put("call", json_calls);
        params.put("current_date", json_date);
        return params;
      }
    };
    Volley.newRequestQueue(this).add(stringRequest);

  }


  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        Intent intent = new Intent(Settings.this,MainActivity.class);
        finish();
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}

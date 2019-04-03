package com.example.misha.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ActivityStart extends Activity {
  private static final String schedule_import= "http://schedu1e.h1n.ru/schedule.php";
  private static final String subjects_import = "http://schedu1e.h1n.ru/subjects.php";
  private static final String audiences_import = "http://schedu1e.h1n.ru/audiences.php";
  private static final String educators_import= "http://schedu1e.h1n.ru/educators.php";
  private static final String call_schedule = "http://schedu1e.h1n.ru/ActivityCallSchedule.php";
  private static final String date = "http://schedu1e.h1n.ru/date_start.php";



  final String sch="schedule";
  final String sub="subject";
  final String aud="audience";
  final String edu="educator";
  final String cal="calls";
  final String dat="date_start";

  String database_name="";

  private com.example.misha.myapplication.data.ScheduleDB ScheduleDB;


  final Context context = this;
  public ArrayAdapter<String> adapter;


  @Override
    protected void onCreate(Bundle savedInstanceState) {
    ScheduleDB = new ScheduleDB();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.welcome_activity);




    Button start_buttonOne = findViewById(R.id.start_buttonOne);
    start_buttonOne.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        onCreateDialogImport().show();
      }
    });
    Button start_buttonTwo = findViewById(R.id.start_buttonTwo);
    start_buttonTwo.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent (ActivityStart.this, ActivityStartSettings.class);
        finish();
        startActivity(intent);
      }
    });

  }

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
                SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                db.execSQL("DROP TABLE " + ScheduleClass.subjects.TABLE_NAME);
                db.execSQL("CREATE TABLE " + ScheduleClass.subjects.TABLE_NAME + " ("
                        + ScheduleClass.subjects.idd_subject + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ScheduleClass.subjects.subject + " STRING UNIQUE ON CONFLICT IGNORE );");
                db.execSQL("INSERT INTO " + ScheduleClass.subjects.TABLE_NAME + " (" + ScheduleClass.subjects.subject + ") VALUES ('Предмет');");
                db.execSQL("DROP TABLE " + ScheduleClass.audiences.TABLE_NAME);
                db.execSQL("CREATE TABLE " + ScheduleClass.audiences.TABLE_NAME + " ("
                        + ScheduleClass.audiences.idd_audience + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ScheduleClass.audiences.audience + " STRING UNIQUE ON CONFLICT IGNORE );");
                db.execSQL("INSERT INTO " + ScheduleClass.audiences.TABLE_NAME + " (" + ScheduleClass.audiences.audience + ") VALUES ('Аудитория');");
                db.execSQL("DROP TABLE " + ScheduleClass.educators.TABLE_NAME);
                db.execSQL("CREATE TABLE " + ScheduleClass.educators.TABLE_NAME + " ("
                        + ScheduleClass.educators.idd_educator + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ScheduleClass.educators.educator + " STRING UNIQUE ON CONFLICT IGNORE );");
                db.execSQL("INSERT INTO " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator + ") VALUES ('Преподаватель');");
                load_db(sub, subjects_import);
                load_db(aud,audiences_import);
                load_db(edu,educators_import);
                load_db(sch,schedule_import);
                load_db(cal,call_schedule);
                load_db(dat,date);
                Intent intent = new Intent(ActivityStart.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent (ActivityStart.this, ActivityStartSettings.class);
                finish();
                startActivity(intent);
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
                                        db.execSQL("insert into " + ScheduleClass.audiences.TABLE_NAME + " (" + ScheduleClass.audiences.audience
                                                + ") values ('" + audience + "');");
                                    }
                                    if (table == edu) {
                                        String educator = obj.getString("educator");
                                        db.execSQL("insert into " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator
                                                + ") values ('" + educator + "');");
                                    }
                                    if (table == sub) {
                                        String subject = obj.getString("subject");
                                        db.execSQL("insert into " + ScheduleClass.subjects.TABLE_NAME + " (" + ScheduleClass.subjects.subject
                                                + ") values ('" + subject + "');");
                                    }
                                    if (table==sch) {
                                        String id = obj.getString("id");
                                        String id_subject = obj.getString("id_subject");
                                        String id_audience = obj.getString("id_audience");
                                        String id_educator = obj.getString("id_educator");
                                        String id_typelesson = obj.getString("id_typelesson");
                                        db.execSQL(
                                                "update " + ScheduleClass.schedule.TABLE_NAME + " set " + ScheduleClass.schedule.id_subject + " = "
                                                        + id_subject + ", " + ScheduleClass.schedule.id_audience + " = " + id_audience + ", "
                                                        + ScheduleClass.schedule.id_educator + " = " + id_educator + ", "
                                                        + ScheduleClass.schedule.id_typelesson + " = " + id_typelesson + " where "
                                                        + ScheduleClass.schedule.id + " = " + id);
                                    }
                                    if (table == cal) {
                                        String id_call = obj.getString("id_call");
                                        String time = obj.getString("time");
                                        db.execSQL("update " + ScheduleClass.calls.TABLE_NAME + " set " + ScheduleClass.calls.time + " = '" +
                                                time + "' where " + ScheduleClass.calls.id_call + " = " + id_call);
                                    }
                                    if (table == dat) {
                                        String id_date = obj.getString("id_date");
                                        String date = obj.getString("date");
                                        db.execSQL("update " + ScheduleClass.date_start.TABLE_NAME + " set " + ScheduleClass.date_start.date + " = '" +
                                                date + "' where " + ScheduleClass.date_start.id_date + " = " + id_date);

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


}

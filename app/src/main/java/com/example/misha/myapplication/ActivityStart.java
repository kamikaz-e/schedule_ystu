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
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Educator;
import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.database.entity.Subject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.misha.myapplication.data.ScheduleClass.audiences.AUDIENCE;
import static com.example.misha.myapplication.data.ScheduleClass.audiences.audience_id;
import static com.example.misha.myapplication.data.ScheduleClass.calls.CALLS;
import static com.example.misha.myapplication.data.ScheduleClass.date_start.DATE_START;
import static com.example.misha.myapplication.data.ScheduleClass.educators.EDUCATOR;
import static com.example.misha.myapplication.data.ScheduleClass.educators.educator_id;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.SCHEDULE;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.SUBJECT;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.subject_id;


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



  final Context context = this;
  public ArrayAdapter<String> adapter;


  @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.name_schedule);
        builder.setCancelable(false).setPositiveButton("Импортировать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                database_name = name_db.getText().toString();

                SubjectDao.getInstance().deleteAll();
                AudienceDao.getInstance().deleteAll();
                EducatorDao.getInstance().deleteAll();
                load_db(sub, subjects_import);
                load_db(aud, audiences_import);
                load_db(edu, educators_import);
                load_db(sch, schedule_import);
                load_db(cal, call_schedule);
                load_db(dat, date);
                Intent intent = new Intent(ActivityStart.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }

    void load_db(final String table, final String url) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            String jsonString;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonString = jsonArray.getString(i);



                                if (table.equals(aud)) {
                                    ArrayList<Audience> audiences = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Audience>>(){}.getType());
                                    AudienceDao.getInstance().insertAll(audiences);
                                }
                                if (table == edu) {
                                    ArrayList<Educator> educators = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Educator>>(){}.getType());
                                    EducatorDao.getInstance().insertAll(educators);
                                }
                                if (table == sub) {
                                    ArrayList<Subject> subjects = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Subject>>(){}.getType());
                                    SubjectDao.getInstance().insertAll(subjects);
                                }
                                if (table == sch) {
                                    ArrayList<Lesson> lessons = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Lesson>>(){}.getType());
                                    LessonDao.getInstance().insertAll(lessons);
                                }
                                if (table == cal) {
                                    ArrayList<Calls> calls = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Calls>>(){}.getType());
                                    CallDao.getInstance().insertAll(calls);
                                }
                                    /*if (table == dat) {
                                        ArrayList<Date> date = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Date>>(){}.getType());
                                        DateDao.getInstance().insertAll(date);

                                        SharedPreferences settings = getSharedPreferences("week", 0);
                                        SharedPreferences.Editor editor = settings.edit();
                                        editor.putLong("current_week", Long.valueOf(date).longValue());
                                        editor.commit();
                                    }*/
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
                params.put("name_db", database_name);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }


}

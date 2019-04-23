package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.R;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.misha.myapplication.Constants.audiences_import;
import static com.example.misha.myapplication.Constants.call_schedule;
import static com.example.misha.myapplication.Constants.educators_import;
import static com.example.misha.myapplication.Constants.schedule_import;
import static com.example.misha.myapplication.Constants.subjects_import;


public class ActivityStart extends BaseActivity {


    final String sch = "schedule";
    final String sub = "subject";
    final String aud = "audience";
    final String edu = "educator";
    final String cal = "calls";
    final String dat = "date_start";

    String database_name = "";


    final Context context = this;
    public ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);


        Button start_buttonOne = findViewById(R.id.startButtonOne);
        start_buttonOne.setOnClickListener(v -> onCreateDialogImport().show());
        Button start_buttonTwo = findViewById(R.id.startButtonTwo);
        start_buttonTwo.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityStart.this, ActivityStartSettings.class);
            finish();
            startActivity(intent);
        });

    }

    public Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.nameSchedule);
        builder.setCancelable(false).setPositiveButton("Импортировать", (dialog, id) -> {
            database_name = name_db.getText().toString();

            SubjectDao.getInstance().deleteAll();
            AudienceDao.getInstance().deleteAll();
            EducatorDao.getInstance().deleteAll();
            load_db(sub, subjects_import);
            load_db(aud, audiences_import);
            load_db(edu, educators_import);
            load_db(sch, schedule_import);
            load_db(cal, call_schedule);
           // load_db(dat, date);
            Intent intent = new Intent(ActivityStart.this, MainActivity.class);
            finish();
            startActivity(intent);
        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }

    void load_db(final String table, final String url) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        String jsonString;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonString = jsonArray.getString(i);


                            if (table.equals(aud)) {
                                ArrayList<Audience> audiences = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Audience>>() {
                                }.getType());
                                AudienceDao.getInstance().insertAll(audiences);
                            }
                            if (table == edu) {
                                ArrayList<Educator> educators = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Educator>>() {
                                }.getType());
                                EducatorDao.getInstance().insertAll(educators);
                            }
                            if (table == sub) {
                                ArrayList<Subject> subjects = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Subject>>() {
                                }.getType());
                                SubjectDao.getInstance().insertAll(subjects);
                            }
                            if (table == sch) {
                                ArrayList<Lesson> lessons = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Lesson>>() {
                                }.getType());
                                LessonDao.getInstance().insertAll(lessons);
                            }
                            if (table == cal) {
                                ArrayList<Calls> calls = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Calls>>() {
                                }.getType());
                                CallDao.getInstance().insertAll(calls);
                            }
                                /*if (table == dat) {
                                    ArrayList<Date> date = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Date>>(){}.getType());
                                    DateDao.getInstance().insertAll(date);

                                    SharedPreferences fragment_settings = getSharedPreferences("week", 0);
                                    SharedPreferences.Editor editor = fragment_settings.edit();
                                    editor.putLong("current_week", Long.valueOf(date).longValue());
                                    editor.commit();
                                }*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace()
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

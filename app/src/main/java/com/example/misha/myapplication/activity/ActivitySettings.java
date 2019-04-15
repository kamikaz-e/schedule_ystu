package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.Preferences;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class ActivitySettings extends BaseActivity {

    private static final String schedule_import = "http://schedu1e.h1n.ru/schedule.php";
    private static final String subjects_import = "http://schedu1e.h1n.ru/subjects.php";
    private static final String audiences_import = "http://schedu1e.h1n.ru/audiences.php";
    private static final String educators_import = "http://schedu1e.h1n.ru/educators.php";
    private static final String call_schedule = "http://schedu1e.h1n.ru/ActivityCallSchedule.php";
    private static final String date = "http://schedu1e.h1n.ru/date_start.php";
    private static final String export = "http://schedu1e.h1n.ru/export.php";
    final String sch = "schedule";
    final String sub = "subject";
    final String aud = "audience";
    final String edu = "educator";
    final String cal = "calls";
    final String dat = "date_start";


    String database_name = "";


    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String json_schedule = "";
    String json_subjects = "";
    String json_audiences = "";
    String json_educators = "";
    String json_calls = "";
    String json_date = "";
    String name_db_string = "database";

    public ArrayAdapter<String> adapter;
    RelativeLayout layout_pick_week;
    RelativeLayout layout_import;
    RelativeLayout layout_export;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        requestQueue = Volley.newRequestQueue(ActivitySettings.this);
        progressDialog = new ProgressDialog(ActivitySettings.this);

        layout_pick_week = findViewById(R.id.oneitem);
        layout_import = findViewById(R.id.twoitem);
        layout_export = findViewById(R.id.threeitem);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);


        layout_pick_week.setOnClickListener(v -> get_current_week());
        layout_import.setOnClickListener(v -> onCreateDialogImport().show());
        layout_export.setOnClickListener(v -> onCreateDialogExport().show());
    }


    void get_current_week() {
        Calendar calendar = Calendar.getInstance();
        final Calendar selectedDate = Calendar.getInstance();
        new DatePickerDialog(ActivitySettings.this, (view, year, month, dayOfMonth) -> {
            selectedDate.set(Calendar.YEAR, year);
            selectedDate.set(Calendar.MONTH, month);
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Preferences.getInstance().setSemesterStart(selectedDate.getTimeInMillis());
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.name_schedule);
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
            load_db(dat, date);
            Intent intent = new Intent(ActivitySettings.this, MainActivity.class);
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

                                    SharedPreferences settings = getSharedPreferences("week", 0);
                                    SharedPreferences.Editor editor = settings.edit();
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


    public Dialog onCreateDialogExport() {
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.name_schedule);
        builder.setCancelable(false).setPositiveButton("Экспортировать", (dialog, id) -> {
            name_db_string = name_db.getText().toString();
            upload();
        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }


    void upload() {
        ArrayList<Lesson> lessons = LessonDao.getInstance().getAllData();
        json_schedule = new Gson().toJson(lessons);

        ArrayList<Subject> subjects = SubjectDao.getInstance().getAllData();
        json_subjects = new Gson().toJson(subjects);

        ArrayList<Audience> audiences = AudienceDao.getInstance().getAllData();
        json_audiences = new Gson().toJson(audiences);

        ArrayList<Educator> educators = EducatorDao.getInstance().getAllData();
        json_educators = new Gson().toJson(educators);

        ArrayList<Calls> calls = CallDao.getInstance().getAllData();
        json_calls = new Gson().toJson(calls);

        progressDialog.setMessage("Пожалуйста подождите. Идет выгрузка данных на сервер");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, export,
                ServerResponse -> {
                    progressDialog.dismiss();
                    Toast.makeText(ActivitySettings.this, ServerResponse, Toast.LENGTH_LONG).show();
                },
                volleyError -> {
                    progressDialog.dismiss();
                    Toast.makeText(ActivitySettings.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("name_db", name_db_string);
                params.put("schedule", json_schedule);
                params.put("subjects", json_subjects);
                params.put("audiences", json_audiences);
                params.put("educators", json_educators);
                params.put("call", json_calls);
                params.put("selectedDate", json_date);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ActivitySettings.this, MainActivity.class);
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivitySettings.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

}

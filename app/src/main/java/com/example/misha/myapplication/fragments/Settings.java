package com.example.misha.myapplication.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CustomSpinnerAdapter;
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
import com.example.misha.myapplication.fragmentsSchedule.FragmentScheduleByDays;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class Settings extends Fragment {


    private static final String schedule_import = "http://schedu1e.h1n.ru/schedule.php";
    private static final String subjects_import = "http://schedu1e.h1n.ru/subjects.php";
    private static final String audiences_import = "http://schedu1e.h1n.ru/audiences.php";
    private static final String educators_import = "http://schedu1e.h1n.ru/educators.php";
    private static final String call_schedule = "http://schedu1e.h1n.ru/ActivityCallsSchedule.php";
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
    long differentBetweenDate = 0;
    long selectDate = 0;
    long curr_week = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Button buttonHome = view.findViewById(R.id.buttonHome);
        TextView title = view.findViewById(R.id.title);
        title.setText("Настройки");
        buttonHome.setBackgroundResource(R.drawable.ic_home);
        buttonHome.setOnClickListener(v -> {
            FragmentScheduleByDays fragment = new FragmentScheduleByDays();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        });

        requestQueue = Volley.newRequestQueue(getContext());
        progressDialog = new ProgressDialog(getContext());

        layout_pick_week = view.findViewById(R.id.oneitem);
        layout_import = view.findViewById(R.id.twoitem);
        layout_export = view.findViewById(R.id.threeitem);


        layout_pick_week.setOnClickListener(v -> get_current_week());
        layout_import.setOnClickListener(v -> onCreateDialogImport().show());
        layout_export.setOnClickListener(v -> onCreateDialogExport().show());
        return view;
    }


    void get_current_week() {
        Calendar calendar = Calendar.getInstance();
        final Calendar selectedDate = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            selectedDate.set(Calendar.YEAR, year);
            selectedDate.set(Calendar.MONTH, month);
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Preferences.getInstance().setSemesterStart(selectedDate.getTimeInMillis());
            Spinner spinner = getActivity().findViewById(R.id.spinner);


            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(Preferences.getInstance().getSemestStart());
            mCalendar.setFirstDayOfWeek(Calendar.MONDAY);
            mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            ArrayList<String> allDays = new ArrayList<>();
            SimpleDateFormat mFormat = new SimpleDateFormat("dd.MM");
            for (int week = 0; week < 17; week++) {
                for (int day = 0; day < 7; day++) {
                    String startWeek = mFormat.format(mCalendar.getTime());

                    mCalendar.add(Calendar.WEEK_OF_YEAR, 1);
                    mCalendar.add(Calendar.DAY_OF_YEAR, -1);
                    allDays.add(startWeek + " - " + mFormat.format(mCalendar.getTime()));
                    mCalendar.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                }
            }

            CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(),
                    allDays);
            spinner.setAdapter(customSpinnerAdapter);
            calculateDate();
            spinner.setSelection((int) curr_week);

        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
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

        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }

    private void calculateDate() {
        Calendar calendar = Calendar.getInstance();
        selectDate = Preferences.getInstance().getSemestStart();
        differentBetweenDate = calendar.getTimeInMillis() - selectDate;
        curr_week = (differentBetweenDate / (7 * 24 * 60 * 60 * 1000));
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
        Volley.newRequestQueue(getContext()).add(postRequest);
    }


    public Dialog onCreateDialogExport() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
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
                    Toast.makeText(getContext(), ServerResponse, Toast.LENGTH_LONG).show();
                },
                volleyError -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
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
        Volley.newRequestQueue(getContext()).add(stringRequest);

    }

}

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
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Educator;
import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.database.entity.Subject;
import com.example.misha.myapplication.database.entity.Typelesson;
import com.example.misha.myapplication.network.RetrofitClient;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Settings extends BaseFragment {


    String database_name = "schedule";


    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    private String json_lessons = "lessons";
    String json_subjects = "subjects";
    String json_audiences = "audiences";
    String json_educators = "educators";
    String json_typelessons = "typelessons";
    String json_calls = "calls";
    String json_date = "";
    String name_db_string = "database";
    public ArrayAdapter<String> adapter;
    RelativeLayout layoutPickWeek;
    RelativeLayout layoutImport;
    RelativeLayout layoutExport;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle("Настройки");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        requestQueue = Volley.newRequestQueue(getActivity());
        progressDialog = new ProgressDialog(getContext());

        layoutPickWeek = view.findViewById(R.id.current_date);
        layoutImport = view.findViewById(R.id.import_data);
        layoutExport = view.findViewById(R.id.export_data);


        layoutPickWeek.setOnClickListener(v -> get_current_week());
        layoutImport.setOnClickListener(v -> onCreateDialogImport().show());
        layoutExport.setOnClickListener(v -> onCreateDialogExport().show());
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
            // load_db();

        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }

  /*  void load_db() {
        RetrofitClient.getInstance().getRequestInterface().getSubjects(new ScheduleRequest(database_name)).enqueue(new Callback<ArrayList<Subject>>() {
            @Override
            public void onResponse(Call<ArrayList<Subject>> call, Response<ArrayList<Subject>> response) {
                SubjectDao.getInstance().insertAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Subject>> call, Throwable t) {
                t.printStackTrace();
            }
        });
       RetrofitClient.getInstance().getRequestInterface().getAudiences(new ScheduleRequest(database_name)).enqueue(new Callback<ArrayList<Audience>>() {
            @Override
            public void onResponse(Call<ArrayList<Audience>> call, Response<ArrayList<Audience>> response) {
            AudienceDao.getInstance().insertAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Audience>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
*/

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
        ArrayList<Subject> subjects = SubjectDao.getInstance().getAllData();
        ArrayList<Audience> audiences = AudienceDao.getInstance().getAllData();
        ArrayList<Educator> educators = EducatorDao.getInstance().getAllData();
        ArrayList<Typelesson> typelessons = TypelessonDao.getInstance().getAllData();
        ArrayList<Calls> calls = CallDao.getInstance().getAllData();
        ArrayList<Lesson> lessons = LessonDao.getInstance().getAllData();

        json_subjects = new Gson().toJson(subjects);
        json_audiences = new Gson().toJson(audiences);
        json_educators = new Gson().toJson(educators);
        json_typelessons = new Gson().toJson(typelessons);
        json_calls = new Gson().toJson(calls);
        json_lessons = new Gson().toJson(lessons);
        json_date = Long.toString(Preferences.getInstance().getSemestStart());


        RetrofitClient.getInstance().getRequestInterface().insertData(name_db_string, json_subjects, json_audiences, json_educators, json_typelessons, json_calls, json_lessons, json_date).enqueue(new Callback<Throwable>() {
            @Override
            public void onResponse(Call<Throwable> call, Response<Throwable> response) {

            }

            @Override
            public void onFailure(Call<Throwable> call, Throwable t) {

            }
        });
    }
}

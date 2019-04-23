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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.APIService;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.RetrofitClient;
import com.example.misha.myapplication.adapter.CustomSpinnerAdapter;
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
import com.example.misha.myapplication.fragmentsSchedule.FragmentScheduleByDays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Settings extends Fragment {


    private static final String schedule_import = "http://schedu1e.h1n.ru/schedule.php";
    private static final String subjects_import = "http://schedu1e.h1n.ru/subjects.php";
    private static final String audiences_import = "http://schedu1e.h1n.ru/audiences.php";
    private static final String educators_import = "http://schedu1e.h1n.ru/educators.php";
    private static final String call_schedule = "http://schedu1e.h1n.ru/call_schedule.php";
    private static final String date = "http://schedu1e.h1n.ru/date_start.php";
    private static final String export = "http://schedu1e.h1n.ru/export.php";
    final String sch = "lessons";
    final String sub = "subjects";
    final String aud = "audiences";
    final String edu = "educators";
    final String typ = "typelessons";
    final String cal = "calls";
    final String dat = "selectedDate";
    ArrayList<Audience> audience_list = new ArrayList<>();

    String database_name = "";
    private APIService mAPIService;

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String json_lessons = "lessons";
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
    long differentBetweenDate = 0;
    long selectDate = 0;
    long curr_week = 0;
    private static Retrofit retrofit;
    private List<Subject> userList =null;

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
                    .replace(R.id.contentFrame, fragment)
                    .commit();
        });

        requestQueue = Volley.newRequestQueue(getActivity());
        progressDialog = new ProgressDialog(getContext());

        layoutPickWeek = view.findViewById(R.id.currentDate);
        layoutImport = view.findViewById(R.id.importData);
        layoutExport = view.findViewById(R.id.exportData);


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

        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        APIService weatherAPIs = retrofit.create(APIService.class);
        Map<String, String> params = new HashMap<>();
        params.put("name_db", database_name);
        Call<List<Subject>> call  = weatherAPIs.getSubject(database_name);
        call.enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
              userList = response.body();

            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t)  {
                t.printStackTrace();
            }
        });
    }


    public Dialog onCreateDialogExport() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.nameSchedule);
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
        ArrayList<Typelesson>  typelessons = TypelessonDao.getInstance().getAllData();
        ArrayList<Calls> calls = CallDao.getInstance().getAllData();
        ArrayList<Lesson> lessons = LessonDao.getInstance().getAllData();
        json_subjects = new Gson().toJson(subjects);
        json_audiences = new Gson().toJson(audiences);
        json_educators = new Gson().toJson(educators);
        json_typelessons = new Gson().toJson(typelessons);
        json_calls = new Gson().toJson(calls);
        json_lessons = new Gson().toJson(lessons);
        json_date = Long.toString(Preferences.getInstance().getSemestStart());
                OkHttpClient client = new OkHttpClient();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://schedu1e.h1n.ru")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                APIService service = retrofit.create(APIService.class);


                Call<Throwable> call = service.insertData(name_db_string,json_subjects,json_audiences, json_educators,json_typelessons, json_calls, json_lessons, json_date);
                call.enqueue(new Callback<Throwable>() {

                    @Override
                    public void onResponse(Call<Throwable> call, retrofit2.Response<Throwable> response) {

                    }

                    @Override
                    public void onFailure(Call<Throwable> call, Throwable t) {

                    }
                });


    }


}

package com.example.misha.myapplication.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.misha.myapplication.data.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.network.RetrofitClient;
import com.example.misha.myapplication.data.network.request.ScheduleRequest;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Settings extends BaseFragment {


    private String nameGroup;
    public ArrayAdapter<String> adapter;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        RelativeLayout layoutPickWeek = view.findViewById(R.id.current_date);
        RelativeLayout layoutImport = view.findViewById(R.id.import_data);

        layoutPickWeek.setOnClickListener(v -> get_current_week());
        layoutImport.setOnClickListener(v -> onCreateDialogImport().show());
        return view;
    }


    private void get_current_week() {
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
            @SuppressLint("SimpleDateFormat") SimpleDateFormat mFormat = new SimpleDateFormat("dd.MM");
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


    private Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(getContext());
        @SuppressLint("InflateParams") View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.name_schedule);
        builder.setCancelable(false).setPositiveButton("Загрузить", (dialog, id) -> {
            nameGroup = name_db.getText().toString();
            SubjectDao.getInstance().deleteAll();
            AudienceDao.getInstance().deleteAll();
            EducatorDao.getInstance().deleteAll();
            load_db();

        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }

    private void load_db() {
        RetrofitClient.getInstance().getRequestInterface().getSubjects(new ScheduleRequest(nameGroup)).enqueue(new Callback<ArrayList<Subject>>() {
            @Override
            public void onResponse(Call<ArrayList<Subject>> call, Response<ArrayList<Subject>> response) {
                SubjectDao.getInstance().insertAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Subject>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        RetrofitClient.getInstance().getRequestInterface().getAudiences(new ScheduleRequest(nameGroup)).enqueue(new Callback<ArrayList<Audience>>() {
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


}

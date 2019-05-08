package com.example.misha.myapplication.module.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class SettingsFragment extends BaseMainFragment implements SettingsFragmentView, View.OnClickListener {


    private String nameGroup;
    public ArrayAdapter<String> adapter;
    private SettingsPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle(R.string.settings);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SettingsPresenter();
    }

    @NonNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        RelativeLayout layoutPickWeek = view.findViewById(R.id.current_date);
        RelativeLayout layoutImport = view.findViewById(R.id.import_data);
        RelativeLayout layoutAbout = view.findViewById(R.id.about);

        layoutPickWeek.setOnClickListener(this);
        layoutImport.setOnClickListener(this);
        layoutAbout.setOnClickListener(this);
        return view;
    }

    public void get_current_week() {
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


    public Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(getContext());
        @SuppressLint("InflateParams") View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.name_schedule);
        builder.setCancelable(false).setPositiveButton("Загрузить", (dialog, id) -> presenter.loadSubjects(name_db.getText().toString())).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }


    public Dialog onCreateDialogAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setPositiveButton("Профиль Вконтакте", (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mikhailvolkov1"));
            startActivity(browserIntent);
        }).setNeutralButton("Отмена", (dialog, id) -> dialog.cancel()).setNegativeButton("Электронная почта", (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:mikhailvolkov2014-2014@ya.ru"));
            startActivity(browserIntent);
        }).setTitle("Обратная связь с разработчиком");
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.current_date) {
            presenter.onDateClicked();
        }
        if (v.getId() == R.id.import_data) {
            presenter.onCreateDialogImport();
        }
        if (v.getId() == R.id.about) {
            presenter.onCreateDialogAbout();
        }
    }


}

package com.example.misha.myapplication.module.audience;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.util.DataUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchAudiencePresenter extends BaseMainPresenter<SearchAudienceFragmentView> implements SearchAudiencePresenterInterface {

    private ArrayList<Audience> listAudiences = new ArrayList<>();
    private Activity context;


    public SearchAudiencePresenter(FragmentActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int currentDay = day <= 1 ? 0 : day - 1;
        Preferences.getInstance().setSelectedDay(String.valueOf(currentDay));
        Preferences.getInstance().setSelectedWeek(Long.toString(DataUtil.getCurrWeek() + 1));
        getView().updateTextViewDate("");
    }

    @Override
    public void updateAudienceList() {
        getView().updateListAudiences(listAudiences);
    }

    @Override
    public String dateForTextView(String text) {
        String pattern = "EEEE, d MMMM";
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat(pattern);
        Date currentDate = Calendar.getInstance().getTime();
        String date = df.format(currentDate);
        if (!text.equals("")) {
            date = text;
        }
        return date.substring(0, 1).toUpperCase() + date.substring(1);
    }

    public void onClickDate(View v) {
        getCurrentDate();
    }


    public void onLessonSelected(int selectedLesson) {
        Preferences.getInstance().setSelectedLesson(String.valueOf(selectedLesson + 1));
        loadFreeAudienceAudiences(Preferences.getInstance().getSelectedWeek(), Preferences.getInstance().getSelectedDay(), Preferences.getInstance().getSelectedLesson());
    }


    @Override
    public void loadFreeAudienceAudiences(String week, String day, String lesson) {
        getView().showProgressBar();
        getCompositeDisposable().add(getRepositoryManager()
                .getFreeAudiences(week, day, lesson)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(audiences -> {
                    getView().hideProgressBar();
                    listAudiences.clear();
                    listAudiences.addAll(audiences);
                    updateAudienceList();
                }, throwable -> {
                    getView().hideProgressBar();
                    processSimpleError(throwable);
                })
        );
    }

    public void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        final Calendar selectedDateCalendar = Calendar.getInstance();
        new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {
            selectedDateCalendar.set(Calendar.YEAR, year);
            selectedDateCalendar.set(Calendar.MONTH, month);
            selectedDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(Long.valueOf(Preferences.getInstance().getSemestStart()));
            mCalendar.setFirstDayOfWeek(Calendar.MONDAY);
            mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String pattern = "EEEE, d MMMM";
            @SuppressLint("SimpleDateFormat")
            DateFormat df = new SimpleDateFormat(pattern);
            String date = df.format(selectedDateCalendar.getTime());
            getView().updateTextViewDate(date);

            Preferences.getInstance().setSelectedWeek(String.valueOf(DataUtil.getSelectedWeek(selectedDateCalendar.getTimeInMillis())));
            int day = selectedDateCalendar.get(Calendar.DAY_OF_WEEK);
            int selectedDay = day <= 1 ? 0 : day - 1;
            Preferences.getInstance().setSelectedDay(String.valueOf(selectedDay));
            loadFreeAudienceAudiences(Preferences.getInstance().getSelectedWeek(), Preferences.getInstance().getSelectedDay(), Preferences.getInstance().getSelectedLesson());

        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();


    }

}
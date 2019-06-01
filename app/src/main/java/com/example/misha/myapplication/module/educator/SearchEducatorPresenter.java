package com.example.misha.myapplication.module.educator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.LessonsEducator;
import com.example.misha.myapplication.util.DataUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchEducatorPresenter extends BaseMainPresenter<SearchEducatorFragmentView> implements SearchEducatorPresenterInterface {

    private ArrayList<Educator> educatorList = new ArrayList<>();
    private ArrayList<LessonsEducator> lessonList = new ArrayList<>();
    private Activity context;

    public SearchEducatorPresenter(FragmentActivity context) {
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
    public void updateEducatorList() {
        getView().updateListEducators(educatorList);
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

    @Override
    public void onClickItem(String educator) {
        getView().showProgressBar();
        loadLessonsEducator(Preferences.getInstance().getSelectedWeek(), Preferences.getInstance().getSelectedDay(), educator);
    }

    @Override
    public void loadEducators(String week, String day) {
        getView().showProgressBar();
        getCompositeDisposable().add(getRepositoryManager()
                .getEducatorsCurrentDay(week, day)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(educators -> {
                    getView().hideProgressBar();
                    educatorList.clear();
                    educatorList.addAll(educators);
                    updateEducatorList();
                }, throwable -> {
                    getView().hideProgressBar();
                    processSimpleError(throwable);
                })
        );
    }


    @Override
    public void loadLessonsEducator(String week, String day, String educator) {
        getCompositeDisposable().add(getRepositoryManager()
                .getLessonsEducator(week, day, educator)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(lessons -> {
                    SearchEducatorPresenter.this.getView().hideProgressBar();
                    lessonList.clear();
                    lessonList.addAll(lessons);
                    getView().showLessonsDialog(lessonList,educator);
                }, throwable -> {
                    SearchEducatorPresenter.this.getView().hideProgressBar();
                    SearchEducatorPresenter.this.processSimpleError(throwable);
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
            loadEducators(Preferences.getInstance().getSelectedWeek(), Preferences.getInstance().getSelectedDay());
            Preferences.getInstance().setSelectedDay(String.valueOf(selectedDay));

            getView().showProgressBar();
            loadEducators(Preferences.getInstance().getSelectedWeek(), Preferences.getInstance().getSelectedDay());

        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
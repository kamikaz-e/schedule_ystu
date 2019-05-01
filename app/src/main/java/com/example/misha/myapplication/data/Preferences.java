package com.example.misha.myapplication.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.misha.myapplication.ScheduleApp;

import java.util.Calendar;

public final class Preferences {

    private static volatile Preferences instance;
    private final SharedPreferences mPrefs;
    private Calendar calendar = Calendar.getInstance();

    private static final String PREF_KEY_FIRST_OPEN_HINT = "PREF_KEY_FIRST_OPEN_HINT";
    private static final String PREF_KEY_CALLS_OPEN_HINT = "PREF_KEY_CALLS_OPEN_HINT";
    private static final String PREF_KEY_SEMESTER_START = "PREF_KEY_SEMESTER_START";
    private static final String PREF_KEY_SELECT_WEEK = "PREF_KEY_SELECT_WEEK";
    private static final String PREF_KEY_SELECT_TAB_DAYS = "PREF_KEY_SELECT_TAB_DAYS";
    private static final String PREF_KEY_SELECT_LESSON = "PREF_KEY_SELECT_LESSON";
    private static final String PREF_KEY_FAB_OPEN = "PREF_KEY_FAB_OPEN";

    public static Preferences getInstance() {
        if (instance != null) return instance;
        instance = new Preferences();
        return instance;
    }


    private Preferences() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ScheduleApp.getAppContext());
    }



    public boolean isHintsOpened() {
        return mPrefs.getBoolean(PREF_KEY_FIRST_OPEN_HINT, false);
    }
    public void setHintsOpened() {
        mPrefs.edit().putBoolean(PREF_KEY_FIRST_OPEN_HINT, true).apply();
    }



    public void setSemesterStart(long date) {
        mPrefs.edit().putLong(PREF_KEY_SEMESTER_START, date).apply();
    }
    public long getSemestStart() {
        return mPrefs.getLong(PREF_KEY_SEMESTER_START, calendar.getTimeInMillis());
    }



    public boolean isCallsOpened() {
        return mPrefs.getBoolean(PREF_KEY_CALLS_OPEN_HINT, true);
    }
    public void setCallsOpened(boolean state) {
        mPrefs.edit().putBoolean(PREF_KEY_CALLS_OPEN_HINT, state).apply();
    }



    public void setSelectedWeekEditSchedule(int position) {
        mPrefs.edit().putInt(PREF_KEY_SELECT_WEEK, position).apply();
    }
    public int getSelectedWeekEditSchedule() {
        return mPrefs.getInt(PREF_KEY_SELECT_WEEK, 0);
    }



    public void setSelectedPositionTabDays(int position) {
        mPrefs.edit().putInt(PREF_KEY_SELECT_TAB_DAYS, position).apply();
    }
    public int getSelectedPositionTabDays() {
        return mPrefs.getInt(PREF_KEY_SELECT_TAB_DAYS, 0);
    }



    public void setFabOpen(boolean state) {
        mPrefs.edit().putBoolean(PREF_KEY_FAB_OPEN, state).apply();
    }
    public boolean getFabOpen() {
        return mPrefs.getBoolean(PREF_KEY_FAB_OPEN, true);
    }



    public void setSelectedPositionLesson(int position) {
        mPrefs.edit().putInt(PREF_KEY_SELECT_LESSON, position).apply();
    }
    public int getSelectedPositionLesson() {
        return mPrefs.getInt(PREF_KEY_SELECT_LESSON, 0);
    }



}

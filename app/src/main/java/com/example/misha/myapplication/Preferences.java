package com.example.misha.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

public final class Preferences {

    private static volatile Preferences instance;

    private final SharedPreferences mPrefs;

    private static final String PREF_KEY_FIRST_OPEN_HINT = "PREF_KEY_FIRST_OPEN_HINT";
    private static final String PREF_KEY_SEMESTER_START = "PREF_KEY_SEMESTER_START";

    public static Preferences getInstance() {
        if (instance != null) return instance;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 0);
        long startDate = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        long endDate = calendar.getTimeInMillis();



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
}

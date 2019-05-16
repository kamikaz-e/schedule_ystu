package com.example.misha.myapplication.util;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import com.example.misha.myapplication.data.preferences.Preferences;

import java.util.Calendar;

public final class DataUtil {

    public static long getCurrWeek() {
        Calendar calendar = Calendar.getInstance();
        long selectDate = Preferences.getInstance().getSemestStart();
        long differentBetweenDate = calendar.getTimeInMillis() - selectDate;
        return (differentBetweenDate / (7 * 24 * 60 * 60 * 1000));
    }

}

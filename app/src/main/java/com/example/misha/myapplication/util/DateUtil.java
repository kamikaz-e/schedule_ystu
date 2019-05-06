package com.example.misha.myapplication.util;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import com.example.misha.myapplication.data.preferences.Preferences;

import java.util.Calendar;

public final class DateUtil {

    public static long getCurrWeek() {
        Calendar calendar = Calendar.getInstance();
        long selectDate = Preferences.getInstance().getSemestStart();
        long differentBetweenDate = calendar.getTimeInMillis() - selectDate;
        return (differentBetweenDate / (7 * 24 * 60 * 60 * 1000));
    }

    public static void hintKeyboard(Activity contex) {
        InputMethodManager imm = (InputMethodManager) contex.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (contex.getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(contex.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

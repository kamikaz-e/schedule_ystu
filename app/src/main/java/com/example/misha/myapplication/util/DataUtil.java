package com.example.misha.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.misha.myapplication.data.preferences.Preferences;

import java.util.Calendar;

public final class DataUtil {

    public static long getCurrWeek() {
        long selectDate = Long.valueOf(Preferences.getInstance().getSemestStart());
        long differentBetweenDate = Calendar.getInstance().getTimeInMillis() - selectDate;
        int calculateWeek = (int) (differentBetweenDate / (7 * 24 * 60 * 60 * 1000));
        return calculateWeek >= 16 ? 16 : calculateWeek;
    }

    public static int getSelectedWeek(long selectDate) {
        long start_semestr = Long.valueOf(Preferences.getInstance().getSemestStart());
        long differentBetweenDate = selectDate - start_semestr;
        return (int) (differentBetweenDate / (7 * 24 * 60 * 60 * 1000) + 1);
    }

    public static void hintKeyboard(Activity contex) {
        InputMethodManager imm = (InputMethodManager) contex.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (contex.getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(contex.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showKeyboard(View v, Activity contex) {
        InputMethodManager imm = (InputMethodManager) contex.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }
}

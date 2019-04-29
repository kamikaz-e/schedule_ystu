package com.example.misha.myapplication.common.core;


import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import android.view.View;


public interface BaseView {

    void showError(@StringRes int message);

    void showError(String message);

    void showSnack(@StringRes int message);

    void showSnack(String message, String button, View.OnClickListener callback);

    void showSnack(String message);

    void showGlobalError(@StringRes int message);

    void showGlobalError(String message);

    void replaceFragment(Fragment fragment);
}

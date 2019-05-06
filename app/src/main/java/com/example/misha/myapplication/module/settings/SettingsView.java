package com.example.misha.myapplication.module.settings;

import android.app.Dialog;

import com.example.misha.myapplication.common.core.BaseView;

public interface SettingsView extends BaseView {

    void showProgressDialog();

    void hideProgressDialog();

    void get_current_week();

    Dialog onCreateDialogImport();

    Dialog onCreateDialogAbout();

}

package com.example.misha.myapplication.module.settings;

import android.app.Dialog;

import com.example.misha.myapplication.common.core.BaseView;

public interface SettingsView extends BaseView {

    void load_db();

    void get_current_week();

    Dialog onCreateDialogImport();

}

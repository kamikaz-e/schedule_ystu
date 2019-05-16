package com.example.misha.myapplication.module.settings;

import com.example.misha.myapplication.entity.Subject;

import java.util.ArrayList;

public interface SettingsPresenterInterface {

    void onDateClicked();

    void getCurrentWeek();

    void onCreateDialogImport();

    void onCreateDialogSelectTheme();

    void onCreateDialogAbout();

    void loadSubjects(String name);

    void loadAudiences(String name, ArrayList<Subject> response);
}

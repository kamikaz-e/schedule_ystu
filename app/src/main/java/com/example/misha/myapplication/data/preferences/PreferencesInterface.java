package com.example.misha.myapplication.data.preferences;

public interface PreferencesInterface {

    boolean isHintsOpened();

    void setHintsOpened();

    void setSemesterStart(long date);

    long getSemestStart();

    boolean isCallsOpened();

    void setCallsOpened(boolean state);

    void setSelectedWeekEditSchedule(int position);

    int getSelectedWeekEditSchedule();

    void setSelectedPositionTabDays(int position);

    int getSelectedPositionTabDays();

    void setFabOpen(boolean state);

    boolean getFabOpen();

    void setSelectedPositionLesson(int position);

    int getSelectedPositionLesson();
}

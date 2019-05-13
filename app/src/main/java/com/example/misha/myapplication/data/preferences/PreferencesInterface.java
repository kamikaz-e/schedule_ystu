package com.example.misha.myapplication.data.preferences;

public interface PreferencesInterface {

    boolean isHintsOpened();

    void setHintsOpened();

    void setSemesterStart(long date);

    long getSemestStart();

    boolean isCallsOpened();

    void setCallsOpened(boolean state);

    void setSelectedWeekSchedule(int position);

    int getSelectedWeekSchedule();

    void setSelectedPositionTabDays(int position);

    int getSelectedPositionTabDays();

    void setFabOpen(boolean state);

    boolean getFabOpen();

    void setSelectedPositionLesson(int position);

    int getSelectedPositionLesson();

    void setSelectDate(String selectDate);

    String getSelectDate();
}

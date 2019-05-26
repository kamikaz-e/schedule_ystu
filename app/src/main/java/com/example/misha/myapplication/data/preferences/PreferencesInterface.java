package com.example.misha.myapplication.data.preferences;

public interface PreferencesInterface {

    boolean isHintsOpened();

    void setHintsOpened();

    void setSemesterStart(String date);

    String getSemestStart();

    boolean isCallsOpened();

    void setCallsOpened(boolean state);

    int getSelectedWeekSchedule();

    void setSelectedWeekSchedule(int position);

    int getSelectedPositionTabDays();

    void setSelectedPositionTabDays(int position);

    boolean getFabOpen();

    void setFabOpen(boolean state);

    int getSelectedPositionLesson();

    void setSelectedPositionLesson(int position);

    String getSelectDate();

    void setSelectDate(String selectDate);

    String getSelectedTheme();

    void setSelectedTheme(String stringTheme);

    String getSelectedLesson();

    void setSelectedLesson(String lesson);

    String getSelectedWeek();

    void setSelectedWeek(String week);

    String getSelectedDay();

    void setSelectedDay(String day);

    String getSelectedDate();

    void setSelectedDate(String date);

}

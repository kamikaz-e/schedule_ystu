package com.example.misha.myapplication.module.schedule.explore;

public interface SchedulePresenterInterface {

    void onWeekSelected(int position);

    void onEditorButtonClicked();

    void onPageSwiped(int position);

    void onPageSelected(int position);

    void selectDefaultWeek();
}

package com.example.misha.myapplication.module.schedule.edit;

public interface EditSchedulePresenterInterface {

    void onWeekSelected(int position);

    void onEditorButtonClicked();

    void onPageSwiped(int position);

    void onPageSelected(int position);

    void selectDefaultWeek();
}

package com.example.misha.myapplication.module.schedule.edit.fragments;

public interface EditSchedulePresenterInterface {

    void onWeekSelected(int position);

    void onButtonClicked(int id);

    void onPageSwiped(int position);

    void onPageSelected(int position);

    void selectDefaultWeek();
}

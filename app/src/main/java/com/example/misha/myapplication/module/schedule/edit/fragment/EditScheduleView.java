package com.example.misha.myapplication.module.schedule.edit.fragment;

import com.example.misha.myapplication.common.core.BaseView;

public interface EditScheduleView extends BaseView {

    void selectWeek(int position);

    void openEditor();

    void selectCurrentDay(int currentDay);

    void selectCurrentWeek(int currentWeek);

    void swipePage(int position);

    void selectPage(int position);

    void animateFAB();

    void evenWeekFab();

    void unevenWeekFab();

}

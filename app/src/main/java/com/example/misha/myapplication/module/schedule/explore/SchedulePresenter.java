package com.example.misha.myapplication.module.schedule.explore;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.util.DateUtil;

import java.util.Calendar;

public class SchedulePresenter extends BaseMainPresenter<ScheduleView> implements SchedulePresenterInterface {


    @Override
    public void init() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int currentDay = day <= 2 ? 0 : day - 2;
        getView().selectCurrentDay(currentDay);
    }

    @Override
    public void onWeekSelected(int position) {
        getView().selectWeek(position);
    }

    @Override
    public void onButtonClicked() {
        getView().openEditor();
    }

    @Override
    public void onPageSwiped(int position) {
        getView().swipePage(position);
    }

    @Override
    public void onPageSelected(int position) {
        getView().selectPage(position);
    }

    @Override
    public void selectDefaultWeek() {
        int currentWeek = (int) DateUtil.getCurrWeek();
        getView().selectCurrentWeek(currentWeek);
    }
}

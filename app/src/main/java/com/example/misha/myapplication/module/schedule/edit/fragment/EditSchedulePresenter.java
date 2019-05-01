package com.example.misha.myapplication.module.schedule.edit.fragment;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.Preferences;

public class EditSchedulePresenter extends BaseMainPresenter<EditScheduleView> implements EditSchedulePresenterInterface {

    @Override
    public void init() {
        int currentDay = Preferences.getInstance().getSelectedPositionTabDays();
        getView().selectCurrentDay(currentDay);
    }

    @Override
    public void onWeekSelected(int position) {
        getView().selectWeek(position);
    }

    @Override
    public void onButtonClicked(int id) {
        if (id == R.id.main_fab) {
            getView().animateFAB();
        }
        if (id == R.id.even_weekFab) {
            getView().evenWeekFab();
        }
        if (id == R.id.uneven_weekFab) {
            getView().unevenWeekFab();
        }
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
        int currentWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
        getView().selectCurrentWeek(currentWeek);
    }


}

package com.example.misha.myapplication.module.schedule.edit;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.Preferences;

public class EditSchedulePresenter extends BaseMainPresenter<EditScheduleView> implements EditSchedulePresenterInterface {

    @Override
    public void init() {
    }

    @Override
    public void onWeekSelected(int position) {
        getView().selectWeek(position);
    }

    @Override
    public void onEditorButtonClicked() {
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
        int currentWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
        getView().selectCurrentWeek(currentWeek);
    }


}

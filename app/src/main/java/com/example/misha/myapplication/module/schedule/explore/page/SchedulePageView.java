package com.example.misha.myapplication.module.schedule.explore.page;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.data.database.entity.Lesson;

import java.util.List;

public interface SchedulePageView extends BaseView {

    void showProgressBar();

    void hideProgressBar();

    void showErrorView();

    void hideErrorView();

    void updateList(List<Lesson> lessonList);

    void setWeek(int position);

}

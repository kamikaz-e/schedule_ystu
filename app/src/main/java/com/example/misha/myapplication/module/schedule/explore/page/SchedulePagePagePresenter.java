package com.example.misha.myapplication.module.schedule.explore.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.entity.Lesson;

import java.util.List;

public class SchedulePagePagePresenter extends BaseMainPresenter<SchedulePageFragmentView> implements SchedulePagePresenterInterface {

    private int day;

    private int positionWeek;

    private List<Lesson> lessons;

    public SchedulePagePagePresenter(int day, int positionWeek) {
        this.day = day;
        this.positionWeek = positionWeek;
    }

    @Override
    public void init() {
        if (lessons == null) {
            getView().showProgressBar();
            loadWeek();
        } else {
            getView().updateList(lessons);
        }
    }

    private void loadWeek() {
        getCompositeDisposable().add(getRepositoryManager()
                .getLessonsByDayWeek(day, positionWeek)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(lessons -> {
                    getView().hideProgressBar();
                    getView().hideErrorView();
                    this.lessons = lessons;
                    getView().updateList(lessons);

                }, throwable -> {
                    getView().hideProgressBar();
                    getView().showErrorView();
                    processGlobalError(throwable);
                })
        );
    }


    @Override
    public void setWeek(int positionWeek) {
        this.positionWeek = positionWeek;
        loadWeek();
    }
}

package com.example.misha.myapplication.module.schedule.explore.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.entity.Lesson;

import java.util.List;

public class Presenter extends BaseMainPresenter<View> implements PresenterInterface {

    private int day;

    private int positionWeek;

    private List<Lesson> lessons;

    public Presenter(int day, int positionWeek) {
        this.day = day;
        this.positionWeek = positionWeek;
    }

    @Override
    public void init() {
        if (lessons == null) {
            getView().showProgressBar();
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
        } else {
            getView().updateList(lessons);
        }
    }


    @Override
    public void setWeek(int positionWeek) {
        this.positionWeek = positionWeek;
       getView().setWeek(positionWeek);
    }
}

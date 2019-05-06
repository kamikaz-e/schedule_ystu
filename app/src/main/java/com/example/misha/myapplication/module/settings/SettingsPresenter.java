package com.example.misha.myapplication.module.settings;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.network.request.ScheduleRequest;

import java.util.ArrayList;

public class SettingsPresenter extends BaseMainPresenter<SettingsView> implements SettingsPresenterInterface {

    @Override
    public void init() {
    }
    @Override
    public void onDateClicked() {
        getView().get_current_week();
    }

    @Override
    public void onCreateDialogImport() {
         getView().onCreateDialogImport().show();
    }

    @Override
    public void onCreateDialogAbout() {
        getView().onCreateDialogAbout().show();
    }

    @Override
    public void loadSubjects(String nameGroup) {
        getView().showProgressDialog();
        getCompositeDisposable().add(getRepositoryManager()
                .getSubjects(new ScheduleRequest(nameGroup))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    loadAudiences(nameGroup, response);
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    @Override
    public void loadAudiences(String name, ArrayList<Subject> subjects) {
        getCompositeDisposable().add(getRepositoryManager()
                .getAudiences(new ScheduleRequest(name))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().hideProgressDialog();
                    SubjectDao.getInstance().deleteAll();
                    AudienceDao.getInstance().deleteAll();
                    EducatorDao.getInstance().deleteAll();
                    SubjectDao.getInstance().insertAll(subjects);
                    AudienceDao.getInstance().insertAll(response);
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }
}

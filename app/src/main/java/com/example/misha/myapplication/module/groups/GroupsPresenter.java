package com.example.misha.myapplication.module.groups;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.Typelesson;
import com.example.misha.myapplication.util.DataUtil;

import java.util.ArrayList;

public class GroupsPresenter extends BaseMainPresenter<GroupsFragmentView> implements GroupsPresenterInterface {

    private ArrayList<Groups> listGroups = new ArrayList<>();
    private Activity context;

    public GroupsPresenter(FragmentActivity context) {
        this.context=context;
    }

    @Override
    public void init() {
        getView().updateListGroups(listGroups);
    }

    public void onClickItem(String group, View v) {
        loadAfterLoadGroups(group);
        getView().showProgressDialog();
    }

    @Override
    public void load() {
        getView().showProgressDialog();
        getCompositeDisposable().add(getRepositoryManager()
                .getGroups()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::loadGroups, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    @Override
    public void loadGroups(ArrayList<Groups> groups) {
        getCompositeDisposable().add(getRepositoryManager()
                .getGroups()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups1 -> {
                    getView().hideProgressDialog();
                    listGroups.addAll(groups);
                    init();
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    @Override
    public void loadAfterLoadGroups(String group) {
        getView().showProgressDialog();
        getCompositeDisposable().add(getRepositoryManager()
                .getSubjects(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(subjects -> loadSubjects(group), throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    public void loadSubjects(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getSubjects(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups -> {
                    getView().hideProgressDialog();
                    SubjectDao.getInstance().deleteAll();
                    SubjectDao.getInstance().insertAll(groups);
                    loadAudiences(group);
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    public void loadAudiences(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getAudiences(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups -> {
                    getView().hideProgressDialog();
                    AudienceDao.getInstance().deleteAll();
                    AudienceDao.getInstance().insertAll(groups);
                    loadEducators(group);
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    public void loadEducators(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getEducators(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups -> {
                    getView().hideProgressDialog();
                    EducatorDao.getInstance().deleteAll();
                    EducatorDao.getInstance().insertAll(groups);
                    loadTypelessons(group);
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    public void loadTypelessons(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getTypelessons()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups -> {
                    getView().hideProgressDialog();
                    TypelessonDao.getInstance().deleteAll();
                    TypelessonDao.getInstance().insertAll(groups);
                    loadLessons(group);
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    public void loadLessons(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getLessons(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups -> {
                    getView().hideProgressDialog();
                    LessonDao.getInstance().deleteAll();
                    LessonDao.getInstance().insertAll(groups);
                    DataUtil.hintKeyboard(context);
                    getView().openFragmentSchedule();
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }


}
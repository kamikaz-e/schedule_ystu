package com.example.misha.myapplication.module.groups;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.common.core.BaseActivity;
import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.entity.Groups;
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
                .subscribe(subjects -> loadSubject(group), throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

    public void loadSubject(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getSubjects(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups -> {
                    getView().hideProgressDialog();
                    SubjectDao.getInstance().deleteAll();
                    SubjectDao.getInstance().insertAll(groups);
                    DataUtil.hintKeyboard(context);
                    getView().openFragmentSchedule();
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }


}
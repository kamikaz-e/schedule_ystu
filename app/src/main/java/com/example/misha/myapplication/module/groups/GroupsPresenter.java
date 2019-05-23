package com.example.misha.myapplication.module.groups;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.entity.Groups;

import java.util.ArrayList;

public class GroupsPresenter extends BaseMainPresenter<GroupsFragmentView> implements GroupsPresenterInterface {

    private ArrayList<Groups> listGroups = new ArrayList<>();

    @Override
    public void init() {
        getView().updateListGroups(listGroups);
    }

    @Override
    public void onClickItem(int position) {
        System.out.println(listGroups.get(position));
    }

    @Override
    public void load() {
        getView().showProgressDialog();
        getCompositeDisposable().add(getRepositoryManager()
                .getGroups()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(groups -> loadGroups(groups), throwable -> {
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
                .subscribe(response -> {
                    getView().hideProgressDialog();
                    listGroups.addAll(response);
                    init();
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

}
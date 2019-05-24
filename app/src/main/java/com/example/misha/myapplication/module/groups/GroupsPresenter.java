package com.example.misha.myapplication.module.groups;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Request;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class GroupsPresenter extends BaseMainPresenter<GroupsFragmentView> implements GroupsPresenterInterface {

    private ArrayList<Request> listGroups = new ArrayList<>();

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
                .subscribe(new Consumer<ArrayList<Request>>() {
                    @Override
                    public void accept(ArrayList<Request> groups) throws Exception {
                        loadGroups();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().hideProgressDialog();
                        processSimpleError(throwable);
                    }
                })
        );
    }

    @Override
    public void loadGroups() {
        getCompositeDisposable().add(getRepositoryManager()
                .getGroups()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ArrayList<Request>>() {
                    @Override
                    public void accept(ArrayList<Request> groups) throws Exception {
                        getView().hideProgressDialog();
                        listGroups.addAll(groups);
                        init();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().hideProgressDialog();
                        processSimpleError(throwable);
                    }
                })
        );
    }

}
package com.example.misha.myapplication.module.groups;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Request;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

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
                        loadGroups(groups);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        GroupsPresenter.this.getView().hideProgressDialog();
                        GroupsPresenter.this.processSimpleError(throwable);
                    }
                })
        );
    }

    @Override
    public void loadGroups(ArrayList<Request> requestArrayList) {
        getCompositeDisposable().add(getRepositoryManager()
                .getGroups()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe((Consumer<ArrayList<Request>>) groups -> {
                    getView().hideProgressDialog();

                    listGroups.addAll(groups);

                    init();
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
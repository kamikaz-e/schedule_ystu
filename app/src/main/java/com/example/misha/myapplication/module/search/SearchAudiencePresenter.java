package com.example.misha.myapplication.module.search;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.entity.Audience;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class SearchAudiencePresenter extends BaseMainPresenter<SearchAudienceFragmentView> implements SearchAudiencePresenterInterface {

    private ArrayList<Audience> listAudiences = new ArrayList<>();
    private Activity context;

    public SearchAudiencePresenter(FragmentActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
        getView().updateListAudiences(listAudiences);
    }

    public void onClickItem(String date, View v) {
        getView().showProgressDialog();
        loadAudiences(date);
    }

    @Override
    public void loadAudiences(String date) {
        getCompositeDisposable().add(getRepositoryManager()
                .getAudience(date)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(audiences -> {
                    listAudiences.addAll(audiences);
                    getView().hideProgressDialog();
                }, throwable -> {
                    getView().hideProgressDialog();
                    processSimpleError(throwable);
                })
        );
    }

}
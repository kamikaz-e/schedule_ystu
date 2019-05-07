package com.example.misha.myapplication.module.schedule.edit.page.fragment;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.SimpleItem;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import java.util.ArrayList;

public class PageFragmentPageFragmentPresenter extends BaseMainPresenter<PageFragmentView> implements PageFragmentPresenterInterface {

    private int day;
    private int positionWeek;

    public PageFragmentPageFragmentPresenter(int day, int positionWeek) {
        this.day = day;
        this.positionWeek = positionWeek;
    }

    @Override
    public void init() {
        getView().updateList(day, positionWeek);
    }

    @Override
    public void onWeekSelected(int position) {
        getView().updateList(day, positionWeek);
    }

    @Override
    public void onSubjectClick(int position, ArrayList<Subject> item) {
        getView().onSubjecttClick(position, item);
    }

    @Override
    public void onAudienceClick(int position, ArrayList<Audience> item) {
        getView().onAudienceClick(position, item);
    }

    @Override
    public void onEducatorClick(int position, ArrayList<Educator> item) {
        getView().onEducatorClick(position, item);
    }

    @Override
    public void onTypelessonClick(int position, ArrayList<Typelesson> item) {
        getView().onTypelessonClick(position, item);
    }

    @Override
    public void onCopyUpClick(int position) {
        getView().onCopyUpClick(position);
    }

    @Override
    public void onCopyDownClick(int position) {
        getView().onCopyDownClick(position);
    }

    @Override
    public void onClearLessonClick(int position) {
        getView().onClearLessonClick(position);
    }

}

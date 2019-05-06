package com.example.misha.myapplication.module.schedule.edit.page.fragment;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.entity.SimpleItem;

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
    public void onItemClick(int position, ArrayList<SimpleItem> subject) {
        getView().onItemFragmentClick(position, subject);
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

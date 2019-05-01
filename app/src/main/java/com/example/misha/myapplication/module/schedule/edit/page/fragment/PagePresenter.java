package com.example.misha.myapplication.module.schedule.edit.page.fragment;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import java.util.ArrayList;

public class PagePresenter extends BaseMainPresenter<View> implements PresenterInterface {

    @Override
    public void init() {
        getView().updateList();
    }

    @Override
    public void onWeekSelected(int position) {
        getView().updateList();
    }

    @Override
    public void onButtonClicked(int id) {

    }

    @Override
    public void initAtOpen() {
    getView().initAtOpen();
    }

    @Override
    public void onAudienceClick(int position, ArrayList<Audience> audience) {
        getView().onAudienceClick(position, audience);
    }

    @Override
    public void onEducatorClick(int position, ArrayList<Educator> educator) {
        getView().onEducatorClick(position, educator);
    }

    @Override
    public void onSubjectClick(int position, ArrayList<Subject> subject) {
        getView().onSubjectClick(position, subject);
    }

    @Override
    public void onTypelessonClick(int position, ArrayList<Typelesson> typelesson) {
        getView().onTypelessonClick(position, typelesson);
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

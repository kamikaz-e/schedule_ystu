package com.example.misha.myapplication.module.educator;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.LessonsEducator;

import java.util.ArrayList;

public interface SearchEducatorFragmentView extends BaseView {

    String LESSON_LIST = "LESSON_LIST";
    String NAME_EDUCATOR = "NAME_EDUCATOR";

    void updateListEducators(ArrayList<Educator> listGroups);

    void showProgressBar();

    void hideProgressBar();

    void updateTextViewDate(String date);

    void showErrorView();

    void hideErrorView();

    void showEditDialog(ArrayList<LessonsEducator> items, String nameEducator);
}

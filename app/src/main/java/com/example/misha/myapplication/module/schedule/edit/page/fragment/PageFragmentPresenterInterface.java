package com.example.misha.myapplication.module.schedule.edit.page.fragment;

import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import java.util.ArrayList;

public interface PageFragmentPresenterInterface {

    void onWeekSelected(int position);

    void onSubjectClick(int position, ArrayList<Subject> item);

    void onAudienceClick(int position, ArrayList<Audience> item);

    void onEducatorClick(int position, ArrayList<Educator> item);

    void onTypelessonClick(int position, ArrayList<Typelesson> item);

    void onCopyUpClick(int position);

    void onCopyDownClick(int position);

    void onClearLessonClick(int position);
}

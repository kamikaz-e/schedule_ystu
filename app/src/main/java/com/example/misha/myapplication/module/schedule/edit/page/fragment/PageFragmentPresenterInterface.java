package com.example.misha.myapplication.module.schedule.edit.page.fragment;

import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.SimpleItem;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import java.util.ArrayList;

public interface PageFragmentPresenterInterface {

    void onWeekSelected(int position);

    void onItemClick(int position, ArrayList<SimpleItem> item);

    void onCopyUpClick(int position);

    void onCopyDownClick(int position);

    void onClearLessonClick(int position);
}

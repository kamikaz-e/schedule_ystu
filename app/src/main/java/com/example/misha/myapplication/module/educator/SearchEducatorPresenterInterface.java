package com.example.misha.myapplication.module.educator;

import android.view.View;

public interface SearchEducatorPresenterInterface {

    void onClickDate(View v);

    void onClickItem(String group);

    void loadEducators(String week, String day);

    void loadLessonsEducator (String week, String day, String educator);

    void updateEducatorList();

    String dateForTextView(String text);
}

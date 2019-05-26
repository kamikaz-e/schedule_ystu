package com.example.misha.myapplication.module.search;

import android.view.View;

public interface SearchAudiencePresenterInterface {

    void onClickDate(View v);

    void onLessonSelected(int selectedLesson);

    void loadFreeAudienceAudiences(String week, String day, String lesson);

    void updateAudienceList();

    String dateForTextView(String text);
}

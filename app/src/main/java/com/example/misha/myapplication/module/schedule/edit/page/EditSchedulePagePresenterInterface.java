package com.example.misha.myapplication.module.schedule.edit.page;

public interface EditSchedulePagePresenterInterface {

    void onSubjectClick(int position);

    void onAudienceClick(int position);

    void onEducatorClick(int position);

    void onTypelessonClick(int position);

    void onCopyUpClick(int position);

    void onCopyDownClick(int position);

    void onClearLessonClick(int position);

    void setWeek(int position);

}

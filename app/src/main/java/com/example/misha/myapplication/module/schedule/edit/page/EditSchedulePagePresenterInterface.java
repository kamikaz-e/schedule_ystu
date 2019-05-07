package com.example.misha.myapplication.module.schedule.edit.page;

public interface EditSchedulePagePresenterInterface {

    void onWeekSelected(int position);

    void onSubjectClick(int position);

    void onAudienceClick(int position);

    void onEducatorClick(int position);

    void onTypelessonClick(int position);

    void onCopyUpClick(int position);

    void onCopyDownClick(int position);

    void onClearLessonClick(int position);
}

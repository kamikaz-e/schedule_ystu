package com.example.misha.myapplication.adapter;

public interface EditScheduleCallback {

    void onSubjectSelected(int position, Object subject);

    void onAudienceSelected(int position, Object audience);

    void onEducatorSelected(int position, Object educator);
}

package com.example.misha.myapplication.adapter.editSchedule;

import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import java.util.ArrayList;

public interface EditScheduleCallback {

    void onAudienceClick(int position, ArrayList<Audience> audience);

    void onEducatorClick(int position, ArrayList<Educator> educator);

    void onSubjectClick(int position, ArrayList<Subject> subject);

    void onTypelessonClick(int position, ArrayList<Typelesson> typelesson);

    void onCopyUpClick(int position);

    void onCopyDownClick(int position);

    void onClearLessonClick(int position);
}

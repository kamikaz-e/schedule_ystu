package com.example.misha.myapplication.adapter.EditSchedule;


import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Educator;
import com.example.misha.myapplication.database.entity.Subject;
import com.example.misha.myapplication.database.entity.Typelesson;

import java.util.ArrayList;

public interface EditScheduleCallback {


    void onAudienceClick(int position, ArrayList<Audience> audience);

    void onEducatorClick(int position, ArrayList<Educator> educator);

    void onSubjectClick(int position, ArrayList<Subject> subject);

    void onTypelessonClick(int position, ArrayList<Typelesson> typelesson);



}

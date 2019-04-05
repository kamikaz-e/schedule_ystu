package com.example.misha.myapplication.adapter;


import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Educator;
import com.example.misha.myapplication.database.entity.Subject;

import java.util.ArrayList;

public interface EditScheduleCallback {


    void onAudienceClick(int position, ArrayList<Audience> audience);

    void onEducatorClick(int position, ArrayList<Educator> educator);

    void onSubjectClick(int position, ArrayList<Subject> subject);

    void onSubjectSetText (int position, ArrayList<Subject> subject);


}

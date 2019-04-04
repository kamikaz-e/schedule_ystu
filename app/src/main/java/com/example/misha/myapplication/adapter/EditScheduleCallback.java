package com.example.misha.myapplication.adapter;


import com.example.misha.myapplication.model.Audience;
import com.example.misha.myapplication.model.Educator;
import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;

public interface EditScheduleCallback {


    void onAudienceClick(int position, ArrayList<Audience> audience);

    void onEducatorClick(int position, ArrayList<Educator> educator);

    void onSubjectClick(int position, ArrayList<Subject> subject);

    void onSubjectSetText (int position, ArrayList<Subject> subject);


}

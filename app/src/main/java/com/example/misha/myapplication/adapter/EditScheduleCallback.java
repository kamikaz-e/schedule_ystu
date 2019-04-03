package com.example.misha.myapplication.adapter;


import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;

public interface EditScheduleCallback {


    void onAudienceSelected(int position, String audience);

    void onEducatorSelected(int position, String educator);

    void onSubjectClick(int position, ArrayList<Subject> subject);

}

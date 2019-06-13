package com.example.misha.myapplication.module.groups;

import android.view.View;

import com.example.misha.myapplication.entity.Groups;

import java.util.ArrayList;

public interface GroupsPresenterInterface {

    void onClickItem(String group);

    void load();

    void loadGroups(ArrayList<Groups> groups);

    void loadSubjects(String name);


}

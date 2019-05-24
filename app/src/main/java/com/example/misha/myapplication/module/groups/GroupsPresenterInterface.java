package com.example.misha.myapplication.module.groups;

import android.view.View;

import com.example.misha.myapplication.entity.Groups;

import java.util.ArrayList;

public interface GroupsPresenterInterface {

    void onClickItem(String group, View v);

    void load();

    void loadGroups(ArrayList<Groups> groups);

    void loadAfterLoadGroups(String name);

    void loadSubject(String name);



}

package com.example.misha.myapplication.module.groups;

import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Subject;

import java.util.ArrayList;

public interface GroupsPresenterInterface {

    void onClickItem(int position);

    void load();

    void loadGroups(ArrayList<Groups> groups);
}

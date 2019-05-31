package com.example.misha.myapplication.module.groups;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.entity.Groups;

import java.util.ArrayList;

public interface GroupsFragmentView extends BaseView {

    void updateListGroups(ArrayList<Groups> listGroups);

    void openFragmentSchedule();

    void showErrorView();

    void hideErrorView();

    void showProgressBar();

    void hideProgressBar();
}

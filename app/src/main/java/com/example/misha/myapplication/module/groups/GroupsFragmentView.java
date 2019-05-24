package com.example.misha.myapplication.module.groups;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Request;

import java.util.ArrayList;

public interface GroupsFragmentView extends BaseView {

    void updateListGroups(ArrayList<Groups> listGroups);

    void showProgressDialog();

    void hideProgressDialog();

}

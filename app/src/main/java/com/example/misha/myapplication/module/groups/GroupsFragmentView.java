package com.example.misha.myapplication.module.groups;

import android.app.Dialog;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.entity.EditDataModel;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.SimpleItem;

import java.util.ArrayList;

public interface GroupsFragmentView extends BaseView {

   void updateItemsAdapter(ArrayList<Groups> listGroups);

    void showProgressDialog();

    void hideProgressDialog();
}

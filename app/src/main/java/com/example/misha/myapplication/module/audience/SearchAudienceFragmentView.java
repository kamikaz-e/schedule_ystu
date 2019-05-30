package com.example.misha.myapplication.module.audience;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.entity.Audience;

import java.util.ArrayList;

public interface SearchAudienceFragmentView extends BaseView {

    void updateListAudiences(ArrayList<Audience> listGroups);

    void showProgressDialog();

    void hideProgressDialog();

    void updateTextViewDate(String date);

    void showErrorView();

    void hideErrorView();
}

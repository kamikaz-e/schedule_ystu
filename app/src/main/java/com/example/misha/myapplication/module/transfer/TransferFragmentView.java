package com.example.misha.myapplication.module.transfer;

import com.example.misha.myapplication.common.core.BaseView;

public interface TransferFragmentView extends BaseView {

    void openFragmentSchedule();

    void showProgressDialog();

    void hideProgressDialog();

    void openDirectory();


}

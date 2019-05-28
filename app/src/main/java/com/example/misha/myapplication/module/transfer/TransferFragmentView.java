package com.example.misha.myapplication.module.transfer;

import android.content.Intent;

import com.example.misha.myapplication.common.core.BaseView;

public interface TransferFragmentView extends BaseView {

    void openFragmentSchedule();

    void showProgressDialog();

    void hideProgressDialog();


}

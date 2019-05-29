package com.example.misha.myapplication.module.transfer;

public interface TransferPresenterInterface {

    void onClickImport();

    void onClickExport();

    void import_data(String file);
}

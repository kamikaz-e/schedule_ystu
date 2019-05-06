package com.example.misha.myapplication.module.settings;

import com.example.misha.myapplication.common.core.BaseMainPresenter;

public class SettingsPresenter extends BaseMainPresenter<SettingsView> implements SettingsPresenterInterface {

    @Override
    public void init() {
    }
    @Override
    public void onDateClicked() {
        getView().get_current_week();
    }

    @Override
    public void onCreateDialogImport() {
         getView().onCreateDialogImport().show();
    }
}

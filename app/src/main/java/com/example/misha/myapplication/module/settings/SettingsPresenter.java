package com.example.misha.myapplication.module.settings;

import com.example.misha.myapplication.common.core.BaseMainPresenter;

public class SettingsPresenter extends BaseMainPresenter<SettingsFragmentView> implements SettingsPresenterInterface {


    @Override
    public void init() {
    }

    @Override
    public void onSearchAudience() {
        getView().openFragmentSearchAudience();
    }

    @Override
    public void onOpenFragmentGroups() {
        getView().openFragmentGroups();
    }

    @Override
    public void onCreateDialogSelectTheme() {
        getView().showDialogSelectTheme();
    }

    @Override
    public void onCreateDialogAbout() {
        getView().onCreateDialogAbout().show();
    }

    @Override
    public void onOpenFragmentTransferData() {
        getView().openFragmentTransferData();
    }
}

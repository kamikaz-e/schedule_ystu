package com.example.misha.myapplication.module.editData.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;

public class EditDataFragmentPresenter extends BaseMainPresenter<EditDataFragmentView> implements EditDataFragmentPresenterInterface {

    @Override
    public void init() {
    }

    @Override
    public void onUpdateListView() {
        getView().updateListView();
    }

}

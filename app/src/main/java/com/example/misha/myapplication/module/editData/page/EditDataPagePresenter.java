package com.example.misha.myapplication.module.editData.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;

public class EditDataPagePresenter extends BaseMainPresenter<EditDataFragmentPageView> implements EditDataPagePresenterInterface {

    @Override
    public void init() {
    }

    @Override
    public void onUpdateListView() {
        getView().updateListView();
    }

}

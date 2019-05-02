package com.example.misha.myapplication.module.callSchedule;

import com.example.misha.myapplication.common.core.BaseMainPresenter;

public class CallsPresenter extends BaseMainPresenter<CallsView> implements CallsPresenterInterface {

    @Override
    public void init() {
    }

    @Override
    public void onClick(int position) {
        getView().onClick(position);
    }
}

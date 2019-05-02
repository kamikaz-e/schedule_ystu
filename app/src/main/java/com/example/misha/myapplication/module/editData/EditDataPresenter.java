package com.example.misha.myapplication.module.editData;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.AbsDao;

public class EditDataPresenter extends BaseMainPresenter<EditDataView> implements EditDataPresenterInterface {

    @Override
    public void init() {
    }


    @Override
    public void onClearClick(AbsDao dao) {
        getView().clear(dao);
    }
}

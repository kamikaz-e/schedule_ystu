package com.example.misha.myapplication.module.editData;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.data.database.AbsDao;

public interface EditDataFragmentView extends BaseView {

    void clear(AbsDao dao);
}
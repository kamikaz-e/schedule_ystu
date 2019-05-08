package com.example.misha.myapplication.module.editData.page;

import android.app.Dialog;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.entity.SimpleItem;

import java.util.ArrayList;

public interface EditDataFragmentPageView extends BaseView {

    Dialog onCreateDialogDeleteItem(int position, AbsDao absDao);

    void updateView(ArrayList<SimpleItem> absDao);
}

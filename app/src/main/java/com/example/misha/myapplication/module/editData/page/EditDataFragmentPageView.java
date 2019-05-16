package com.example.misha.myapplication.module.editData.page;

import android.app.Dialog;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.entity.EditDataModel;
import com.example.misha.myapplication.entity.SimpleItem;

import java.util.ArrayList;

public interface EditDataFragmentPageView extends BaseView {

    Dialog onCreateDialogDeleteItem(int position);

    void updateItemsAdapter(ArrayList<SimpleItem> listItems);

    void setupWidgets(EditDataModel editDataModel);

}

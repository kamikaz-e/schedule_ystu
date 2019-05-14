package com.example.misha.myapplication.module.editData.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.entity.EditDataModel;
import com.example.misha.myapplication.entity.SimpleItem;
import com.example.misha.myapplication.entity.Subject;

import java.util.ArrayList;

public class EditDataPagePresenter extends BaseMainPresenter<EditDataFragmentPageView> implements EditDataPagePresenterInterface {

    private final EditDataModel editDataModel;

    private ArrayList<SimpleItem> listItems = new ArrayList<>();

    private AbsDao absDao;


    public EditDataPagePresenter(EditDataModel editDataModel) {
        this.editDataModel = editDataModel;
        absDao = editDataModel.getDao();
    }

    public void onClearClick(int position) {
        getView().onCreateDialogDeleteItem(position, absDao).show();
    }

    @Override
    public void init() {
        listItems = absDao.getAllData();
        getView().setupWidgets(editDataModel);
        getView().updateView(listItems);
    }

    @Override
    public void insert(String itemName) {
        SimpleItem item = new Subject();
        item.setName(itemName);
        absDao.insertItem(item);
        listItems.add(item);
        init();
    }

    @Override
    public void deleteItem(int position) {
        absDao.deleteItemById(Long.parseLong(listItems.get(position).getId()));
        listItems.remove(position);
        init();
    }

    @Override
    public String getNameAt(int position) {
        return listItems.get(position).getName();
    }

}

package com.example.misha.myapplication.module.editData.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.SimpleItem;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import java.util.ArrayList;

import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_TYPELESSONS;

public class EditDataPagePresenter extends BaseMainPresenter<EditDataFragmentPageView> implements EditDataPagePresenterInterface {

    private String currentFragment;

    private ArrayList<SimpleItem> listItems = new ArrayList<>();

    private AbsDao absDao;


    public EditDataPagePresenter(String currentFragment) {
        this.currentFragment = currentFragment;
        if (currentFragment.equals(FRAGMENT_SUBJECTS)) {
            absDao = SubjectDao.getInstance();
        }
        if (currentFragment.equals(FRAGMENT_AUDIENCES)) {
            absDao = AudienceDao.getInstance();
        }
        if (currentFragment.equals(FRAGMENT_EDUCATORS)) {
            absDao = EducatorDao.getInstance();
        }
        if (currentFragment.equals(FRAGMENT_TYPELESSONS)) {
            absDao = TypelessonDao.getInstance();
        }
    }

    public void onClearClick(int position) {
        getView().onCreateDialogDeleteItem(position, absDao).show();
    }

    @Override
    public void init() {
        listItems = absDao.getAllData();
        getView().updateView(listItems);
    }

    @Override
    public void insert(String itemName) {
        SimpleItem item = null;
        if (currentFragment.equals(FRAGMENT_SUBJECTS)) {
            item = new Subject();
        }
        if (currentFragment.equals(FRAGMENT_AUDIENCES)) {
            item = new Audience();
        }
        if (currentFragment.equals(FRAGMENT_EDUCATORS)) {
            item = new Educator();
        }
        if (currentFragment.equals(FRAGMENT_TYPELESSONS)) {
            item = new Typelesson();
        }
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

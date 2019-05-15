package com.example.misha.myapplication.module.schedule.edit.page.dialog.addData;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.SimpleItem;
import com.example.misha.myapplication.entity.Subject;
import com.example.misha.myapplication.entity.Typelesson;
import com.example.misha.myapplication.module.schedule.edit.page.EditSchedulePageFragmentView;

import java.util.ArrayList;

import static com.example.misha.myapplication.Constants.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.Constants.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.Constants.FRAGMENT_TYPELESSONS;

public class DialogFragmentDataPresenter extends BaseMainPresenter<DialogFragmentAddDataView> implements DialogFragmentDataPresenterInterface {


    private AbsDao absDao;
    private int fragmentCode;
    private ArrayList<SimpleItem> listItems = new ArrayList<>();

    public DialogFragmentDataPresenter(int fragmentCode) {
        this.fragmentCode = fragmentCode;
        if (fragmentCode == FRAGMENT_SUBJECTS) {
            absDao = SubjectDao.getInstance();
        }
        if (fragmentCode == FRAGMENT_AUDIENCES) {
            absDao = AudienceDao.getInstance();
        }
        if (fragmentCode == FRAGMENT_EDUCATORS) {
            absDao = EducatorDao.getInstance();
        }
        if (fragmentCode == FRAGMENT_TYPELESSONS) {
            absDao = TypelessonDao.getInstance();
        }
    }



    @Override
    public void init() {
        listItems = absDao.getAllData();
    }


    @Override
    public void insert(String itemName) {
        SimpleItem item = null;
        if (fragmentCode == FRAGMENT_SUBJECTS) {
            item = new Subject();
        }
        if (fragmentCode == FRAGMENT_AUDIENCES) {
            item = new Audience();
        }
        if (fragmentCode == FRAGMENT_EDUCATORS) {
            item = new Educator();
        }
        if (fragmentCode == FRAGMENT_TYPELESSONS) {
            item = new Typelesson();
        }
        item.setName(itemName);
        absDao.insertItem(item);
        listItems.add(item);
        init();
    }


}

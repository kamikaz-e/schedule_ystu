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

public class DialogFragmentDataPresenter extends BaseMainPresenter<DialogFragmentAddDataView> implements DialogFragmentDataPresenterInterface {


    private AbsDao absDao;
    private int fragmentCode;
    private ArrayList<SimpleItem> listItems = new ArrayList<>();

    public DialogFragmentDataPresenter(int fragmentCode) {
        this.fragmentCode = fragmentCode;
        if (fragmentCode == EditSchedulePageFragmentView.SUBJECT) {
            absDao = SubjectDao.getInstance();
        }
        if (fragmentCode == EditSchedulePageFragmentView.AUDIENCE) {
            absDao = AudienceDao.getInstance();
        }
        if (fragmentCode == EditSchedulePageFragmentView.EDUCATOR) {
            absDao = EducatorDao.getInstance();
        }
        if (fragmentCode == EditSchedulePageFragmentView.TYPELESSON) {
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
        if (fragmentCode == EditSchedulePageFragmentView.SUBJECT) {
            item = new Subject();
        }
        if (fragmentCode == EditSchedulePageFragmentView.AUDIENCE) {
            item = new Audience();
        }
        if (fragmentCode == EditSchedulePageFragmentView.EDUCATOR) {
            item = new Educator();
        }
        if (fragmentCode == EditSchedulePageFragmentView.TYPELESSON) {
            item = new Typelesson();
        }
        item.setName(itemName);
        absDao.insertItem(item);
        listItems.add(item);
        init();
    }


}

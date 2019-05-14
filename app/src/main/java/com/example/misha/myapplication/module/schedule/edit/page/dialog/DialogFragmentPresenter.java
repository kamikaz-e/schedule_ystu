package com.example.misha.myapplication.module.schedule.edit.page.dialog;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.entity.SimpleItem;
import com.example.misha.myapplication.module.schedule.edit.page.EditSchedulePageFragment;

import java.util.ArrayList;

import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_TYPELESSONS;

public class DialogFragmentPresenter extends BaseMainPresenter<DialogFragmentListItemsView> implements DialogFragmentPresenterInterface {

    private int fragmentCode;
    private AbsDao absDao;
    private ArrayList<SimpleItem> listItems = new ArrayList<>();

    public DialogFragmentPresenter(int fragmentCode) {
        this.fragmentCode = fragmentCode;
        if (fragmentCode == EditSchedulePageFragment.SUBJECT) {
            absDao = SubjectDao.getInstance();
        }
        if (fragmentCode == EditSchedulePageFragment.AUDIENCE) {
            absDao = AudienceDao.getInstance();
        }
        if (fragmentCode == EditSchedulePageFragment.EDUCATOR) {
            absDao = EducatorDao.getInstance();
        }
        if (fragmentCode== EditSchedulePageFragment.TYPELESSON) {
            absDao = TypelessonDao.getInstance();
        }
    }

    @Override
    public void init() {
        updateList();
    }


    @Override
    public void onItemClick(int fragmentCode) {
        getView().showAddDataDialog(listItems, fragmentCode);
        updateList();
    }

    public ArrayList<SimpleItem> getSubjectList() {
        listItems = absDao.getAllData();
        return listItems;
    }



    public void updateList() {

    }

}

package com.example.misha.myapplication.module.schedule.edit.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import java.util.ArrayList;

public class EditSchedulePagePresenter extends BaseMainPresenter<EditSchedulePageFragmentView> implements EditSchedulePagePresenterInterface {

    private ArrayList<Subject> subjectList = SubjectDao.getInstance().getAllData();
    private ArrayList<Audience> audienceList = AudienceDao.getInstance().getAllData();
    private ArrayList<Educator> educatorList = EducatorDao.getInstance().getAllData();
    private ArrayList<Typelesson> typelessonList = TypelessonDao.getInstance().getAllData();


    private int positionWeek;
    private int day;


    public EditSchedulePagePresenter(int positionWeek, int day) {
        this.positionWeek = positionWeek;
        this.day = day;
    }

    @Override
    public void init() {
        getView().updateList(positionWeek, day);
    }

    @Override
    public void onWeekSelected(int position) {
        getView().updateList(positionWeek, day);
    }

    @Override
    public void onSubjectClick(int position) {
        getView().showEditDialog(subjectList, position, EditSchedulePageFragmentView.SUBJECT);
    }

    @Override
    public void onAudienceClick(int position) {
        getView().showEditDialog(audienceList, position, EditSchedulePageFragmentView.AUDIENCE);
    }

    @Override
    public void onEducatorClick(int position) {
        getView().showEditDialog(educatorList, position, EditSchedulePageFragmentView.EDUCATOR);
    }

    @Override
    public void onTypelessonClick(int position) {
        getView().showEditDialog(typelessonList, position, EditSchedulePageFragmentView.TYPELESSON);
    }

    @Override
    public void onCopyUpClick(int position) {
        getView().onCopyUpClick(position);
    }

    @Override
    public void onCopyDownClick(int position) {
        getView().onCopyDownClick(position);
    }

    @Override
    public void onClearLessonClick(int position) {
        getView().onClearLessonClick(position);
    }

}

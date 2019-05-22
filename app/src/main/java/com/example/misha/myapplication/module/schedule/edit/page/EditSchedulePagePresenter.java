package com.example.misha.myapplication.module.schedule.edit.page;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.Subject;
import com.example.misha.myapplication.entity.Typelesson;

import java.util.ArrayList;

import static com.example.misha.myapplication.Constants.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.Constants.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.Constants.FRAGMENT_TYPELESSONS;


public class EditSchedulePagePresenter extends BaseMainPresenter<EditSchedulePageFragmentView> implements EditSchedulePagePresenterInterface {


    private ArrayList<Lesson> lessonList = new ArrayList<>();

    private int positionWeek;
    private int day;

    public EditSchedulePagePresenter(int positionWeek, int day) {
        this.positionWeek = positionWeek;
        this.day = day;
    }

    @Override
    public void init() {
        updateList(day, positionWeek);
    }


    @Override
    public void onSubjectClick(int position) {
        ArrayList<Subject> subjectList = SubjectDao.getInstance().getAllData();
        getView().showEditDialog(subjectList, position, FRAGMENT_SUBJECTS);
        updateList(day, positionWeek);
    }

    @Override
    public void onAudienceClick(int position) {
        ArrayList<Audience> audienceList = AudienceDao.getInstance().getAllData();
        getView().showEditDialog(audienceList, position, FRAGMENT_AUDIENCES);
    }

    @Override
    public void onEducatorClick(int position) {
        ArrayList<Educator> educatorList = EducatorDao.getInstance().getAllData();
        getView().showEditDialog(educatorList, position, FRAGMENT_EDUCATORS);
    }

    @Override
    public void onTypelessonClick(int position) {
        ArrayList<Typelesson> typelessonList = TypelessonDao.getInstance().getAllData();
        getView().showEditDialog(typelessonList, position, FRAGMENT_TYPELESSONS);
    }

    @Override
    public void setWeek(int positionWeek) {
        this.positionWeek = positionWeek;
        updateList(day, positionWeek);
    }

    public ArrayList<Lesson> getLessonList() {
        return lessonList;
    }

    @Override
    public void onCopyUpClick(int position) {
        lessonList.get(position - 1).setSubject(lessonList.get(position).getSubject());
        lessonList.get(position - 1).setAudience(lessonList.get(position).getAudience());
        lessonList.get(position - 1).setTypeLesson(lessonList.get(position).getTypeLesson());
        lessonList.get(position - 1).setEducator(lessonList.get(position).getEducator());
        getView().updateView(lessonList);
        LessonDao.getInstance().updateItemByID(lessonList.get(position - 1));
    }

    @Override
    public void onCopyDownClick(int position) {
        lessonList.get(position + 1).setSubject(lessonList.get(position).getSubject());
        lessonList.get(position + 1).setAudience(lessonList.get(position).getAudience());
        lessonList.get(position + 1).setTypeLesson(lessonList.get(position).getTypeLesson());
        lessonList.get(position + 1).setEducator(lessonList.get(position).getEducator());
        getView().updateView(lessonList);
        LessonDao.getInstance().updateItemByID(lessonList.get(position + 1));
    }

    @Override
    public void onClearLessonClick(int position) {
        lessonList.get(position).setSubject("0");
        lessonList.get(position).setAudience("0");
        lessonList.get(position).setTypeLesson("0");
        lessonList.get(position).setEducator("0");
        getView().updateView(lessonList);
        LessonDao.getInstance().updateItemByID(lessonList.get(position));
    }

    public void updateList(int day, int positionWeek) {
        lessonList = LessonDao.getInstance().getLessonByWeekAndDay(positionWeek, day);
        getView().updateView(lessonList);
    }

}

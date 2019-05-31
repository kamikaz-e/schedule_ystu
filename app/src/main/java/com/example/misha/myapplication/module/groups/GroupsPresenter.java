package com.example.misha.myapplication.module.groups;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.AppContentProvider;
import com.example.misha.myapplication.data.database.DatabaseHelper;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.util.DataUtil;

import java.util.ArrayList;

import static com.example.misha.myapplication.data.database.dao.LessonDao.ID;
import static com.example.misha.myapplication.data.database.dao.LessonDao.ID_AUDIENCE;
import static com.example.misha.myapplication.data.database.dao.LessonDao.ID_EDUCATOR;
import static com.example.misha.myapplication.data.database.dao.LessonDao.ID_SUBJECT;
import static com.example.misha.myapplication.data.database.dao.LessonDao.ID_TYPE_LESSON;
import static com.example.misha.myapplication.data.database.dao.LessonDao.NUMBER_DAY;
import static com.example.misha.myapplication.data.database.dao.LessonDao.NUMBER_LESSON;
import static com.example.misha.myapplication.data.database.dao.LessonDao.NUMBER_WEEK;

public class GroupsPresenter extends BaseMainPresenter<GroupsFragmentView> implements GroupsPresenterInterface {

    private ArrayList<Groups> listGroups = new ArrayList<>();
    private Activity context;

    public GroupsPresenter(FragmentActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
        getView().updateListGroups(listGroups);
    }

    public void onClickItem(String group) {
        loadSubjects(group);
    }

    @Override
    public void load() {
        getView().showProgressBar();
        getCompositeDisposable().add(getRepositoryManager()
                .getGroups()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::loadGroups, throwable -> {
                    getView().hideProgressBar();
                    getView().showErrorView();
                    processGlobalError(throwable);
                })
        );
    }

    @Override
    public void loadGroups(ArrayList<Groups> groups) {
        listGroups.clear();
        listGroups.addAll(groups);
        init();
    }


    public void loadSubjects(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getSubjects(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(subjects -> {
                    if (subjects.isEmpty()) {
                        Toast.makeText(context, context.getString(R.string.string_load_subjects), Toast.LENGTH_SHORT).show();
                    } else {
                        SubjectDao.getInstance().deleteAll();
                        SubjectDao.getInstance().insertAll(subjects);
                        loadAudiences(group);
                    }
                }, this::processSimpleError)
        );
    }

    public void loadAudiences(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getAudiences(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(audiences -> {
                    AudienceDao.getInstance().deleteAll();
                    AudienceDao.getInstance().insertAll(audiences);
                    loadEducators(group);
                }, this::processSimpleError)
        );
    }

    public void loadEducators(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getEducators(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(educators -> {
                    EducatorDao.getInstance().deleteAll();
                    EducatorDao.getInstance().insertAll(educators);
                    loadTypelessons(group);
                }, this::processSimpleError)
        );
    }

    public void loadTypelessons(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getTypelessons()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(typelessons -> {
                    TypelessonDao.getInstance().deleteAll();
                    TypelessonDao.getInstance().insertAll(typelessons);
                    loadLessons(group);
                }, this::processSimpleError)
        );
    }

    public void loadLessons(String group) {
        getCompositeDisposable().add(getRepositoryManager()
                .getLessons(group)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(lessons -> {
                    LessonDao.getInstance().deleteAll();
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    SQLiteDatabase database = databaseHelper.getReadableDatabase();
                    ContentValues set;
                    database.beginTransaction();
                    try {
                        for (int i = 0; i < 612; i++) {
                            set = new ContentValues();
                            set.put(ID, i + 1);
                            set.put(NUMBER_WEEK, lessons.get(i).getNumber_week());
                            set.put(NUMBER_DAY, lessons.get(i).getNumber_day());
                            set.put(NUMBER_LESSON, lessons.get(i).getNumber_lesson());
                            set.put(ID_SUBJECT, lessons.get(i).getId_subject());
                            set.put(ID_AUDIENCE, lessons.get(i).getId_audience());
                            set.put(ID_EDUCATOR, lessons.get(i).getId_educator());
                            set.put(ID_TYPE_LESSON, lessons.get(i).getId_typelesson());
                            database.insert(AppContentProvider.LESSONS_TABLE, null, set);
                        }
                        database.setTransactionSuccessful();
                    } finally {
                        database.endTransaction();
                    }
                    DataUtil.hintKeyboard(context);
                    getView().openFragmentSchedule();
                }, this::processSimpleError)
        );
    }


}
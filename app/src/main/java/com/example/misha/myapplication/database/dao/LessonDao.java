package com.example.misha.myapplication.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.misha.myapplication.database.AbsDao;
import com.example.misha.myapplication.database.AppContentProvider;
import com.example.misha.myapplication.database.entity.Lesson;

public class LessonDao extends AbsDao<Lesson> {

    private static volatile LessonDao instance;

    private LessonDao() {}

    public static LessonDao getInstance() {
        if (null == instance){
            instance = new LessonDao();
        }
        return instance;
    }

    public final static String ID = "_id";
    public final static String ID_WEEK = "id_week";
    public final static String ID_DAY = "id_day";
    public final static String ID_SUBJECT = "id_subject";
    public final static String ID_AUDIENCE = "id_audience";
    public final static String ID_EDUCATOR = "id_educator";
    public final static String ID_TYPE_LESSON = "id_typelesson";
    public final static String TIME_LESSON = "time_lesson";

    public static final String[] ALL_SET_PROPERTIES = new String[] {ID, ID_WEEK,
            ID_DAY, ID_SUBJECT, ID_AUDIENCE, ID_EDUCATOR, ID_TYPE_LESSON, TIME_LESSON};

    @Override
    protected String[] getAllColumns() {
        return ALL_SET_PROPERTIES;
    }

    @Override
    protected Uri getTableUri() {
        return AppContentProvider.SCHEDULE_URI;
    }

    @Override
    protected Lesson makeInstanceFromCursor(Cursor cursor) {
        Lesson lesson = new Lesson();
        lesson.setId(getString(cursor, ID));
        lesson.setEducator(getString(cursor, ID_EDUCATOR));
        lesson.setAudience(getString(cursor, ID_AUDIENCE));
        lesson.setSubject(getString(cursor, ID_SUBJECT));
        lesson.setTypeLesson(getString(cursor, ID_TYPE_LESSON));
        lesson.setDay(getString(cursor,ID_DAY));
        lesson.setWeek(getString(cursor, ID_WEEK));
        lesson.setTimeLesson((getString(cursor,TIME_LESSON)));
        return lesson;
    }

    @Override
    protected ContentValues makeContentValuesFromInstance(Lesson instance) {
        ContentValues set = new ContentValues();
        set.put(ID, instance.getId());
        set.put(ID_EDUCATOR, instance.getEducator());
        set.put(ID_AUDIENCE, instance.getAudience());
        set.put(ID_SUBJECT, instance.getSubject());
        set.put(ID_TYPE_LESSON, instance.getTimeLesson());
        set.put(ID_DAY, instance.getDay());
        set.put(ID_WEEK, instance.getWeek());
        set.put(TIME_LESSON, instance.getTimeLesson());
        return set;
    }
}

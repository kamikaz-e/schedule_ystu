package com.example.misha.myapplication.data.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.AppContentProvider;
import com.example.misha.myapplication.entity.Lesson;

import java.util.ArrayList;

public class LessonDao extends AbsDao<Lesson> {

    public final static String ID = "id";
    public final static String WEEK = "week";
    public final static String DAY = "day";
    public final static String ID_SUBJECT = "id_subject";
    public final static String ID_AUDIENCE = "id_audience";
    public final static String ID_EDUCATOR = "id_educator";
    public final static String ID_TYPE_LESSON = "id_typelesson";
    public final static String TIME_LESSON = "id_time_lesson";
    public static final String[] ALL_LESSONS_PROPERTIES = new String[]{ID, WEEK,
            DAY, ID_SUBJECT, ID_AUDIENCE, ID_EDUCATOR, ID_TYPE_LESSON, TIME_LESSON};
    private static volatile LessonDao instance;
    private LessonDao() {
    }

    public static LessonDao getInstance() {
        if (null == instance) {
            instance = new LessonDao();
        }
        return instance;
    }

    @Override
    protected String[] getAllColumns() {
        return ALL_LESSONS_PROPERTIES;
    }

    @Override
    protected Uri getTableUri() {
        return AppContentProvider.LESSONS_URI;
    }

    @Override
    protected Lesson makeInstanceFromCursor(Cursor cursor) {
        Lesson lesson = new Lesson();
        lesson.setId(getString(cursor, ID));
        lesson.setWeek(getString(cursor, WEEK));
        lesson.setDay(getString(cursor, DAY));
        lesson.setSubject(getString(cursor, ID_SUBJECT));
        lesson.setAudience(getString(cursor, ID_AUDIENCE));
        lesson.setEducator(getString(cursor, ID_EDUCATOR));
        lesson.setTypeLesson(getString(cursor, ID_TYPE_LESSON));
        lesson.setTimeLesson((getString(cursor, TIME_LESSON)));
        return lesson;
    }

    @Override
    protected ContentValues makeContentValuesFromInstance(Lesson instance) {
        ContentValues set = new ContentValues();
        set.put(ID, instance.getId());
        set.put(WEEK, instance.getWeek());
        set.put(DAY, instance.getDay());
        set.put(ID_SUBJECT, instance.getSubject());
        set.put(ID_AUDIENCE, instance.getAudience());
        set.put(ID_EDUCATOR, instance.getEducator());
        set.put(ID_TYPE_LESSON, instance.getTypeLesson());
        set.put(TIME_LESSON, instance.getTimeLesson());
        return set;
    }


    public void initTable() {
        ArrayList<Lesson> dd = getAllData();
        if (!dd.isEmpty()) return;
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (int week = 0; week < 17; week++) {
            for (int day = 0; day < 6; day++) {
                for (int timeLesson = 1; timeLesson < 7; timeLesson++) {
                    lessons.add(new Lesson(week, day, 0, 0, 0, 0, timeLesson));
                }
            }
        }
        insertAll(lessons);
    }


    public ArrayList<Lesson> getLessonByWeekAndDay(int week, int day) {
        Cursor cursor = getContentResolver().query(getTableUri(), getAllColumns(),
                WEEK + EQUALS + " AND " + DAY + EQUALS, new String[]{String.valueOf(week), String.valueOf(day)}, null);
        return extractItemsFromCursor(cursor);
    }


    public ArrayList<Lesson> getLessonByWeek(int week) {
        Cursor cursor = getContentResolver().query(getTableUri(), getAllColumns(),
                WEEK + EQUALS, new String[]{String.valueOf(week)}, null);
        return extractItemsFromCursor(cursor);
    }


    public boolean updateItemByID(Lesson lesson) {
        int affectedRows = getContentResolver().update(getTableUri(), makeContentValuesFromInstance(lesson),
                ID + EQUALS, new String[]{String.valueOf(lesson.getId())});
        return affectedRows == 1;
    }

}

package com.example.misha.myapplication.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.misha.myapplication.database.AbsDao;
import com.example.misha.myapplication.database.AppContentProvider;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Lesson;

public class CallDao extends AbsDao<Calls> {

    private static volatile CallDao instance;

    private CallDao() {}

    public static CallDao getInstance() {
        if (null == instance){
            instance = new CallDao();
        }
        return instance;
    }

    public final static String ID =  "id_call";
    public final static String TIME = "time";


    public static final String[] ALL_SET_PROPERTIES = new String[] {ID, TIME};

    @Override
    protected String[] getAllColumns() {
        return ALL_SET_PROPERTIES;
    }

    @Override
    protected Uri getTableUri() {
        return AppContentProvider.SCHEDULE_URI;
    }

    @Override
    protected Calls makeInstanceFromCursor(Cursor cursor) {
        Calls calls = new Calls();
        calls.setId(getString(cursor, ID));
        calls.setName(getString(cursor, TIME));
        return calls;
    }

    @Override
    protected ContentValues makeContentValuesFromInstance(Calls instance) {
        ContentValues set = new ContentValues();
        set.put(ID, instance.getId());
        set.put(TIME, instance.getName());
        return set;
    }
}

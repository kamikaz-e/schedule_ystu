package com.example.misha.myapplication.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.misha.myapplication.database.AbsDao;
import com.example.misha.myapplication.database.AppContentProvider;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Lesson;

import java.util.ArrayList;

public class CallDao extends AbsDao<Calls> {

    private static volatile CallDao instance;

    private CallDao() {}

    public static CallDao getInstance() {
        if (null == instance){
            instance = new CallDao();
        }
        return instance;
    }

    public final static String ID =  "id";
    public final static String TIME = "time_lesson";


    public static final String[] ALL_SET_PROPERTIES = new String[] {ID, TIME};

    @Override
    protected String[] getAllColumns() {
        return ALL_SET_PROPERTIES;
    }

    @Override
    protected Uri getTableUri() {
        return AppContentProvider.CALLS_URI;
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

    public void initTable() {
        ArrayList<Calls> dd = getAllData();
        if (!dd.isEmpty()) return;
        ArrayList<Calls> calls = new ArrayList<>();

            calls.add(new Calls( String.valueOf(1), "типа время1"));
            calls.add(new Calls( String.valueOf(2), "типа время2"));
            calls.add(new Calls( String.valueOf(3), "типа время3"));
            calls.add(new Calls( String.valueOf(4), "типа время4"));
            calls.add(new Calls( String.valueOf(5), "типа время5"));
            calls.add(new Calls( String.valueOf(6), "типа время6"));

        insertAll(calls);
    }

    public boolean updateItemByID(Calls calls) {
        int affectedRows = getContentResolver().update(getTableUri(), makeContentValuesFromInstance(calls),
                KEY_ID + EQUALS, new String[]{String.valueOf(calls.getId())});
        return affectedRows == 1;
    }

}

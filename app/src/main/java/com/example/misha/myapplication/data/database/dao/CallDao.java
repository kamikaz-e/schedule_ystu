package com.example.misha.myapplication.data.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.AppContentProvider;
import com.example.misha.myapplication.data.database.entity.Calls;

import java.util.ArrayList;

public class CallDao extends AbsDao<Calls> {

    private static volatile CallDao instance;

    private CallDao() {
    }

    public static CallDao getInstance() {
        if (null == instance) {
            instance = new CallDao();
        }
        return instance;
    }

    public final static String ID = "id";
    public final static String TIME = "time_lesson";


    public static final String[] ALL_SET_PROPERTIES = new String[]{ID, TIME};

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

        calls.add(new Calls(String.valueOf(1), "8:30 - 10:00 "));
        calls.add(new Calls(String.valueOf(2), "10:10 - 11:40 "));
        calls.add(new Calls(String.valueOf(3), "12:20 - 13:50 "));
        calls.add(new Calls(String.valueOf(4), "14:00 - 15:30 "));
        calls.add(new Calls(String.valueOf(5), "15:40 - 17:10 "));
        calls.add(new Calls(String.valueOf(6), "17:30 - 19:00 "));

        insertAll(calls);
    }

    public boolean updateItemByID(Calls calls) {
        int affectedRows = getContentResolver().update(getTableUri(), makeContentValuesFromInstance(calls),
                KEY_ID + EQUALS, new String[]{String.valueOf(calls.getId())});
        return affectedRows == 1;
    }

}

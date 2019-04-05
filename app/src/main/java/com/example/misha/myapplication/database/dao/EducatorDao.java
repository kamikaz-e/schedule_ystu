package com.example.misha.myapplication.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.misha.myapplication.database.AbsDao;
import com.example.misha.myapplication.database.AppContentProvider;
import com.example.misha.myapplication.database.entity.Educator;

public class EducatorDao extends AbsDao<Educator> {

    private static volatile EducatorDao instance;

    private EducatorDao() {}

    public static EducatorDao getInstance() {
        if (null == instance){
            instance = new EducatorDao();
        }
        return instance;
    }

    public final static String ID =  "idd_educator";
    public final static String EDUCATOR = "educators";

    public static final String[] ALL_SET_PROPERTIES = new String[] {ID, EDUCATOR};

    @Override
    protected String[] getAllColumns() {
        return ALL_SET_PROPERTIES;
    }

    @Override
    protected Uri getTableUri() {
        return AppContentProvider.SCHEDULE_URI;
    }

    @Override
    protected Educator makeInstanceFromCursor(Cursor cursor) {
        Educator educator = new Educator();
        educator.setId(getString(cursor, ID));
        educator.setName(getString(cursor, EDUCATOR));
        return educator;
    }

    @Override
    protected ContentValues makeContentValuesFromInstance(Educator instance) {
        ContentValues set = new ContentValues();
        set.put(ID, instance.getId());
        set.put(EDUCATOR, instance.getName());
        return set;
    }
}

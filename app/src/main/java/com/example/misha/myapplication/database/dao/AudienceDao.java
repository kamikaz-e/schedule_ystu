package com.example.misha.myapplication.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.misha.myapplication.database.AbsDao;
import com.example.misha.myapplication.database.AppContentProvider;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Lesson;

public class AudienceDao extends AbsDao<Audience> {

    private static volatile AudienceDao instance;

    private AudienceDao() {}

    public static AudienceDao getInstance() {
        if (null == instance){
            instance = new AudienceDao();
        }
        return instance;
    }

    public final static String ID = "id";
    public final static String AUDIENCE = "audience";

    public static final String[] ALL_SET_PROPERTIES = new String[] {ID, AUDIENCE};

    @Override
    protected String[] getAllColumns() {
        return ALL_SET_PROPERTIES;
    }


    @Override
    protected Uri getTableUri() {
        return AppContentProvider.AUDIENCES_URI;
    }

    @Override
    protected Audience makeInstanceFromCursor(Cursor cursor) {
        Audience audience = new Audience();
        audience.setId(getString(cursor, ID));
        audience.setName(getString(cursor, AUDIENCE));
        return audience;
    }

    @Override
    protected ContentValues makeContentValuesFromInstance(Audience instance) {
        ContentValues set = new ContentValues();
        set.put(ID, instance.getId());
        set.put(AUDIENCE, instance.getName());
        return set;
    }

    public boolean deleteItemById(long id) {
        return super.deleteItemById(id, ID);
    }
}

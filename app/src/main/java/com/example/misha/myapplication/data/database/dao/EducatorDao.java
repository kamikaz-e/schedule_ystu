package com.example.misha.myapplication.data.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.AppContentProvider;
import com.example.misha.myapplication.data.database.entity.Educator;

public class EducatorDao extends AbsDao<Educator> {

    private static volatile EducatorDao instance;

    private EducatorDao() {}

    public static EducatorDao getInstance() {
        if (null == instance){
            instance = new EducatorDao();
        }
        return instance;
    }

    public final static String ID =  "id";
    public final static String EDUCATOR = "educator";

    public static final String[] ALL_SET_PROPERTIES = new String[] {ID, EDUCATOR};

    @Override
    protected String[] getAllColumns() {
        return ALL_SET_PROPERTIES;
    }

    @Override
    protected Uri getTableUri() {
        return AppContentProvider.EDUCATORS_URI;
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
    public boolean deleteItemById(long id) {
        return super.deleteItemById(id, ID);
    }
}

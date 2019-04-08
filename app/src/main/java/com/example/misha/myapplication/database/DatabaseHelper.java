package com.example.misha.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/** Class creates data base if it don't exist. */
public class DatabaseHelper extends SQLiteOpenHelper {

    /** Name of database file. */
    public static final String DATABASE_NAME = "Lesson.db";
    /** Database version. */
    public static final int DATABASE_VERSION = 1;
    /** Migration list. */
    private final ArrayList<Patch> migrationsList = new ArrayList<Patch>() { {
        add(createV1Patch());
    }
    };



    private static final String CREATE_TABLE_SUBJECTS= "CREATE TABLE  subjects " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "subject VARCHAR UNIQUE ON CONFLICT IGNORE );";
    private static final String CREATE_TABLE_AUDIENCES= "CREATE TABLE  audiences  " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "audience VARCHAR UNIQUE ON CONFLICT IGNORE );";
    private static final String CREATE_TABLE_EDUCATORS= "CREATE TABLE  educators  " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "educator VARCHAR UNIQUE ON CONFLICT IGNORE );";
    private static final String CREATE_TABLE_TYPELESSONS= "CREATE TABLE  typelessons  " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " typelesson VARCHAR UNIQUE ON CONFLICT IGNORE );";

    private static final String CREATE_CALL_SCHEDULE  = "CREATE TABLE calls " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "time_lesson VARCHAR UNIQUE ON CONFLICT IGNORE );";

    private static final String CREATE_TABLE_LESSONS = "CREATE TABLE lessons " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "week INTEGER, day INTEGER, id_subject INTEGER, id_audience INTEGER, id_educator INTEGER, id_typelesson INTEGER, id_time_lesson INTEGER," +
            "FOREIGN KEY (id_subject) REFERENCES subjects (id), " +
            "FOREIGN KEY (id_audience) REFERENCES audiences (id), " +
            "FOREIGN KEY (id_educator) REFERENCES educators (id), " +
            "FOREIGN KEY (id_typelesson) REFERENCES typelessons (id), " +
            "FOREIGN KEY (id_time_lesson) REFERENCES calls (id))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sdb) {
        for (int i = 0; i < migrationsList.size(); i++) {
            migrationsList.get(i).apply(sdb);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            migrationsList.get(i).revert(sdb);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            migrationsList.get(i).apply(sdb);
        }
    }


    /**
     * Create v1 patch.
     * @return v1 patch
     */
    private Patch createV1Patch() {
        return new Patch() {

            public void apply(SQLiteDatabase sdb) {
                sdb.execSQL(CREATE_TABLE_SUBJECTS);
                sdb.execSQL(CREATE_TABLE_AUDIENCES);
                sdb.execSQL(CREATE_TABLE_EDUCATORS);
                sdb.execSQL(CREATE_TABLE_TYPELESSONS);
                sdb.execSQL(CREATE_CALL_SCHEDULE);
                sdb.execSQL(CREATE_TABLE_LESSONS);
            }

            @Override
            public void revert(SQLiteDatabase sdb) {
                //do nothing
            }
        };
    }

    /** Database patch abstract class. */
    abstract class Patch {

        /**
         * Apply patch.
         * @param sdb database
         */
        public abstract void apply(SQLiteDatabase sdb);

        /**
         * Revert patch.
         * @param sdb database
         */
        public abstract void revert(SQLiteDatabase sdb);
    }
}

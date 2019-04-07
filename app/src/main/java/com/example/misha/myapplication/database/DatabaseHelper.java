package com.example.misha.myapplication.database;

import android.annotation.SuppressLint;
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
            "id_week INTEGER, id_day INTEGER, id_subject INTEGER, id_audience INTEGER, id_educator INTEGER, id_typelesson INTEGER, id_time_lesson INTEGER," +
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

    /** Migration list. */
    private final ArrayList<Patch> migrationsList = new ArrayList<Patch>() { {
            add(createV1Patch());
        }
    };

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

                for (int week = 1; week < 19; week++) {
                    for (int day = 1; day < 7; day++) {
                        for (int lesson = 1; lesson < 7; lesson++) {
                            String sql = "INSERT INTO lessons (" +
                                    "id_week,  " +
                                    "id_day," +
                                    "id_subject," +
                                    "id_audience," +
                                    "id_educator, " +
                                    "id_typelesson, " +
                                    "id_time_lesson )" +
                                    " VALUES(" + week + "," + day + ",'','','',''," + lesson+");";
                            sdb.execSQL(sql);
                        }
                    }
                }
            }

            @Override
            public void revert(SQLiteDatabase sdb) {
                //do nothing
            }
        };
    }

    /**
     * Create v1 patch.
     * @return v1 patch
     */
    private Patch createV2Patch() {
        return new Patch() {
            @SuppressLint("SQLiteString")
            @Override
            public void apply(SQLiteDatabase sdb) {
                sdb.execSQL("DROP TABLE IF EXISTS subjects");
                sdb.execSQL("DROP TABLE IF EXISTS audiences");
                sdb.execSQL("DROP TABLE IF EXISTS educators");
                sdb.execSQL("DROP TABLE IF EXISTS typelessons");
                sdb.execSQL("DROP TABLE IF EXISTS calls");
                sdb.execSQL("DROP TABLE IF EXISTS lessons");
                onCreate(sdb);
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

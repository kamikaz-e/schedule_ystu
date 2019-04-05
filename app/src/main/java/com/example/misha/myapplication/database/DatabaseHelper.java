package com.example.misha.myapplication.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.entity.Lesson;

import java.util.ArrayList;

import static com.example.misha.myapplication.data.ScheduleClass.audiences.AUDIENCE;
import static com.example.misha.myapplication.data.ScheduleClass.calls.CALLS;
import static com.example.misha.myapplication.data.ScheduleClass.date_start.DATE_START;
import static com.example.misha.myapplication.data.ScheduleClass.educators.EDUCATOR;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.SCHEDULE;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.SUBJECT;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.TYPELESSON;

/** Class creates data base if it don't exist. */
public class DatabaseHelper extends SQLiteOpenHelper {

    /** Name of database file. */
    public static final String DATABASE_NAME = "Lesson.db";
    /** Database version. */
    public static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE_SUBJECTS= "CREATE TABLE  subjects  (" +
            " idd_subject INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "subject STRING UNIQUE ON CONFLICT IGNORE );";
    private static final String CREATE_TABLE_AUDIENCES= "CREATE TABLE  audiences  (" +
            " idd_audience INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "audience STRING UNIQUE ON CONFLICT IGNORE );";
    private static final String CREATE_TABLE_EDUCATORS= "CREATE TABLE  educators  (" +
            " idd_educator INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "educators STRING UNIQUE ON CONFLICT IGNORE );";
    private static final String CREATE_TABLE_TYPELESSONS= "CREATE TABLE  typelessons  ( " +
            "idd_typelesson INTEGER PRIMARY KEY AUTOINCREMENT," +
            " typelesson STRING UNIQUE ON CONFLICT IGNORE );";


    private static final String CREATE_TABLE_SCHEDULE = "CREATE TABLE schedule (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_week INTEGER, id_day INTEGER, id_subject INTEGER, id_audience INTEGER, id_educator INTEGER, id_typelesson INTEGER, time_lesson LONG," +
            "FOREIGN KEY (id_subject) REFERENCES subjects (idd_subject), " +
            "FOREIGN KEY (id_audience) REFERENCES audiences (idd_audience), " +
            "FOREIGN KEY (id_educator) REFERENCES educators (idd_educator), " +
            "FOREIGN KEY (id_typelesson) REFERENCES typelessons (idd_typelesson))";

    private static final String CREATE_CALL_SCHEDULE  = "CREATE TABLE calls ( id_call INTEGER PRIMARY KEY AUTOINCREMENT, time  STRING );";


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
            @SuppressLint("SQLiteString")
            @Override
            public void apply(SQLiteDatabase sdb) {
                sdb.execSQL(CREATE_TABLE_SCHEDULE);
                sdb.execSQL(CREATE_TABLE_SUBJECTS);
                sdb.execSQL(CREATE_TABLE_AUDIENCES);
                sdb.execSQL(CREATE_TABLE_EDUCATORS);
                sdb.execSQL(CREATE_TABLE_TYPELESSONS);
                sdb.execSQL(CREATE_CALL_SCHEDULE);
                ArrayList<Lesson> lessons = new ArrayList<>();
                for (int j = 1; j < 19; j++) {
                    for (int i = 1; i < 7; i++) {
                        lessons.add(new Lesson(j, i, "", "", "", "", ""));
                    }
                }
                LessonDao.getInstance().insertAll(lessons);
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
                sdb.execSQL("DROP TABLE IF EXISTS " + SUBJECT);
                sdb.execSQL("DROP TABLE IF EXISTS " + AUDIENCE);
                sdb.execSQL("DROP TABLE IF EXISTS " + EDUCATOR);
                sdb.execSQL("DROP TABLE IF EXISTS " + TYPELESSON);
                sdb.execSQL("DROP TABLE IF EXISTS " + SCHEDULE);
                sdb.execSQL("DROP TABLE IF EXISTS " + CALLS);
                sdb.execSQL("DROP TABLE IF EXISTS " + DATE_START);
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

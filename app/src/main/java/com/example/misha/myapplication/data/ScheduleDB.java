package com.example.misha.myapplication.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.misha.myapplication.ScheduleApp;
import com.example.misha.myapplication.data.ScheduleClass.date_start;
import java.util.Calendar;

import static com.example.misha.myapplication.data.ScheduleClass.audiences.AUDIENCE;
import static com.example.misha.myapplication.data.ScheduleClass.audiences.audience;
import static com.example.misha.myapplication.data.ScheduleClass.audiences.audience_id;
import static com.example.misha.myapplication.data.ScheduleClass.calls.CALLS;
import static com.example.misha.myapplication.data.ScheduleClass.calls.id_call;
import static com.example.misha.myapplication.data.ScheduleClass.calls.time;
import static com.example.misha.myapplication.data.ScheduleClass.date_start.DATE_START;
import static com.example.misha.myapplication.data.ScheduleClass.date_start.date;
import static com.example.misha.myapplication.data.ScheduleClass.date_start.id_date;
import static com.example.misha.myapplication.data.ScheduleClass.educators.EDUCATOR;
import static com.example.misha.myapplication.data.ScheduleClass.educators.educator;
import static com.example.misha.myapplication.data.ScheduleClass.educators.educator_id;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.SCHEDULE;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_audience;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_day;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_educator;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_subject;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_typelesson;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.id_week;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.SUBJECT;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.subject;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.subject_id;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.TYPELESSON;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.typelesson;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.typelesson_id;


public class ScheduleDB extends SQLiteOpenHelper {

    public static final String LOG_TAG = ScheduleDB.class.getSimpleName();

    private static final String DATABASE_NAME = "Lesson.db";

    private static final int DATABASE_VERSION = 1;




    private static final String CREATE_TABLE_SUBJECTS= "CREATE TABLE " + SUBJECT + " ("
            + subject_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + subject + " STRING UNIQUE ON CONFLICT IGNORE );";

    private static final String CREATE_TABLE_AUDIENCES="CREATE TABLE " + AUDIENCE + " ("
            + audience_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + audience + " STRING UNIQUE ON CONFLICT IGNORE );";

    private static final String CREATE_TABLE_EDUCATORS = "CREATE TABLE " + EDUCATOR + " ("
            + educator_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + educator + " STRING UNIQUE ON CONFLICT IGNORE );";

    private static final String  CREATE_TABLE_TYPELESSONS = "CREATE TABLE " + TYPELESSON + " ("
            + typelesson_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + typelesson + " STRING UNIQUE ON CONFLICT IGNORE );";

    private static final String  CREATE_TABLE_SCHEDULE = "CREATE TABLE " + SCHEDULE + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + id_week+ " INTEGER, "
            + id_day + " INTEGER, "
            + id_subject + " INTEGER, "
            + id_audience + " INTEGER, "
            + id_educator + " INTEGER, "
            + id_typelesson + " INTEGER, "
            + " FOREIGN KEY (" + id_subject + ") REFERENCES " + SUBJECT + "(" + subject_id + "),"
            + " FOREIGN KEY (" + id_audience + ") REFERENCES " + AUDIENCE + "(" + audience_id + "),"
            + " FOREIGN KEY (" + id_educator + ") REFERENCES " + EDUCATOR + "(" + educator_id + "),"
            + " FOREIGN KEY (" + id_typelesson + ") REFERENCES " + TYPELESSON + "(" + typelesson_id + "));";

    private static final String CREATE_CALL_SCHEDULE  = "CREATE TABLE " + CALLS + " ("
            +  id_call + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + time + " STRING );";


    private static final String  CREATE_CURRENT_DATE_SCHEDULE = "CREATE TABLE " + DATE_START + " ("
            + id_date + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + date + " STRING UNIQUE ON CONFLICT IGNORE );";


    public ScheduleDB() {
        super(ScheduleApp.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }






    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_SUBJECTS);
        db.execSQL(CREATE_TABLE_AUDIENCES);
        db.execSQL(CREATE_TABLE_EDUCATORS);
        db.execSQL(CREATE_TABLE_TYPELESSONS);
        db.execSQL(CREATE_TABLE_SCHEDULE);
        db.execSQL(CREATE_CALL_SCHEDULE);
        db.execSQL(CREATE_CURRENT_DATE_SCHEDULE);

        for (int j = 1; j < 19; j++) {
                for (int i = 1; i < 7; i++) {
                    String sql = "INSERT INTO " + SCHEDULE + "("
                            + id_week + "," + ScheduleClass.schedule.id_day + "," + id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + SCHEDULE + "("
                            + id_week + "," + ScheduleClass.schedule.id_day + "," + id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + SCHEDULE + "("
                            + id_week + "," + ScheduleClass.schedule.id_day + "," + id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + SCHEDULE + "("
                            + id_week + "," + ScheduleClass.schedule.id_day + "," + id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + SCHEDULE + "("
                            + id_week + "," + ScheduleClass.schedule.id_day + "," + id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + SCHEDULE + "("
                            + id_week + "," + ScheduleClass.schedule.id_day + "," + id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                }
            }

        Calendar today = Calendar.getInstance();
        String  current_date = String.valueOf(today.getTimeInMillis());
        db.execSQL("INSERT INTO " + DATE_START + " (" + date + ") VALUES ("+current_date+");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + AUDIENCE);
        db.execSQL("DROP TABLE IF EXISTS " + EDUCATOR);
        db.execSQL("DROP TABLE IF EXISTS " + TYPELESSON);
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + CALLS);
        db.execSQL("DROP TABLE IF EXISTS " + DATE_START);
        onCreate(db);
            }}
package com.example.misha.myapplication.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.misha.myapplication.data.ScheduleClass.date_start;

import java.util.Calendar;


public class ScheduleDB extends SQLiteOpenHelper {

    public static final String LOG_TAG = ScheduleDB.class.getSimpleName();

    /**
     * Имя файла базы данных
     */
    private static final String DATABASE_NAME = "Lesson.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Конструктор {@link ScheduleDB}.
     *
     * @param context Контекст приложения
     */
    public ScheduleDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Вызывается при создании базы данных
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

      db.beginTransaction();
      try {
            String subjects = "CREATE TABLE " + ScheduleClass.subjects.TABLE_NAME + " ("
                    + ScheduleClass.subjects.idd_subject + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.subjects.subject + " STRING UNIQUE ON CONFLICT IGNORE );";
            db.execSQL(subjects);

            String audiences = "CREATE TABLE " + ScheduleClass.audiences.TABLE_NAME + " ("
                    + ScheduleClass.audiences.idd_audience + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.audiences.audience + " STRING UNIQUE ON CONFLICT IGNORE );";
            db.execSQL(audiences);

            String educators = "CREATE TABLE " + ScheduleClass.educators.TABLE_NAME + " ("
                    + ScheduleClass.educators.idd_educator + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.educators.educator + " STRING UNIQUE ON CONFLICT IGNORE );";
            db.execSQL(educators);

            String typelessons = "CREATE TABLE " + ScheduleClass.typelessons.TABLE_NAME + " ("
                    + ScheduleClass.typelessons.idd_typelesson + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.typelessons.typelesson + " STRING UNIQUE ON CONFLICT IGNORE );";
            db.execSQL(typelessons);

            String schedule = "CREATE TABLE " + ScheduleClass.schedule.TABLE_NAME + " ("
                    + ScheduleClass.schedule.id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.schedule.id_week + " INTEGER, "
                    + ScheduleClass.schedule.id_day + " INTEGER, "
                    + ScheduleClass.schedule.id_subject + " INTEGER, "
                    + ScheduleClass.schedule.id_audience + " INTEGER, "
                    + ScheduleClass.schedule.id_educator + " INTEGER, "
                    + ScheduleClass.schedule.id_typelesson + " INTEGER, "
                   + " FOREIGN KEY (" + ScheduleClass.schedule.id_subject + ") REFERENCES " + ScheduleClass.subjects.TABLE_NAME + "(" + ScheduleClass.subjects.idd_subject + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_audience + ") REFERENCES " + ScheduleClass.audiences.TABLE_NAME + "(" + ScheduleClass.audiences.idd_audience + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_educator + ") REFERENCES " + ScheduleClass.educators.TABLE_NAME + "(" + ScheduleClass.educators.idd_educator + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_typelesson + ") REFERENCES " + ScheduleClass.typelessons.TABLE_NAME + "(" + ScheduleClass.typelessons.idd_typelesson + "));";
            db.execSQL(schedule);

        String calls_schedule = "CREATE TABLE " + ScheduleClass.calls.TABLE_NAME + " ("
            + ScheduleClass.calls.id_call + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ScheduleClass.calls.time + " STRING );";
        db.execSQL(calls_schedule);

        String current_date_schedule = "CREATE TABLE " + date_start.TABLE_NAME + " ("
            + date_start.id_date + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + date_start.date + " STRING UNIQUE ON CONFLICT IGNORE );";
        db.execSQL(current_date_schedule);

        for (int j = 1; j < 19; j++) {
                for (int i = 1; i < 7; i++) {
                    String sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                            + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + "," + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                            + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + "," + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                            + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + "," + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                            + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + "," + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                            + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + "," + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                    sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                            + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + "," + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience + ","
                            + ScheduleClass.schedule.id_educator + "," + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i + ",'','','','');";
                    db.execSQL(sql);
                }
            }
          db.execSQL("INSERT INTO " + ScheduleClass.typelessons.TABLE_NAME + " (" + ScheduleClass.typelessons.typelesson + ") VALUES ('');");
          db.execSQL("INSERT INTO " + ScheduleClass.typelessons.TABLE_NAME + " (" + ScheduleClass.typelessons.typelesson + ") VALUES ('Лекция');");
          db.execSQL("INSERT INTO " + ScheduleClass.typelessons.TABLE_NAME + " (" + ScheduleClass.typelessons.typelesson + ") VALUES ('Лаб./раб.');");
          db.execSQL("INSERT INTO " + ScheduleClass.typelessons.TABLE_NAME + " (" + ScheduleClass.typelessons.typelesson + ") VALUES ('Практика');");

            for (int i=0;i<6;i++){
            db.execSQL("INSERT INTO " + ScheduleClass.calls.TABLE_NAME + " (" + ScheduleClass.calls.time + ") VALUES ('');");}
            db.execSQL("INSERT INTO " + ScheduleClass.subjects.TABLE_NAME + " (" + ScheduleClass.subjects.subject + ") VALUES ('Предмет');");
            db.execSQL("INSERT INTO " + ScheduleClass.audiences.TABLE_NAME + " (" + ScheduleClass.audiences.audience + ") VALUES ('Аудитория');");
            db.execSQL("INSERT INTO " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator + ") VALUES ('Преподаватель');");

        Calendar today = Calendar.getInstance();
        String  current_date = String.valueOf(today.getTimeInMillis());
        db.execSQL("INSERT INTO " + date_start.TABLE_NAME + " (" + date_start.date + ") VALUES ("+current_date+");");
        db.setTransactionSuccessful();
      } finally {
        db.endTransaction();
      }
    }
    /**
     * Вызывается при обновлении схемы базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*

            String weeks = "CREATE TABLE " + ScheduleClass.weeks.TABLE_NAME + " ("
                    + ScheduleClass.weeks.idd_week + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.weeks.week + " INTEGER);";
            db.execSQL(weeks);

            String days = "CREATE TABLE " + ScheduleClass.days.TABLE_NAME + " ("
                    + ScheduleClass.days.idd_day + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.days.day + " STRING);";
            db.execSQL(days);

            String subjects = "CREATE TABLE " + ScheduleClass.subjects.TABLE_NAME + " ("
                    + ScheduleClass.subjects.idd_subject + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.subjects.subject + " STRING UNIQUE ON CONFLICT IGNORE )";
            db.execSQL(subjects);

            String audiences = "CREATE TABLE " + ScheduleClass.audiences.TABLE_NAME + " ("
                    + ScheduleClass.audiences.idd_audience + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.audiences.audience + " STRING UNIQUE ON CONFLICT IGNORE );";
            db.execSQL(audiences);

            String educators = "CREATE TABLE " + ScheduleClass.educators.TABLE_NAME + " ("
                    + ScheduleClass.educators.idd_educator + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.educators.educator + " STRING UNIQUE ON CONFLICT IGNORE );";
            db.execSQL(educators);

            String typelessons = "CREATE TABLE " + ScheduleClass.typelessons.TABLE_NAME + " ("
                    + ScheduleClass.typelessons.idd_typelesson + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.typelessons.typelesson + " STRING UNIQUE ON CONFLICT IGNORE );";
            db.execSQL(typelessons);

            String schedule = "CREATE TABLE " + ScheduleClass.schedule.TABLE_NAME + " ("
                    + ScheduleClass.schedule.id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleClass.schedule.id_week + " INTEGER ,"
                    + ScheduleClass.schedule.id_day + " INTEGER, "
                    + ScheduleClass.schedule.id_subject + " INTEGER, "
                    + ScheduleClass.schedule.id_audience + " INTEGER, "
                    + ScheduleClass.schedule.id_educator + " INTEGER, "
                    + ScheduleClass.schedule.id_typelesson + " INTEGER, "
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_week + ") REFERENCES " + ScheduleClass.weeks.TABLE_NAME + "(" + ScheduleClass.weeks.idd_week + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_day + ") REFERENCES " + ScheduleClass.days.TABLE_NAME + "(" + ScheduleClass.days.idd_day + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_subject + ") REFERENCES " + ScheduleClass.subjects.TABLE_NAME + "(" + ScheduleClass.subjects.idd_subject + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_audience + ") REFERENCES " + ScheduleClass.audiences.TABLE_NAME + "(" + ScheduleClass.audiences.idd_audience + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_educator + ") REFERENCES " + ScheduleClass.educators.TABLE_NAME + "(" + ScheduleClass.educators.idd_educator + "),"
                    + " FOREIGN KEY (" + ScheduleClass.schedule.id_typelesson + ") REFERENCES " + ScheduleClass.typelessons.TABLE_NAME + "(" + ScheduleClass.typelessons.idd_typelesson + "));";
            db.execSQL(schedule);
*/

           /* for (int j = 1; j < 19; j++) {
              for (int i = 1; i < 7; i++) {
                String sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                    + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + ","
                    + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience
                    + ","
                    + ScheduleClass.schedule.id_educator + ","
                    + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i
                    + ",'','','','');";
                db.execSQL(sql);
                sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                    + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + ","
                    + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience
                    + ","
                    + ScheduleClass.schedule.id_educator + ","
                    + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i
                    + ",'','','','');";
                db.execSQL(sql);
                sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                    + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + ","
                    + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience
                    + ","
                    + ScheduleClass.schedule.id_educator + ","
                    + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i
                    + ",'','','','');";
                db.execSQL(sql);
                sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                    + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + ","
                    + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience
                    + ","
                    + ScheduleClass.schedule.id_educator + ","
                    + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i
                    + ",'','','','');";
                db.execSQL(sql);
                sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                    + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + ","
                    + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience
                    + ","
                    + ScheduleClass.schedule.id_educator + ","
                    + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i
                    + ",'','','','');";
                db.execSQL(sql);
                sql = "INSERT INTO " + ScheduleClass.schedule.TABLE_NAME + "("
                    + ScheduleClass.schedule.id_week + "," + ScheduleClass.schedule.id_day + ","
                    + ScheduleClass.schedule.id_subject + "," + ScheduleClass.schedule.id_audience
                    + ","
                    + ScheduleClass.schedule.id_educator + ","
                    + ScheduleClass.schedule.id_typelesson + ") VALUES(" + j + "," + i
                    + ",'','','','');";
                db.execSQL(sql);}}*/


            }}
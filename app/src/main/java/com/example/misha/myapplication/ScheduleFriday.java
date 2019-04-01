package com.example.misha.myapplication;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.calls;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFriday extends android.support.v4.app.Fragment {
    private ScheduleDB ScheduleDB;
    private List<Lesson> schedule;
    String SubjectOne="";
    String SubjectTwo="";
    String SubjectThree="";
    String SubjectFour="";
    String SubjectFive="";
    String SubjectSix="";
    String AudienceOne="";
    String AudienceTwo="";
    String AudienceThree="";
    String AudienceFour="";
    String AudienceFive="";
    String AudienceSix="";
    String EducatorOne="";
    String EducatorTwo="";
    String EducatorThree="";
    String EducatorFour="";
    String EducatorFive="";
    String EducatorSix="";
    String TypeLessonOne="";
    String TypeLessonTwo="";
    String TypeLessonThree="";
    String TypeLessonFour="";
    String TypeLessonFive="";
    String TypeLessonSix="";
    Integer position_pager=0;

    ArrayList<String> calls_schedule = new ArrayList<>();

    public ScheduleFriday() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    void load_calls_schedule(){

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT "+ calls.time +" FROM " + calls.TABLE_NAME;
        Cursor cursor = db.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            calls_schedule.add(cursor.getString(0));
        }
        cursor.close();
    }
    private void data_schedule(){

        schedule = new ArrayList<>();
        schedule.add(new Lesson("1",calls_schedule.get(0),SubjectOne, AudienceOne, EducatorOne, TypeLessonOne));
        schedule.add(new Lesson("2",calls_schedule.get(1),SubjectTwo, AudienceTwo, EducatorTwo, TypeLessonTwo));
        schedule.add(new Lesson("3",calls_schedule.get(2),SubjectThree, AudienceThree, EducatorThree, TypeLessonThree));
        schedule.add(new Lesson("4",calls_schedule.get(3),SubjectFour, AudienceFour, EducatorFour, TypeLessonFour));
        schedule.add(new Lesson("5",calls_schedule.get(4),SubjectFive, AudienceFive, EducatorFive, TypeLessonFive));
        schedule.add(new Lesson("6",calls_schedule.get(5),SubjectSix, AudienceSix, EducatorSix, TypeLessonSix));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        ScheduleDB = new ScheduleDB(getContext());
        load_calls_schedule();
        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("choice_week",0);
        position_pager = Integer.valueOf(preferences.getString("position", "0"));

        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        RecyclerView rv =  rootView.findViewById(R.id.rv_recycler_view);


        Cursor cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 25) + "));", null);
        try {
            int idSubjectOne = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SubjectOne = cursor.getString(idSubjectOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 25) + "));", null);
        try {
            int idAudienceOne = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                AudienceOne = cursor.getString(idAudienceOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 25) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                EducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 25) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 26) + "));", null);
        try {
            int idSubjectTwo = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SubjectTwo = cursor.getString(idSubjectTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 26) + "));", null);
        try {
            int idAudienceTwo = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                AudienceTwo = cursor.getString(idAudienceTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 26) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                EducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 26) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 27) + "));", null);
        try {
            int idSubjectThree = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SubjectThree = cursor.getString(idSubjectThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 27) + "));", null);
        try {
            int idAudienceThree = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                AudienceThree = cursor.getString(idAudienceThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 27) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                EducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 27) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 28) + "));", null);
        try {
            int idSubjectFour = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SubjectFour = cursor.getString(idSubjectFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 28) + "));", null);
        try {
            int idAudienceFour = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                AudienceFour = cursor.getString(idAudienceFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 28) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                EducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 28) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 29) + "));", null);
        try {
            int idSubjectFive = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SubjectFive = cursor.getString(idSubjectFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 29) + "));", null);
        try {
            int idAudienceFive = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                AudienceFive = cursor.getString(idAudienceFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 29) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                EducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 29) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.subjects.idd_subject + "= (SELECT " + ScheduleClass.schedule.id_subject + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 30) + "));", null);
        try {
            int idSubjectSix = cursor.getColumnIndex(ScheduleClass.subjects.subject);
            while (cursor.moveToNext()) {
                SubjectSix = cursor.getString(idSubjectSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.audiences.idd_audience + "= (SELECT " + ScheduleClass.schedule.id_audience + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 30) + "));", null);
        try {
            int idAudienceSix = cursor.getColumnIndex(ScheduleClass.audiences.audience);
            while (cursor.moveToNext()) {
                AudienceSix = cursor.getString(idAudienceSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.educators.idd_educator + "= (SELECT " + ScheduleClass.schedule.id_educator + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 30) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(ScheduleClass.educators.educator);
            while (cursor.moveToNext()) {
                EducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + ScheduleClass.typelessons.typelesson + " FROM " + ScheduleClass.typelessons.TABLE_NAME + ", " + ScheduleClass.schedule.TABLE_NAME + " WHERE (" + ScheduleClass.typelessons.idd_typelesson + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + ScheduleClass.schedule.TABLE_NAME + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 30) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(ScheduleClass.typelessons.typelesson);
            while (cursor.moveToNext()) {
                TypeLessonSix = cursor.getString(idTypeLessonSix);
            }
        } finally {
            cursor.close();
        }
        data_schedule();


        MyAdapter adapter = new MyAdapter(schedule);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
        return rootView;
    }

}
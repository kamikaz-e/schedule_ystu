package com.example.misha.myapplication.fragments;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.Lesson;
import com.example.misha.myapplication.MyAdapter;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.calls;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;
import java.util.List;

import static com.example.misha.myapplication.data.ScheduleClass.audiences.AUDIENCE;
import static com.example.misha.myapplication.data.ScheduleClass.audiences.audience;
import static com.example.misha.myapplication.data.ScheduleClass.audiences.audience_id;
import static com.example.misha.myapplication.data.ScheduleClass.calls.CALLS;
import static com.example.misha.myapplication.data.ScheduleClass.educators.EDUCATOR;
import static com.example.misha.myapplication.data.ScheduleClass.educators.educator;
import static com.example.misha.myapplication.data.ScheduleClass.educators.educator_id;
import static com.example.misha.myapplication.data.ScheduleClass.schedule.SCHEDULE;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.SUBJECT;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.subject;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.subject_id;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.TYPELESSON;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.typelesson;
import static com.example.misha.myapplication.data.ScheduleClass.typelessons.typelesson_id;

public class ScheduleMonday extends android.support.v4.app.Fragment {
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

    public ScheduleMonday() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

  void load_calls_schedule(){
    SQLiteDatabase db = ScheduleDB.getReadableDatabase();
    String searchQuery = "SELECT "+ calls.time +" FROM " + CALLS;
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
        ScheduleDB = new ScheduleDB();

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        load_calls_schedule();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("choice_week", 0);
        position_pager = Integer.valueOf(preferences.getString("position", "0"));

        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        RecyclerView rv = rootView.findViewById(R.id.rv_recycler_view);

/*

      Cursor cursor = db.rawQuery("SELECT " + subject + " FROM " + SUBJECT + ", " + SCHEDULE + " WHERE (" + subject_id + "= (SELECT " + subject_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 1) + "));", null);
        try {
            int idSubjectOne = cursor.getColumnIndex(subject);
            while (cursor.moveToNext()) {
                 SubjectOne = cursor.getString(idSubjectOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + audience + " FROM " + AUDIENCE + ", " + SCHEDULE + " WHERE (" + audience_id + "= (SELECT " + audience_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 1) + "));", null);
        try {
            int idAudienceOne = cursor.getColumnIndex(audience);
            while (cursor.moveToNext()) {
                 AudienceOne = cursor.getString(idAudienceOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + educator + " FROM " + EDUCATOR + ", " + SCHEDULE + " WHERE (" + educator_id + "= (SELECT " + educator_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 1) + "));", null);
        try {
            int idEducatorOne = cursor.getColumnIndex(educator);
            while (cursor.moveToNext()) {
                 EducatorOne = cursor.getString(idEducatorOne);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + typelesson + " FROM " + TYPELESSON + ", " + SCHEDULE + " WHERE (" + typelesson_id + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 1) + "));", null);
        try {
            int idTypeLessonOne = cursor.getColumnIndex(typelesson);
            while (cursor.moveToNext()) {
                 TypeLessonOne = cursor.getString(idTypeLessonOne);
            }
        } finally {
            cursor.close();
        }

        //2 занятие
        cursor = db.rawQuery("SELECT " + subject + " FROM " + SUBJECT + ", " + SCHEDULE + " WHERE (" + subject_id + "= (SELECT " + subject_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 2) + "));", null);
        try {
            int idSubjectTwo = cursor.getColumnIndex(SUBJECT);
            while (cursor.moveToNext()) {
                 SubjectTwo = cursor.getString(idSubjectTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + audience + " FROM " + AUDIENCE + ", " + SCHEDULE + " WHERE (" + audience_id + "= (SELECT " + audience_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 2) + "));", null);
        try {
            int idAudienceTwo = cursor.getColumnIndex(audience);
            while (cursor.moveToNext()) {
                 AudienceTwo = cursor.getString(idAudienceTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + educator + " FROM " + EDUCATOR + ", " + SCHEDULE + " WHERE (" + educator_id + "= (SELECT " + educator_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 2) + "));", null);
        try {
            int idEducatorTwo = cursor.getColumnIndex(educator);
            while (cursor.moveToNext()) {
                 EducatorTwo = cursor.getString(idEducatorTwo);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + typelesson + " FROM " + TYPELESSON + ", " + SCHEDULE + " WHERE (" + typelesson_id + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 2) + "));", null);
        try {
            int idTypeLessonTwo = cursor.getColumnIndex(typelesson);
            while (cursor.moveToNext()) {
                 TypeLessonTwo = cursor.getString(idTypeLessonTwo);
            }
        } finally {
            cursor.close();
        }

        //3 занятие
        cursor = db.rawQuery("SELECT " + subject + " FROM " + SUBJECT + ", " + SCHEDULE + " WHERE (" + subject_id + "= (SELECT " + subject_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 3) + "));", null);
        try {
            int idSubjectThree = cursor.getColumnIndex(subject);
            while (cursor.moveToNext()) {
                 SubjectThree = cursor.getString(idSubjectThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + audience + " FROM " + AUDIENCE + ", " + SCHEDULE + " WHERE (" + audience_id + "= (SELECT " + audience_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 3) + "));", null);
        try {
            int idAudienceThree = cursor.getColumnIndex(audience);
            while (cursor.moveToNext()) {
                 AudienceThree = cursor.getString(idAudienceThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + educator + " FROM " + EDUCATOR + ", " + SCHEDULE + " WHERE (" + educator_id + "= (SELECT " + educator_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 3) + "));", null);
        try {
            int idEducatorThree = cursor.getColumnIndex(educator);
            while (cursor.moveToNext()) {
                 EducatorThree = cursor.getString(idEducatorThree);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + typelesson + " FROM " + TYPELESSON + ", " + SCHEDULE + " WHERE (" + typelesson_id + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 3) + "));", null);
        try {
            int idTypeLessonThree = cursor.getColumnIndex(typelesson);
            while (cursor.moveToNext()) {
                 TypeLessonThree = cursor.getString(idTypeLessonThree);
            }
        } finally {
            cursor.close();
        }

        //4 занятие
        cursor = db.rawQuery("SELECT " + subject + " FROM " + SUBJECT + ", " + SCHEDULE + " WHERE (" + subject_id + "= (SELECT " + subject_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 4) + "));", null);
        try {
            int idSubjectFour = cursor.getColumnIndex(subject);
            while (cursor.moveToNext()) {
                 SubjectFour = cursor.getString(idSubjectFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + audience + " FROM " + AUDIENCE + ", " + SCHEDULE + " WHERE (" + audience_id + "= (SELECT " + audience_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 4) + "));", null);
        try {
            int idAudienceFour = cursor.getColumnIndex(audience);
            while (cursor.moveToNext()) {
                 AudienceFour = cursor.getString(idAudienceFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + educator + " FROM " + EDUCATOR + ", " + SCHEDULE + " WHERE (" + educator_id + "= (SELECT " + educator_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 4) + "));", null);
        try {
            int idEducatorFour = cursor.getColumnIndex(educator);
            while (cursor.moveToNext()) {
                 EducatorFour = cursor.getString(idEducatorFour);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + typelesson + " FROM " + TYPELESSON + ", " + SCHEDULE + " WHERE (" + typelesson_id + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 4) + "));", null);
        try {
            int idTypeLessonFour = cursor.getColumnIndex(typelesson);
            while (cursor.moveToNext()) {
                 TypeLessonFour = cursor.getString(idTypeLessonFour);
            }
        } finally {
            cursor.close();
        }

        //5 занятие
        cursor = db.rawQuery("SELECT " + subject + " FROM " + SUBJECT + ", " + SCHEDULE + " WHERE (" + subject_id + "= (SELECT " + subject_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 5) + "));", null);
        try {
            int idSubjectFive = cursor.getColumnIndex(subject);
            while (cursor.moveToNext()) {
                 SubjectFive = cursor.getString(idSubjectFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + audience + " FROM " + AUDIENCE + ", " + SCHEDULE + " WHERE (" + audience_id + "= (SELECT " + audience_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 5) + "));", null);
        try {
            int idAudienceFive = cursor.getColumnIndex(audience);
            while (cursor.moveToNext()) {
                 AudienceFive = cursor.getString(idAudienceFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + educator + " FROM " + EDUCATOR + ", " + SCHEDULE + " WHERE (" + educator_id + "= (SELECT " + educator_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 5) + "));", null);
        try {
            int idEducatorFive = cursor.getColumnIndex(educator);
            while (cursor.moveToNext()) {
                 EducatorFive = cursor.getString(idEducatorFive);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + typelesson + " FROM " + TYPELESSON + ", " + SCHEDULE + " WHERE (" + typelesson_id + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 5) + "));", null);
        try {
            int idTypeLessonFive = cursor.getColumnIndex(typelesson);
            while (cursor.moveToNext()) {
                 TypeLessonFive = cursor.getString(idTypeLessonFive);
            }
        } finally {
            cursor.close();
        }

        //6 занятие
        cursor = db.rawQuery("SELECT " + subject + " FROM " + SUBJECT + ", " + SCHEDULE + " WHERE (" + subject_id + "= (SELECT " + subject_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 6) + "));", null);
        try {
            int idSubjectSix = cursor.getColumnIndex(subject);
            while (cursor.moveToNext()) {
                 SubjectSix = cursor.getString(idSubjectSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + audience + " FROM " + AUDIENCE + ", " + SCHEDULE + " WHERE (" + audience_id + "= (SELECT " + audience_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 6) + "));", null);
        try {
            int idAudienceSix = cursor.getColumnIndex(audience);
            while (cursor.moveToNext()) {
                 AudienceSix = cursor.getString(idAudienceSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + educator + " FROM " + EDUCATOR + ", " + SCHEDULE + " WHERE (" + educator_id + "= (SELECT " + educator_id + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 6) + "));", null);
        try {
            int idEducatorSix = cursor.getColumnIndex(educator);
            while (cursor.moveToNext()) {
                 EducatorSix = cursor.getString(idEducatorSix);
            }
        } finally {
            cursor.close();
        }

        cursor = db.rawQuery("SELECT " + typelesson + " FROM " + TYPELESSON + ", " + SCHEDULE + " WHERE (" + typelesson_id + "= (SELECT " + ScheduleClass.schedule.id_typelesson + " FROM " + SCHEDULE + " WHERE " + ScheduleClass.schedule.id + "=" + ((position_pager * 36) + 6) + "));", null);
        try {
            int idTypeLessonSix = cursor.getColumnIndex(typelesson);
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

            }
*/
        return rootView;  }
        }
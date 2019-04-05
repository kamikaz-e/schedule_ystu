package com.example.misha.myapplication.fragments;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.ScheduleClass.calls;

import java.util.ArrayList;
import java.util.List;

import static com.example.misha.myapplication.data.ScheduleClass.calls.CALLS;

public class ScheduleMonday extends android.support.v4.app.Fragment {
    private List<Lesson> schedule;

    Integer position_pager = 0;

    ArrayList<String> calls_schedule = new ArrayList<>();

    public ScheduleMonday() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void load_calls_schedule() {
        ;
        String searchQuery = "SELECT " + calls.time + " FROM " + CALLS;
        Cursor cursor = db.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            calls_schedule.add(cursor.getString(0));
        }
        cursor.close();
    }

    /*private void data_schedule(){

        schedule = new ArrayList<>();
        schedule.add(new Lesson("1",calls_schedule.get(0),SubjectOne, AudienceOne, EducatorOne, TypeLessonOne));
        schedule.add(new Lesson("2",calls_schedule.get(1),SubjectTwo, AudienceTwo, EducatorTwo, TypeLessonTwo));
        schedule.add(new Lesson("3",calls_schedule.get(2),SubjectThree, AudienceThree, EducatorThree, TypeLessonThree));
        schedule.add(new Lesson("4",calls_schedule.get(3),SubjectFour, AudienceFour, EducatorFour, TypeLessonFour));
        schedule.add(new Lesson("5",calls_schedule.get(4),SubjectFive, AudienceFive, EducatorFive, TypeLessonFive));
        schedule.add(new Lesson("6",calls_schedule.get(5),SubjectSix, AudienceSix, EducatorSix, TypeLessonSix));
    }*/
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


        return rootView;
    }
}
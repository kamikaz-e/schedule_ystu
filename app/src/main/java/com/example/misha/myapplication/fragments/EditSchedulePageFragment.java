package com.example.misha.myapplication.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.dialog.AudienceList;
import com.example.misha.myapplication.dialog.EducatorList;
import com.example.misha.myapplication.model.Audience;
import com.example.misha.myapplication.model.Educator;
import com.example.misha.myapplication.dialog.SubjectList;
import com.example.misha.myapplication.Lesson;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.EditScheduleAdapter;
import com.example.misha.myapplication.adapter.EditScheduleCallback;
import com.example.misha.myapplication.data.ScheduleClass.audiences;
import com.example.misha.myapplication.data.ScheduleClass.educators;
import com.example.misha.myapplication.data.ScheduleDB;
import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;
import java.util.List;

import static com.example.misha.myapplication.data.ScheduleClass.audiences.AUDIENCE;
import static com.example.misha.myapplication.data.ScheduleClass.educators.EDUCATOR;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.SUBJECT;
import static com.example.misha.myapplication.data.ScheduleClass.subjects.subject;

public class EditSchedulePageFragment extends Fragment implements EditScheduleCallback {

    private View fragmentView;

    private RecyclerView rvLessons;
    private EditScheduleAdapter rvadapter;
    private ScheduleDB ScheduleDB;
    private List<Lesson> lessonList = new ArrayList<>();

    final ArrayList<Subject> subjectList = new ArrayList<>();
    final ArrayList<Audience> audienceList = new ArrayList<>();
    final ArrayList<Educator> educatorList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rvadapter = new EditScheduleAdapter(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLessonList();
        initResources();
        rvadapter.setLessonList(lessonList);
        rvadapter.setAudiences(audienceList);
        rvadapter.setEducators(educatorList);
        rvadapter.setSubjects(subjectList);
        rvadapter.notifyDataSetChanged();
    }

    private void initLessonList(){
        lessonList = new ArrayList<>();
        lessonList.add(new Lesson("1","a","aaa", "ewq", "rfv", "zcv"));
        lessonList.add(new Lesson("2","b","bbb", "qwe", "vfr", "bfd"));
        lessonList.add(new Lesson("3","c","ccc", "dsa", "edc", "fds"));
        lessonList.add(new Lesson("4","d","ddd", "asd", "cde", "qwe"));
        lessonList.add(new Lesson("5","e","eee", "cxz", "xsw", "xaw"));
        lessonList.add(new Lesson("6","f","fff", "zxc", "qaz", "zxa"));
    }

    void initResources() {
        ScheduleDB = new ScheduleDB();
        SQLiteDatabase myDataBase = ScheduleDB.getReadableDatabase();


        String searchQuery = "SELECT * FROM " + SUBJECT  ;
        Cursor cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            subjectList.add(new Subject(cursor));
        }
        cursor.close();

        searchQuery = "SELECT * FROM " + AUDIENCE;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            audienceList.add(new Audience(cursor));
        }
        cursor.close();

        searchQuery = "SELECT * FROM " + EDUCATOR;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            educatorList.add(new Educator(cursor));
        }
        cursor.close();


    }

    @Override
    public void onAudienceClick(int position, ArrayList<Audience> audience) {
        AudienceList dialogFragment = AudienceList.newInstance(position, audience);
        dialogFragment.show(getChildFragmentManager(), Audience.class.getSimpleName());
    }

    @Override
    public void onEducatorClick(int position, ArrayList<Educator> educator) {
        EducatorList dialogFragment = EducatorList.newInstance(position, educator);
        dialogFragment.show(getChildFragmentManager(), EducatorList.class.getSimpleName());
    }

    @Override
    public void onSubjectClick(int position, ArrayList<Subject> subject) {
        SubjectList dialogFragment = SubjectList.newInstance(position, subject);
        dialogFragment.show(getChildFragmentManager(), SubjectList.class.getSimpleName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultOk, Intent data) {
        if (requestCode == SubjectList.SUBJECT_CODE) {
            int lessonPosition = data.getIntExtra(SubjectList.POSITION, 0);
            Subject subject = data.getParcelableExtra(SubjectList.SUBJECT_LIST);
            lessonList.get(lessonPosition).setSubjectEdit(subject.getName());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
        }
        if (requestCode == AudienceList.AUDIENCE_CODE) {
            int lessonPosition = data.getIntExtra(AudienceList.POSITION, 0);
            Audience audience = data.getParcelableExtra(AudienceList.AUDIENCE_LIST);
            lessonList.get(lessonPosition).setAudienceEdit(audience.getName());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
        }
        if (requestCode == EducatorList.EDUCATOR_CODE) {
            int lessonPosition = data.getIntExtra(EducatorList.POSITION, 0);
            Educator educator = data.getParcelableExtra(EducatorList.EDUCATOR_LIST);
            lessonList.get(lessonPosition).setEducatorEdit(educator.getName());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSubjectSetText(int position, ArrayList<Subject> subject) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.card_item_edit_monday_for_recycler, container, false);
        ScheduleDB = new ScheduleDB();
        rvLessons = fragmentView.findViewById(R.id.rv_lessons_edit);
        rvLessons.setAdapter(rvadapter);
        return fragmentView;
    }

}
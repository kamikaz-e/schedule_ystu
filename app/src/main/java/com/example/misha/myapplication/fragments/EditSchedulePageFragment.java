package com.example.misha.myapplication.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.subjectDialogFragment;
import com.example.misha.myapplication.Lesson;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.EditScheduleAdapter;
import com.example.misha.myapplication.adapter.EditScheduleCallback;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;
import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class EditSchedulePageFragment extends Fragment implements EditScheduleCallback {

    private View fragmentView;

    private RecyclerView rvLessons;
    private EditScheduleAdapter rvadapter;
    private ScheduleDB ScheduleDB;
    private List<Lesson> lessonList = new ArrayList<>();

    final ArrayList<Subject> subjectList = new ArrayList<>();
    final ArrayList<String> audienceList = new ArrayList<>();
    final ArrayList<String> educatorList = new ArrayList<>();


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
        lessonList.add(new Lesson("1","adas","asdasd", "asdasdasd", "zxczxc", "vcxvxc"));
        lessonList.add(new Lesson("2","adas","asdasd", "asdasdasd", "zxczxc", "vcxvxc"));
        lessonList.add(new Lesson("3","adas","asdasd", "asdasdasd", "zxczxc", "vcxvxc"));
        lessonList.add(new Lesson("4","adas","asdasd", "asdasdasd", "zxczxc", "vcxvxc"));
        lessonList.add(new Lesson("5","adas","asdasd", "asdasdasd", "zxczxc", "vcxvxc"));
        lessonList.add(new Lesson("6","adas","asdasd", "asdasdasd", "zxczxc", "vcxvxc"));
    }

    void initResources() {
        SQLiteDatabase myDataBase = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT * FROM " + ScheduleClass.subjects.TABLE_NAME;
        Cursor cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            subjectList.add(new Subject(cursor));
        }
        cursor.close();

        myDataBase = ScheduleDB.getReadableDatabase();
        searchQuery = "SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            audienceList.add(cursor.getString(0));
        }
        cursor.close();

        myDataBase = ScheduleDB.getReadableDatabase();
        searchQuery = "SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            educatorList.add(cursor.getString(0));
        }
        cursor.close();


    }

    @Override
    public void onAudienceSelected(int position, String audience) {
        lessonList.get(position).setAudienceEdit(audience);
    }

    @Override
    public void onEducatorSelected(int position, String editor) {
        lessonList.get(position).setEducator(editor);

    }

    @Override
    public void onSubjectClick(int position, ArrayList<Subject> subject) {
        DialogFragment dialogFragment = subjectDialogFragment.newInstance(position, subject);
        dialogFragment.show(getChildFragmentManager(), subjectDialogFragment.class.getSimpleName());
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
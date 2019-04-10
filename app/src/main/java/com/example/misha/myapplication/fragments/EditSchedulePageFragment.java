package com.example.misha.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.EditSchedule.EditScheduleAdapter;
import com.example.misha.myapplication.adapter.EditSchedule.EditScheduleCallback;
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Educator;
import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.database.entity.Subject;
import com.example.misha.myapplication.database.entity.Typelesson;
import com.example.misha.myapplication.dialog.AudienceList;
import com.example.misha.myapplication.dialog.EducatorList;
import com.example.misha.myapplication.dialog.SubjectList;
import com.example.misha.myapplication.dialog.TypelessonList;

import java.util.ArrayList;
import java.util.List;

public class EditSchedulePageFragment extends Fragment implements EditScheduleCallback {

    private View fragmentView;
    private RecyclerView rvLessons;
    private EditScheduleAdapter rvadapter;
    private List<Lesson> lessonList = new ArrayList<>();
    ArrayList<Subject> subjectList = new ArrayList<>();
    ArrayList<Audience> audienceList = new ArrayList<>();
    ArrayList<Educator> educatorList = new ArrayList<>();
    ArrayList<Typelesson> typelessonList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvadapter = new EditScheduleAdapter(this);
    }

    public static EditSchedulePageFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("pos", position);
        EditSchedulePageFragment fragment = new EditSchedulePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initResources();
        rvadapter.setLessonList(lessonList);
        rvadapter.setAudiences(audienceList);
        rvadapter.setEducators(educatorList);
        rvadapter.setSubjects(subjectList);
        rvadapter.setTypelesson(typelessonList);
        rvadapter.notifyDataSetChanged();
    }


    void initResources() {
        Bundle bundle = getArguments();
        int position_day = bundle.getInt("pos");
        subjectList = SubjectDao.getInstance().getAllData();
        audienceList = AudienceDao.getInstance().getAllData();
        educatorList = EducatorDao.getInstance().getAllData();
        typelessonList = TypelessonDao.getInstance().getAllData();
        lessonList = LessonDao.getInstance().getLessonByWeekAndDay(0, position_day);
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
    public void onTypelessonClick(int position, ArrayList<Typelesson> typelesson) {
        TypelessonList dialogFragment = TypelessonList.newInstance(position, typelesson);
        dialogFragment.show(getChildFragmentManager(), TypelessonList.class.getSimpleName());
    }


    @Override
    public void onActivityResult(int requestCode, int resultOk, Intent data) {
        if (requestCode == SubjectList.SUBJECT_CODE) {
            int lessonPosition = data.getIntExtra(SubjectList.POSITION, 0);
            Subject subject = data.getParcelableExtra(SubjectList.SUBJECT_LIST);
            lessonList.get(lessonPosition).setSubject(subject.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));
        }
        if (requestCode == TypelessonList.TYPELESSON_CODE) {
            int lessonPosition = data.getIntExtra(TypelessonList.POSITION, 0);
            Typelesson typelesson = data.getParcelableExtra(TypelessonList.TYPELESSON_LIST);
            lessonList.get(lessonPosition).setTypeLesson(typelesson.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));
        }
        if (requestCode == AudienceList.AUDIENCE_CODE) {
            int lessonPosition = data.getIntExtra(AudienceList.POSITION, 0);
            Audience audience = data.getParcelableExtra(AudienceList.AUDIENCE_LIST);
            lessonList.get(lessonPosition).setAudience(audience.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));
        }
        if (requestCode == EducatorList.EDUCATOR_CODE) {
            int lessonPosition = data.getIntExtra(EducatorList.POSITION, 0);
            Educator educator = data.getParcelableExtra(EducatorList.EDUCATOR_LIST);
            lessonList.get(lessonPosition).setEducatorEdit(educator.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.item_edit_schedule_recycler, container, false);
        rvLessons = fragmentView.findViewById(R.id.rv_lessons_edit);
        rvLessons.setAdapter(rvadapter);
        return fragmentView;
    }
}
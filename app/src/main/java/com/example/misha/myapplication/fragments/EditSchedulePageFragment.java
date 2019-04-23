package com.example.misha.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.editSchedule.EditScheduleAdapter;
import com.example.misha.myapplication.adapter.editSchedule.EditScheduleCallback;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class EditSchedulePageFragment extends Fragment implements EditScheduleCallback {

    private View fragmentView;
    private RecyclerView rvLessons;
    private EditScheduleAdapter rvadapter;
    private List<Lesson> lessonList = new ArrayList<>();
    ArrayList<Subject> subjectList = new ArrayList<>();
    ArrayList<Audience> audienceList = new ArrayList<>();
    ArrayList<Educator> educatorList = new ArrayList<>();
    ArrayList<Typelesson> typelessonList = new ArrayList<>();
    FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private int positionWeek;
    private int day;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvadapter = new EditScheduleAdapter(this);
        day = getArguments().getInt(Constants.DAY);
        positionWeek = getArguments().getInt(Constants.SELECTED_WEEK);
    }

    public static EditSchedulePageFragment newInstance(int selectedWeek, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.SELECTED_WEEK, selectedWeek);
        args.putInt(Constants.DAY, position);
        EditSchedulePageFragment fragment = new EditSchedulePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.item_edit_schedule_recycler, container, false);
        rvLessons = fragmentView.findViewById(R.id.rv_lessons_edit);
        fab = getActivity().findViewById(R.id.mainFab);
        fab1 = getActivity().findViewById(R.id.evenWeekFab);
        fab2 = getActivity().findViewById(R.id.unevenWeekFab);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);
        rvLessons.setAdapter(rvadapter);

        rvLessons.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    if (Preferences.getInstance().getFabOpen()){
                    fab.hide();
                        fab.startAnimation(rotate_backward);
                    fab.setClickable(false);
                    fab1.startAnimation(fab_close);
                    fab2.startAnimation(fab_close);
                    fab1.setClickable(false);
                    fab2.setClickable(false);
                    Preferences.getInstance().setFabOpen(false);}
                    else {
                        fab.hide();
                        fab.setClickable(false);
                    }
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                        fab.show();
                        fab.setClickable(true);
                }
            }
        });

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateList();
    }

    private void updateList() {
        lessonList = LessonDao.getInstance().getLessonByWeekAndDay(positionWeek, day);
        subjectList = SubjectDao.getInstance().getAllData();
        audienceList = AudienceDao.getInstance().getAllData();
        educatorList = EducatorDao.getInstance().getAllData();
        typelessonList = TypelessonDao.getInstance().getAllData();
        rvadapter.setLessonList(lessonList);
        rvadapter.setAudiences(audienceList);
        rvadapter.setEducators(educatorList);
        rvadapter.setSubjects(subjectList);
        rvadapter.setTypelesson(typelessonList);
        rvadapter.notifyDataSetChanged();
    }

    public void setWeek(int selectedWeek) {
        this.positionWeek = selectedWeek;
        updateList();
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
    public void onCopyUpClick(int position) {
        lessonList.get(position - 1).setSubject(lessonList.get(position).getSubject());
        lessonList.get(position - 1).setAudience(lessonList.get(position).getAudience());
        lessonList.get(position - 1).setTypeLesson(lessonList.get(position).getTypeLesson());
        lessonList.get(position - 1).setEducator(lessonList.get(position).getEducator());
        rvadapter.setLessonList(lessonList);
        rvadapter.notifyDataSetChanged();
        LessonDao.getInstance().updateItemByID(lessonList.get(position - 1));
    }

    @Override
    public void onCopyDownClick(int position) {
        lessonList.get(position + 1).setSubject(lessonList.get(position).getSubject());
        lessonList.get(position + 1).setAudience(lessonList.get(position).getAudience());
        lessonList.get(position + 1).setTypeLesson(lessonList.get(position).getTypeLesson());
        lessonList.get(position + 1).setEducator(lessonList.get(position).getEducator());
        rvadapter.setLessonList(lessonList);
        rvadapter.notifyDataSetChanged();
        LessonDao.getInstance().updateItemByID(lessonList.get(position + 1));
    }

    @Override
    public void onClearLessonClick(int position) {
        lessonList.get(position).setSubject("0");
        lessonList.get(position).setAudience("0");
        lessonList.get(position).setTypeLesson("0");
        lessonList.get(position).setEducator("0");
        rvadapter.setLessonList(lessonList);
        rvadapter.notifyDataSetChanged();
        LessonDao.getInstance().updateItemByID(lessonList.get(position));
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


}
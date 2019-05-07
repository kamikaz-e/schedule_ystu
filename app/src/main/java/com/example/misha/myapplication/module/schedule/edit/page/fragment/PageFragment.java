package com.example.misha.myapplication.module.schedule.edit.page.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.database.entity.SimpleItem;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.Lesson;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;
import com.example.misha.myapplication.module.schedule.edit.page.EditScheduleAdapter;
import com.example.misha.myapplication.module.schedule.edit.page.dialog.FragmentList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends BaseMainFragment implements PageFragmentView {

    private EditScheduleAdapter rvadapter;
    private List<Lesson> lessonList = new ArrayList<>();
    private FloatingActionButton mainFab, evenWeekFab, unevenWeekFab;
    private Animation fabClose;
    private Animation rotateBackward;
    private PageFragmentPageFragmentPresenter presenter;

    //BottomNavigationView

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int day = getArguments().getInt(Constants.DAY);
        int positionWeek = getArguments().getInt(Constants.SELECTED_WEEK);
        presenter = new PageFragmentPageFragmentPresenter(day, positionWeek);
    }

    public static PageFragment newInstance(int selectedWeek, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.SELECTED_WEEK, selectedWeek);
        args.putInt(Constants.DAY, position);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public android.view.View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View fragmentView = inflater.inflate(R.layout.item_edit_schedule_recycler, container, false);
        mainFab = getActivity().findViewById(R.id.main_fab);
        evenWeekFab = getActivity().findViewById(R.id.even_weekFab);
        unevenWeekFab = getActivity().findViewById(R.id.uneven_weekFab);

        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        rotateBackward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);

        RecyclerView rvLessons = fragmentView.findViewById(R.id.rv_lessons_edit);
        rvLessons.setAdapter(rvadapter);
        rvLessons.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mainFab.getVisibility() == android.view.View.VISIBLE) {
                    if (Preferences.getInstance().getFabOpen()) {
                        mainFab.hide();
                        mainFab.startAnimation(rotateBackward);
                        mainFab.setClickable(false);
                        evenWeekFab.startAnimation(fabClose);
                        unevenWeekFab.startAnimation(fabClose);
                        evenWeekFab.setClickable(false);
                        unevenWeekFab.setClickable(false);
                        Preferences.getInstance().setFabOpen(false);
                    } else {
                        mainFab.hide();
                        mainFab.setClickable(false);
                    }
                } else if (dy < 0 && mainFab.getVisibility() != android.view.View.VISIBLE) {
                    mainFab.show();
                    mainFab.setClickable(true);
                }
            }
        });
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    public void updateList(int day, int positionWeek) {
        lessonList = LessonDao.getInstance().getLessonByWeekAndDay(positionWeek, day);
        ArrayList<Subject> subjectList = SubjectDao.getInstance().getAllData();
        ArrayList<Audience> audienceList = AudienceDao.getInstance().getAllData();
        ArrayList<Educator> educatorList = EducatorDao.getInstance().getAllData();
        ArrayList<Typelesson> typelessonList = TypelessonDao.getInstance().getAllData();
        rvadapter.setLessonList(lessonList);
        rvadapter.setAudiences(audienceList);
        rvadapter.setEducators(educatorList);
        rvadapter.setSubjects(subjectList);
        rvadapter.setTypelesson(typelessonList);
        rvadapter.notifyDataSetChanged();
    }

    @Override
    public void onSubjecttClick(int position, ArrayList<Subject> item) {
        FragmentList dialogFragment = FragmentList.newInstance(position, item);
        dialogFragment.show(getChildFragmentManager(), FragmentList.class.getSimpleName());
    }

    @Override
    public void onAudienceClick(int position, ArrayList<Audience> item) {
        FragmentList dialogFragment = FragmentList.newInstance(position, item);
        dialogFragment.show(getChildFragmentManager(), FragmentList.class.getSimpleName());
    }

    @Override
    public void onEducatorClick(int position, ArrayList<Educator> item) {
        FragmentList dialogFragment = FragmentList.newInstance(position, item);
        dialogFragment.show(getChildFragmentManager(), FragmentList.class.getSimpleName());
    }

    @Override
    public void onTypelessonClick(int position, ArrayList<Typelesson> item) {
        FragmentList dialogFragment = FragmentList.newInstance(position, item);
        dialogFragment.show(getChildFragmentManager(), FragmentList.class.getSimpleName());
    }



    public void setWeek(int selectedWeek) {
        presenter.onWeekSelected(selectedWeek);
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
        if (requestCode == FragmentList.SUBJECT_CODE) {
            int lessonPosition = data.getIntExtra(FragmentList.POSITION, 0);
            Subject subject = data.getParcelableExtra(FragmentList.SUBJECT_LIST);
            lessonList.get(lessonPosition).setSubject(subject.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));
        }
        if (requestCode == FragmentList.TYPELESSON_CODE) {
            int lessonPosition = data.getIntExtra(FragmentList.POSITION, 0);
            Typelesson typelesson = data.getParcelableExtra(FragmentList.TYPELESSON_LIST);
            lessonList.get(lessonPosition).setTypeLesson(typelesson.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));
        }
        if (requestCode == FragmentList.AUDIENCE_CODE) {
            int lessonPosition = data.getIntExtra(FragmentList.POSITION, 0);
            Audience audience = data.getParcelableExtra(FragmentList.AUDIENCE_LIST);
            lessonList.get(lessonPosition).setAudience(audience.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));
        }
        if (requestCode == FragmentList.EDUCATOR_CODE) {
            int lessonPosition = data.getIntExtra(FragmentList.POSITION, 0);
            Educator educator = data.getParcelableExtra(FragmentList.EDUCATOR_LIST);
            lessonList.get(lessonPosition).setEducatorEdit(educator.getId());
            rvadapter.setLessonList(lessonList);
            rvadapter.notifyDataSetChanged();
            LessonDao.getInstance().updateItemByID(lessonList.get(lessonPosition));

        }
    }

}
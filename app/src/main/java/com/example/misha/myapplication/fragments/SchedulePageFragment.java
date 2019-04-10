package com.example.misha.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.Schedule.ScheduleAdapter;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class SchedulePageFragment extends Fragment {

    private View fragmentView;
    private RecyclerView rvLessons;
    private ScheduleAdapter rvadapter;
    private List<Lesson> lessonList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvadapter = new ScheduleAdapter();
    }

    public static SchedulePageFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("positionPager", position);
        SchedulePageFragment fragment = new SchedulePageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initResources();
        rvadapter.setLessonList(lessonList);
        rvadapter.notifyDataSetChanged();
    }


    void initResources() {
        Bundle bundle = getArguments();
        int position_day = bundle.getInt("positionPager");
        lessonList = LessonDao.getInstance().getLessonByWeekAndDay(0, position_day);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.item_edit_schedule_recycler, container, false);
        rvLessons = fragmentView.findViewById(R.id.rv_lessons_edit);
        rvLessons.setAdapter(rvadapter);
        return fragmentView;
    }
}
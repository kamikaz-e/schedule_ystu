package com.example.misha.myapplication.module.schedule.explore.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.entity.Lesson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SchedulePageFragment extends BaseMainFragment implements SchedulePageFragmentView {

    private ScheduleFragmentPagerAdapter rvadapter;
    private SchedulePagePresenter presenter;

    public static SchedulePageFragment newInstance(int selectedWeek, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.SELECTED_WEEK, selectedWeek);
        args.putInt(Constants.DAY, position+1);
        SchedulePageFragment fragment = new SchedulePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int day = getArguments().getInt(Constants.DAY);
        int positionWeek = getArguments().getInt(Constants.SELECTED_WEEK);
        rvadapter = new ScheduleFragmentPagerAdapter();
        presenter = new SchedulePagePresenter(day, positionWeek);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.item_edit_schedule_recycler, container, false);
        RecyclerView rvLessons = fragmentView.findViewById(R.id.rv_lessons_edit);
        rvLessons.setAdapter(rvadapter);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    @NonNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }

    public void updateList(ArrayList<Lesson> lessonList) {
        rvadapter.setLessonList(lessonList);
        rvadapter.notifyDataSetChanged();
    }

    public void setWeek(int selectedWeek) {
        presenter.setWeek(selectedWeek+1);
    }
}
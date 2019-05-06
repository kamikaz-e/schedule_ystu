package com.example.misha.myapplication.module.schedule.explore.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.data.database.entity.Lesson;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class SchedulePageFragment extends BaseMainFragment implements com.example.misha.myapplication.module.schedule.explore.page.View {

    private ScheduleAdapter rvadapter;
    private Presenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvadapter = new ScheduleAdapter();
        int day = getArguments().getInt(Constants.DAY);
        int  positionWeek = getArguments().getInt(Constants.SELECTED_WEEK);
        presenter = new Presenter(day,positionWeek);
    }

    public static SchedulePageFragment newInstance(int selectedWeek, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.SELECTED_WEEK, selectedWeek);
        args.putInt(Constants.DAY, position);
        SchedulePageFragment fragment = new SchedulePageFragment();
        fragment.setArguments(args);
        return fragment;
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
    protected BasePresenter getPresenter() {
        return presenter;
    }

    public void updateList(List<Lesson> lessonList) {
        rvadapter.setLessonList(lessonList);
        rvadapter.notifyDataSetChanged();
    }

    public void setWeek(int selectedWeek) {

    }
}
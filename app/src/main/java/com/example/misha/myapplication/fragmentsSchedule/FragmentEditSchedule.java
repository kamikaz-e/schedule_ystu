package com.example.misha.myapplication.fragmentsSchedule;

import android.app.AlertDialog;
import android.app.Dialog;
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
import com.example.misha.myapplication.activity.MainActivity;
import com.example.misha.myapplication.adapter.tabDays.editSchedule.TabDaysAdapterEditSchedule;
import com.example.misha.myapplication.adapter.tabDays.editSchedule.TabDaysPagerAdapterEditSchedule;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.entity.Lesson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

public class FragmentEditSchedule extends Fragment implements View.OnClickListener {

    TabDaysPagerAdapterEditSchedule pagerAdapter;
    TabDaysAdapterEditSchedule adapterTabDays;
    RecyclerView dayTabs;
    private ViewPager viewPager;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private int selectedWeek;
    private List<Lesson> lessonListWeek = new ArrayList<>();
    private List<Lesson> lessonListWeekCurrent = new ArrayList<>();
    Integer currentWeek = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterTabDays = new TabDaysAdapterEditSchedule((position, view) -> {
            viewPager.setCurrentItem(position);
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_schedule, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        viewPager = view.findViewById(R.id.viewpager);
        int selectedDayTab = Preferences.getInstance().getSelectedPositionTabDays();


        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapterTabDays.setSelection(position);
            }
        });


        pagerAdapter = new TabDaysPagerAdapterEditSchedule(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(selectedDayTab);
        adapterTabDays.setSelection(selectedDayTab);
        viewPager.setOffscreenPageLimit(6);
        dayTabs = view.findViewById(R.id.rvTab);
        dayTabs.setAdapter(adapterTabDays);


        fab = view.findViewById(R.id.mainFab);
        fab1 = view.findViewById(R.id.evenWeekFab);
        fab2 = view.findViewById(R.id.unevenWeekFab);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.WEEK_CODE) {
            selectedWeek = data.getIntExtra(Constants.SELECTED_WEEK, 0);
            pagerAdapter.setWeek(selectedWeek);

            adapterTabDays = new TabDaysAdapterEditSchedule((position, view) ->
                    viewPager.setCurrentItem(position));
            adapterTabDays.setSelection(viewPager.getCurrentItem());
            dayTabs.setAdapter(adapterTabDays);
        }
    }

    public void animateFAB() {

        if (Preferences.getInstance().getFabOpen()) {

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            Preferences.getInstance().setFabOpen(false);


        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            Preferences.getInstance().setFabOpen(true);

        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.mainFab:
                animateFAB();
                break;
            case R.id.evenWeekFab:
                onCreateDialogCopyEvenWeek().show();
                break;
            case R.id.unevenWeekFab:
                onCreateDialogCopyUnevenWeek().show();
                break;
        }
    }


    public Dialog onCreateDialogCopyUnevenWeek() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) -> {
            currentWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
            for (int idWeek = 0; idWeek < 17; idWeek += 2) {
                lessonListWeekCurrent = LessonDao.getInstance().getLessonByWeek(currentWeek);
                lessonListWeek = LessonDao.getInstance().getLessonByWeek(idWeek);

                for (int idRowWeek = 0; idRowWeek < 36; idRowWeek++) {
                    lessonListWeek.get(idRowWeek).setData(lessonListWeekCurrent.get(idRowWeek).getSubject(), lessonListWeekCurrent.get(idRowWeek).getAudience(),
                            lessonListWeekCurrent.get(idRowWeek).getEducator(), lessonListWeekCurrent.get(idRowWeek).getTypeLesson());
                    LessonDao.getInstance().updateItemByID(lessonListWeek.get(idRowWeek));
                }
            }
        }).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Скопировать неделю в нечетные недели?");
        return builder.create();
    }


    public Dialog onCreateDialogCopyEvenWeek() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) -> {
            for (int idWeek = 1; idWeek < 17; idWeek += 2) {
                lessonListWeekCurrent = LessonDao.getInstance().getLessonByWeek(currentWeek);
                lessonListWeek = LessonDao.getInstance().getLessonByWeek(idWeek);

                for (int idRowWeek = 0; idRowWeek < 36; idRowWeek++) {
                    lessonListWeek.get(idRowWeek).setData(lessonListWeekCurrent.get(idRowWeek).getSubject(), lessonListWeekCurrent.get(idRowWeek).getAudience(),
                            lessonListWeekCurrent.get(idRowWeek).getEducator(), lessonListWeekCurrent.get(idRowWeek).getTypeLesson());
                    LessonDao.getInstance().updateItemByID(lessonListWeek.get(idRowWeek));
                }
            }
        }).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Скопировать неделю в четные недели?");
        return builder.create();
    }
}

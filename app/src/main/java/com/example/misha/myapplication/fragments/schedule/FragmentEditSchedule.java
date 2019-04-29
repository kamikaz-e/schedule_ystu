package com.example.misha.myapplication.fragments.schedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CustomSpinnerAdapter;
import com.example.misha.myapplication.adapter.tabDays.editSchedule.TabDaysAdapterEditSchedule;
import com.example.misha.myapplication.adapter.tabDays.editSchedule.TabDaysPagerAdapterEditSchedule;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.fragments.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentEditSchedule extends BaseFragment implements View.OnClickListener {

    private TabDaysPagerAdapterEditSchedule pagerAdapter;
    private TabDaysAdapterEditSchedule adapterTabDays;
    private RecyclerView dayTabs;
    private Spinner spinner;
    private ViewPager viewPager;
    private int currentWeek = 0;
    private FloatingActionButton mainFab, evenWeekFab, unevenWeekFab;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
    private List<Lesson> lessonListWeek = new ArrayList<>();
    private List<Lesson> lessonListWeekCurrent = new ArrayList<>();
    private CustomSpinnerAdapter customSpinnerAdapter;

    @Override
    public void onResume() {
        super.onResume();
        spinner = new Spinner(getContext());
        spinner.setBackgroundColor(Color.TRANSPARENT);
        spinner.setAdapter(customSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                pagerAdapter.setWeek(position);
                adapterTabDays.updateData(position);
                adapterTabDays.setSelection(viewPager.getCurrentItem());
                Preferences.getInstance().setSelectedWeekEditSchedule(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        getContext().getToolbar().addView(spinner);
        getContext().setCurrentTitle(null);
        spinner.setSelection(Preferences.getInstance().getSelectedWeekEditSchedule());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setDrawerEnabled(false);
        customSpinnerAdapter = new CustomSpinnerAdapter(getContext());
        pagerAdapter = new TabDaysPagerAdapterEditSchedule(getChildFragmentManager());
        adapterTabDays = new TabDaysAdapterEditSchedule((position, view) -> viewPager.setCurrentItem(position));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_schedule, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        int selectedDayTab = Preferences.getInstance().getSelectedPositionTabDays();
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapterTabDays.setSelection(position);
            }
        });

        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(selectedDayTab);
        adapterTabDays.setSelection(selectedDayTab);
        viewPager.setOffscreenPageLimit(6);
        dayTabs = view.findViewById(R.id.rv_tab);
        dayTabs.setAdapter(adapterTabDays);


        mainFab = view.findViewById(R.id.main_fab);
        evenWeekFab = view.findViewById(R.id.even_weekFab);
        unevenWeekFab = view.findViewById(R.id.uneven_weekFab);
        fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);
        mainFab.setOnClickListener(this);
        evenWeekFab.setOnClickListener(this);
        unevenWeekFab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_empty, menu);
        menu.findItem(R.id.button).setIcon(R.drawable.ic_ok);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().getToolbar().removeView(spinner);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button) {
            getContext().getSupportFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }


    public void animateFAB() {

        if (Preferences.getInstance().getFabOpen()) {

            mainFab.startAnimation(rotateBackward);
            evenWeekFab.startAnimation(fabClose);
            unevenWeekFab.startAnimation(fabClose);
            evenWeekFab.setClickable(false);
            unevenWeekFab.setClickable(false);
            Preferences.getInstance().setFabOpen(false);


        } else {

            mainFab.startAnimation(rotateForward);
            evenWeekFab.startAnimation(fabOpen);
            unevenWeekFab.startAnimation(fabOpen);
            evenWeekFab.setClickable(true);
            unevenWeekFab.setClickable(true);
            Preferences.getInstance().setFabOpen(true);

        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.main_fab:
                animateFAB();
                break;
            case R.id.even_weekFab:
                onCreateDialogCopyEvenWeek().show();
                break;
            case R.id.uneven_weekFab:
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

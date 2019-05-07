package com.example.misha.myapplication.module.schedule.edit.fragments;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import com.example.misha.myapplication.CustomSpinnerAdapter;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.data.database.entity.Lesson;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.module.schedule.edit.TabDaysAdapterEditSchedule;
import com.example.misha.myapplication.module.schedule.edit.TabDaysPagerAdapterEditSchedule;
import com.example.misha.myapplication.module.schedule.explore.ScheduleFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EditScheduleFragment extends BaseMainFragment implements EditScheduleView, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TabDaysPagerAdapterEditSchedule pagerAdapter;
    private TabDaysAdapterEditSchedule adapterTabDays;
    private Spinner spinner;
    private ViewPager viewPager;
    private FloatingActionButton mainFab, evenWeekFab, unevenWeekFab;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
    private List<Lesson> lessonListWeek = new ArrayList<>();
    private List<Lesson> lessonListWeekCurrent = new ArrayList<>();
    private RecyclerView dayTabs;
    private CustomSpinnerAdapter customSpinnerAdapter;
    private int currentWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
    private EditSchedulePresenter presenter;


    @Override
    public void onResume() {
        super.onResume();
        spinner = new Spinner(getContext());
        spinner.setBackgroundColor(Color.TRANSPARENT);
        spinner.setAdapter(customSpinnerAdapter);
        spinner.setOnItemSelectedListener(this);
        getContext().getToolbar().addView(spinner);
        getContext().setCurrentTitle(null);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.onWeekSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EditSchedulePresenter();
        setHasOptionsMenu(true);
        customSpinnerAdapter = new CustomSpinnerAdapter(getContext());
        pagerAdapter = new TabDaysPagerAdapterEditSchedule(getChildFragmentManager());
        adapterTabDays = new TabDaysAdapterEditSchedule((position, view) -> presenter.onPageSelected(position));
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_schedule, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                presenter.onPageSwiped(position);
            }
        });

        viewPager.setAdapter(pagerAdapter);
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
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_empty, menu);
        menu.findItem(R.id.btn_edit).setIcon(R.drawable.ic_ok);
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

    @Override
    public void selectWeek(int position) {
        pagerAdapter.setWeek(position);
        adapterTabDays.updateData(position);
        adapterTabDays.setSelection(viewPager.getCurrentItem());
        dayTabs.setAdapter(adapterTabDays);
        Preferences.getInstance().setSelectedWeekEditSchedule(position);
    }

    @Override
    public void openEditor() {
        getContext().replaceFragment(new ScheduleFragment());
    }

    @Override
    public void selectCurrentDay(int currentDay) {
        int selectedDayTab = Preferences.getInstance().getSelectedPositionTabDays();
        viewPager.setCurrentItem(selectedDayTab);
        adapterTabDays.setSelection(selectedDayTab);
    }

    @Override
    public void selectCurrentWeek(int currentWeek) {
        spinner.setSelection(currentWeek);
    }

    @Override
    public void swipePage(int position) {
        adapterTabDays.setSelection(position);
        Preferences.getInstance().setSelectedPositionTabDays(position);
    }

    @Override
    public void selectPage(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().getToolbar().removeView(spinner);
    }


    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (item.getItemId() == R.id.btn_edit) {
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

    public void evenWeekFab() {
        onCreateDialogCopyEvenWeek().show();
    }

    public void unevenWeekFab() {
        onCreateDialogCopyUnevenWeek().show();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.main_fab:
                presenter.onButtonClicked(R.id.main_fab);
                break;
            case R.id.even_weekFab:
                presenter.onButtonClicked(R.id.even_weekFab);
                break;
            case R.id.uneven_weekFab:
                presenter.onButtonClicked(R.id.uneven_weekFab);
                break;
        }
    }


    private Dialog onCreateDialogCopyUnevenWeek() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) -> {
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


    private Dialog onCreateDialogCopyEvenWeek() {
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

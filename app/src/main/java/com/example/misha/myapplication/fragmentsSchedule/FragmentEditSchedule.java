package com.example.misha.myapplication.fragmentsSchedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Spinner;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.activity.BaseFragment;
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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import static com.example.misha.myapplication.activity.MainActivity.WEEK_CODE;

public class FragmentEditSchedule extends BaseFragment implements View.OnClickListener {

    TabDaysPagerAdapterEditSchedule pagerAdapter;
    TabDaysAdapterEditSchedule adapterTabDays;
    RecyclerView dayTabs;
    androidx.appcompat.widget.Toolbar toolbar;
    private ViewPager viewPager;
    private FloatingActionButton mainFab, evenWeekFab, unevenWeekFab;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
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

        viewPager = view.findViewById(R.id.viewpager);
        int selectedDayTab = Preferences.getInstance().getSelectedPositionTabDays();
        Spinner spinner = getActivity().findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        setHasOptionsMenu(true);
        toolbar =  getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);

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


        mainFab = view.findViewById(R.id.mainFab);
        evenWeekFab = view.findViewById(R.id.evenWeekFab);
        unevenWeekFab = view.findViewById(R.id.unevenWeekFab);
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
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.button) {
            FragmentScheduleByDays fragment = new FragmentScheduleByDays();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .commit();
            Intent intent = new Intent();
            intent.putExtra(Constants.SELECTED_WEEK, Preferences.getInstance().getSelectedWeekEditSchedule());
            sendResultToTarget(FragmentScheduleByDays.class, WEEK_CODE, FragmentActivity.RESULT_OK, intent);
            Preferences.getInstance().setSelectedPositionTabDays(0);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WEEK_CODE) {
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

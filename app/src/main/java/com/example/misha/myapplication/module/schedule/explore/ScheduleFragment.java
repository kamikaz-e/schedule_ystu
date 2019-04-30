package com.example.misha.myapplication.module.schedule.explore;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CustomSpinnerAdapter;
import com.example.misha.myapplication.module.schedule.edit.TabDaysAdapterEditSchedule;
import com.example.misha.myapplication.module.schedule.edit.fragment.EditScheduleFragment;
import com.example.misha.myapplication.util.DateUtil;

import org.jetbrains.annotations.NotNull;


public class ScheduleFragment extends BaseMainFragment implements ScheduleView, AdapterView.OnItemSelectedListener {

    private TabDaysPagerAdapter pagerAdapter;
    private TabDaysAdapterEditSchedule adapterTabDays;
    private RecyclerView dayTabs;
    private ViewPager viewPager;
    private Spinner spinner;

    private CustomSpinnerAdapter customSpinnerAdapter;

    private SchedulePresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        spinner = new Spinner(getContext());
        spinner.setBackgroundColor(Color.TRANSPARENT);
        spinner.setAdapter(customSpinnerAdapter);
        spinner.setOnItemSelectedListener(this);
        getContext().getToolbar().addView(spinner);
        getContext().setCurrentTitle(null);
        presenter.selectDefaultWeek();
        Preferences.getInstance().setSelectedWeekEditSchedule((int) DateUtil.getCurrWeek());
        presenter.init();
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
        presenter = new SchedulePresenter();
        setHasOptionsMenu(true);
        customSpinnerAdapter = new CustomSpinnerAdapter(getContext());
        pagerAdapter = new TabDaysPagerAdapter(getChildFragmentManager());
        adapterTabDays = new TabDaysAdapterEditSchedule((position, view) -> presenter.onPageSelected(position));
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

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

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        getContext().replaceFragment(new EditScheduleFragment());
    }

    @Override
    public void selectCurrentDay(int currentDay) {
        viewPager.setCurrentItem(currentDay);
        adapterTabDays.setSelection(currentDay);
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

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_empty, menu);
        menu.findItem(R.id.btn_edit).setIcon(R.drawable.ic_editor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btn_edit) {
            presenter.onButtonClicked();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().getToolbar().removeView(spinner);
    }

}

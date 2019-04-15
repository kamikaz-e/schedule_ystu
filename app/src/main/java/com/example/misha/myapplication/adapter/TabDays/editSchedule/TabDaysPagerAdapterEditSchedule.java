package com.example.misha.myapplication.adapter.tabDays.editSchedule;

import android.annotation.SuppressLint;

import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.fragments.EditSchedulePageFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabDaysPagerAdapterEditSchedule extends FragmentPagerAdapter {

    private ArrayList<EditSchedulePageFragment> fragments = new ArrayList<>();

    private int selectedWeek;

    public TabDaysPagerAdapterEditSchedule(FragmentManager fragmentManager) {
        super(fragmentManager);
        selectedWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
        for (int day = 0; day < 6; day++) {
            fragments.add(EditSchedulePageFragment.newInstance(day, selectedWeek));
        }
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @SuppressLint("ResourceType")
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void setWeek(int selectedWeek) {
        for (EditSchedulePageFragment fragment: fragments) {
            fragment.setWeek(selectedWeek);
        }
    }
}

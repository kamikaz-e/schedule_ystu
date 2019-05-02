package com.example.misha.myapplication.module.schedule.explore;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.misha.myapplication.data.Preferences;
import com.example.misha.myapplication.module.schedule.explore.page.SchedulePageFragment;

import java.util.ArrayList;

public class TabDaysPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<SchedulePageFragment> fragments = new ArrayList<>();

    public TabDaysPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        int selectedWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
        for (int day = 0; day < 6; day++) {
            fragments.add(SchedulePageFragment.newInstance(selectedWeek, day));
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
        for (SchedulePageFragment fragment : fragments) {
            fragment.setWeek(selectedWeek);
        }
    }
}

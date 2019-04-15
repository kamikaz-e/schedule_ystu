package com.example.misha.myapplication.adapter.tabDays.schedule;

import android.annotation.SuppressLint;

import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.fragments.SchedulePageFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class TabDaysPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<SchedulePageFragment> fragments = new ArrayList<>();

    private int selectedWeek;

    public TabDaysPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        selectedWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
        for (int day = 0; day < 6; day++) {
            fragments.add(SchedulePageFragment.newInstance(day, selectedWeek));
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

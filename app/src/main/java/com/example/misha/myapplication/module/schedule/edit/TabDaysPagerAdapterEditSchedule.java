package com.example.misha.myapplication.module.schedule.edit;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.misha.myapplication.data.Preferences;
import com.example.misha.myapplication.module.schedule.edit.page.fragment.PageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TabDaysPagerAdapterEditSchedule extends FragmentPagerAdapter {

    private ArrayList<PageView> fragments = new ArrayList<>();

    public TabDaysPagerAdapterEditSchedule(FragmentManager fragmentManager) {
        super(fragmentManager);
        int selectedWeek = Preferences.getInstance().getSelectedWeekEditSchedule();
        for (int day = 0; day < 6; day++) {
            fragments.add(PageView.newInstance(selectedWeek, day));
        }
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @NotNull
    @SuppressLint("ResourceType")
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void setWeek(int selectedWeek) {
        for (PageView fragment : fragments) {
            fragment.setWeek(selectedWeek);
        }
    }
}

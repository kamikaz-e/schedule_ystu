package com.example.misha.myapplication.adapter.TabDays;

import android.annotation.SuppressLint;

import com.example.misha.myapplication.fragments.SchedulePageFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabDaysPagerAdapter extends FragmentPagerAdapter {


    public TabDaysPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @SuppressLint("ResourceType")
    @Override
    public Fragment getItem(int position) {
        return SchedulePageFragment.newInstance(position);
    }

}

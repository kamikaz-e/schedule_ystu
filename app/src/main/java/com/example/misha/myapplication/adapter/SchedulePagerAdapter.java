package com.example.misha.myapplication.adapter;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.misha.myapplication.FragmentMonday;

public class SchedulePagerAdapter extends FragmentPagerAdapter {

    String tabTitles[] = new String[]{"ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"};

    @SuppressLint("ResourceType")
    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }



    @SuppressLint("ResourceType")
    @Override
    public Fragment getItem(int position) {
        return new FragmentMonday();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "ПН";
        } else if (position == 1) {
            title = "ВТ";
        } else if (position == 2) {
            title = "СР";
        }
        else if (position == 3) {
            title = "ЧТ";
        }
        else if (position == 4) {
            title = "ПТ";
        }
        else if (position == 5) {
            title = "СБ";
        }

        return title;
    }


}

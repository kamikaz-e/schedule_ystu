package com.example.misha.myapplication.adapter.EditSchedule;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.misha.myapplication.fragments.EditSchedulePageFragment;

public class EditSchedulePagerAdapter extends FragmentPagerAdapter {

    String tabTitles[] = new String[]{"ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"};

    @SuppressLint("ResourceType")
    public EditSchedulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }


    @SuppressLint("ResourceType")
    @Override
    public Fragment getItem(int position) {
        return EditSchedulePageFragment.newInstance(position);
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
        } else if (position == 3) {
            title = "ЧТ";
        } else if (position == 4) {
            title = "ПТ";
        } else if (position == 5) {
            title = "СБ";
        }

        return title;
    }


}

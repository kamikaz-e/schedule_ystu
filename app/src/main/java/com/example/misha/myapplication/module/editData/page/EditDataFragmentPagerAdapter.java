package com.example.misha.myapplication.module.editData.page;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.misha.myapplication.module.editData.page.fragments.FragmentPageAudience;
import com.example.misha.myapplication.module.editData.page.fragments.FragmentEducator;
import com.example.misha.myapplication.module.editData.page.fragments.FragmentTypelesson;

import org.jetbrains.annotations.NotNull;


public class EditDataFragmentPagerAdapter extends FragmentStatePagerAdapter {


    public EditDataFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new EditDataFragmentPage();
        } else if (position == 1) {
            fragment = new FragmentPageAudience();
        } else if (position == 2) {
            fragment = new FragmentEducator();
        } else if (position == 3) {
            fragment = new FragmentTypelesson();
        }
        return fragment;
    }

    @Override
    public int getItemPosition(@NotNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Предмет";
        } else if (position == 1) {
            title = "Аудитор";
        } else if (position == 2) {
            title = "Преп-ль";
        } else if (position == 3) {
            title = "Тип/зан";
        }
        return title;
    }

}
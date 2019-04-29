package com.example.misha.myapplication.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.misha.myapplication.editData.FragmentAudience;
import com.example.misha.myapplication.editData.FragmentEducator;
import com.example.misha.myapplication.editData.FragmentSubject;
import com.example.misha.myapplication.editData.FragmentTypelesson;

import org.jetbrains.annotations.NotNull;


public class EditDataViewPagerAdapter extends FragmentStatePagerAdapter {


    public EditDataViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FragmentSubject();
        } else if (position == 1) {
            fragment = new FragmentAudience();
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
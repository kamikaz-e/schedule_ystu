package com.example.misha.myapplication.module.editData.page;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.misha.myapplication.entity.EditDataModel;

import org.jetbrains.annotations.NotNull;

import static com.example.misha.myapplication.Constants.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.Constants.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.Constants.FRAGMENT_TYPELESSONS;


public class EditDataFragmentPagerAdapter extends FragmentStatePagerAdapter {


    public EditDataFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = EditDataFragmentPage.newInstance(new EditDataModel()FRAGMENT_SUBJECTS);
        } else if (position == 1) {
            fragment = EditDataFragmentPage.newInstance(FRAGMENT_AUDIENCES);
        } else if (position == 2) {
            fragment = EditDataFragmentPage.newInstance(FRAGMENT_EDUCATORS);
        } else if (position == 3) {
            fragment = EditDataFragmentPage.newInstance(FRAGMENT_TYPELESSONS);
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
package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.misha.myapplication.editData.FragmentAudience;
import com.example.misha.myapplication.editData.FragmentEducator;
import com.example.misha.myapplication.editData.FragmentSubject;
import com.example.misha.myapplication.editData.FragmentTypelesson;
import com.example.misha.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;

public class ActivityEditData extends BaseActivity {


    Button clear_subjects;

    TabLayout tabLayout;
    ViewPager viewPager;
    Edit_Data_ViewPagerAdapter viewPagerAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);


        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new Edit_Data_ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        clear_subjects = findViewById(R.id.clear_subjects);
        clear_subjects.setBackgroundResource(R.drawable.ic_clear);
        clear_subjects.setOnClickListener(v -> {

            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    onCreateDialogClearSubjects().show();
                    break;
                case 1:
                    onCreateDialogClearAudiences().show();
                    break;
                case 2:
                    onCreateDialogClearEducators().show();
                    break;
                case 3:
                    onCreateDialogClearTypelessons().show();
                    break;
            }
        });

    }


    void clear_subjects() {
        SubjectDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public Dialog onCreateDialogClearSubjects() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_subjects()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить предметы?");
        return builder.create();
    }

    void clear_audiences() {
        AudienceDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }

    }

    public Dialog onCreateDialogClearAudiences() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_audiences()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить аудитории?");
        return builder.create();
    }


    void clear_educators() {
        EducatorDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public Dialog onCreateDialogClearEducators() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_educators()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить преподавателей?");
        return builder.create();
    }

    void clear_typelessons() {
        TypelessonDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public Dialog onCreateDialogClearTypelessons() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_typelessons()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить предметы?");
        return builder.create();
    }

    public class Edit_Data_ViewPagerAdapter extends FragmentStatePagerAdapter {

        public Edit_Data_ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

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
        public int getItemPosition(Object object) {
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


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ActivityEditData.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();

    }


}




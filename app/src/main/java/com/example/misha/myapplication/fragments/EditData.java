package com.example.misha.myapplication.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.EditDataViewPagerAdapter;
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;
import com.example.misha.myapplication.fragmentsSchedule.FragmentScheduleByDays;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class EditData extends Fragment {


    Button clear_subjects;

    TabLayout tabLayout;
    ViewPager viewPager;
    EditDataViewPagerAdapter viewPagerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_data, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        Button buttonHome = view.findViewById(R.id.buttonHome);

        viewPager = view.findViewById(R.id.viewPager);
        viewPagerAdapter = new EditDataViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = view.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);


        buttonHome.setBackgroundResource(R.drawable.ic_home);
        buttonHome.setOnClickListener(v -> {
            FragmentScheduleByDays fragment= new FragmentScheduleByDays();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        });

        clear_subjects = view.findViewById(R.id.buttonClear);
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
        return view;
    }

    void clear_subjects() {
        SubjectDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public Dialog onCreateDialogClearSubjects() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_typelessons()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить предметы?");
        return builder.create();

    }

}
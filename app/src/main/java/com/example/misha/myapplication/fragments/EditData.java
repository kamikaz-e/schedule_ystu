package com.example.misha.myapplication.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.EditDataViewPagerAdapter;
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;


public class EditData extends BaseFragment {


    private TabLayout tabLayout;
    private EditDataViewPagerAdapter viewPagerAdapter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle("Редактор данных");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_data, container, false);


        setHasOptionsMenu(true);

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPagerAdapter = new EditDataViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_empty, menu);
        menu.findItem(R.id.button).setIcon(R.drawable.ic_clear);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.button) {
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
        }
        return super.onOptionsItemSelected(item);
    }


    private void clear_subjects() {
        SubjectDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    private Dialog onCreateDialogClearSubjects() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_subjects()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить предметы?");
        return builder.create();
    }

    private void clear_audiences() {
        AudienceDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }

    }

    private Dialog onCreateDialogClearAudiences() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_audiences()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить аудитории?");
        return builder.create();
    }


    private void clear_educators() {
        EducatorDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    private Dialog onCreateDialogClearEducators() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_educators()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить преподавателей?");
        return builder.create();
    }

    private void clear_typelessons() {
        TypelessonDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    private Dialog onCreateDialogClearTypelessons() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear_typelessons()).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Очистить предметы?");
        return builder.create();

    }

}

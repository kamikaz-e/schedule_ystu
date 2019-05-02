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
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
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
        menu.findItem(R.id.btn_edit).setIcon(R.drawable.ic_clear);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (item.getItemId() == R.id.btn_edit) {
            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    onCreateDialogClear(SubjectDao.getInstance(), R.string.clear_subjects).show();
                    break;
                case 1:
                    onCreateDialogClear(AudienceDao.getInstance(), R.string.clear_audience).show();
                    break;
                case 2:
                    onCreateDialogClear(EducatorDao.getInstance(), R.string.clear_educators).show();
                    break;
                case 3:
                    onCreateDialogClear(TypelessonDao.getInstance(), R.string.clear_typelessons).show();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private Dialog onCreateDialogClear(AbsDao dao, int titleClear) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                clear(dao)).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle(titleClear);
        return builder.create();
    }

    private void clear(AbsDao dao) {
        dao.deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }


}

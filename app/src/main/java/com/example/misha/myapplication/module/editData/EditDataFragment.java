package com.example.misha.myapplication.module.editData;

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
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.module.editData.page.EditDataFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;


public class EditDataFragment extends BaseMainFragment implements EditDataFragmentView {

    private TabLayout tabLayout;
    private EditDataFragmentPagerAdapter viewPagerAdapter;
    private EditDataPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle("Редактор данных");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EditDataPresenter();
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_data, container, false);
        setHasOptionsMenu(true);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPagerAdapter = new EditDataFragmentPagerAdapter(getChildFragmentManager());
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
            presenter.onClearClick(tabLayout.getSelectedTabPosition());
        }
        return super.onOptionsItemSelected(item);
    }


    public Dialog onCreateDialogClear(AbsDao dao, int titleClear) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) ->
                presenter.onClear(dao)).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle(titleClear);
        return builder.create();
    }

    @NotNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }


    @Override
    public void updateView() {
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

}

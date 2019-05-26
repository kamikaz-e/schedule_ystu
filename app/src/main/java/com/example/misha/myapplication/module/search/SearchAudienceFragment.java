package com.example.misha.myapplication.module.search;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.CustomSpinnerAdapterLessons;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.module.schedule.explore.ScheduleFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchAudienceFragment extends BaseMainFragment implements SearchAudienceFragmentView,
        SearchAudienceAdapter.GroupsAdapterListener,AdapterView.OnItemSelectedListener {

    private SearchAudiencePresenter presenter;
    private RecyclerView rvAudiences;
    private SearchView searchView;
    private Spinner spinner;
    private SearchAudienceAdapter searchAudienceAdapter;
    private CustomSpinnerAdapterLessons customSpinnerAdapterLessons;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle("Выберите дату и занятие");
        spinner.setAdapter(customSpinnerAdapterLessons);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchAudiencePresenter(getActivity());
        customSpinnerAdapterLessons = new CustomSpinnerAdapterLessons(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_audience, container, false);
        rvAudiences = view.findViewById(R.id.rv);
        spinner = view.findViewById(R.id.spinner);
        rvAudiences.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }


    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getContext().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAudienceAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchAudienceAdapter.getFilter().filter(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void openFragmentSchedule() {
        Fragment newFragment = new ScheduleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return false;
        }
        super.onBackPressed();
        return false;
    }


    public void updateListAudiences(ArrayList<Audience> requestList) {
        searchAudienceAdapter = new SearchAudienceAdapter(requestList, this);
        rvAudiences.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvAudiences.setAdapter(searchAudienceAdapter);
    }

    @Override
    public void onItemClick(Audience audience, View v) {
        presenter.onClickItem(audience.getName(), v);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "WHat?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

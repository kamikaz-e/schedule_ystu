package com.example.misha.myapplication.module.audience;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.CustomSpinnerAdapterLessons;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.ErrorView;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.entity.Audience;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.misha.myapplication.data.preferences.Preferences.DARK_THEME;
import static com.example.misha.myapplication.data.preferences.Preferences.LIGHT_THEME;

public class SearchAudienceFragment extends BaseMainFragment implements SearchAudienceFragmentView,   AdapterView.OnItemSelectedListener, View.OnClickListener, ErrorView.ErrorListener {

    private SearchAudiencePresenter presenter;
    private RecyclerView rvAudiences;
    private SearchView searchView;
    private Spinner spinner;
    private SearchAudienceAdapter searchAudienceAdapter;
    private CustomSpinnerAdapterLessons customSpinnerAdapterLessons;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle(getString(R.string.title_search_audience));
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
        View view = inflater.inflate(R.layout.fragment_search_audience, container, false);
        RelativeLayout layoutSelectDate = view.findViewById(R.id.rel_date);
        rvAudiences = view.findViewById(R.id.rv_groups);
        rvAudiences.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        spinner = view.findViewById(R.id.spinner);


        ImageView imageSearchAudience = view.findViewById(R.id.image_searchAudience);
        if (Preferences.getInstance().getSelectedTheme().equals(DARK_THEME)) {
            imageSearchAudience.setImageResource(R.drawable.ic_date_range_white);
        }
        if (Preferences.getInstance().getSelectedTheme().equals(LIGHT_THEME)) {
            imageSearchAudience.setImageResource(R.drawable.ic_date_range_black);
        }
        rvAudiences.setLayoutManager(new LinearLayoutManager(getActivity()));
        layoutSelectDate.setOnClickListener(this);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    @Override
    public void onReloadData() {
        presenter.loadFreeAudienceAudiences(Preferences.getInstance().getSelectedWeek(), Preferences.getInstance().getSelectedDay(), Preferences.getInstance().getSelectedLesson());
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


    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
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
        searchAudienceAdapter = new SearchAudienceAdapter(requestList);
        rvAudiences.setAdapter(searchAudienceAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.onLessonSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void updateTextViewDate(String date) {
        TextView textView = getView().findViewById(R.id.text_searchAudience);
        textView.setText(presenter.dateForTextView(date));
    }


    @Override
    public void onClick(View v) {
        presenter.onClickDate(v);
    }

}

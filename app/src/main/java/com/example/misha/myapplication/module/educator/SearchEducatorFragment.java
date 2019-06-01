package com.example.misha.myapplication.module.educator;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.LessonsEducator;
import com.example.misha.myapplication.module.audience.SearchAudienceFragment;
import com.example.misha.myapplication.module.educator.dialog.DialogFragmentListLessons;
import com.example.misha.myapplication.module.schedule.edit.page.dialog.DialogFragmentListItems;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.misha.myapplication.data.preferences.Preferences.DARK_THEME;
import static com.example.misha.myapplication.data.preferences.Preferences.LIGHT_THEME;

public class SearchEducatorFragment extends BaseMainFragment implements SearchEducatorFragmentView, SearchEducatorAdapter.SearchEducatorsAdapterListener, View.OnClickListener {

    private SearchEducatorPresenter presenter;
    private RecyclerView rvEducator;
    private SearchView searchView;
    private SearchEducatorAdapter searchEducatorAdapter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle(getString(R.string.title_search_educator));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchEducatorPresenter(getActivity());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_educator, container, false);
        RelativeLayout layoutSelectDate = view.findViewById(R.id.rel_date);
        rvEducator = view.findViewById(R.id.rv_educators);
        rvEducator.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        ImageView imageSearchAudience = view.findViewById(R.id.image_searchAudience);
        if (Preferences.getInstance().getSelectedTheme().equals(DARK_THEME)) {
            imageSearchAudience.setImageResource(R.drawable.ic_date_range_white);
        }
        if (Preferences.getInstance().getSelectedTheme().equals(LIGHT_THEME)) {
            imageSearchAudience.setImageResource(R.drawable.ic_date_range_black);
        }
        rvEducator.setLayoutManager(new LinearLayoutManager(getActivity()));
        layoutSelectDate.setOnClickListener(this);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
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
                searchEducatorAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchEducatorAdapter.getFilter().filter(query);
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


    public void updateListEducators(ArrayList<Educator> requestList) {
        searchEducatorAdapter = new SearchEducatorAdapter(requestList);
        rvEducator.setAdapter(searchEducatorAdapter);
    }


    public void updateTextViewDate(String date) {
        TextView textView = getView().findViewById(R.id.text_searchAudience);
        textView.setText(presenter.dateForTextView(date));
    }


    @Override
    public void onClick(View v) {
        presenter.onClickDate(v);
    }

    @Override
    public void showLessonsDialog(ArrayList<LessonsEducator> items, String nameEducator) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(LESSON_LIST, items);
        args.putString(NAME_EDUCATOR,nameEducator);
        DialogFragmentListLessons dialogFragment = DialogFragmentListLessons.newInstance();
        dialogFragment.show(getChildFragmentManager(), DialogFragmentListLessons.class.getSimpleName());
    }

    @Override
    public void onItemClick(Educator educator, View v) {
        presenter.onClickItem(educator.getName());
    }
}

package com.example.misha.myapplication.fragments.fragmentsSchedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.CustomSpinnerAdapter;
import com.example.misha.myapplication.adapter.tabDays.schedule.TabDaysAdapter;
import com.example.misha.myapplication.adapter.tabDays.schedule.TabDaysPagerAdapter;
import com.example.misha.myapplication.fragments.BaseFragment;
import com.example.misha.myapplication.util.DateUtil;

import java.util.Calendar;

import static com.example.misha.myapplication.activity.MainActivity.WEEK_CODE;


public class FragmentScheduleByDays extends BaseFragment {

    private TabDaysPagerAdapter pagerAdapter;
    private TabDaysAdapter adapterTabDays;
    private RecyclerView dayTabs;
    private ViewPager viewPager;
    private Spinner spinner;
    private long currWeek = 0;
    private Toolbar toolbar;

    private CustomSpinnerAdapter customSpinnerAdapter;

    @Override
    public void onResume() {
        super.onResume();
        toolbar = getActivity().findViewById(R.id.toolbar);
        if (spinner == null) {
            spinner = new Spinner(getContext());
            spinner.setAdapter(customSpinnerAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.SELECTED_WEEK, position);
                    sendResultToTarget(FragmentScheduleByDays.class, WEEK_CODE, Activity.RESULT_OK, intent);
                    sendResultToTarget(FragmentEditSchedule.class, WEEK_CODE, Activity.RESULT_OK, intent);
                    Preferences.getInstance().setSelectedWeekEditSchedule(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }

            });
            spinner.setSelection((int) DateUtil.getCurrWeek());
        }
        toolbar.addView(spinner);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        customSpinnerAdapter = new CustomSpinnerAdapter(getContext());
        pagerAdapter = new TabDaysPagerAdapter(getChildFragmentManager());
        adapterTabDays = new TabDaysAdapter((position, view) -> viewPager.setCurrentItem(position));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapterTabDays.setSelection(position);
                Preferences.getInstance().setSelectedPositionTabDays(position);
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(6);
        dayTabs = view.findViewById(R.id.rv_tab);
        dayTabs.setAdapter(adapterTabDays);
        currentDay();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void currentDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int selection = day <= 2 ? 0 : day - 2;
        viewPager.setCurrentItem(selection);
        adapterTabDays.setSelection(selection);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_empty, menu);
        menu.findItem(R.id.button).setIcon(R.drawable.ic_editor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.button) {
            replaceFragment(new FragmentEditSchedule(), true);
            Intent intent = new Intent();
            intent.putExtra(Constants.SELECTED_WEEK, (int) currWeek);
            sendResultToTarget(FragmentEditSchedule.class, WEEK_CODE, FragmentActivity.RESULT_OK, intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WEEK_CODE) {
            int selectedWeek = data.getIntExtra(Constants.SELECTED_WEEK, 0);
            pagerAdapter.setWeek(selectedWeek);
            adapterTabDays = new TabDaysAdapter((position, view) ->
                    viewPager.setCurrentItem(position));
            adapterTabDays.setSelection(viewPager.getCurrentItem());
            dayTabs.setAdapter(adapterTabDays);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        toolbar.removeView(spinner);
    }
}

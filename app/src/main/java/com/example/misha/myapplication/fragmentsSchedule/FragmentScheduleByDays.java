package com.example.misha.myapplication.fragmentsSchedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.tabDays.schedule.TabDaysAdapter;
import com.example.misha.myapplication.adapter.tabDays.schedule.TabDaysPagerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import static com.example.misha.myapplication.activity.MainActivity.WEEK_CODE;

public class FragmentScheduleByDays extends Fragment {

    TabDaysPagerAdapter pagerAdapter;
    TabDaysAdapter adapterTabDays;
    RecyclerView dayTabs;
    private ViewPager viewPager;

    private int selectedWeek;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterTabDays = new TabDaysAdapter((position, view) -> {
            viewPager.setCurrentItem(position);
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_by_days, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapterTabDays.setSelection(position);
            }
        });
        pagerAdapter = new TabDaysPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(6);
        dayTabs = view.findViewById(R.id.rv_tab);
        dayTabs.setAdapter(adapterTabDays);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WEEK_CODE) {
            selectedWeek = data.getIntExtra(Constants.SELECTED_WEEK, 0);
            pagerAdapter.setWeek(selectedWeek);
            adapterTabDays = new TabDaysAdapter((position, view) ->
                    viewPager.setCurrentItem(position));
            dayTabs.setAdapter(adapterTabDays);
        }


    }


}

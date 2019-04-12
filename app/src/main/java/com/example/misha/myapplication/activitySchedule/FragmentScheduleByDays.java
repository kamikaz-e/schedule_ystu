package com.example.misha.myapplication.activitySchedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.activity.MainActivity;
import com.example.misha.myapplication.adapter.tabDays.TabDaysAdapter;
import com.example.misha.myapplication.adapter.tabDays.TabDaysPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

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
        View view = inflater.inflate(R.layout.lesson_days, container, false);
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

        Button toEditSchedule = getActivity().findViewById(R.id.toolbar_but);
        toEditSchedule.setBackgroundResource(R.drawable.ic_editor);
        toEditSchedule.setOnClickListener(v -> {
            FragmentEditSchedule fragment= new FragmentEditSchedule();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.WEEK_CODE) {
            selectedWeek = data.getIntExtra(Constants.SELECTED_WEEK, 0);
            pagerAdapter.setWeek(selectedWeek);
        }
    }


}
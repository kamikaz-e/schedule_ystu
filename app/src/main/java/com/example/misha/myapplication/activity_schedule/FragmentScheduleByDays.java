package com.example.misha.myapplication.activity_schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.adapter.TabDays.TabDaysAdapter;
import com.example.misha.myapplication.adapter.TabDays.TabDaysPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class FragmentScheduleByDays extends Fragment {

    TabDaysPagerAdapter PagerAdapterTabDays;
    TabDaysAdapter adapterTabDays;

    RecyclerView dayTabs;

    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterTabDays = new TabDaysAdapter(new SimpleItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                viewPager.setCurrentItem(position);
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapterTabDays.setSelection(position);
            }
        });
        PagerAdapterTabDays = new TabDaysPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(PagerAdapterTabDays);
        viewPager.setOffscreenPageLimit(6);
        dayTabs = view.findViewById(R.id.recycler_view);
        dayTabs.setAdapter(adapterTabDays);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

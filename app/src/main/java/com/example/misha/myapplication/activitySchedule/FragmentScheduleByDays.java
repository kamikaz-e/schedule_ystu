package com.example.misha.myapplication.activitySchedule;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.tabDays.TabDaysAdapter;
import com.example.misha.myapplication.adapter.tabDays.TabDaysPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

public class FragmentScheduleByDays extends Fragment {

    TabDaysPagerAdapter PagerAdapterTabDays;
    TabDaysAdapter adapterTabDays;
    RecyclerView dayTabs;

    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterTabDays = new TabDaysAdapter();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapterTabDays.setSelection(position);

              //  Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
        PagerAdapterTabDays = new TabDaysPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(PagerAdapterTabDays);
        viewPager.setOffscreenPageLimit(6);
        dayTabs = view.findViewById(R.id.rv_tab);
        dayTabs.setAdapter(adapterTabDays);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

package com.example.misha.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.ScheduleFriday;
import com.example.misha.myapplication.ScheduleMonday;
import com.example.misha.myapplication.ScheduleSaturday;
import com.example.misha.myapplication.ScheduleThursday;
import com.example.misha.myapplication.ScheduleTuesday;
import com.example.misha.myapplication.ScheduleWednesday;

public class FragmentScheduleByDays extends android.support.v4.app.Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        final ViewPager viewPager =  view.findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
        viewPager.setAdapter(pagerAdapter);

        final TabLayout tabLayout =  view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        String tabTitles[] = new String[] { "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ" };
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                  return  new ScheduleMonday();
                case 1:
                    return new ScheduleTuesday();
                case 2:
                    return new ScheduleWednesday();
                case 3:
                    return new ScheduleThursday();
                case 4:
                    return new ScheduleFriday();
                case 5:
                    return new ScheduleSaturday();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = "ПН";
            } else if (position == 1) {
                title = "ВТ";
            } else if (position == 2) {
                title = "СР";
            }
            else if (position == 3) {
                title = "ЧТ";
            }
            else if (position == 4) {
                title = "ПТ";
            }
            else if (position == 5) {
                title = "СБ";
            }
            return title;
        }



    }

}

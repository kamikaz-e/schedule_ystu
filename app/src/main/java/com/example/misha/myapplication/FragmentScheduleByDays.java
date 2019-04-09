package com.example.misha.myapplication;

import android.content.Context;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.fragments.ScheduleMonday;

public class FragmentScheduleByDays extends Fragment {

    public FragmentScheduleByDays() {
    }

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
        public Fragment getItem(int position) {
            return new ScheduleMonday();
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

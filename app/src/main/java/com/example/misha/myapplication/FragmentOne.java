package com.example.misha.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentOne extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentOne() {
    }

    public static FragmentOne newInstance() {
        FragmentOne fragment = new FragmentOne();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ViewPager viewPager =  view.findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout =  view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));

        }

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

            return tabTitles[position];
        }

        public View getTabView(int position) {
            View tab = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null );
              TextView tv =  tab.findViewById(R.id.custom_text);
              tv.setText(tabTitles[position]);

            return tab;
        }}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentOne.OnFragmentInteractionListener) {
            mListener = (FragmentOne.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

package com.example.misha.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.TabDays.TabDaysAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class TabFragment extends Fragment {

    private View fragmentView;
    private RecyclerView rvLessons;
    private TabDaysAdapter rvadapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvadapter = new TabDaysAdapter();
    }

    public static TabFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("positionTab", position);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initResources();
        rvadapter.notifyDataSetChanged();
    }


    void initResources() {
        Bundle bundle = getArguments();
        int position_day = bundle.getInt("positionTab");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.item_tabs_recycler, container, false);
        rvLessons = fragmentView.findViewById(R.id.rv_tab);
        rvLessons.setAdapter(rvadapter);
        return fragmentView;
    }
}
package com.example.misha.myapplication.model.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.R;

public class FragmentFriday extends android.support.v4.app.Fragment {
    public FragmentFriday() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_item_edit_friday, container, false);
        return rootView; }
}
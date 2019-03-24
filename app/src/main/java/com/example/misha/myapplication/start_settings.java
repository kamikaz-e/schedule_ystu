package com.example.misha.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class start_settings extends AppCompatActivity
        implements fragment_date.OnFragmentInteractionListener,fragment_call_schedule.OnFragmentInteractionListener,fragment_start_subjects.OnFragmentInteractionListener,fragment_start_audiences.OnFragmentInteractionListener,fragment_start_educators.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_settings);

        Fragment fragment = new fragment_date();

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {

    }
}

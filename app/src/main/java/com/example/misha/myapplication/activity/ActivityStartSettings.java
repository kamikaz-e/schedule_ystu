package com.example.misha.myapplication.activity;

import android.os.Bundle;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.startFragments.FragmentDate;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ActivityStartSettings extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_settings);

        Fragment fragment = new FragmentDate();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrame, fragment);
            ft.commit();

        }
    }


}

package com.example.misha.myapplication.activity;

import android.os.Bundle;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.start_fragments.FragmentDate;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityStartSettings extends BaseActivity
     {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_settings);

        Fragment fragment = new FragmentDate();
            if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();

        }
    }


}

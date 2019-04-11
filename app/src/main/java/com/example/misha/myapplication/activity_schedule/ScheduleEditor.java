package com.example.misha.myapplication.activity_schedule;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.activity.BaseActivity;
import com.example.misha.myapplication.activity.MainActivity;
import com.example.misha.myapplication.activitySchedule.FragmentScheduleByDays;
import com.example.misha.myapplication.fragments.EditSchedulePageFragment;
import com.example.misha.myapplication.fragments.SchedulePageFragment;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.EditSchedule.EditSchedulePagerAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import static com.example.misha.myapplication.activity.MainActivity.WEEK_CODE;


public class ScheduleEditor extends BaseActivity {

    Integer position_week = 0;
    Integer position_day = 0;
    Integer flag_autosave = 1;
    ViewPager viewPager;
    EditSchedulePagerAdapter pagerAdapter;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_editor_menu);

        toolbar.setOverflowIcon(drawable);

        viewPager = findViewById(R.id.viewpager);
        PopupMenu menu = new PopupMenu(this, viewPager);
       // menu.setOnMenuItemClickListener(ScheduleEditor.this::onMenuItemClicked);
        pagerAdapter = new EditSchedulePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(6);

        final MaterialBetterSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.weeks));
        spinner.setAdapter(arrayAdapter);

        SharedPreferences settings = getSharedPreferences("choice_week", 0);
        int current_week = Integer.valueOf(settings.getString("position", "0"));

        spinner.setText(arrayAdapter.getItem(current_week));
        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position_day = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        spinner.setOnDismissListener(() -> spinner.clearFocus());

        final AdapterView.OnItemClickListener itemClickListener = (parent, view, position, id) -> {
            if (flag_autosave == 1) {
            }
            Intent intent = new Intent();
            intent.putExtra(Constants.SELECTED_WEEK, position);
            sendResultToTarget(ScheduleEditor.class,  WEEK_CODE, Activity.RESULT_OK, intent);
        };
        spinner.setOnItemClickListener(itemClickListener);
        final AdapterView.OnItemClickListener itemSelectedListener = (parent, view, position, id) -> {



        };
        spinner.setOnItemClickListener(itemSelectedListener);

    }


    @Override
    public void onResume() {
        super.onResume();
    }


    private boolean onMenuItemClicked(MenuItem menuItem) {
        return  false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ScheduleEditor.this, MainActivity.class);
                finish();
                startActivity(intent);
        }
        return true;
    }}




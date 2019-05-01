package com.example.misha.myapplication.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseActivity;
import com.example.misha.myapplication.fragments.BaseFragment;
import com.example.misha.myapplication.util.DateUtil;
import com.google.android.material.navigation.NavigationView;

import static com.example.misha.myapplication.util.DateUtil.hintKeyboard;

public abstract class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    
    protected NavigationView navigationView;
    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerLayout = findViewById(R.id.root_view);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.rasp_day);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hintKeyboard(DrawerActivity.this);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                hintKeyboard(DrawerActivity.this);
            }
        };

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        getToolbar().setNavigationOnClickListener(createDrawerClick());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public View.OnClickListener createDrawerClick() {
        return view -> {
            if (drawerLayout.isEnabled()) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            } else {
                DateUtil.hintKeyboard(DrawerActivity.this);
                DrawerActivity.this.onBackPressed();
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (!BaseFragment.handleBackPressed(getSupportFragmentManager())) {
            if (drawerLayout.isEnabled()) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    super.onBackPressed();
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }


    public void disable() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.setEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    public void enable() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setEnabled(true);
    }

    public void markPartition(int id) {
        navigationView.setCheckedItem(id);
    }


    /* Restore action bar. */
    public void restoreActionBar() {
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(currentTitle);
    }

    public DrawerLayout getNavigationView() {
        return drawerLayout;
    }
}


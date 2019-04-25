package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.DatabaseHelper;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.fragments.CallsSchedule;
import com.example.misha.myapplication.fragments.EditData;
import com.example.misha.myapplication.fragments.Settings;
import com.example.misha.myapplication.fragments.fragmentsSchedule.FragmentEditSchedule;
import com.example.misha.myapplication.fragments.fragmentsSchedule.FragmentScheduleByDays;
import com.google.android.material.navigation.NavigationView;

import static com.example.misha.myapplication.util.DateUtil.hintKeyboard;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int WEEK_CODE = 2221;
    DrawerLayout drawer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawer = findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hintKeyboard(MainActivity.this);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                hintKeyboard(MainActivity.this);
            }
        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(R.id.rasp_day);
        if (!Preferences.getInstance().isHintsOpened()) {
            Intent intent = new Intent(MainActivity.this, ActivityStart.class);
            startActivity(intent);
            Preferences.getInstance().setHintsOpened();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new DatabaseHelper(this).getWritableDatabase();
        CallDao.getInstance().initTable();
        LessonDao.getInstance().initTable();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.rasp_day) {
        } else if (id == R.id.edit_schedule) {
        } else if (id == R.id.settings) {
        } else if (id == R.id.nav_share) {
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;

        switch (viewId) {
            case R.id.rasp_day:
                fragment = new FragmentScheduleByDays();
                break;
            case R.id.edit_schedule:
                fragment = new FragmentEditSchedule();
                break;
            case R.id.edit_data:
                fragment = new EditData();
                break;
            case R.id.call_schedule:
                fragment = new CallsSchedule();
                break;
            case R.id.settings:
                fragment = new Settings();
                break;
            case R.id.nav_share:
                onCreateDialog().show();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }



    public Dialog onCreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setPositiveButton("Профиль Вконтакте", (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mikhailvolkov1"));
            finish();
            startActivity(browserIntent);
        }).setNeutralButton("Отмена", (dialog, id) -> dialog.cancel()).setNegativeButton("Электронная почта", (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:mikhailvolkov2014-2014@ya.ru"));
            finish();
            startActivity(browserIntent);
        }).setTitle("Обратная связь с разработчиком");
        return builder.create();
    }

}

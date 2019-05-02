package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.misha.myapplication.data.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.database.DatabaseHelper;
import com.example.misha.myapplication.data.database.dao.CallDao;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.module.callSchedule.CallsSchedule;
import com.example.misha.myapplication.module.editData.EditData;
import com.example.misha.myapplication.module.settings.Settings;
import com.example.misha.myapplication.module.schedule.explore.ScheduleFragment;

public class MainActivity extends DrawerActivity {

    private int selectedFragmentId = R.id.rasp_day;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Preferences.getInstance().isHintsOpened()) {
            Intent intent = new Intent(MainActivity.this, ActivityStart.class);
            startActivity(intent);
            Preferences.getInstance().setHintsOpened();
        }
        replaceFragment(new ScheduleFragment());
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
        Fragment fragment = null;
        if (id == selectedFragmentId) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        selectedFragmentId = id;
        if (id == R.id.rasp_day) {
            fragment = new ScheduleFragment();
        } else if (id == R.id.edit_data) {
            fragment = new EditData();
        } else if (id == R.id.call_schedule) {
            fragment = new CallsSchedule();
        } else if (id == R.id.settings) {
            fragment = new Settings();
        } else if (id == R.id.nav_share) {
            onCreateDialog().show();
        }
        if (fragment != null) {
            replaceFragment(fragment);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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

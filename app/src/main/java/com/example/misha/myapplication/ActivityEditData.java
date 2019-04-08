package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;

public class ActivityEditData extends AppCompatActivity {


    Button clear_subjects;

    TabLayout tabLayout;
    ViewPager viewPager;
    Edit_Data_ViewPagerAdapter viewPagerAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityeditdata);

      /* SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
      String hasVisited = sharedPreferences.getString("hasVisited", "nope");

      if (hasVisited == "nope") {
        new MaterialTapTargetPrompt.Builder(this)
                .setTarget(spinner)
                .setPromptBackground(new RectanglePromptBackground())
                .setPromptFocal(new RectanglePromptFocal())
                .setPrimaryText("Переключение между данными")
                .setSecondaryText("Нажав на раскрывающийся список, Вы можете выбрать нужный раздел редактирования данных")
                .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                .setBackgroundColour(Color.rgb(100, 100, 255))
                .setPrimaryTextColour(Color.rgb(255, 255, 255))
                .setSecondaryTextColour(Color.rgb(255, 255, 255))
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                  public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                    if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {

                    }
                  }
                })
                .show();

        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString("hasVisited", "yes");
        e.commit();
      }*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);


        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new Edit_Data_ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        clear_subjects = findViewById(R.id.clear_subjects);
        clear_subjects.setBackgroundResource(R.drawable.ic_clear);
        clear_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (tabLayout.getSelectedTabPosition()) {
                    case 0:
                        onCreateDialogClearSubjects().show();
                        break;
                    case 1:
                        onCreateDialogClearAudiences().show();
                        break;
                    case 2:
                        onCreateDialogClearEducators().show();
                        break;
                    case 3:
                        onCreateDialogClearTypelessons().show();
                        break;
                }
            }
        });

    }


    void clear_subjects() {
        SubjectDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public Dialog onCreateDialogClearSubjects() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_subjects();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить предметы?");
        return builder.create();
    }

    void clear_audiences() {
        AudienceDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }

    }

    public Dialog onCreateDialogClearAudiences() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_audiences();

            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить аудитории?");
        return builder.create();
    }


    void clear_educators() {
        EducatorDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public Dialog onCreateDialogClearEducators() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_educators();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить преподавателей?");
        return builder.create();
    }

    void clear_typelessons() {
        TypelessonDao.getInstance().deleteAll();
        if (!(viewPagerAdapter == null)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public Dialog onCreateDialogClearTypelessons() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_typelessons();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить предметы?");
        return builder.create();
    }

    public class Edit_Data_ViewPagerAdapter extends FragmentStatePagerAdapter {

        public Edit_Data_ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new FragmentSubject();
            } else if (position == 1) {
                fragment = new FragmentAudience();
            } else if (position == 2) {
                fragment = new FragmentEducator();
            } else if (position == 3) {
                fragment = new FragmentTypelesson();
            }
            return fragment;

        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = "Предмет";
            } else if (position == 1) {
                title = "Аудитория";
            } else if (position == 2) {
                title = "Преподаватель";
            } else if (position == 3) {
                title = "Тип занятия";
            }
            return title;
        }

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ActivityEditData.this, MainActivity.class);
                finish();
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityEditData.this, MainActivity.class);
        finish();
        startActivity(intent);
    }


}




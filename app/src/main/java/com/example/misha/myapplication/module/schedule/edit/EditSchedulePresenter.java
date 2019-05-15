package com.example.misha.myapplication.module.schedule.edit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseActivity;
import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.dao.LessonDao;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

public class EditSchedulePresenter extends BaseMainPresenter<EditScheduleFragmentView> implements EditSchedulePresenterInterface {

    private List<Lesson> lessonListWeek = new ArrayList<>();
    private List<Lesson> lessonListWeekCurrent = new ArrayList<>();

    private Context context;

    public EditSchedulePresenter(BaseActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
        int currentDay = Preferences.getInstance().getSelectedPositionTabDays();
        int currentWeek = Preferences.getInstance().getSelectedWeekSchedule();
        getView().selectCurrentDay(currentDay);
        getView().selectCurrentWeek(currentWeek);
    }

    @Override
    public void onWeekSelected(int position) {
        getView().selectWeek(position);
    }

    public void evenWeekFab() {
        onCreateDialogCopyEvenWeek().show();
    }

    public void unevenWeekFab() {
        onCreateDialogCopyUnevenWeek().show();
    }


    @Override
    public void onButtonClicked(int id) {
        if (id == R.id.main_fab) {
            getView().animateFAB();
        }
        if (id == R.id.even_weekFab) {
            evenWeekFab();
        }
        if (id == R.id.uneven_weekFab) {
            unevenWeekFab();
        }
    }

    @Override
    public void onPageSwiped(int position) {
        getView().swipePage(position);
        Preferences.getInstance().setSelectedPositionTabDays(position);
    }

    private Dialog onCreateDialogCopyEvenWeek() {
        int currentWeek = Preferences.getInstance().getSelectedWeekSchedule();
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) -> {
            for (int idWeek = 1; idWeek < 17; idWeek += 2) {
                lessonListWeekCurrent = LessonDao.getInstance().getLessonByWeek(currentWeek);
                lessonListWeek = LessonDao.getInstance().getLessonByWeek(idWeek);

                for (int idRowWeek = 0; idRowWeek < 36; idRowWeek++) {
                    lessonListWeek.get(idRowWeek).setData(lessonListWeekCurrent.get(idRowWeek).getSubject(), lessonListWeekCurrent.get(idRowWeek).getAudience(),
                            lessonListWeekCurrent.get(idRowWeek).getEducator(), lessonListWeekCurrent.get(idRowWeek).getTypeLesson());
                    LessonDao.getInstance().updateItemByID(lessonListWeek.get(idRowWeek));
                }
            }
        }).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Скопировать неделю в четные недели?");
        return builder.create();
    }


    private Dialog onCreateDialogCopyUnevenWeek() {
        int currentWeek = Preferences.getInstance().getSelectedWeekSchedule();
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) -> {
            for (int idWeek = 0; idWeek < 17; idWeek += 2) {
                lessonListWeekCurrent = LessonDao.getInstance().getLessonByWeek(currentWeek);
                lessonListWeek = LessonDao.getInstance().getLessonByWeek(idWeek);

                for (int idRowWeek = 0; idRowWeek < 36; idRowWeek++) {
                    lessonListWeek.get(idRowWeek).setData(lessonListWeekCurrent.get(idRowWeek).getSubject(), lessonListWeekCurrent.get(idRowWeek).getAudience(),
                            lessonListWeekCurrent.get(idRowWeek).getEducator(), lessonListWeekCurrent.get(idRowWeek).getTypeLesson());
                    LessonDao.getInstance().updateItemByID(lessonListWeek.get(idRowWeek));
                }
            }
        }).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Скопировать неделю в нечетные недели?");
        return builder.create();
    }

    @Override
    public void onPageSelected(int position) {
        getView().selectPage(position);
    }


}

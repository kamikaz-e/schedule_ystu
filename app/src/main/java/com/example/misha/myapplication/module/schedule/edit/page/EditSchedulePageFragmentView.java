package com.example.misha.myapplication.module.schedule.edit.page;

import com.example.misha.myapplication.common.core.BaseView;
import com.example.misha.myapplication.data.database.entity.SimpleItem;

import java.util.ArrayList;

public interface EditSchedulePageFragmentView extends BaseView {

    int SUBJECT = 4440;
    int AUDIENCE = 2220;
    int EDUCATOR = 1110;
    int TYPELESSON = 3330;


    String ITEMS = "ITEMS";
    String FRAGMENT_CODE = "FRAGMENT_CODE";
    String POSITION = "POSITION";

    void updateList(int day, int positionWeek);

    void onCopyUpClick(int position);

    void onCopyDownClick(int position);

    void onClearLessonClick(int position);

    void showEditDialog(ArrayList<? extends SimpleItem> subjectList, int position, int subject);

    void setWeek(int position);
}

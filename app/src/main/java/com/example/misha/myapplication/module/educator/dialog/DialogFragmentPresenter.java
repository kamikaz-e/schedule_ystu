package com.example.misha.myapplication.module.educator.dialog;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.SimpleItem;

import java.util.ArrayList;

import static com.example.misha.myapplication.Constants.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.Constants.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.Constants.FRAGMENT_TYPELESSONS;

public class DialogFragmentPresenter extends BaseMainPresenter<DialogFragmentListLessonsView> implements DialogFragmentPresenterInterface {


    @Override
    public void init() {

    }
}

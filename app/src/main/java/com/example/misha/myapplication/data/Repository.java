package com.example.misha.myapplication.data;

import com.example.misha.myapplication.data.database.DatabaseInterface;
import com.example.misha.myapplication.data.database.DatabaseManager;
import com.example.misha.myapplication.data.network.APIService;
import com.example.misha.myapplication.data.network.RetrofitClient;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.data.preferences.PreferencesInterface;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.Subject;
import com.example.misha.myapplication.entity.Typelesson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class Repository implements RepositoryManager {

    private static volatile Repository instance;

    private DatabaseInterface databaseInterface;

    private APIService apiService;

    private PreferencesInterface preferencesInterface;

    private Repository() {
        databaseInterface = DatabaseManager.getInstance();
        apiService = RetrofitClient.getInstance().getRequestInterface();
        preferencesInterface = Preferences.getInstance();
    }

    public static Repository getInstance() {
        if (instance != null) return instance;
        instance = new Repository();
        return instance;
    }

    @Override
    public Single<List<Lesson>> getLessonsByDayWeek(int week, int day) {
        return databaseInterface.getLessonsByDayWeek(week, day);
    }


    @Override
    public Single<ArrayList<Groups>> getGroups() {
        return apiService.getGroups();
    }

    @Override
    public Single<ArrayList<Audience>> getFreeAudiences(String week, String day, String lesson) {
        return apiService.getFreeAudiences(week, day, lesson);
    }

    @Override
    public Single<ArrayList<Subject>> getSubjects(String name_group) {
        return apiService.getSubjects(name_group);
    }

    @Override
    public Single<ArrayList<Audience>> getAudiences(String name_group) {
        return apiService.getAudiences(name_group);
    }

    @Override
    public Single<ArrayList<Educator>> getEducators(String name_group) {
        return apiService.getEducators(name_group);
    }

    @Override
    public Single<ArrayList<Typelesson>> getTypelessons() {
        return apiService.getTypelessons();
    }

    @Override
    public Single<ArrayList<Lesson>> getLessons(String name_group) {
        return apiService.getLessons(name_group);
    }


    @Override
    public boolean isHintsOpened() {
        return preferencesInterface.isHintsOpened();
    }

    @Override
    public void setHintsOpened() {
        preferencesInterface.setHintsOpened();
    }

    @Override
    public void setSemesterStart(String date) {
        preferencesInterface.setSemesterStart(date);
    }

    @Override
    public String getSemestStart() {
        return preferencesInterface.getSemestStart();
    }

    @Override
    public boolean isCallsOpened() {
        return preferencesInterface.isCallsOpened();
    }

    @Override
    public void setCallsOpened(boolean state) {
        preferencesInterface.setCallsOpened(state);
    }

    @Override
    public int getSelectedWeekSchedule() {
        return preferencesInterface.getSelectedWeekSchedule();
    }

    @Override
    public void setSelectedWeekSchedule(int position) {
        preferencesInterface.setSelectedWeekSchedule(position);
    }

    @Override
    public int getSelectedPositionTabDays() {
        return preferencesInterface.getSelectedPositionTabDays();
    }

    @Override
    public void setSelectedPositionTabDays(int position) {
        preferencesInterface.setSelectedPositionTabDays(position);
    }

    @Override
    public boolean getFabOpen() {
        return preferencesInterface.getFabOpen();
    }

    @Override
    public void setFabOpen(boolean state) {
        preferencesInterface.setFabOpen(state);
    }

    @Override
    public int getSelectedPositionLesson() {
        return preferencesInterface.getSelectedPositionLesson();
    }

    @Override
    public void setSelectedPositionLesson(int position) {
        preferencesInterface.setSelectedPositionLesson(position);
    }

    @Override
    public String getSelectDate() {
        return preferencesInterface.getSelectDate();
    }

    @Override
    public void setSelectDate(String selectDate) {
        preferencesInterface.setSelectDate(selectDate);
    }

    @Override
    public String getSelectedTheme() {
        return preferencesInterface.getSelectedTheme();
    }

    @Override
    public void setSelectedTheme(String stringTheme) {
        preferencesInterface.setSelectedTheme(stringTheme);
    }

    @Override
    public String getSelectedLesson() {
        return preferencesInterface.getSelectedLesson();
    }

    @Override
    public void setSelectedLesson(String lesson) {

    }

    @Override
    public String getSelectedWeek() {
        return preferencesInterface.getSelectedWeek();
    }

    @Override
    public void setSelectedWeek(String week) {

    }

    @Override
    public String getSelectedDay() {
        return preferencesInterface.getSelectedDay();
    }

    @Override
    public void setSelectedDay(String day) {

    }

    @Override
    public String getSelectedDate() {
        return preferencesInterface.getSelectedDate();
    }

    @Override
    public void setSelectedDate(String date) {

    }
}

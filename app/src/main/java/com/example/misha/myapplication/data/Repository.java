package com.example.misha.myapplication.data;

import com.example.misha.myapplication.data.database.DatabaseInterface;
import com.example.misha.myapplication.data.database.DatabaseManager;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Lesson;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.network.APIService;
import com.example.misha.myapplication.data.network.RetrofitClient;
import com.example.misha.myapplication.data.network.request.ScheduleRequest;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.data.preferences.PreferencesInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class Repository implements RepositoryManager {

    private static volatile Repository instance;

    private DatabaseInterface databaseInterface;

    private APIService apiService;

    private PreferencesInterface preferencesInterface;

    public static Repository getInstance() {
        if (instance != null) return instance;
        instance = new Repository();
        return instance;
    }

    private Repository() {
        databaseInterface = DatabaseManager.getInstance();
        apiService = RetrofitClient.getInstance().getRequestInterface();
        preferencesInterface = Preferences.getInstance();
    }

    @Override
    public Single<List<Lesson>> getLessonsByDayWeek(int week, int day) {
        return databaseInterface.getLessonsByDayWeek(week, day);
    }

    @Override
    public Single<Throwable> insertData(String nameGroup, String subjects, String audiences, String educators, String typelessons, String calls, String lessons) {
        return apiService.insertData(nameGroup, subjects, audiences, educators, typelessons, calls, lessons);
    }

    @Override
    public Single<ArrayList<Subject>> getSubjects(ScheduleRequest request) {
        return apiService.getSubjects(request);
    }

    @Override
    public Single<ArrayList<Audience>> getAudiences(ScheduleRequest request) {
        return apiService.getAudiences(request);
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
    public void setSemesterStart(long date) {
        preferencesInterface.setSemesterStart(date);
    }

    @Override
    public long getSemestStart() {
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
    public void setSelectedWeekEditSchedule(int position) {
        preferencesInterface.setSelectedWeekEditSchedule(position);
    }

    @Override
    public int getSelectedWeekEditSchedule() {
        return preferencesInterface.getSelectedWeekEditSchedule();
    }

    @Override
    public void setSelectedPositionTabDays(int position) {
        preferencesInterface.setSelectedPositionTabDays(position);
    }

    @Override
    public int getSelectedPositionTabDays() {
        return preferencesInterface.getSelectedPositionTabDays();
    }

    @Override
    public void setFabOpen(boolean state) {
        preferencesInterface.setFabOpen(state);
    }

    @Override
    public boolean getFabOpen() {
        return preferencesInterface.getFabOpen();
    }

    @Override
    public void setSelectedPositionLesson(int position) {
        preferencesInterface.setSelectedPositionLesson(position);
    }

    @Override
    public int getSelectedPositionLesson() {
        return preferencesInterface.getSelectedPositionLesson();
    }

    @Override
    public void setSelectDate(String selectDate) {
        preferencesInterface.setSelectDate(selectDate);
    }

    @Override
    public String getSelectDate() {
        return preferencesInterface.getSelectDate();
    }
}

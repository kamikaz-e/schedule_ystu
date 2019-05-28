package com.example.misha.myapplication.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImportData {

    @Expose
    @SerializedName("audiences")
    private ArrayList<Audience> audiences;

    public ArrayList<Audience> getAudiences() {
        return audiences;
    }

    @Expose
    @SerializedName("subjects")
    private ArrayList<Subject> subjects;

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    @Expose
    @SerializedName("educators")
    private ArrayList<Educator> educators;

    public ArrayList<Educator> getEducators() {
        return educators;
    }

    @Expose
    @SerializedName("typelessons")
    private ArrayList<Typelesson> typelessons;


    public ArrayList<Typelesson> getTypelessons() {
        return typelessons;
    }

    @Expose
    @SerializedName("lessons")
    private ArrayList<Lesson> lessons;

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    @Expose
    @SerializedName("calls")
    private ArrayList<Calls> calls;

    public ArrayList<Calls> getCalls() {
        return calls;
    }
}

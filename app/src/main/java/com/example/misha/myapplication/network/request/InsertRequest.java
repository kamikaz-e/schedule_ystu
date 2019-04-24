package com.example.misha.myapplication.network.request;

import com.google.gson.annotations.SerializedName;

public class InsertRequest {

    @SerializedName("name_db")
    private String get_name;
    @SerializedName("subjects")
    private String subjects;
    @SerializedName("audiences")
    private String audiences;
    @SerializedName("educators")
    private String educators;
    @SerializedName("typelessons")
    private String typelessons;
    @SerializedName("calls")
    private String calls;
    @SerializedName("lessons")
    private String lessons;
    @SerializedName("date")
    private String date;


    public String getGet_name() {
        return get_name;
    }

    public void setGet_name(String get_name) {
        this.get_name = get_name;
    }


    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }


    public String getAudiences() {
        return audiences;
    }

    public void setAudiences(String audiences) {
        this.audiences = audiences;
    }


    public String getEducators() {
        return educators;
    }

    public void setEducators(String educators) {
        this.educators = educators;
    }


    public String getTypelessons() {
        return typelessons;
    }

    public void setTypelessons(String typelessons) {
        this.typelessons = typelessons;
    }


    public String getCalls() {
        return calls;
    }

    public void setCalls(String calls) {
        this.calls = calls;
    }


    public String getLessons() {
        return lessons;
    }

    public void setLessons(String lessons) {
        this.lessons = lessons;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

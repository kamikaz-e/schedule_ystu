package com.example.misha.myapplication.database.entity;

public class Lesson {

    private String id;

    private String week;

    private String day;

    private String subject;

    private String audience;

    private String educator;

    private String typeLesson;

    private String timeLesson;

    public Lesson() {

    }


    public Lesson(int week, int day, int subject, int audience, int educator, int typeLesson, int timeLesson) {
        this.week = String.valueOf(week);
        this.day = String.valueOf(day);
        this.subject = String.valueOf(subject);
        this.audience =  String.valueOf(audience);
        this.educator =  String.valueOf(educator);
        this.typeLesson =  String.valueOf(typeLesson);
        this.timeLesson = String.valueOf(timeLesson);
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getAudience() {
        return audience;
    }

    public String getEducator() {
        return educator;
    }

    public String getTypeLesson() {
        return typeLesson;
    }

    public String getTimeLesson() {
        return timeLesson;
    }

    public String getDay() {
        return day;
    }

    public String getWeek() {
        return week;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setEducatorEdit(String educatorEdit) {
        this.educator = educatorEdit;
    }

    public void setEducator(String educator) {
        this.educator = educator;
    }
    public void setData (String subject, String audience, String educator, String typeLesson)
    {
        this.subject=subject;
        this.audience=audience;
        this.educator=educator;
        this.typeLesson=typeLesson;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeLesson(String timeLesson) {
        this.timeLesson = timeLesson;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setTypeLesson(String typeLesson) {
        this.typeLesson = typeLesson;
    }
}

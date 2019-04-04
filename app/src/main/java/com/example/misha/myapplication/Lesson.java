package com.example.misha.myapplication;

public class Lesson {

    private String id;

    private String time;

    private String subjectEdit;

    private String audienceEdit;

    private String educator;

    private String typeLesson;

    public Lesson(String idcards, String timelesson, String subjectEdit, String audienceEdit, String educator, String typelesson) {
        this.id = idcards;
        this.time = timelesson;
        this.subjectEdit = subjectEdit;
        this.audienceEdit = audienceEdit;
        this.educator = educator;
        this.typeLesson = typelesson;
    }

    public String getId() {
        return id;
    }
    public String getTime() {
        return time;
    }
    public String getSubjectEdit() {
        return subjectEdit;
    }
    public String getAudienceEdit() {
        return audienceEdit;
    }
    public String getEducatorEdit() {
        return educator;
    }
    public String getTypeLesson() {
        return typeLesson;
    }

    public void setSubjectEdit(String subjectEdit) {
        this.subjectEdit = subjectEdit;
    }

    public void setAudienceEdit(String audienceEdit) {
        this.audienceEdit = audienceEdit;
    }

    public void setEducatorEdit(String educatorEdit) {
        this.educator = educatorEdit;
    }
}

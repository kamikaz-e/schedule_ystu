package com.example.misha.myapplication.model;

import com.example.misha.myapplication.database.entity.Lesson;

import java.util.List;

public class Day {

    private String name;

    private List<Lesson> lessons;

    public Day(String name, List<Lesson> lessons) {
        this.lessons = lessons;
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public String getName() {
        return name;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}

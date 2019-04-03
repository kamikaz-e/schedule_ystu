package com.example.misha.myapplication.model;

public class Audience {

    private long id;

    private String name;

    public Audience(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}

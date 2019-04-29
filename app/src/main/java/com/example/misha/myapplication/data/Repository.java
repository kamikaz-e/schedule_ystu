package com.example.misha.myapplication.data;

import com.example.misha.myapplication.data.network.RepositoryManager;

public class Repository implements RepositoryManager {

    private static volatile Repository instance;

    public static Repository getInstance() {
        if (instance != null) return instance;
        instance = new Repository();
        return instance;
    }

    private Repository() {

    }
}

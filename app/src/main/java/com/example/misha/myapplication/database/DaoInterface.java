package com.example.misha.myapplication.database;

import java.util.ArrayList;

public interface DaoInterface<T> {

    T getItemByID(long id);

    void insertItem(T item);

    void insertAll(ArrayList<T> items);

    void deleteAll();

    boolean deleteItemById(long id, String idKey);


}

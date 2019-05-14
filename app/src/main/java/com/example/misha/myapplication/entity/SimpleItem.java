package com.example.misha.myapplication.entity;

import android.os.Parcelable;

public interface SimpleItem extends Parcelable {

    String getId();

    String getName();

    void setId(String id);

    void setName(String name);
}

package com.example.misha.myapplication.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Educator implements Parcelable, SimpleItem {

    public static final Creator<Educator> CREATOR = new Creator<Educator>() {
        @Override
        public Educator createFromParcel(Parcel in) {
            return new Educator(in);
        }

        @Override
        public Educator[] newArray(int size) {
            return new Educator[size];
        }
    };
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("name_educator")
    private String name;

    public Educator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Educator(Cursor cursor) {
        this.id = cursor.getString(0);
        this.name = cursor.getString(1);
    }

    protected Educator(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public Educator() {

    }

    @Override
    public String toString() {
        return name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

}

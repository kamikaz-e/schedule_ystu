package com.example.misha.myapplication.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Audience implements Parcelable {

    private final long id;

    private final String name;

    public Audience(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Audience(Cursor cursor) {
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
    }


    protected Audience(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public static final Creator<Audience> CREATOR = new Creator<Audience>() {
        @Override
        public Audience createFromParcel(Parcel in) {
            return new Audience(in);
        }

        @Override
        public Audience[] newArray(int size) {
            return new Audience[size];
        }
    };

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }
}

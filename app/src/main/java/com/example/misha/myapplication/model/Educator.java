package com.example.misha.myapplication.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Educator implements Parcelable {

    private final long id;

    private final String name;

    public Educator(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Educator(Cursor cursor) {
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
    }

    protected Educator(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

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

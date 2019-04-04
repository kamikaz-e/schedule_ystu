package com.example.misha.myapplication.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


//Todo прочитать про сериализацию и Parcelable
public class Subject implements Parcelable {

    private final long id;

    private final String name;

    public Subject(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Subject(Cursor cursor) {
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
    }

    protected Subject(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
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

package com.example.misha.myapplication.database.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


//Todo прочитать про сериализацию и Parcelable
public class Subject implements Parcelable {

    private  String id;

    private  String name;

    public Subject(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public Subject(Cursor cursor) {
        this.id = cursor.getString(0);
        this.name = cursor.getString(1);
    }
    @Override
    public String toString() {
        return name;
    }
    protected Subject(Parcel in) {
        id = in.readString();
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

    public Subject() {
        
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    public void setId(String id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
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

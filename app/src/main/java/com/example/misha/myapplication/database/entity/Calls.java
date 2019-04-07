package com.example.misha.myapplication.database.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


//Todo прочитать про сериализацию и Parcelable
public class Calls implements Parcelable {

    private String id;
    private String name;


    public Calls(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public Calls(Cursor cursor) {
        this.id = cursor.getString(0);
        this.name = cursor.getString(1);
    }

    protected Calls(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<Calls> CREATOR = new Creator<Calls>() {
        @Override
        public Calls createFromParcel(Parcel in) {
            return new Calls(in);
        }

        @Override
        public Calls[] newArray(int size) {
            return new Calls[size];
        }
    };

    public Calls() {
        
    }

    @Override
    public String toString() {
        return name;
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

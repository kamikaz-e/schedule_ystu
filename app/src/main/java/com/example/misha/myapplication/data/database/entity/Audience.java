package com.example.misha.myapplication.data.database.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Audience implements Parcelable, SimpleItem {

    @Expose
    @SerializedName("id")
    private  String id;

    @Expose
    @SerializedName("audience")
    private  String name;

    public Audience(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Audience(Cursor cursor) {
        this.id = cursor.getString(0);
        this.name = cursor.getString(1);
    }


    protected Audience(Parcel in) {
        id = in.readString();
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

    public Audience() {

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

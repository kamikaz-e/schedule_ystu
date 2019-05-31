package com.example.misha.myapplication.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonsEducator implements Parcelable {


    public static final Creator<LessonsEducator> CREATOR = new Creator<LessonsEducator>() {
        @Override
        public LessonsEducator createFromParcel(Parcel in) {
            return new LessonsEducator(in);
        }

        @Override
        public LessonsEducator[] newArray(int size) {
            return new LessonsEducator[size];
        }
    };

    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("name_subject")
    private String name_subject;
    @Expose
    @SerializedName("name_audience")
    private String name_audience;
    @Expose
    @SerializedName("name_typelesson")
    private String name_typelesson;
    @Expose
    @SerializedName("number_lesson")
    private int number_lesson;


    public LessonsEducator() {

    }

    public LessonsEducator(Cursor cursor) {
        this.id = cursor.getString(0);
        this.name_subject = cursor.getString(1);
        this.name_audience = cursor.getString(2);
        this.name_typelesson = cursor.getString(3);
        this.number_lesson = cursor.getInt(4);
    }

    protected LessonsEducator(Parcel in) {
        id = in.readString();
        name_subject = in.readString();
        name_audience = in.readString();
        name_typelesson = in.readString();
        number_lesson = in.readInt();
    }

    public LessonsEducator(String name_subject, String name_audience, String name_typelesson, int number_lesson) {
        this.name_subject = String.valueOf(name_subject);
        this.name_audience = String.valueOf(name_audience);
        this.name_typelesson = String.valueOf(name_typelesson);
        this.number_lesson = number_lesson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public String getName_audience() {
        return name_audience;
    }

    public void setName_audience(String name_audience) {
        this.name_audience = name_audience;
    }

    public String getName_typelesson() {
        return name_typelesson;
    }

    public void setName_typelesson(String name_typelesson) {
        this.name_typelesson = name_typelesson;
    }

    public int getNumber_lesson() {
        return number_lesson;
    }

    public void setNumber_lesson(int number_lesson) {
        this.number_lesson = number_lesson;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name_subject);
        dest.writeString(name_audience);
        dest.writeString(name_typelesson);
        dest.writeInt(number_lesson);
    }
}

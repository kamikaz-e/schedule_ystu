package com.example.misha.myapplication.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request implements Parcelable {

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
    @Expose
    @SerializedName("name_group")
    private String group;
    @Expose
    @SerializedName("name_audience")
    private String audience;

    public Request(String group, String audience) {
        this.group = group;
        this.audience = audience;
    }

    public Request(Cursor cursor) {
        this.group = cursor.getString(0);
        this.audience = cursor.getString(1);
    }

    protected Request(Parcel in) {
        group = in.readString();
        audience = in.readString();
    }

    public Request() {

    }

    @Override
    public String toString() {
        return group;
    }


    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(group);
        dest.writeString(audience);
    }

}

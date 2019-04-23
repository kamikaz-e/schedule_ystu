package com.example.misha.myapplication.network;

import com.example.misha.myapplication.database.entity.Subject;
import com.example.misha.myapplication.network.request.InsertRequest;
import com.example.misha.myapplication.network.request.ScheduleRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("/export.php")
    Call<Throwable> insertData(@Body InsertRequest request);

    @POST("/subjects.php")
    Call<ArrayList<Subject>> getSubjects(@Body ScheduleRequest request);
}

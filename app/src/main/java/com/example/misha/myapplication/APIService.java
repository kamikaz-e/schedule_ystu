package com.example.misha.myapplication;

import com.example.misha.myapplication.database.entity.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @FormUrlEncoded
    @POST("/export.php")
    Call<Throwable> insertData(
            @Field("name_db") String get_name,
            @Field("subjects") String subjects,
            @Field("audiences") String audiences,
            @Field("educators") String educators,
            @Field("typelessons") String typelessons,
            @Field("calls") String calls,
            @Field("lessons") String lessons,
            @Field("date") String date);


    @GET("/subjects.php")
   Call<List<Subject>> getSubjects(String s);
           // @Path("subject") String subject);
}

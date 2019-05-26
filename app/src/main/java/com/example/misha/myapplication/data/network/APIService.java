package com.example.misha.myapplication.data.network;

import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.Subject;
import com.example.misha.myapplication.entity.Typelesson;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    /* @FormUrlEncoded
    @POST("export.php")
   Single<Throwable>  insertData(@Field("name_db") String nameGroup,
                                 @Field("subjects") String subjects,
                                 @Field("audiences") String audiences,
                                 @Field("educators") String educators,
                                 @Field("typelessons") String typelessons,
                                 @Field("calls") String calls,
                                 @Field("lessons") String lessons);*/


    @GET("getGroups.php")
    Single<ArrayList<Groups>> getGroups();

    @FormUrlEncoded
    @POST("getFreeAudiences.php")
    Single<ArrayList<Audience>> getFreeAudiences(@Field("week") String week, @Field("day") String day, @Field("lesson") String lesson );

    @FormUrlEncoded
    @POST("getSubjects.php")
    Single<ArrayList<Subject>> getSubjects(@Field("group") String name_group);

    @FormUrlEncoded
    @POST("getAudiences.php")
    Single<ArrayList<Audience>> getAudiences(@Field("group") String name_group);

    @FormUrlEncoded
    @POST("getEducators.php")
    Single<ArrayList<Educator>> getEducators(@Field("group") String name_group);

    @GET("getTypelessons.php")
    Single<ArrayList<Typelesson>> getTypelessons();

    @FormUrlEncoded
    @POST("getLessons.php")
    Single<ArrayList<Lesson>> getLessons(@Field("group") String name_group);
}

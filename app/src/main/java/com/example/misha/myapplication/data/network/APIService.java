package com.example.misha.myapplication.data.network;

import com.example.misha.myapplication.data.network.request.ScheduleRequest;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Subject;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.Body;
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

    @GET("getSubjects.php")
    Single<ArrayList<Subject>> getSubjects();

   /* @POST("audiences.php")
    Single<ArrayList<Audience>> getAudiences(@Body ScheduleRequest request);*/
}

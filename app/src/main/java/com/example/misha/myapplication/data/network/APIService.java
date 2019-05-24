package com.example.misha.myapplication.data.network;

import com.example.misha.myapplication.entity.Request;
import com.example.misha.myapplication.entity.Subject;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import retrofit2.http.GET;

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

    @GET("getGroups.php")
    Single<ArrayList<Request>> getGroups();

   /* @GET("getAudience.php")
    Single<ArrayList<Audience>> getAudiences();*/
}

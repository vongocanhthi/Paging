package com.vnat.pagingpractice.API;

import com.vnat.pagingpractice.Model.Photo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientService {
    @GET("list")
    Single<List<Photo>> getPhotoList(@Query("page") Integer page,
                                     @Query("limit") Integer limit);
}

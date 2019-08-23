package com.test.vaishnavi.testwz.repository.service;

import com.test.vaishnavi.testwz.repository.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetService {
    //Define the network request
    @Headers("Authorization:563492ad6f91700001000001414a159eaed14a5594dff71a233e1bd7")
    @GET("search")
    Call<Result> getPhotos(@Query("query") String query, @Query("per_page") int perpage, @Query("page") int page);
}

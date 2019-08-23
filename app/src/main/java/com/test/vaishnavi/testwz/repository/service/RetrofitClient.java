package com.test.vaishnavi.testwz.repository.service;

import android.arch.lifecycle.MutableLiveData;

import com.test.vaishnavi.testwz.repository.model.Photos;
import com.test.vaishnavi.testwz.repository.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    Photos photos;
    private static final String BASE_URL = "https://api.pexels.com/v1/";

    //create an instance of retrofit
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Make retrofit call
     * @param photosList
     * @param searchText
     */
    public void getPhotoList(final MutableLiveData<List<Photos>> photosList, String searchText) {
        final List<Photos> data = new ArrayList<>();
        GetService service = RetrofitClient.getRetrofitInstance().create(GetService.class);
        Call<Result> call = service.getPhotos(searchText,30,1);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                for(int i = 0; i<response.body().photos.size(); i++){
                    Photos photos = new Photos();
                    photos.photographer = response.body().photos.get(i).photographer;
                    photos.src = response.body().photos.get(i).src;
                    data.add(photos);
                    photosList.setValue(data);

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //photosList.setValue(null);
                t.printStackTrace();
            }
        });
    }
}

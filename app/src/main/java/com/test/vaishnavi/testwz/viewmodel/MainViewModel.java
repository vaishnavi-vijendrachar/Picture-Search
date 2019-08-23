package com.test.vaishnavi.testwz.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.test.vaishnavi.testwz.repository.model.Photos;
import com.test.vaishnavi.testwz.repository.service.RetrofitClient;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Photos>> photoList;

    public LiveData<List<Photos>> getDataList(String searchText) {

        if(photoList == null){
            photoList = new MutableLiveData<>();
        }

        new RetrofitClient().getPhotoList(photoList,searchText);
        return photoList;
    }

}

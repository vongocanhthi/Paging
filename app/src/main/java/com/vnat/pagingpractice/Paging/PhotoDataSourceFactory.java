package com.vnat.pagingpractice.Paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PhotoDataSourceFactory extends DataSource.Factory {
    private PhotoDataSource dataSource;
    private MutableLiveData<PhotoDataSource> dataSourceLiveData;

    public PhotoDataSourceFactory() {
        dataSourceLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSource = new PhotoDataSource();
        dataSourceLiveData.postValue(dataSource);
        return dataSource;
    }

    public LiveData<PhotoDataSource> getPhotoDataSourceLiveData() {
        return dataSourceLiveData;
    }
}
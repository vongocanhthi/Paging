package com.vnat.pagingpractice.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.vnat.pagingpractice.Model.Photo;
import com.vnat.pagingpractice.Paging.PhotoDataSource;
import com.vnat.pagingpractice.Paging.PhotoDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PhotoViewModel extends ViewModel {
    private LiveData<PagedList<Photo>> pagedListLiveData;
    private LiveData<PhotoDataSource> dataSourceLiveData;
    private Executor executor;

    public PhotoViewModel() {
        PhotoDataSourceFactory factory = new PhotoDataSourceFactory();
        dataSourceLiveData = factory.getPhotoDataSourceLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPrefetchDistance(10)
                .setPageSize(50)
                .build();

        executor = Executors.newFixedThreadPool(5);

        pagedListLiveData = (new LivePagedListBuilder<Integer, Photo>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Photo>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
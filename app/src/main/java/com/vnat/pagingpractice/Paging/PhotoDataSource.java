package com.vnat.pagingpractice.Paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.vnat.pagingpractice.API.ApiService;
import com.vnat.pagingpractice.API.ClientService;
import com.vnat.pagingpractice.Model.Photo;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PhotoDataSource extends PageKeyedDataSource<Integer, Photo> {
    private ClientService clientService;
    private int PAGE = 1;
    private int LIMIT = 50;

    public PhotoDataSource() {
        clientService = ApiService.getClientService();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Photo> callback) {
        clientService.getPhotoList(PAGE, LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Photo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Photo> photos) {
                        if (photos != null) {
                            callback.onResult(photos, null, PAGE + 1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
        clientService.getPhotoList(params.key, LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Photo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Photo> photos) {
                        if (photos != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(photos, key);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
        clientService.getPhotoList(params.key, LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Photo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Photo> photos) {
                        if (photos != null) {
                            callback.onResult(photos, params.key + 1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
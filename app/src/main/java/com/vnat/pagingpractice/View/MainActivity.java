package com.vnat.pagingpractice.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vnat.pagingpractice.Adapter.PhotoAdapter;
import com.vnat.pagingpractice.Model.Photo;
import com.vnat.pagingpractice.R;
import com.vnat.pagingpractice.ViewModel.PhotoViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rcvPhoto)
    RecyclerView rcvPhoto;
    PhotoAdapter photoAdapter;
    PhotoViewModel photoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);

        init();
        event();
    }

    private void event() {
        photoViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(PagedList<Photo> photos) {
                photoAdapter.submitList(photos);
            }
        });

        rcvPhoto.setAdapter(photoAdapter);
    }

    private void init() {
        rcvPhoto.setHasFixedSize(true);
        rcvPhoto.setLayoutManager(new LinearLayoutManager(this));
        photoAdapter = new PhotoAdapter(this);
    }
}
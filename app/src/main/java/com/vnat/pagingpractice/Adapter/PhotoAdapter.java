package com.vnat.pagingpractice.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vnat.pagingpractice.Model.Photo;
import com.vnat.pagingpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoAdapter extends PagedListAdapter<Photo, PhotoAdapter.PhotoViewHolder> {
    private Context context;

    public PhotoAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo obj = getItem(position);

        Glide.with(context)
                .load(obj.getDownloadUrl())
                .error(android.R.drawable.stat_notify_error)
                .into(holder.imgDownloadUrl);

        holder.txtAuthor.setText(obj.getAuthor());

    }

    public static final DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.equals(newItem);
        }
    };

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgDownloadUrl)
        ImageView imgDownloadUrl;

        @BindView(R.id.txtAuthor)
        TextView txtAuthor;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
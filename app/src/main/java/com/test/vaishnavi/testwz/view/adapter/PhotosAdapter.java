package com.test.vaishnavi.testwz.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.vaishnavi.testwz.R;
import com.test.vaishnavi.testwz.repository.model.Photos;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    List<Photos> photosList;
    Context context;
    int position;

    public PhotosAdapter(List<Photos> photosList, Context context){
        this.photosList = photosList;
        this.context =  context;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //set up the view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup,false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder photosViewHolder, int i) {
        position = i;
        //bind view to the data
        Photos photo = photosList.get(i);
        photosViewHolder.photographer.setText(photo.photographer);

        //using glide libary to load the images efficiently
        Glide.with(context)
                .load(photo.src.small)
                .centerCrop()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(photosViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    //create view holder
    class PhotosViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView photographer;
        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.thumb_nail);
            photographer = itemView.findViewById(R.id.photoragpher_name);
        }
    }
}

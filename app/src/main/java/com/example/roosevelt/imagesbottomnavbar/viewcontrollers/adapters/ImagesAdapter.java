package com.example.roosevelt.imagesbottomnavbar.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.example.roosevelt.imagesbottomnavbar.R;
import com.example.roosevelt.imagesbottomnavbar.models.Image;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>{

    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public ImagesAdapter setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public ImagesAdapter(List<Image> images) {
        this.images = images;
    }

    public ImagesAdapter() {
    }

    @NonNull
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ViewHolder holder, int position) {
        holder.updatesViews(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView imageImageView;
        private TextView tagTextView;

        public ViewHolder(View view) {
            super(view);
            imageImageView = (ANImageView) view.findViewById(R.id.image_picture);
            tagTextView = (TextView) view.findViewById(R.id.text_tag);
        }

        public void updatesViews(final Image image) {
            imageImageView.setDefaultImageResId(R.mipmap.ic_launcher);
            imageImageView.setErrorImageResId(R.mipmap.ic_launcher);
            imageImageView.setImageUrl(image.getUrl());
            tagTextView.setText(image.getTag());
        }
    }
}

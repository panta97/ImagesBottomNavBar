package com.example.roosevelt.imagesbottomnavbar.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.roosevelt.imagesbottomnavbar.R;
import com.example.roosevelt.imagesbottomnavbar.models.Image;
import com.example.roosevelt.imagesbottomnavbar.network.ImageApi;
import com.example.roosevelt.imagesbottomnavbar.viewcontrollers.adapters.ImagesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {
    private List<Image> images;
    private RecyclerView imagesRecyclerView;
    private RecyclerView.LayoutManager imagesLayoutManager;
    private ImagesAdapter imagesAdapter;


    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_images, container, false);
        images = new ArrayList<>();
        imagesRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_images);
        imagesAdapter = new ImagesAdapter(images);
        imagesLayoutManager = new GridLayoutManager(view.getContext(), 2);
        imagesRecyclerView.setAdapter(imagesAdapter);
        imagesRecyclerView.setLayoutManager(imagesLayoutManager);
        updateData();
        return view;
    }

    private void updateData() {
        AndroidNetworking
                .get(ImageApi.GetPhotos())
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        images = Image.Builder
                                .from(response)
                                .buildAll();

                        imagesAdapter.setImages(images);
                        imagesAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("Images", anError.getErrorDetail());
                    }
                });
    }

}

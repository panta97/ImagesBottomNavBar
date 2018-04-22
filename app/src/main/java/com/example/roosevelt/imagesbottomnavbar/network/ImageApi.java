package com.example.roosevelt.imagesbottomnavbar.network;

public class ImageApi {
    private static String BASE_URL = "http://104.236.115.201/api/photos";

    public static String GetPhotos() {
        return BASE_URL + "/";
    }

}

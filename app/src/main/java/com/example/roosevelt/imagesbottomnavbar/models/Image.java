package com.example.roosevelt.imagesbottomnavbar.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Image {
    private String tag;
    private String url;

    public Image(String tag, String url) {
        this.tag = tag;
        this.url = url;
    }

    public Image() {
    }

    public String getTag() {
        return tag;
    }

    public Image setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Image setUrl(String url) {
        this.url = url;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("tag", getTag());
        bundle.putString("url", getUrl());
        return bundle;
    }

    public static class Builder {
        private Image image;
        private List<Image> images;

        public Builder() {
            this.image = new Image();
            this.images = new ArrayList<>();
        }

        public Builder(Image image) {
            this.image = image;
        }

        public Builder(List<Image> images) {
            this.images = images;
        }

        public Image build() {
            return image;
        }

        public List<Image> buildAll() {
            return images;
        }

        // son tres from

        public static Builder from(Bundle bundle) {
            return new Builder(new Image(
                    bundle.getString("tag"),
                    bundle.getString("url")));
        }

        public static Builder from(JSONObject jsonImage) {
            try {
                return new Builder(new Image(
                        jsonImage.getString("tag"),
                        jsonImage.getString("url")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Builder from(JSONArray jsonImages) {
            int length = jsonImages.length();
            List<Image> images = new ArrayList<>();
            for(int i = 0; i < length; i++) {
                try {
                    images.add(Builder.from(jsonImages.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(images);
        }
    }


}

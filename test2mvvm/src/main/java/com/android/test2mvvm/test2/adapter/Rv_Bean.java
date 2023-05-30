package com.android.test2mvvm.test2.adapter;

public class Rv_Bean {
    String imageUrl;
    String description;
    String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Rv_Bean(String imageUrl, String description, String user_id) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.user_id = user_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Rv_Bean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

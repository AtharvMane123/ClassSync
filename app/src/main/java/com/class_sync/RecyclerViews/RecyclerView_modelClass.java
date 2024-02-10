package com.class_sync.RecyclerViews;

public class RecyclerView_modelClass {
    String name;
    String author;
    String rating;
    String Img;
    String audioUrl;
    String description;

    public RecyclerView_modelClass() {
    }

    public RecyclerView_modelClass(String name, String author, String rating, String img, String audioUrl, String description) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        Img = img;
        this.audioUrl = audioUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBkImgurl() {
        return Img;
    }

    public void setBkImgurl(String bkImgurl) {
        this.Img = bkImgurl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

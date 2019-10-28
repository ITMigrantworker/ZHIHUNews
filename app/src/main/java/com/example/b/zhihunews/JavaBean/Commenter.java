package com.example.b.zhihunews.JavaBean;

import android.graphics.Bitmap;

public class Commenter {

    private String name;
    private String context;
    private String image;
    private Bitmap bitmap;
    private String id;
    private int like;
    private String time;

    public Commenter(String name, String context, String image, String id, int like, String time) {

        this.image = image;
        this.context = context;
        this.id = id;
        this.name = name;
        this.like = like;
        this.time = time;
    }


    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getContext() {
        return context;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getLike() {
        return like;
    }

    public String getTime() {
        return time;
    }
}

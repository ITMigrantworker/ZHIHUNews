package com.example.b.zhihunews.JavaBean;

import android.graphics.Bitmap;

public class News {
    private String title;
    private String image;
    private String id;
    private Bitmap bitmap;
    private String body;
    private String date;

    public News(String title, String image, String id, String date) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.date = date;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getDate(){
        return date;
    }


}

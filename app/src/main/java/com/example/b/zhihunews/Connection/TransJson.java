package com.example.b.zhihunews.Connection;


import android.util.Log;

import com.example.b.zhihunews.JavaBean.Commenter;
import com.example.b.zhihunews.JavaBean.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransJson {


    public static String getBody(String text) {
        String context = "";
        try {
            JSONObject object = new JSONObject(text);
            context = object.getString("share_url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }

    //今天的新闻
    public static List transJson(String text) {
        List<News> list = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(text);
            String date = object.getString("date");
            //date 是日期。。。。

            String stories = object.getString("stories");

            JSONArray titles = new JSONArray(stories);

            for (int i = 0; i < titles.length(); i++) {

                JSONObject object1 = titles.getJSONObject(i);
                final String title = object1.getString("title");
                final String id = object1.getString("id");
                String images = object1.getString("images");

                JSONArray array = new JSONArray(images);
                String simage = array.get(0).toString();//若水三千 只取一瓢
                //对图片进行处理
                News news = new News(title, simage, id, date);
                list.add(news);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //加载过去的新闻
    public static List transJson(String text, List<News> list) {

        try {
            JSONObject object = new JSONObject(text);
            String date = object.getString("date");
            //date 是日期。。。。

            String stories = object.getString("stories");

            JSONArray titles = new JSONArray(stories);

            for (int i = 0; i < titles.length(); i++) {

                JSONObject object1 = titles.getJSONObject(i);
                final String title = object1.getString("title");
                final String id = object1.getString("id");
                String images = object1.getString("images");

                JSONArray array = new JSONArray(images);
                String simage = array.get(0).toString();//若水三千 只取一瓢
                //对图片进行处理
                News news = new News(title, simage, id, date);
                list.add(news);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List getTop(String text) {
        List<News> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(text);
            String top_stories = object.getString("top_stories");
            JSONArray array = new JSONArray(top_stories);

            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                String title = object1.getString("title");
                String image = object1.getString("image");
                String id = object1.getString("id");
                News news = new News(title, image, id, null);//头条新闻是当天日期
                list.add(news);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List getComment(String text) {

        List<Commenter> lists = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(text);

            String comments = object.getString("comments");

            JSONArray array = new JSONArray(comments);

            for (int i = 0; i < array.length(); i++) {

                JSONObject object1 = array.getJSONObject(i);

                String name = object1.getString("author");
                String id = object1.getString("id");
                String context = object1.getString("content");
                String image = object1.getString("avatar");
                int like = object1.getInt("likes");
                String time = object1.getString("time");

                lists.add(new Commenter(name, context, image, id, like, time));
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }


}

package com.example.b.zhihunews.File;

import android.util.Log;

import com.example.b.zhihunews.JavaBean.News;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class CacheFile {



    public static void CacheWrite(File files , List<News> list) throws IOException {

        if (!files.exists()){
            files.createNewFile();
        }

        File file = new File(files,"news.txt");

        FileOutputStream outputStream = new FileOutputStream(file);

        ObjectOutputStream out = new ObjectOutputStream(outputStream);

        out.writeObject(list);

        out.flush();
        out.close();

    }

    public static List<News> CacheRead(File files ) throws Exception {

        File file = new File(files,"news.txt");

        FileInputStream inputStream = new FileInputStream(file);

        ObjectInputStream in = new ObjectInputStream(inputStream);

        List<News> list = (List<News>) in.readObject();

        return list;

    }



}

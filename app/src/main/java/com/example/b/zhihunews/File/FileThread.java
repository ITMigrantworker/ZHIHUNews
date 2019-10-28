package com.example.b.zhihunews.File;

import android.util.Log;

import com.example.b.zhihunews.JavaBean.News;

import java.io.File;
import java.util.List;

public class FileThread extends Thread {

    private File file;
    private List<News> list;

    public FileThread(File file , List<News> list){
       this.file = file;
       this.list = list;
    }


    @Override
    public void run() {
        try {
            Log.w("2333","run方法执行了");
            CacheFile.CacheWrite(file, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

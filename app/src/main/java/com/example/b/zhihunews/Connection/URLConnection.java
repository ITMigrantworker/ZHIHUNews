package com.example.b.zhihunews.Connection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnection {

    public static interface CallBack {
        void onSuccess(Object text);

        void onFilure(int n);
    }

    public static void sentConnection_get(final String url_get, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(url_get);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    int n = conn.getResponseCode();
                    if (n == 200) {
                        InputStream in = conn.getInputStream();
                        callBack.onSuccess(getStringFromInputStream(in));



                    } else {

                        callBack.onFilure(n);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void bitmap(final String path, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    int n = conn.getResponseCode();
                    if (n == 200) {
                        InputStream in = conn.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        callBack.onSuccess(bitmap);




                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        os.close();
        return state;
    }
}

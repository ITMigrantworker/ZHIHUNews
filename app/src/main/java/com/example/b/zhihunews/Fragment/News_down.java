package com.example.b.zhihunews.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b.zhihunews.Activity.NewsBody;
import com.example.b.zhihunews.Adapter.RacAdapters;
import com.example.b.zhihunews.Connection.TransJson;
import com.example.b.zhihunews.Connection.URLConnection;
import com.example.b.zhihunews.JavaBean.News;
import com.example.b.zhihunews.R;

import java.util.List;

public class News_down extends Fragment {

    RecyclerView rv_name;
    List<News> list;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_down, container, false);
        return view;

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rv_name = view.findViewById(R.id.rv_name);
        context = view.getContext();

        getNewsTitle();
        setScrollListener();
//        getPastNews();

    }



    @RequiresApi(api = Build.VERSION_CODES.M)

    private void setScrollListener() {

        rv_name.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //如果不能够执行滑动
                if (!rv_name.canScrollVertically(0)) {

                    int size = list.size();
                    getPastNews(size);

                }

            }



        });

    }

    //发送网络请求 得到新闻title
    public void getNewsTitle() {
        URLConnection.sentConnection_get("https://news-at.zhihu.com/api/4/news/latest", new URLConnection.CallBack() {
            @Override
            public void onSuccess(Object text) {
                list = TransJson.transJson((String) text);

                for (int i = 0; i < list.size(); i++) {
                    final int finalI = i;
                    URLConnection.bitmap(list.get(i).getImage(), new URLConnection.CallBack() {
                        @Override
                        public void onSuccess(Object text) {

                            list.get(finalI).setBitmap((Bitmap) text);


                        }

                        @Override
                        public void onFilure(int n) {
                            //广播
                        }
                    });
                }

                Message msg = new Message();
                msg.obj = list;
                handler.sendMessage(msg);

            }

            @Override
            public void onFilure(int n) {

            }
        });
    }


    //获取消息内容与离线下载
    public void getPastNews(final int size) {
        final List<News> lists = list;
        //需要一个date处理。。。
        URLConnection.sentConnection_get("https://news-at.zhihu.com/api/4/news/before/" + list.get(list.size() - 1).getDate(), new URLConnection.CallBack() {
            @Override
            public void onSuccess(Object text) {
                final List<News> list = TransJson.transJson(text.toString(), lists);

                //重复工作 设置图片 影响性能 换一种架构
                for (int i = 0; i < list.size(); i++) {
                    final int finalI = i;
                    URLConnection.bitmap(list.get(i).getImage(), new URLConnection.CallBack() {
                        @Override
                        public void onSuccess(Object text) {
                            list.get(finalI).setBitmap((Bitmap) text);
                        }

                        @Override
                        public void onFilure(int n) {
                            //广播


                        }
                    });
                }
                Message msg = new Message();
                msg.obj = list;
                msg.what = size;
                handler.sendMessage(msg);

            }

            @Override
            public void onFilure(int n) {


            }
        });

    }

    //执行跳转到新闻内容界面
    Handler handlers = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(getView().getContext(), NewsBody.class);
            intent.putExtra("context", msg.obj + " ");
            startActivity(intent);
        }
    };

    //新闻标题界面
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            LinearLayoutManager manager = new LinearLayoutManager(context);
            rv_name.setLayoutManager(manager);

            manager.scrollToPosition(msg.what - 5);

            while (list.get(list.size() - 1).getBitmap() == null){

            }

            List<News> list = (List<News>) msg.obj;
            rv_name.setAdapter(new RacAdapters(list));

        }
    };
}

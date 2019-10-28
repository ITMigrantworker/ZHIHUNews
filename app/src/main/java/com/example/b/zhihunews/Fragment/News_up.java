package com.example.b.zhihunews.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b.zhihunews.Adapter.RacAdapter;
import com.example.b.zhihunews.Connection.TransJson;
import com.example.b.zhihunews.Connection.URLConnection;
import com.example.b.zhihunews.JavaBean.News;
import com.example.b.zhihunews.R;

import java.util.List;

public class News_up extends Fragment {

    RecyclerView rac_context;
    List<News> list;
    LinearLayoutManager manager;

    //标题界面
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            rac_context.setLayoutManager(manager);
            RacAdapter adapter = new RacAdapter(list);
            rac_context.setAdapter(adapter);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_up, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        URLConnection.sentConnection_get("https://news-at.zhihu.com/api/4/news/latest", new URLConnection.CallBack() {
            @Override
            public void onSuccess(Object text) {
                list = TransJson.getTop((String) text);
                //子线程里面 发一个消息
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
                rac_context = view.findViewById(R.id.rac_name);
                manager = new LinearLayoutManager(view.getContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);


                    SystemClock.sleep(350);//让第一个item加载出来
                    manager.scrollToPositionWithOffset(Integer.MAX_VALUE / 2, 0);
                    manager.setStackFromEnd(true);
                    handler.sendMessage(new Message());



            }

            @Override
            public void onFilure(int n) {

                //广播监听。。。。
            }
        });

    }
}

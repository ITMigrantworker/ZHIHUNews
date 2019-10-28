package com.example.b.zhihunews.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.b.zhihunews.Activity.MainActivity;
import com.example.b.zhihunews.Connection.TransJson;
import com.example.b.zhihunews.Connection.URLConnection;
import com.example.b.zhihunews.JavaBean.News;
import com.example.b.zhihunews.R;

import java.util.ArrayList;
import java.util.List;


public class Photo_down extends Fragment {

    TextView tv_name;
    ProgressBar bar;
    TextView tv_mention;
    ImageView iv_name;
    int value = 20;
    int num = 0;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.down, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {


        tv_name = view.findViewById(R.id.tv_name);
        bar = view.findViewById(R.id.pb_name);
        tv_mention = view.findViewById(R.id.tv_mention);
        iv_name = view.findViewById(R.id.iv_name);

        iv_name.setBackgroundResource(R.drawable.my_anim);
        AnimationDrawable animation = (AnimationDrawable) iv_name.getBackground();
        animation.start();


        context = view.getContext();
        bar.setMax(100);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (value < bar.getMax()) {
                    handler.sendMessage(new Message());
                    SystemClock.sleep(20);

                }
            }
        }).start();
        //根据气泡数量


    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            String text = "";
            value += 1;
            switch (num) {
                case 0:
                    text = "。";
                    num = 1;
                    break;
                case 1:
                    text = "。。";
                    num = 2;
                    break;
                case 2:
                    text = "。。。";
                    num = 3;
                    break;
                case 3:
                    text = " ";
                    num = 0;
                    break;
            }
            bar.setProgress(value);
            tv_name.setText(value + "%");
            tv_mention.setText(text);

            if (value >= bar.getMax()){
                Intent intent = new Intent(getView().getContext(),MainActivity.class);
                startActivity(intent);
            }

        }
    };

}

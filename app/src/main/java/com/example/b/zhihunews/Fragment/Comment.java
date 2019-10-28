package com.example.b.zhihunews.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.b.zhihunews.Adapter.CommentListAdapter;
import com.example.b.zhihunews.Connection.TransJson;
import com.example.b.zhihunews.Connection.URLConnection;
import com.example.b.zhihunews.JavaBean.Commenter;
import com.example.b.zhihunews.R;

import java.util.List;

public class Comment extends Fragment {

    ListView lv_comment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Intent intent = getActivity().getIntent();

        lv_comment = view.findViewById(R.id.lv_name);

        URLConnection.sentConnection_get("https://news-at.zhihu.com/api/4/story/" + intent.getStringExtra("id") + "/short-comments", new URLConnection.CallBack() {
            @Override
            public void onSuccess(Object text) {
                final List<Commenter> list = TransJson.getComment(text.toString());

                for (int i = 0; i < list.size(); i++) {

                    final int finalI = i;
                    URLConnection.bitmap(list.get(i).getImage(), new URLConnection.CallBack() {
                        @Override
                        public void onSuccess(Object text) {
                            list.get(finalI).setBitmap((Bitmap) text);
                            
                        }

                        @Override
                        public void onFilure(int n) {


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

        URLConnection.sentConnection_get("https://news-at.zhihu.com/api/4/story/" + intent.getStringExtra("id") + "/long-comments", new URLConnection.CallBack() {
            @Override
            public void onSuccess(Object text) {
                //
            }

            @Override
            public void onFilure(int n) {

            }
        });
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            List<Commenter> list = (List<Commenter>) msg.obj;
            lv_comment.setAdapter(new CommentListAdapter(list));
        }
    };
}

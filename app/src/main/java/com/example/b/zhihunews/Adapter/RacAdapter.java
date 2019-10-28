package com.example.b.zhihunews.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.b.zhihunews.Activity.NewsBody;
import com.example.b.zhihunews.Connection.TransJson;
import com.example.b.zhihunews.JavaBean.News;
import com.example.b.zhihunews.R;


import java.util.List;

public class RacAdapter extends RecyclerView.Adapter<RacAdapter.ViewHolder> {


    ViewHolder holder;
    List<News> list;

    public RacAdapter(List<News> list) {
        this.list = list;
    }

    @NonNull
    @Override
    //监听点击操作
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.title_item, viewGroup, false);
        holder = new ViewHolder(view);
        return holder;
    }

    //更新ui
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText(list.get(i % list.size()).getTitle());
        viewHolder.iv_name.setImageBitmap(list.get(i % list.size()).getBitmap());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String id = list.get(i % list.size()).getId();
                com.example.b.zhihunews.Connection.URLConnection.sentConnection_get("https://news-at.zhihu.com/api/4/news/" + id, new com.example.b.zhihunews.Connection.URLConnection.CallBack() {
                    @Override
                    public void onSuccess(Object text) {
                        Intent intent = new Intent(v.getContext(), NewsBody.class);
                        String context = TransJson.getBody(text.toString());
                        intent.putExtra("context", context);

                        intent.putExtra("id", id);
                        v.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFilure(int n) {

                    }
                });
            }
        });
    }

    //数量
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_name = itemView.findViewById(R.id.iv_name);

        }
    }
}

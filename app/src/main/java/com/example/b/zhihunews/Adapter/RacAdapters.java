package com.example.b.zhihunews.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b.zhihunews.Activity.NewsBody;
import com.example.b.zhihunews.Connection.TransJson;
import com.example.b.zhihunews.Connection.URLConnection;
import com.example.b.zhihunews.JavaBean.News;
import com.example.b.zhihunews.R;

import java.util.List;

public class RacAdapters extends RecyclerView.Adapter<RacAdapters.ViewHoder> {


    ViewHoder hoder;
    private List<News> list = null;

    public RacAdapters(List<News> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);

        hoder = new ViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, final int i) {
        viewHoder.tv_name.setText(list.get(i).getTitle());
        viewHoder.iv_name.setImageBitmap(list.get(i).getBitmap());
        viewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                URLConnection.sentConnection_get("https://news-at.zhihu.com/api/4/news/" + list.get(i).getId(), new URLConnection.CallBack() {
                    @Override
                    public void onSuccess(Object text) {
                        Intent intent = new Intent(v.getContext(), NewsBody.class);
                        String context = TransJson.getBody(text.toString());
                        intent.putExtra("context", context);
                        intent.putExtra("id", list.get(i).getId());
                        v.getContext().startActivity(intent);


                    }

                    @Override
                    public void onFilure(int n) {


                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ImageView iv_name;
        TextView tv_name;

        public ViewHoder(@NonNull View itemView) {

            super(itemView);
            iv_name = itemView.findViewById(R.id.iv_name);
            tv_name = itemView.findViewById(R.id.tv_title);


        }
    }
}

package com.example.b.zhihunews.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b.zhihunews.JavaBean.News;
import com.example.b.zhihunews.R;

import java.util.List;


public class ListAdapter extends BaseAdapter {

    //listview如果在fragment里面 是需要传入上下文的
    Context context;
    List<News> list = null;

    public ListAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    @Override

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.news_item, null);
        } else {
            view = convertView;
        }
        TextView tv_title = view.findViewById(R.id.tv_title);
        ImageView iv_name = view.findViewById(R.id.iv_name);

        tv_title.setText(list.get(position).getTitle());
        iv_name.setImageBitmap(list.get(position).getBitmap());
        return view;
    }
}

package com.example.b.zhihunews.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b.zhihunews.JavaBean.Commenter;
import com.example.b.zhihunews.R;

import java.util.List;

public class CommentListAdapter extends BaseAdapter {

    List<Commenter> list;

    public CommentListAdapter(List<Commenter> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.w("2334", list.size() + " ");
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
            view = View.inflate(parent.getContext(), R.layout.comment_item, null);

        } else {
            view = convertView;


        }

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_context = view.findViewById(R.id.tv_context);
        ImageView iv_name = view.findViewById(R.id.iv_name);

        tv_context.setText(list.get(position).getContext());
        tv_name.setText(list.get(position).getName());
        iv_name.setImageBitmap(list.get(position).getBitmap());

        return view;
    }
}

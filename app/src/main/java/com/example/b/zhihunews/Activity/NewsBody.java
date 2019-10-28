package com.example.b.zhihunews.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.TableLayout;

import com.example.b.zhihunews.Adapter.PagerAdapter;
import com.example.b.zhihunews.Adapter.TabFragmentAdapter;
import com.example.b.zhihunews.Fragment.Comment;
import com.example.b.zhihunews.Fragment.Context;
import com.example.b.zhihunews.R;

public class NewsBody extends AppCompatActivity {


    private String[] titles = {"新闻", "评论"};

    private Fragment[] fragments = {new Context(), new Comment()};

    private TableLayout tabs;

    private ViewPager vp_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_body);

        Intent intent = getIntent();

        tabs = (TableLayout) findViewById(R.id.tabs);
        vp_name = (ViewPager) findViewById(R.id.vp_name);


        vp_name.setAdapter(new PagerAdapter(getSupportFragmentManager(),titles,fragments));


//        wv_name = (WebView)findViewById(R.id.wv_name);
//
//        wv_name.loadUrl(intent.getStringExtra("context"));

    }

}


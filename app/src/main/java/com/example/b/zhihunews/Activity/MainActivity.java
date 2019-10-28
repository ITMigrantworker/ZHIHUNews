package com.example.b.zhihunews.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.b.zhihunews.R;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "确定退出该程序？", Toast.LENGTH_SHORT).show();
    }
}

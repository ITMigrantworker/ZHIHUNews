package com.example.b.zhihunews.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private String titles[];
    private Fragment fragments[];

    public PagerAdapter(FragmentManager fm, String titles[], Fragment fragments[]) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;

    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {

        return titles.length;

    }
}

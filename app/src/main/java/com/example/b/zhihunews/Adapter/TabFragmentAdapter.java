package com.example.b.zhihunews.Adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class TabFragmentAdapter extends FragmentPagerAdapter {

    private String title[] = null;
    private Fragment fragment[] = null;

    public TabFragmentAdapter(FragmentManager fm, String title[], Fragment fragment[]) {
        super(fm);
        this.title = title;
        this.fragment = fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position & title.length];
    }

    @Override
    public Fragment getItem(int i) {
        return fragment[i % fragment.length];
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}

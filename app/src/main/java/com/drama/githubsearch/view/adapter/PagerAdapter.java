package com.drama.githubsearch.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.drama.githubsearch.view.fragment.GitFragment;
import com.drama.githubsearch.view.fragment.LocalFragment;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String tabTitles[] = new String[]{"GIT", "LOCAL"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new GitFragment());
        fragments.add(new LocalFragment());
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
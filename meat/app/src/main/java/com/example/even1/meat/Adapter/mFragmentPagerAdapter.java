package com.example.even1.meat.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Even1 on 2017/11/11.
 */

public class mFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragment;
    private List<String> titlelist;

    public mFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragment,List<String> titlelist) {
        super(fm);
        this.fragment = fragment;
        this.titlelist = titlelist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

}

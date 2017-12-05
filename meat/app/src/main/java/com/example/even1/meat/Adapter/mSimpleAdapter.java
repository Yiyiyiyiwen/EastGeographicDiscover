package com.example.even1.meat.Adapter;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.even1.meat.R;
import com.example.even1.meat.fragment.mSimpleFragment;

public class mSimpleAdapter extends FragmentPagerAdapter {

    // 展示信息
    private static final Section[] SECTIONS = {
            new Section("经常", R.mipmap.meet1),
            new Section("有时",  R.mipmap.meet2),
            new Section("偶尔",  R.mipmap.meet3)
    };

    // 默认构造器
    public mSimpleAdapter(FragmentManager fm) {
        super(fm);
    }

    // 根据不同位置（position），显示不同的Fragment
    @Override
    public Fragment getItem(int position) {
        return mSimpleFragment.newInstance(position);
    }

    // 子页面Fragment的个数
    @Override
    public int getCount() {
        return SECTIONS.length;
    }

    // 每个页面的标题，当ToolBar联动时，即为Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        if (position >= 0 && position < SECTIONS.length) {
            return SECTIONS[position].getTitle();
        }
        return null;
    }

    // 图片接口
    public @DrawableRes
    int getDrawable(int position) {
        if (position >= 0 && position < SECTIONS.length) {
            return SECTIONS[position].getDrawable();
        }
        return -1;
    }

    // 存储类
    private static final class Section {
        private final String mTitle; // 标题
        private final @DrawableRes
        int mDrawable; // 图片

        public Section(String title, int drawable) {
            mTitle = title;
            mDrawable = drawable;
        }

        public String getTitle() {
            return mTitle;
        }

        public int getDrawable() {
            return mDrawable;
        }
    }
}

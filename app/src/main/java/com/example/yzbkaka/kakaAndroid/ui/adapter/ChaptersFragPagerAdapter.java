package com.example.yzbkaka.kakaAndroid.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yzbkaka.kakaAndroid.bean.Chapter;
import com.example.yzbkaka.kakaAndroid.ui.chapter.ChapterListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ChaptersFragPagerAdapter extends FragmentPagerAdapter {

    private List<Chapter> chapterList = new ArrayList<>();


    public ChaptersFragPagerAdapter(FragmentManager fm,List<Chapter> chapterList) {
        super(fm);
        this.chapterList = chapterList;
    }


    @Override
    public Fragment getItem(int position) {
        return ChapterListFragment.instantiate(chapterList.get(position).getId());
    }


    @Override
    public int getCount() {
        return chapterList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return chapterList.get(position).getName();
    }
}

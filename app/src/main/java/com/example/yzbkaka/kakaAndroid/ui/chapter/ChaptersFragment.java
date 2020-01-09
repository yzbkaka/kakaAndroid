package com.example.yzbkaka.kakaAndroid.ui.chapter;

/**
 * Created by yzbkaka on 20-1-9.
 */


import android.os.Bundle;

import com.example.yzbkaka.kakaAndroid.bean.Chapter;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;
import com.example.yzbkaka.kakaAndroid.ui.adapter.ChaptersFragPagerAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 公众号
 */
public class ChaptersFragment extends BaseTabFragment<ChaptersPresenter> implements ChapterContract.IChaptersView {

    private List<Chapter> chapterList = new ArrayList<>();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getChapters();
    }


    @Override
    protected ChaptersPresenter createPresenter(){
        return new ChaptersPresenter();
    }


    @Override
    public void setData(List<Chapter> data) {
        chapterList.clear();
        chapterList.addAll(data);
    }


    @Override
    public List<Chapter> getData() {
        return chapterList;
    }


    @Override
    public void showContent() {
        ChaptersFragPagerAdapter adapter = new ChaptersFragPagerAdapter(getChildFragmentManager(),chapterList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(chapterList.size());
        tabLayout.setupWithViewPager(viewPager);
    }


    public void scrollToTop(){
        int id = chapterList.get(viewPager.getCurrentItem()).getId();
        RxEvent.getInstance().postEvent(Const.EVENT_ACTION.CHAPTER_LIST,new Event(Event.Type.SCROLL_TOP,id));
    }

}

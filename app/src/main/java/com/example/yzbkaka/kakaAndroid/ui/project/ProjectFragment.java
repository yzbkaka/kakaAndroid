package com.example.yzbkaka.kakaAndroid.ui.project;

import android.os.Bundle;

import com.example.yzbkaka.kakaAndroid.bean.ProjectCate;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;
import com.example.yzbkaka.kakaAndroid.ui.adapter.ProjectFragPagerAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ProjectFragment extends BaseTabFragment<ProjectCatePresenter> implements ProjectCateContract.IProjectCateView {

    private List<ProjectCate> cateList = new ArrayList<>();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getProjectCate();
    }


    @Override
    protected ProjectCatePresenter createPresenter() {
        return new ProjectCatePresenter();
    }


    @Override
    public void setData(List<ProjectCate> data) {
        cateList.clear();
        cateList.addAll(data);
    }


    @Override
    public List<ProjectCate> getData() {
        return cateList;
    }


    @Override
    public void showContent() {
        ProjectFragPagerAdapter adapter = new ProjectFragPagerAdapter(getChildFragmentManager(),cateList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(cateList.size());
        tabLayout.setupWithViewPager(viewPager);
    }


    public void scrollToTop(){
        int id = cateList.get(viewPager.getCurrentItem()).getId();
        RxEvent.getInstance().postEvent(Const.EVENT_ACTION.PROJECT_LIST,new Event(Event.Type.SCROLL_TOP,id));
    }
}

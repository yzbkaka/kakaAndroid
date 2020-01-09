package com.example.yzbkaka.kakaAndroid.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yzbkaka.kakaAndroid.bean.ProjectCate;
import com.example.yzbkaka.kakaAndroid.ui.project.ProjectListFragment;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ProjectFragPagerAdapter extends FragmentPagerAdapter {

    private List<ProjectCate> projectCateList;


    public ProjectFragPagerAdapter(FragmentManager fm,List<ProjectCate> projectCateList) {
        super(fm);
        this.projectCateList = projectCateList;
    }


    @Override
    public Fragment getItem(int position) {
        return ProjectListFragment.instantiate(projectCateList.get(position).getId());
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return projectCateList.get(position).getName();
    }


    @Override
    public int getCount() {
        return projectCateList == null ? 0:projectCateList.size();
    }
}

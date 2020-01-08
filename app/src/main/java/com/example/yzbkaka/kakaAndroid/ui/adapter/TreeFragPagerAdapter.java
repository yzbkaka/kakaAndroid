package com.example.yzbkaka.kakaAndroid.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.ui.tree.TreeListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzbkaka on 20-1-8.
 */


/**
 * tab适配器
 */
public class TreeFragPagerAdapter extends FragmentPagerAdapter {

    private List<Tree.ChildrenBean> mTreeDatas = new ArrayList<>();


    public TreeFragPagerAdapter(FragmentManager fragmentManager,List<Tree.ChildrenBean> mTreeDatas) {
        super(fragmentManager);
        this.mTreeDatas = mTreeDatas;
    }


    @Override
    public Fragment getItem(int position) {
        return TreeListFragment.instantiate(mTreeDatas.get(position).getId());
    }


    @Override
    public int getCount() {
        return mTreeDatas.size();
    }


    @Override
    public CharSequence getPageTitle(int position){
        return mTreeDatas.get(position).getName();
    }
}

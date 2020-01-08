package com.example.yzbkaka.kakaAndroid.ui.base;

/**
 * Created by yzbkaka on 20-1-8.
 */


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.yzbkaka.kakaAndroid.R;

/**
 * 基础选项卡
 */
public abstract class BaseTabActivity extends BaseActivity {

    private TabLayout tabLayout;

    protected ViewPager viewPager;

    protected FloatingActionButton btn_scroll_top;


    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        FragmentPagerAdapter fragmentPagerAdapter = createFragmentPagerAdapter();
        if(fragmentPagerAdapter != null){
            viewPager.setAdapter(fragmentPagerAdapter);  //设置适配器
            tabLayout.setupWithViewPager(viewPager);  //设置pager
        }
    }


    @Override
    protected int getLayoutId(){
        return R.layout.activity_tree;
    }


    @Override
    protected void initViews(){
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        btn_scroll_top = (FloatingActionButton)findViewById(R.id.btn_scroll_top);
    }


    protected abstract FragmentPagerAdapter createFragmentPagerAdapter();
}

package com.example.yzbkaka.kakaAndroid.ui.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;

/**
 * Created by yzbkaka on 20-1-9.
 */


/**
 * 碎片选项卡
 */
public abstract class BaseTabFragment<P extends BasePresenter> extends BasePresenterFragment<P> {

    protected TabLayout tabLayout;

    protected ViewPager viewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.base_tab_layout;
    }


    @Override
    protected void initViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
    }
}

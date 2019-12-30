package com.example.yzbkaka.kakaAndroid.ui.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.widget.BaseAdapter;

import com.example.yzbkaka.kakaAndroid.bean.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzbkaka on 19-12-30.
 */

public class BannerAdapter extends PagerAdapter {

    private List<Banner> mBannerDatas = new ArrayList<>();

    private SparseArray<View> mViews;


    public BannerAdapter(List<Banner> mBannerDatas){
        this.mBannerDatas = mBannerDatas;
        mViews = new SparseArray<>();
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}

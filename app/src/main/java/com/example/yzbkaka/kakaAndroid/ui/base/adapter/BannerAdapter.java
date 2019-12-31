package com.example.yzbkaka.kakaAndroid.ui.base.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.Banner;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.manager.ImageLoaderManager;
import com.example.yzbkaka.kakaAndroid.web.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzbkaka on 19-12-30.
 */

/**
 * banner适配器
 */
public class BannerAdapter extends PagerAdapter {

    private List<Banner> mBannerDatas = new ArrayList<>();

    /**
     * 性能优化，代替HashMap
     */
    private SparseArray<View> mViews;


    public BannerAdapter(List<Banner> mBannerDatas){
        this.mBannerDatas = mBannerDatas;
        mViews = new SparseArray<>();
    }


    public void notifyDatas(List<Banner> mBannerDatas){
        this.mBannerDatas = mBannerDatas;
        notifyDataSetChanged();  //PageAdapter自带方法
    }


    @Override
    public int getCount() {
        if (mBannerDatas == null) return 0;
        return mBannerDatas.size() <= 1 ? mBannerDatas.size() : Integer.MAX_VALUE;
    }


    /**
     * 分屏预加载
     */
    @Override
    public Object instantiateItem(final ViewGroup container,int position){
        View view = mViews.get(position);
        if(view == null){
            position = position % mBannerDatas.size();  //进行循环
            final Banner bean = mBannerDatas.get(position);
            view = LayoutInflater.from(AppContext.getContext()).inflate(R.layout.item_banner,container,false);
            ImageView imageView = view.findViewById(R.id.img);
            TextView titleView = view.findViewById(R.id.title);
            ImageLoaderManager.displayImage(bean.getImagePath(), imageView, Const.IMAGE_LOADER.NOMAL_IMG);  //显示图片
            titleView.setText(bean.getTitle());  //显示标题
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(container.getContext(),WebViewActivity.class);
                    Article mArticle = new Article();
                    mArticle.setTitle(bean.getTitle());
                    mArticle.setLink(bean.getUrl());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Const.BUNDLE_KEY.OBJ,mArticle);  //存储序列化对象
                    intent.putExtras(bundle);  //进行传递
                    container.getContext().startActivity(intent);
                }
            });
            mViews.put(position,view);  //存入数组
        }
        container.addView(view);
        return view;
    }


    /**
     * 移除分屏
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


    @Override
    public int getItemPosition(Object object){
        mViews.clear();
        return POSITION_NONE;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

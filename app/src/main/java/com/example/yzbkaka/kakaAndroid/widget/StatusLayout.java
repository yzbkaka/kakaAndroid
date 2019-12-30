package com.example.yzbkaka.kakaAndroid.widget;

/**
 * Created by yzbkaka on 19-12-30.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.yzbkaka.kakaAndroid.R;

/**
 * 加载各种状态的布局
 */
public class StatusLayout extends FrameLayout {

    private View mLoadingView;

    private View mErrorView;

    private View mEmptyView;

    private View mContentView;


    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        mLoadingView = inflater.inflate(R.layout.loading_layout, this, false);
        mErrorView = inflater.inflate(R.layout.error_layout, this, false);
        mEmptyView = inflater.inflate(R.layout.empty_layout, this, false);
        addView(mLoadingView);
        addView(mErrorView);
        addView(mEmptyView);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
    }


    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom){
        super.onLayout(changed,left,top,right,bottom);
        if(changed){
            int count = getChildCount();
            mContentView = getChildAt(count-1);
        }
    }


    public void showLoding() {
        mLoadingView.setVisibility(VISIBLE);
        mErrorView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        if (mContentView != null) mContentView.setVisibility(GONE);
    }


    public void showError(){
        mErrorView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        if (mContentView != null) mContentView.setVisibility(GONE);
    }


    public void showEmpty(){
        mEmptyView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        if (mContentView != null) mContentView.setVisibility(GONE);
    }


    public void showContent() {
        if (mContentView != null) mContentView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
    }
}
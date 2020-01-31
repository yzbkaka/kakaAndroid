package com.example.yzbkaka.kakaAndroid.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.Scroller;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by yzbkaka on 19-12-30.
 */

/**
 * 自定义Banner组
 */
public class BannerViewPager extends ViewPager {

    private Handler mHandler;

    private TaskRunnable mTaskRunnable;

    private BannerViewPager instance;

    public static boolean mIsRunning = false;


    private static class TaskRunnable implements Runnable{

        private WeakReference<BannerViewPager> weakReference;

        TaskRunnable(BannerViewPager bannerViewPager) {
            this.weakReference = new WeakReference<>(bannerViewPager);
        }

        @Override
        public void run() {  //进行切换任务
            BannerViewPager instance = weakReference.get();
            if (instance == null) return;
            instance.setCurrentItem();
        }
    }


    public BannerViewPager(Context context) {
        super(context);
    }


    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        instance = this;
        OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {  //当产生滑动
                switch (state){
                    case ViewPager.SCROLL_STATE_IDLE:  //空闲状态
                        start();
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:  //手指触摸滑动
                        stop();
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:  //手指松开，惯性滑动
                        break;
                }
            }
        };
        addOnPageChangeListener(onPageChangeListener);
        setViewPagerDuration();  //设置pager的滚动时间间隔
    }


    /**
     * 开启任务
     */
    private void startTimingTask(){
        if (mHandler == null && !mIsRunning) {
            mHandler = new Handler();
            mTaskRunnable = new TaskRunnable(instance);
            mHandler.postDelayed(mTaskRunnable, 6000);  //设置时间
            mIsRunning = true;
        }
    }


    /**
     * 停止任务
     */
    private void stopTimingTask(){
        if (mHandler != null && mIsRunning) {
            mHandler.removeCallbacks(mTaskRunnable);
            mHandler = null;
            mIsRunning = false;
        }
    }


    /**
     * 设置切换
     */
    private void setCurrentItem(){
        setCurrentItem(getCurrentItem() + 1, true);
        mHandler.postDelayed(mTaskRunnable, 6000);
    }


    public void start(){
        this.startTimingTask();
    }


    public void stop(){
        this.stopTimingTask();
    }


    private class FixedSpeedScroll extends Scroller {
        /**
         * 毫秒
         */
        private int mDuration = 750;

        private FixedSpeedScroll(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);

        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy);
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setFirstLayout(false);
        start();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }


    private void setFirstLayout(boolean isFirstLayout) {
        try {
            Class<?> clazz = Class.forName("android.support.v4.view.ViewPager");  //反射
            Field field = clazz.getDeclaredField("mFirstLayout");
            field.setAccessible(true);
            field.setBoolean(this, isFirstLayout);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改ViewPager滑动速度
     */
    private void setViewPagerDuration() {
        try {
            Class<?> clazz = Class.forName("android.support.v4.view.ViewPager");
            FixedSpeedScroll mScriller = new FixedSpeedScroll(getContext());
            Field field = clazz.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(this, mScriller);  //将ViewPager中的mScroller设置为mScriller
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

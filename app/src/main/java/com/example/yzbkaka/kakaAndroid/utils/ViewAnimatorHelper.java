package com.example.yzbkaka.kakaAndroid.utils;

import android.animation.Animator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;

/**
 * Created by yzbkaka on 19-12-29.
 */


/**
 * 设置FloatActionBar的显示
 */
public class ViewAnimatorHelper {

    private boolean isAnimating;

    private ViewPropertyAnimator viewPropertyAnimator;

    private View view;


    public void bindView(View view){
        if(view == null){
            throw new NullPointerException("The view is cannot null");
        }
        this.view = view;
        if (viewPropertyAnimator == null){  //设置多个view动画
            viewPropertyAnimator = view.animate();
            viewPropertyAnimator.setDuration(300);
            viewPropertyAnimator.setInterpolator(new LinearOutSlowInInterpolator());  //设置插值器
        }
    }


    public boolean isAnimating(){
        return this.isAnimating;
    }


    public void hideFloatActionButton(){
        viewPropertyAnimator.scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setListener((Animator.AnimatorListener) animationListener);
    }


    public void showFloatActionButton(){
        view.setVisibility(View.VISIBLE);
        viewPropertyAnimator.scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setListener(null);
    }


    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            isAnimating = true;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            isAnimating = false;
            view.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {}
    };
}

package com.example.yzbkaka.kakaAndroid.core.view;

/**
 * Created by yzbkaka on 19-12-29.
 */

public interface IView {

    /**
     * 显示进度条
     */
    void showLoading(String msg);

    /**
     * 隐藏进度条
     */
    void hideLoading();

    /**
     * 失败
     */
    void showFail(String msg);

    /**
     * 错误
     */
    void showError();

    /**
     * 没有数据
     */
    void showEmpty();
}

package com.example.yzbkaka.kakaAndroid.core.view;

/**
 * Created by yzbkaka on 19-12-29.
 */

public interface IPageLoadDataView<T> extends IListDataView<T>{

    int getFirstPage();

    int getPage();

    /**
     * 自动加载
     */
    void autoLoadMore();

    /**
     * 清空数据
     */
    void clearListData();

    /**
     * 没有更多数据
     */
    void showNoMore();
}
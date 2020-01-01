package com.example.yzbkaka.kakaAndroid.core.model;

/**
 * Created by yzbkaka on 20-1-1.
 */

import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

/**
 * 通用业务接口
 */
public interface ICommonModel {

    /**
     * 收藏文章
     */
    void collectArticle(int id, RxObserver<String> callback);

    /**
     * 取消收藏文章
     */
    void unCollectArticle(int id, RxObserver<String> callback);
}

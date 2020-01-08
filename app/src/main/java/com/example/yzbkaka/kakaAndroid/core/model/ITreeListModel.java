package com.example.yzbkaka.kakaAndroid.core.model;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

/**
 * Created by yzbkaka on 20-1-8.
 */


/**
 * 知识体系文章业务
 */
public interface ITreeListModel {

    /**
     * 获取知识体系具体文章
     */
    void getTreeList(int page, int cid, RxPageListObserver<Article> rxObserver);
}

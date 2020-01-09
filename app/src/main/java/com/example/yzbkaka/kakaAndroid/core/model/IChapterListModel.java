package com.example.yzbkaka.kakaAndroid.core.model;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface IChapterListModel {

    /**
     * 获取文章列表
     */
    void getChapterArticleList(int page, int cid, RxPageListObserver<Article> rxPageListObserver);
}

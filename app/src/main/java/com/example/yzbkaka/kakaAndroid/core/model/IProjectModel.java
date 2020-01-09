package com.example.yzbkaka.kakaAndroid.core.model;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface IProjectModel {

    /**
     * 获取项目列表
     */
    void getProjectList(int page, int cid, RxPageListObserver<Article> rxPageListObserver);
}

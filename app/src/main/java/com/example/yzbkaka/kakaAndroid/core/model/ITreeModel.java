package com.example.yzbkaka.kakaAndroid.core.model;

/**
 * Created by yzbkaka on 20-1-8.
 */


import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * 知识体系业务接口
 */
public interface ITreeModel {

    /**
     * 获取知识体系
     */
    void getTree(RxObserver<List<Tree>> callback);
}

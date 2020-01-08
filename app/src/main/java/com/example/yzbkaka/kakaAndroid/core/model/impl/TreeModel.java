package com.example.yzbkaka.kakaAndroid.core.model.impl;

/**
 * Created by yzbkaka on 20-1-8.
 */

import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.core.model.ITreeModel;
import com.example.yzbkaka.kakaAndroid.net.RxSchedulers;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * 知识体系model（业务类）
 */
public class TreeModel extends BaseModel implements ITreeModel {

    @Override
    public void getTree(RxObserver<List<Tree>> callback) {
        doRxRequest()
                .getTree()
                .compose(RxSchedulers.<List<Tree>>io_main())
                .subscribe(callback);
    }
}

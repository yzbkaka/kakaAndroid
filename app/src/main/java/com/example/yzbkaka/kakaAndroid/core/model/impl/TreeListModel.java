package com.example.yzbkaka.kakaAndroid.core.model.impl;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.PageListData;
import com.example.yzbkaka.kakaAndroid.core.model.ITreeListModel;
import com.example.yzbkaka.kakaAndroid.net.RxSchedulers;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

/**
 * Created by yzbkaka on 20-1-8.
 */

public class TreeListModel extends CommonModel implements ITreeListModel {


    @Override
    public void getTreeList(int page, int cid, RxPageListObserver<Article> rxObserver) {
        doRxRequest()
                .getTreeList(page, cid)
                .compose(RxSchedulers.<PageListData<Article>>io_main())
                .subscribe(rxObserver);
    }
}

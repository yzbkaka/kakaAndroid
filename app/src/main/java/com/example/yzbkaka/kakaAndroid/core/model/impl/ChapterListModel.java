package com.example.yzbkaka.kakaAndroid.core.model.impl;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.PageListData;
import com.example.yzbkaka.kakaAndroid.core.model.IChapterListModel;
import com.example.yzbkaka.kakaAndroid.net.RxSchedulers;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ChapterListModel extends CommonModel implements IChapterListModel {

    @Override
    public void getChapterArticleList(int page, int cid, RxPageListObserver<Article> rxPageListObserver) {
        doRxRequest()
                .getChapterList(page,cid)
                .compose(RxSchedulers.<PageListData<Article>>io_main())
                .subscribe(rxPageListObserver);
    }
}

package com.example.yzbkaka.kakaAndroid.core.model.impl;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.PageListData;
import com.example.yzbkaka.kakaAndroid.core.model.IProjectModel;
import com.example.yzbkaka.kakaAndroid.net.RxSchedulers;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ProjectModel extends CommonModel implements IProjectModel {

    @Override
    public void getProjectList(int page, int cid, RxPageListObserver<Article> rxPageListObserver) {
        doRxRequest()
                .getProjectList(page,cid)
                .compose(RxSchedulers.<PageListData<Article>>io_main())
                .subscribe(rxPageListObserver);
    }
}

package com.example.yzbkaka.kakaAndroid.core.model.impl;

import com.example.yzbkaka.kakaAndroid.core.model.ICommonModel;
import com.example.yzbkaka.kakaAndroid.net.RxSchedulers;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

/**
 * Created by yzbkaka on 20-1-1.
 */


/**
 * Rx
 */
public class CommonModel extends BaseModel implements ICommonModel {

    /**
     * 收藏
     */
    @Override
    public void collectArticle(int id, RxObserver<String> callback) {
        doRxRequest().
                collectArticle(id)
                .compose(RxSchedulers.<String>io_main())
                .subscribe(callback);

    }


    /**
     * 取消收藏
     */
    @Override
    public void unCollectArticle(int id, RxObserver<String> callback) {
        doRxRequest()
                .unCollectArticle(id)
                .compose(RxSchedulers.<String>io_main())
                .subscribe(callback);
    }
}

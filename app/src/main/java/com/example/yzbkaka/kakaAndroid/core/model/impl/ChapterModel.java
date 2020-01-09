package com.example.yzbkaka.kakaAndroid.core.model.impl;

/**
 * Created by yzbkaka on 20-1-9.
 */


import com.example.yzbkaka.kakaAndroid.bean.Chapter;
import com.example.yzbkaka.kakaAndroid.core.model.IChapterModel;
import com.example.yzbkaka.kakaAndroid.net.RxSchedulers;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * 公众号业务类
 */
public class ChapterModel extends BaseModel implements IChapterModel {

    @Override
    public void getChapters(RxObserver<List<Chapter>> rxObserver) {
        doRxRequest()
                .getChapters()
                .compose(RxSchedulers.<List<Chapter>>io_main())
                .subscribe(rxObserver);
    }
}

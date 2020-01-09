package com.example.yzbkaka.kakaAndroid.core.model;

import com.example.yzbkaka.kakaAndroid.bean.Chapter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface IChapterModel {

    void getChapters(RxObserver<List<Chapter>> rxObserver);
}

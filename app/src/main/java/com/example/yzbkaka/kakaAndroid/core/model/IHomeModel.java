package com.example.yzbkaka.kakaAndroid.core.model;

/**
 * Created by yzbkaka on 19-12-29.
 */

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.Banner;
import com.example.yzbkaka.kakaAndroid.bean.BaseBean;
import com.example.yzbkaka.kakaAndroid.bean.HomeData;
import com.example.yzbkaka.kakaAndroid.bean.PageListData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function3;
import io.reactivex.observers.DisposableObserver;

/**
 * 首页业务接口
 */
public interface IHomeModel {

    /**
     * 获取首页banner，置顶文章和列表文章
     */
    void getHomeData(int page, Function3<BaseBean<List<Banner>>, BaseBean<List<Article>>, BaseBean<PageListData<Article>>, HomeData> function3, DisposableObserver<HomeData> rxObserver);


    /**
     * 获取更多文章
     */
    void getMoreArticleList(int page,RxPageListObserver<Article> rxPageListObserver);


    Observable<BaseBean<List<Banner>>> getBannerObservable();

    Observable<BaseBean<List<Article>>> getHomeTopObservable();

    Observable<BaseBean<PageListData<Article>>> getHomeListObservable(int page);
}


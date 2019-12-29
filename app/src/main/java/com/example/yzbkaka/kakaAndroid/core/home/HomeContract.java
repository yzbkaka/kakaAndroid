package com.example.yzbkaka.kakaAndroid.core.home;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.Banner;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;

import java.util.List;

/**
 * Created by yzbkaka on 19-12-29.
 */

public interface HomeContract {

    interface IHomePresenter {
        void getHomeData();
        void getMoreArticleList();
        void collectArticle();
        void unCollectArticle();
    }


    interface IHomeView extends IPageLoadDataView<Article> {
        int getArticleId();
        void setBannerData(List<Banner> banner);
        void collect(boolean isCollect,String result);
    }
}

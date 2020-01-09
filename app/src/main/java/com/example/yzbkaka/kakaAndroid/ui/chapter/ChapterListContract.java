package com.example.yzbkaka.kakaAndroid.ui.chapter;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface ChapterListContract {

    interface IChapterListPresenter{
        void getChapterList();
        void collectArticle();
        void unCollectArticle();
    }


    interface IChapterListView extends IPageLoadDataView<Article> {
        int getCid();
        int getArticleId();
        void collect(boolean isCollect,String result);
    }
}

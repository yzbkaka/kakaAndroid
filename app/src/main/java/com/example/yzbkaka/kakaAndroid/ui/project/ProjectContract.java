package com.example.yzbkaka.kakaAndroid.ui.project;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface ProjectContract {

    interface IProjectPresenter {
        void getProjectList();
        void collectArticle();
        void unCollectArticle();
    }


    interface IProjectView extends IPageLoadDataView<Article> {
        int getCid();
        int getArticleId();
        void collect(boolean isCollect,String result);
    }
}

package com.example.yzbkaka.kakaAndroid.ui.tree;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;

/**
 * Created by yzbkaka on 20-1-8.
 */

public interface TreeListContract {

    interface ITreePresenter {
        void loadTreeList();
        void collectArticle();
        void unCollectArticle();
    }

    interface ITreeListView extends IPageLoadDataView<Article> {
        int getCid();
        int getArticleId();//文章id
        void collect(boolean isCollect,String result);
    }
}

package com.example.yzbkaka.kakaAndroid.web;

import com.example.yzbkaka.kakaAndroid.core.view.IView;

/**
 * Created by yzbkaka on 20-1-8.
 */

public class WebViewContract {

    interface IWebViewPresenter{
        void collectArticle();
    }


    interface IWebView extends IView{
        int getArticleId();
        void collect(boolean isCollect,String result);
    }
}

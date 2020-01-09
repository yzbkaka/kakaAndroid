package com.example.yzbkaka.kakaAndroid.ui.chapter;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.core.model.impl.ChapterListModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ChapterListPresenter  extends BasePresenter<ChapterListContract.IChapterListView> implements ChapterListContract.IChapterListPresenter {

    private ChapterListModel chapterArticleModel;

    private ChapterListContract.IChapterListView chapterArticleView;


    public ChapterListPresenter(){
        this.chapterArticleModel = new ChapterListModel();
    }


    @Override
    public void getChapterList() {
        chapterArticleView = getView();
        RxPageListObserver<Article> rxPageListObserver = new RxPageListObserver<Article>(this) {
            @Override
            public void onSuccess(List<Article> mData) {
                chapterArticleView.setData(mData);
                if (chapterArticleView.getData().size() == 0){
                    chapterArticleView.showEmpty();
                }else {
                    chapterArticleView.showContent();
                }
            }
            @Override
            public void onFail(int errorCode, String errorMsg) {
                chapterArticleView.showFail(errorMsg);
            }
        };
        chapterArticleModel.getChapterArticleList(chapterArticleView.getPage(),chapterArticleView.getCid(),rxPageListObserver);
        addDisposable(rxPageListObserver);
    }


    @Override
    public void collectArticle() {}


    @Override
    public void unCollectArticle() {}
}
